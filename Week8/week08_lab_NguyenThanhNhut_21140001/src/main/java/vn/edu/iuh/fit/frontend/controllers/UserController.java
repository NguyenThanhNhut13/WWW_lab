/*
 * @ (#) UserController.java       1.0     08/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.frontend.controllers;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 08/11/2024
 * @version:    1.0
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.backend.dtos.CompanyDTO;
import vn.edu.iuh.fit.backend.security.MyUserDetails;
import vn.edu.iuh.fit.frontend.models.AddressModel;
import vn.edu.iuh.fit.frontend.models.CompanyModel;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    private final AddressModel addressModel;
    private final CompanyModel companyModel;

    public UserController(AddressModel addressModel, CompanyModel companyModel) {
        this.addressModel = addressModel;
        this.companyModel = companyModel;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

//    @RequestMapping(value = "/do-login", method = RequestMethod.POST)
//    public String login(Model model, @RequestParam String username, @RequestParam String password) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//            if (authentication.isAuthenticated()) {
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//                return "redirect:/home";
//            }
//        } catch (Exception e) {
//            model.addAttribute("error", "Username or password is incorrect");
//        }
//        return "login";
//    }

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String index() {
//        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
//            return "redirect:/home";
//        }
//        return "redirect:/login";
//    }

    @RequestMapping(value = "/company/account-registration", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) throws JsonProcessingException {
        if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            return "redirect:/home";
        }
        System.out.println("Authorities:");
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(System.out::println);
        List<Map<String, String>> countries = addressModel.getCountries();
        model.addAttribute("company", new CompanyDTO());
        model.addAttribute("countries", countries);
        return "companies/company-account-registration";
    }

    @RequestMapping(value = "/company/register", method = RequestMethod.POST)
    public String registerCompany(@ModelAttribute("company") CompanyDTO companyDTO, Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        companyDTO.setUserId(userDetails.getUser().getId());
        if (companyModel.saveCompany(companyDTO)) {
            return "companies/job-posting";
        }
        return "companies/company-account-registration";
    }


}

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.frontend.models.AddressModel;
import vn.edu.iuh.fit.frontend.models.CompanyModel;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final AddressModel addressModel;
    private final CompanyModel companyModel;

    public UserController(AuthenticationManager authenticationManager, AddressModel addressModel, CompanyModel companyModel) {
        this.authenticationManager = authenticationManager;
        this.addressModel = addressModel;
        this.companyModel = companyModel;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, @RequestParam String username, @RequestParam String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            if (authentication.isAuthenticated()) {
                return "redirect:/home";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Username or password is incorrect");
        }
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "redirect:/home";
    }

    @RequestMapping(value = "/company/account-registration", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) throws JsonProcessingException {
        List<String> countries = addressModel.getCountries();
        model.addAttribute("countries", countries);
        return "companies/company-account-registration";
    }

    @RequestMapping(value = "/company/register", method = RequestMethod.POST)
    public String registerCompany(@RequestParam Map<String, String> params, Model model) {
        if (companyModel.saveCompany(params)) {
            return "companies/job-posting";
        }
        return "companies/company-account-registration";
    }


}

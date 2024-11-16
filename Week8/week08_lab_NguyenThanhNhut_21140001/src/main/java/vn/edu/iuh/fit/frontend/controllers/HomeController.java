    /*
     * @ (#) JobController.java       1.0     07/11/2024
     *
     * Copyright (c) 2024 IUH. All rights reserved.
     */

    package vn.edu.iuh.fit.frontend.controllers;
    /*
     * @description:
     * @author: Nguyen Thanh Nhut
     * @date: 07/11/2024
     * @version:    1.0
     */

    import com.fasterxml.jackson.core.JsonProcessingException;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import vn.edu.iuh.fit.backend.dtos.CandidateDTO;
    import vn.edu.iuh.fit.backend.dtos.CompanyDTO;
    import vn.edu.iuh.fit.backend.dtos.JobDTO;
    import vn.edu.iuh.fit.backend.security.MyUserDetails;
    import vn.edu.iuh.fit.frontend.models.AddressModel;
    import vn.edu.iuh.fit.frontend.models.JobModel;

    import java.util.List;
    import java.util.Map;

    @Controller
    public class HomeController {

        private final JobModel jobModel;
        private final AddressModel addressModel;

        @Autowired
        public HomeController(JobModel jobModel, AddressModel addressModel) {
            this.jobModel = jobModel;
            this.addressModel = addressModel;
        }

        @RequestMapping(value = "/login", method = RequestMethod.GET)
        public String loginPage() {
            return "login";
        }

        @RequestMapping(value = {"/home", "/"}, method = RequestMethod.GET)
        public String index(Model model) {
            if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
                return "redirect:/login";
            }
            List<JobDTO> jobs = jobModel.getAllJob(0, 10).getContent().stream().toList();
            model.addAttribute("jobs", jobs);
            return "home";
        }

        @RequestMapping(value = "/jobs/{id}", method = RequestMethod.GET)
        public String jobDetail(@PathVariable Long id, Model model) {
            JobDTO job = jobModel.getJobById(id);
            model.addAttribute("job", job);
            return "job-detail";
        }

        @RequestMapping(value = "/jobs/{id}/apply", method = RequestMethod.GET)
        public String applyJob(@PathVariable Long id, Model model) throws JsonProcessingException {
            if (!(SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken)) {
                return "redirect:/login";
            }

            CandidateDTO candidate = new CandidateDTO();
            List<Map<String, String>> countries = addressModel.getCountries();
            model.addAttribute("countries", countries);

            model.addAttribute("jobId", id);
            model.addAttribute("candidate", candidate);

            return "job-application";
        }





    }

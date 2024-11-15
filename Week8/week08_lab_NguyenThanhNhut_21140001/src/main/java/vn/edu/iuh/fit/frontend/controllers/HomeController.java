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

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import vn.edu.iuh.fit.backend.dtos.JobDTO;
    import vn.edu.iuh.fit.frontend.models.JobModel;

    import java.util.List;

    @Controller
    public class HomeController {

        private final JobModel jobModel;

        @Autowired
        public HomeController(JobModel jobModel) {
            this.jobModel = jobModel;
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




    }

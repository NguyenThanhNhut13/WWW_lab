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
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.security.core.userdetails.User;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.servlet.mvc.support.RedirectAttributes;
    import vn.edu.iuh.fit.backend.dtos.*;
    import vn.edu.iuh.fit.frontend.models.*;

    import java.time.LocalDate;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Map;

    @Controller
    public class HomeController {

        private final JobModel jobModel;
        private final AddressModel addressModel;
        private final JobApplicationModel jobApplicationModel;
        private final UserModel userModel;
        private final CandidateModel candidateModel;

        @Autowired
        public HomeController(JobModel jobModel, AddressModel addressModel, JobApplicationModel jobApplicationModel, UserModel userModel, CandidateModel candidateModel) {
            this.jobModel = jobModel;
            this.addressModel = addressModel;
            this.jobApplicationModel = jobApplicationModel;
            this.userModel = userModel;
            this.candidateModel = candidateModel;
        }

        @RequestMapping(value = "/login", method = RequestMethod.GET)
        public String loginPage() {
            return "login";
        }

        @RequestMapping(value = {"/home", "/"}, method = RequestMethod.GET)
        public String index(Model model) {
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

            UserDTO currentUser = userModel.getCurrentUser();
            CandidateDTO candidate;
            if (currentUser.getRoles().stream().anyMatch(role -> role.getRoleName().equals("CANDIDATE"))) {
                candidate = candidateModel.findByUsername(currentUser.getUsername());
            } else {
                candidate = new CandidateDTO();
                candidate.setCandidateSkills(new ArrayList<>());
                candidate.setExperiences(new ArrayList<>());
                candidate.setUserId(currentUser.getId());
                candidate.setId(null);
            }
            List<Map<String, String>> countries = addressModel.getCountries();
            model.addAttribute("countries", countries);

            model.addAttribute("jobId", id);
            model.addAttribute("candidate", candidate);

            return "job-application";
        }

        @RequestMapping(value = "/jobs/{id}/apply", method = RequestMethod.POST)
        public String applyJob(@PathVariable Long id, @ModelAttribute("candidate") CandidateDTO candidateDTO, RedirectAttributes redirectAttributes) {
            if (!(SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken)) {
                return "redirect:/login";
            }

            JobApplicationDTO jobApplicationDTO = new JobApplicationDTO();
            JobDTO jobDTO = jobModel.getJobById(id);
            jobApplicationDTO.setJob(jobDTO);
            jobApplicationDTO.setCandidate(candidateDTO);
            jobApplicationDTO.setStatus(0);
            jobApplicationDTO.setApplyAt(LocalDate.now());

            if (jobApplicationModel.saveJobApplication(jobApplicationDTO)) {
                redirectAttributes.addFlashAttribute("successMessage", "Job application submitted successfully");
                return "redirect:/jobs/" + id;
            }

            redirectAttributes.addFlashAttribute("errorMessage", "Error while submitting job application");

            return "redirect:/jobs/" + id;
        }

    }

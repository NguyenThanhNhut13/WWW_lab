/*
 * @ (#) JobController.java       1.0     07/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.resources;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 07/11/2024
 * @version:    1.0
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.backend.dtos.JobDTO;
import vn.edu.iuh.fit.backend.dtos.PageResponseDTO;
import vn.edu.iuh.fit.backend.services.JobService;
import vn.edu.iuh.fit.backend.services.impl.JobRecommendationService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
public class JobRESTController {

    private final JobService jobService;
    private final JobRecommendationService recommendationService;

    @Autowired
    public JobRESTController(JobService jobService, JobRecommendationService recommendationService) {
        this.jobService = jobService;
        this.recommendationService = recommendationService;
    }

    @GetMapping
    public ResponseEntity<PageResponseDTO<JobDTO>> getAllJobs(
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        try {
            PageResponseDTO<JobDTO> jobs = jobService.getAllJob(page.orElse(0), size.orElse(10));
            return ResponseEntity.ok(jobs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(jobService.getJobById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<PageResponseDTO<JobDTO>> getJobsByCompanyId(
            @PathVariable Long id,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        try {
            PageResponseDTO<JobDTO> jobs = jobService.getJobsByCompanyId(id, page.orElse(0), size.orElse(15));
            return ResponseEntity.ok(jobs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<JobDTO> save(@RequestBody JobDTO jobDTO) {
        try {
            return ResponseEntity.ok(jobService.saveJob(jobDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("recommendations/{username}")
    public ResponseEntity<PageResponseDTO<JobDTO>> getRecommendations(@PathVariable String username,
                                                @RequestParam("page") Optional<Integer> page,
                                                @RequestParam("size") Optional<Integer> size) {
        try {
            PageResponseDTO<JobDTO> jobs = jobService.getRecommendJobs(username, page.orElse(0), size.orElse(10));
            return ResponseEntity.ok(jobs);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }





}

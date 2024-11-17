/*
 * @ (#) JobApplicationRESTController.java       1.0     17/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.resources;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 17/11/2024
 * @version:    1.0
 */

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.backend.dtos.JobApplicationDTO;
import vn.edu.iuh.fit.backend.dtos.PageResponseDTO;
import vn.edu.iuh.fit.backend.services.JobApplicationService;

@RestController
@RequestMapping("/api/job-application")
public class JobApplicationRESTController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationRESTController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @GetMapping("/byJob/{jobId}")
    public ResponseEntity<PageResponseDTO<JobApplicationDTO>> findByJobId(@PathVariable Long jobId) {
        try {
            return ResponseEntity.ok(jobApplicationService.findByJobId(jobId, 0, 10));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<JobApplicationDTO> save(@RequestBody JobApplicationDTO jobApplicationDTO) {
        try {
            return ResponseEntity.ok(jobApplicationService.save(jobApplicationDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

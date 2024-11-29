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
import vn.edu.iuh.fit.backend.dtos.CandidateDTO;
import vn.edu.iuh.fit.backend.dtos.JobApplicationDTO;
import vn.edu.iuh.fit.backend.dtos.PageResponseDTO;
import vn.edu.iuh.fit.backend.services.JobApplicationService;

import java.util.Optional;

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

    @GetMapping("/company/{companyId}/candidates")
    public ResponseEntity<PageResponseDTO<CandidateDTO>> getCandidate(@PathVariable Long companyId,
                                                                      @RequestParam("jobId") Optional<Long> jobId,
                                                                      @RequestParam("search") Optional<String> search,
                                                                      @RequestParam("page") Optional<Integer> page,
                                                                      @RequestParam("size") Optional<Integer> size) {
        try {
            int currentPage = page.orElse(0);
            int pageSize = size.orElse(10);
            Long jobIdValue = jobId.orElse(null);
            String searchValue = search.orElse(null);
            return ResponseEntity.ok(jobApplicationService.getCandidate(companyId, jobIdValue, searchValue, currentPage, pageSize));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}

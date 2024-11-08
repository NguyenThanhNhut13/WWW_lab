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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.backend.dtos.JobDTO;
import vn.edu.iuh.fit.backend.dtos.PageResponseDTO;
import vn.edu.iuh.fit.backend.services.JobService;

import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
public class JobRESTController {

    private JobService jobService;

    @Autowired
    public JobRESTController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<PageResponseDTO<JobDTO>> getAllJobs(Model model,
                                                              @RequestParam("page") Optional<Integer> page,
                                                              @RequestParam("size") Optional<Integer> size,
                                                              Optional<String> sortBy,
                                                              Optional<String> sortDir) {

        int currentPage = page.orElse(0);
        int pageSize = size.orElse(5);

        Sort sort = Sort.by(Sort.Direction.fromString(sortDir.orElse("asc")), sortBy.orElse("id"));
        Pageable pageable = PageRequest.of(currentPage, pageSize, sort);

        return ResponseEntity.ok(jobService.getAllJob(pageable));
    }

}

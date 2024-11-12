/*
 * @ (#) JobService.java       1.0     07/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.services;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 07/11/2024
 * @version:    1.0
 */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.iuh.fit.backend.dtos.JobDTO;
import vn.edu.iuh.fit.backend.dtos.PageResponseDTO;


public interface JobService {
    PageResponseDTO<JobDTO> getAllJob(int page, int size);
    JobDTO getJobById(Long id);
    JobDTO saveJob(JobDTO jobDTO);
    JobDTO updateJob(JobDTO jobDTO);
    void deleteJob(Long id);
    PageResponseDTO<JobDTO> searchJob(String keyword, int page, int size, String sortBy, String sortDir);
    PageResponseDTO<JobDTO> findMatchingJobsForCandidate(Long candidateId, long minSkills, Pageable pageable);
}

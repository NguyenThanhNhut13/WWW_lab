/*
 * @ (#) JobApplicationService.java       1.0     17/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.services;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 17/11/2024
 * @version:    1.0
 */

import vn.edu.iuh.fit.backend.dtos.CandidateDTO;
import vn.edu.iuh.fit.backend.dtos.JobApplicationDTO;
import vn.edu.iuh.fit.backend.dtos.PageResponseDTO;

public interface JobApplicationService {
    JobApplicationDTO save(JobApplicationDTO jobApplicationDTO);
    JobApplicationDTO findById(Long id);
    PageResponseDTO<JobApplicationDTO> findByJobId(Long jobId, int page, int size);

    PageResponseDTO<CandidateDTO> getAllCandidatesByCompanyId(Long companyId, int page, int size);
    PageResponseDTO<CandidateDTO> getCandidatesByJobId(Long jobId, int page, int size);
    PageResponseDTO<CandidateDTO> searchCandidate(Long jobId, String keyword, int page, int size);
}

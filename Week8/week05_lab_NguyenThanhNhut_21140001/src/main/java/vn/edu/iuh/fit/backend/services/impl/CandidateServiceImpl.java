/*
 * @ (#) CandidateServiceImpl.java       1.0     01/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.services.impl;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 01/12/2024
 * @version:    1.0
 */

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.converters.CandidateMapper;
import vn.edu.iuh.fit.backend.dtos.CandidateDTO;
import vn.edu.iuh.fit.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.backend.services.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;
    private final CandidateMapper candidateMapper;

    public CandidateServiceImpl(CandidateRepository candidateRepository, CandidateMapper candidateMapper) {
        this.candidateRepository = candidateRepository;
        this.candidateMapper = candidateMapper;
    }

    @Override
    public CandidateDTO findByUsername(String username) {
        return candidateMapper.toDTO(candidateRepository.findByUser_Username(username));
    }
}

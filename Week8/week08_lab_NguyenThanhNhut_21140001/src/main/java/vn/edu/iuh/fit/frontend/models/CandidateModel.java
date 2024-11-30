/*
 * @ (#) CandidateModel.java       1.0     01/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.frontend.models;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 01/12/2024
 * @version:    1.0
 */

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.backend.dtos.CandidateDTO;

@Component
public class CandidateModel {
    private final RestTemplate restTemplate;
    private final String CANDIDATE_API_URL = "http://localhost:8080/api/candidates";

    public CandidateModel(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CandidateDTO findByUsername(String username) {
        return restTemplate.getForObject(CANDIDATE_API_URL + "?username=" + username, CandidateDTO.class);
    }
}

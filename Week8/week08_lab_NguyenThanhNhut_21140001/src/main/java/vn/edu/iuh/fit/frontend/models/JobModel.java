/*
 * @ (#) JobModel.java       1.0     07/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.frontend.models;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 07/11/2024
 * @version:    1.0
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.backend.dtos.JobDTO;
import vn.edu.iuh.fit.backend.dtos.PageResponseDTO;


@Component
public class JobModel {

    private final RestTemplate restTemplate;

    @Autowired
    public JobModel(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static final String JOB_API_URL = "http://localhost:8080/api/jobs";

    public PageResponseDTO<JobDTO> getAllJob(int page, int size) {

        return restTemplate.exchange(JOB_API_URL + "?page=" + page + "&size=" + size, HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<PageResponseDTO<JobDTO>>() {
        }).getBody();
    }
}

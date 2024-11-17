/*
 * @ (#) JobApplicationModel.java       1.0     17/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.frontend.models;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 17/11/2024
 * @version:    1.0
 */

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.backend.dtos.JobApplicationDTO;

@Component
public class JobApplicationModel {

    private final String JOB_APPLICATION_URL = "http://localhost:8080/api/job-application";
    private final RestTemplate restTemplate;

    public JobApplicationModel(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean saveJobApplication(JobApplicationDTO jobApplicationDTO) {
        try {
            restTemplate.postForObject(JOB_APPLICATION_URL, jobApplicationDTO, JobApplicationDTO.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

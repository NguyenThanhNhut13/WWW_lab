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
import vn.edu.iuh.fit.backend.dtos.SkillDTO;

import java.util.Set;


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

    public PageResponseDTO<JobDTO> getJobsByCompanyId(Long id, int page, int size) {
        try {
            return restTemplate.exchange(JOB_API_URL + "/company/" + id + "?page=" + page + "&size=" + size, HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<PageResponseDTO<JobDTO>>() {
            }).getBody();
        } catch (Exception e) {
            System.out.println("Error while getting jobs by company id: " + e.getMessage());
            return null;
        }
    }

    public Set<SkillDTO> getAllSkills() {
        try {
            return restTemplate.exchange("http://localhost:8080/api/skills", HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<Set<SkillDTO>>() {
            }).getBody();
        } catch (Exception e) {
            System.out.println("Error while getting all skills: " + e.getMessage());
            return null;
        }
    }

    public boolean saveJob(JobDTO jobDTO) {
        try {
            restTemplate.postForObject(JOB_API_URL, jobDTO, JobDTO.class);
            return true;
        } catch (Exception e) {
            System.out.println("Error while saving job: " + e.getMessage());
            return false;
        }
    }

    public JobDTO getJobById(Long id) {
        try {
            return restTemplate.exchange(JOB_API_URL + "/" + id, HttpMethod.GET, HttpEntity.EMPTY, JobDTO.class).getBody();
        } catch (Exception e) {
            System.out.println("Error while getting job by id: " + e.getMessage());
            return null;
        }
    }
}

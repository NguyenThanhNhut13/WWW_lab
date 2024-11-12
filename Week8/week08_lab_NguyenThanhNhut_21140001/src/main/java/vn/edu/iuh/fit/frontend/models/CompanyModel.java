/*
 * @ (#) CompanyModel.java       1.0     09/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.frontend.models;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 09/11/2024
 * @version:    1.0
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.backend.dtos.CompanyDTO;

import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Component
public class CompanyModel {

    private final RestTemplate restTemplate;
    private static final String COMPANY_API_URL = "http://localhost:8080/api/companies";

    @Autowired
    public CompanyModel(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean saveCompany(CompanyDTO companyDTO) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            HttpEntity<CompanyDTO> request = new HttpEntity<>(companyDTO, headers);

            ResponseEntity<CompanyDTO> response = restTemplate.exchange(
                    COMPANY_API_URL,
                    HttpMethod.POST,
                    request,
                    CompanyDTO.class);

            return response.getStatusCode() == HttpStatus.CREATED;
        } catch (Exception e) {
            System.out.println("Error while saving company: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}

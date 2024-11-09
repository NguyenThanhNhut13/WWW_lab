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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.backend.dtos.AddressDTO;
import vn.edu.iuh.fit.backend.dtos.CompanyDTO;

import java.util.Map;

@Component
public class CompanyModel {

    private final RestTemplate restTemplate;
    private static final String COMPANY_API_URL = "http://localhost:8080/api/companies";

    @Autowired
    public CompanyModel(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean saveCompany(Map<String, String> allParams) {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setCompName(allParams.get("companyName"));
        companyDTO.setEmail(allParams.get("email"));
        companyDTO.setPhone(allParams.get("phone"));
        companyDTO.setLogo(allParams.get("logo"));
        companyDTO.setWebUrl(allParams.get("website"));
        companyDTO.setAbout(allParams.get("description"));

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCity(allParams.get("city"));
        addressDTO.setCountry(allParams.get("country"));
        addressDTO.setStreet(allParams.get("street"));
        addressDTO.setZipCode(allParams.get("zip"));
        addressDTO.setNumber(allParams.get("number"));

        companyDTO.setAddress(addressDTO);

        System.out.println(companyDTO);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create the HttpEntity with the request body and headers
            HttpEntity<CompanyDTO> requestEntity = new HttpEntity<>(companyDTO, headers);

            // Send the request
            ResponseEntity<CompanyDTO> response = restTemplate.exchange(
                    COMPANY_API_URL,
                    HttpMethod.POST,
                    requestEntity,  // Passing the request entity with the body
                    CompanyDTO.class
            );
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


}

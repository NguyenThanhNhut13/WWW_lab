/*
 * @ (#) AddressModel.java       1.0     09/11/2024
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AddressModel {

    private final RestTemplate restTemplate;
    private final ObjectMapper jacksonObjectMapper;

    @Autowired
    public AddressModel(RestTemplate restTemplate, ObjectMapper jacksonObjectMapper) {
        this.restTemplate = restTemplate;
        this.jacksonObjectMapper = jacksonObjectMapper;
    }

    public List<Map<String, String>> getCountries() throws JsonProcessingException {
        String url = "https://restcountries.com/v3.1/all";
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                String.class
        );

        JsonNode rootNode = jacksonObjectMapper.readTree(response.getBody());

        List<Map<String, String>> countries = new ArrayList<>();

        for (JsonNode node : rootNode) {
            JsonNode nameNode = node.path("name").path("common");
            JsonNode codeNode = node.path("cca2");

            if (!nameNode.isMissingNode() && !codeNode.isMissingNode()) {
                Map<String, String> country = new HashMap<>();
                country.put("name", nameNode.asText());
                country.put("code", codeNode.asText());

                countries.add(country);
            }
        }

        return countries;
    }
}

/*
 * @ (#) UserModel.java       1.0     24/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.frontend.models;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.backend.dtos.UserDTO;

/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 24/11/2024
 * @version:    1.0
 */

@Component
public class UserModel {

    private final RestTemplate restTemplate;
    private final String USER_API_URL = "http://localhost:8080/api/users";

    public UserModel(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDTO getCurrentUser() {
        return restTemplate.getForObject(USER_API_URL + "/current-user", UserDTO.class);
    }


}

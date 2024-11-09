/*
 * @ (#) PostModel.java       1.0     10/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.frontend.model;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 10/11/2024
 * @version:    1.0
 */

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.backend.dtos.PostCommentDTO;
import vn.edu.iuh.fit.backend.dtos.PostDTO;

import java.util.List;

@Component
public class PostModel {

    private final RestTemplate restTemplate;

    public PostModel(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<PostDTO> getAllPosts() {
        return restTemplate.exchange("http://localhost:8080/api/posts", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<PostDTO>>() {
                }).getBody();
    }

    public PostDTO getPostById(Long id) {
        return restTemplate.exchange("http://localhost:8080/api/posts/" + id, HttpMethod.GET, null,
                new ParameterizedTypeReference<PostDTO>() {
                }).getBody();
    }

    public List<PostCommentDTO> getCommentsByPostId(Long id) {
        return restTemplate.exchange("http://localhost:8080/api/posts/" + id + "/comments", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<PostCommentDTO>>() {
                }).getBody();
    }

    public PostDTO postComment(Long id, PostCommentDTO postCommentDTO) {
        return restTemplate.postForObject("http://localhost:8080/api/posts/" + id + "/comments", postCommentDTO, PostDTO.class);
    }


}

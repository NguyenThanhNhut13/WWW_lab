/*
 * @ (#) PostCommentServiceImpl.java       1.0     10/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.services.impl;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 10/11/2024
 * @version:    1.0
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.dtos.PostCommentDTO;
import vn.edu.iuh.fit.backend.entities.PostComment;
import vn.edu.iuh.fit.backend.mapper.PostCommentMapper;
import vn.edu.iuh.fit.backend.mapper.PostMapper;
import vn.edu.iuh.fit.backend.repositories.PostCommentRepository;
import vn.edu.iuh.fit.backend.repositories.PostRepository;
import vn.edu.iuh.fit.backend.services.PostCommentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostCommentServiceImpl implements PostCommentService {

    private final PostCommentRepository postCommentRepository;
    private final PostCommentMapper postCommentMapper;
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Autowired
    public PostCommentServiceImpl(PostCommentRepository postCommentRepository, PostCommentMapper postCommentMapper, PostRepository postRepository, PostMapper postMapper) {
        this.postCommentRepository = postCommentRepository;
        this.postCommentMapper = postCommentMapper;
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    public List<PostCommentDTO> getPostCommentByPostId(Long postId) {
        return postCommentRepository.findPostCommentsByPost_Id(postId).stream().map(postCommentMapper::toDTO).collect(Collectors.toList());
    }
}

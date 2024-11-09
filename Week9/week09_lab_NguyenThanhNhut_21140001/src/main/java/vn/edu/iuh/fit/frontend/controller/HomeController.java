/*
 * @ (#) HomeController.java       1.0     10/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.frontend.controller;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 10/11/2024
 * @version:    1.0
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import vn.edu.iuh.fit.backend.dtos.PostCommentDTO;
import vn.edu.iuh.fit.backend.dtos.PostDTO;
import vn.edu.iuh.fit.backend.entities.Post;
import vn.edu.iuh.fit.backend.entities.PostComment;
import vn.edu.iuh.fit.frontend.model.PostModel;

import java.util.List;

@Controller
public class HomeController {

    private final PostModel postModel;

    public HomeController(PostModel postModel) {
        this.postModel = postModel;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home - Blog App");
        model.addAttribute("section", "Latest Posts");

        // Giả sử bạn có một service để lấy danh sách bài viết
        List<PostDTO> posts = postModel.getAllPosts();
        model.addAttribute("posts", posts);
        return "index"; // Trang chủ
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Trang đăng nhập
    }

    @GetMapping("/register")
    public String register() {
        return "register"; // Trang đăng ký
    }

    @GetMapping("/posts/{id}")
    public String postDetail(@PathVariable("id") Long id, Model model) {
        PostDTO post = postModel.getPostById(id);
        List<PostCommentDTO> comments = postModel.getCommentsByPostId(post.getId());
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        return "post-detail";
    }

    @PostMapping("/posts/{id}/comments")
    public String postComment(@PathVariable("id") Long id, @RequestBody PostCommentDTO postCommentDTO, Model model) {

        PostDTO post = postModel.postComment(id, postCommentDTO);
        model.addAttribute("post", post);

        return "post-detail";
    }

    @GetMapping("/post-form")
    public String postForm() {
        return "post-form"; // Trang tạo/chỉnh sửa bài viết
    }

    @GetMapping("/user-profile")
    public String userProfile() {
        return "user-profile"; // Trang hồ sơ người dùng
    }
}

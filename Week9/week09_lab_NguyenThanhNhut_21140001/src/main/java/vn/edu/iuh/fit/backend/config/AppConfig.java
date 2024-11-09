/*
 * @ (#) AppConfig.java       1.0     09/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.config;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 09/11/2024
 * @version:    1.0
 */

import net.datafaker.Faker;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.backend.entities.Post;
import vn.edu.iuh.fit.backend.entities.PostComment;
import vn.edu.iuh.fit.backend.entities.User;
import vn.edu.iuh.fit.backend.repositories.PostCommentRepository;
import vn.edu.iuh.fit.backend.repositories.PostRepository;
import vn.edu.iuh.fit.backend.repositories.UserRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    CommandLineRunner loadInitialData(UserRepository userRepository, PostRepository postRepository, PostCommentRepository postCommentRepository) {
//        return args -> {
//            Faker faker = new Faker();
//            Random rd = new Random();
//
//            // Create 10 users
//            List<User> users = new ArrayList<>();
//            for (int i = 0; i < 10; i++) {
//                User user = new User();
//                user.setFirstName(faker.name().firstName());
//                user.setMiddleName(faker.name().firstName());
//                user.setLastName(faker.name().lastName());
//                user.setMobile(faker.phoneNumber().phoneNumber());
//                user.setEmail(faker.internet().emailAddress());
//
//                String hashPassword = BCrypt.hashpw(faker.internet().password(), BCrypt.gensalt());
//                user.setPasswordHash(hashPassword);
//
//                user.setRegisteredAt(Instant.now().minusSeconds(rd.nextInt(1000 * 24 * 60 * 60)));
//                user.setLastLogin(Instant.now().minusSeconds(rd.nextInt(100 * 24 * 60 * 60)));
//                user.setIntro(faker.lorem().sentence(10));
//                user.setProfile(faker.lorem().paragraph(2));
//                users.add(user);
//                userRepository.save(user);
//            }
//
//            // Create 20 posts
//            List<Post> posts = new ArrayList<>();
//            for (int i = 0; i < 20; i++) {
//                Post post = new Post();
//                post.setAuthor(users.get(rd.nextInt(users.size())));
//                post.setParent(null);
//                post.setTitle(faker.lorem().sentence(5));
//                post.setMetaTitle(faker.lorem().sentence(5));
//                post.setSummary(faker.lorem().sentence(10));
//                post.setPublished(rd.nextBoolean());
//
//                post.setCreatedAt(Instant.now().minusSeconds(rd.nextInt(365 * 24 * 60 * 60)));
//                post.setUpdatedAt(Instant.now());
//                post.setPublishedAt(post.getPublished() ? Instant.now().minusSeconds(rd.nextInt(365 * 24 * 60 * 60)) : null);
//                post.setContent(faker.lorem().paragraph(5));
//                posts.add(post);
//                postRepository.save(post);
//            }
//
//            // Create 50 comments
//            for (int i = 0; i < 50; i++) {
//                PostComment comment = new PostComment();
//                comment.setPost(posts.get(rd.nextInt(posts.size())));
//                comment.setParent(null);
//                comment.setTitle(faker.lorem().sentence(5));
//                comment.setPublished(rd.nextBoolean());
//
//                comment.setCreatedAt(Instant.now().minusSeconds(rd.nextInt(365 * 24 * 60 * 60)));
//                comment.setPublishedAt(comment.getPublished() ? Instant.now().minusSeconds(rd.nextInt(365 * 24 * 60 * 60)) : null);
//                comment.setContent(faker.lorem().paragraph(3));
//                postCommentRepository.save(comment);
//            }
//        };
//    }
}

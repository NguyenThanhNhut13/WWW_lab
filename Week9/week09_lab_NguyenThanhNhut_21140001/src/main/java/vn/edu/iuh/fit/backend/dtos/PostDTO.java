/*
 * @ (#) PostDTO.java       1.0     10/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.dtos;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 10/11/2024
 * @version:    1.0
 */

import lombok.*;
import vn.edu.iuh.fit.backend.entities.Post;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDTO implements Serializable {
    private Long id;
    private String title;
    private String summary;
    private Boolean published;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant publishedAt;
    private String metaTitle;
    private String content;
    private UserDTO author;

}

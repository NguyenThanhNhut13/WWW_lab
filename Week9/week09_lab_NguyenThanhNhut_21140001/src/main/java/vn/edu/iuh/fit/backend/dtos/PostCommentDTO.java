/*
 * @ (#) PostCommentDTO.java       1.0     10/11/2024
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

import java.io.Serializable;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostCommentDTO implements Serializable {
    private Long id;
    private String title;
    private Boolean published;
    private Instant createdAt;
    private Instant publishedAt;
    private String content;
}

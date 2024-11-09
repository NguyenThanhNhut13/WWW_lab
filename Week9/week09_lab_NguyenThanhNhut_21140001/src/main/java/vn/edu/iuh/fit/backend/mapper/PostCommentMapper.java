/*
 * @ (#) PostCommentMapper.java       1.0     10/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.mapper;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 10/11/2024
 * @version:    1.0
 */

import org.mapstruct.Mapper;
import vn.edu.iuh.fit.backend.dtos.PostCommentDTO;
import vn.edu.iuh.fit.backend.entities.PostComment;

@Mapper(componentModel = "spring")
public interface PostCommentMapper {
    PostCommentDTO toDTO(PostComment postComment);

    PostComment toEntity(PostCommentDTO postCommentDTO);
}

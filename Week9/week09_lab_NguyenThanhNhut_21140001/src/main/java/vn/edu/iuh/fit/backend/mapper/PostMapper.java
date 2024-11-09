/*
 * @ (#) PostMapper.java       1.0     10/11/2024
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
import org.mapstruct.Mapping;
import vn.edu.iuh.fit.backend.dtos.PostDTO;
import vn.edu.iuh.fit.backend.entities.Post;


@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "author", target = "author")
    PostDTO toDTO(Post post);

    @Mapping(source = "author", target = "author")
    Post toEntity(PostDTO postDTO);
}

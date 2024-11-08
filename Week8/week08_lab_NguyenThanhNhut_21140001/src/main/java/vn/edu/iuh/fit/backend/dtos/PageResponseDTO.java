/*
 * @ (#) PageResponseDTO.java       1.0     07/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.dtos;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 07/11/2024
 * @version:    1.0
 */

import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageResponseDTO<T> implements Serializable {
    private Collection<T> content;
    private int page;
    private int size;
    private int totalPage;
    private long totalElement;
    private String sort;
    private String order;

    public PageResponseDTO(Page<T> page) {
        this.content = page.getContent();
        this.page = page.getNumber();
        this.size = page.getSize();
        this.totalPage = page.getTotalPages();
        this.totalElement = page.getTotalElements();
        this.sort = page.getSort().toString();

        // Get order
        Sort.Order order = page.getSort().getOrderFor("id");
        this.order = order != null ? order.getDirection().name() : "asc";
    }
}

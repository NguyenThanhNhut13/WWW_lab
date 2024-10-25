/*
 * @ (#) ProductDTO.java       1.0     04/10/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.dtos;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 04/10/2024
 * @version:    1.0
 */


import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProductDTO implements Serializable {
    private int productId;
    private String name;
    private String description;
    private String imgPath;
    private double price;

}

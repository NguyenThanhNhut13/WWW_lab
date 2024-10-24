/*
 * @ (#) ProductPriceDTO.java       1.0     23/10/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.dtos;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 23/10/2024
 * @version:    1.0
 */

import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductPriceDTO {
    private Integer priceId;
    private Integer productId;
    private Timestamp applyDate;
    private double value;
    private String note;
    private Integer status;

}

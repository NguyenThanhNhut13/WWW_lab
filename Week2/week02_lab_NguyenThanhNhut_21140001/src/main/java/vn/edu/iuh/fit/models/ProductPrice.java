/*
 * @ (#) ProductPrice.java       1.0     21/09/2024
 * 
 * Copyright (c) 2024 IUH. All rights reserved.
 */
 
package vn.edu.iuh.fit.models;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 21/09/2024
 * @version:    1.0
 */


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_price")
public class ProductPrice {
    @Id
    @Column(nullable = false, name = "price_date_time")
    private LocalDateTime priceDateTime;

    @Column(nullable = false)
    private double price;

    private String note;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}

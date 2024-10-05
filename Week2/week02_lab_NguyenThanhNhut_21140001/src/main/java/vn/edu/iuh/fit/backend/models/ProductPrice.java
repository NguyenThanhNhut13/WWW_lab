/*
 * @ (#) ProductPrice.java       1.0     26/09/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.models;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 26/09/2024
 * @version:    1.0
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_price")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

/*
 * @ (#) Product.java       1.0     26/09/2024
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

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.iuh.fit.enums.ProductStatus;

import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long id;

    @Column(length = 25, nullable = false)
    private String unit;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private ProductStatus status;

    @Column(name = "manufacturer_name",length = 100, nullable = false)
    private String manufacturer;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(length = 250, nullable = false)
    private String description;

//    @JsonbTransient
//    @OneToMany(mappedBy = "product")
//    private List<OrderDetail> orderDetails;
//
//    @JsonbTransient
//    @OneToMany(mappedBy = "product")
//    private List<ProductImage> productImageList;
//
//    @JsonbTransient
//    @OneToMany(mappedBy = "product")
//    private List<ProductPrice> productPrices;
}

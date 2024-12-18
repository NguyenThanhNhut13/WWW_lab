/*
 * @ (#) ProductPriceRepository.java       1.0     04/10/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.repositories;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 04/10/2024
 * @version:    1.0
 */

import vn.edu.iuh.fit.backend.entities.ProductPrice;

import java.util.List;

public interface ProductPriceRepository {
    ProductPrice findActivePriceByProduct(int productId);
    ProductPrice findById(int id);
    ProductPrice add(ProductPrice productPrice);
    void save(ProductPrice productPrice);
    List<ProductPrice> findAllProductPriceByProductId(int productId);
}

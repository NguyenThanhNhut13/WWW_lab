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

public interface ProductPriceRepository {
    ProductPrice findActivePriceByProduct(int productId);
}

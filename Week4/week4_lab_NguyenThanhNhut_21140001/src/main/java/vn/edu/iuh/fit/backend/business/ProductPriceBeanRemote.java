/*
 * @ (#) ProductPriceBeanRemote.java       1.0     24/10/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.business;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 24/10/2024
 * @version:    1.0
 */

import jakarta.ejb.Remote;
import vn.edu.iuh.fit.backend.dtos.ProductPriceDTO;

import java.util.List;

@Remote
public interface ProductPriceBeanRemote {
    List<ProductPriceDTO> findAllProductPriceByProductId(int productId);
}

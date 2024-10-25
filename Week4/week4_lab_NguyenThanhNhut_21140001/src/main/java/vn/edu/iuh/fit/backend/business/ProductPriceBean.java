/*
 * @ (#) ProductPriceBean.java       1.0     24/10/2024
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

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import vn.edu.iuh.fit.backend.dtos.ProductPriceDTO;
import vn.edu.iuh.fit.backend.entities.ProductPrice;
import vn.edu.iuh.fit.backend.mapper.ProductPriceMapper;
import vn.edu.iuh.fit.backend.repositories.ProductPriceRepository;

import java.util.List;

@Stateless
public class ProductPriceBean implements ProductPriceBeanRemote{

    @Inject
    private ProductPriceRepository productPriceRepository;

    @Inject
    private ProductPriceMapper productPriceMapper;

    @Override
    public List<ProductPriceDTO> findAllProductPriceByProductId(int productId) {
        List<ProductPrice> productPrices = productPriceRepository.findAllProductPriceByProductId(productId);
        return productPrices.stream().map(productPriceMapper::productPriceToProductPriceDTO).toList();
    }
}

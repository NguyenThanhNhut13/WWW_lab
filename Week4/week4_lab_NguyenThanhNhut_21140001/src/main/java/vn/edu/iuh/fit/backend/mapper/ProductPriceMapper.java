/*
 * @ (#) ProductPriceMapper.java       1.0     23/10/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.mapper;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 23/10/2024
 * @version:    1.0
 */

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import vn.edu.iuh.fit.backend.dtos.ProductPriceDTO;
import vn.edu.iuh.fit.backend.entities.ProductPrice;

@Mapper
public interface ProductPriceMapper {
    ProductPriceMapper INSTANCE = Mappers.getMapper(ProductPriceMapper.class);

    ProductPriceDTO productPriceToProductPriceDTO(ProductPrice productPrice);
    @Mapping(source = "value", target = "value")
    ProductPrice productPriceDTOToProductPrice(ProductPriceDTO productPriceDTO);
}

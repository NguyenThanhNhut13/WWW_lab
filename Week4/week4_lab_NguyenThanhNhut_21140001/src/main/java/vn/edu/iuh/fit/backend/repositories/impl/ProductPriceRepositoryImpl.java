/*
 * @ (#) ProductPriceRepositoryImpl.java       1.0     04/10/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.repositories.impl;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 04/10/2024
 * @version:    1.0
 */

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vn.edu.iuh.fit.backend.entities.ProductPrice;
import vn.edu.iuh.fit.backend.repositories.ProductPriceRepository;

import java.util.List;

public class ProductPriceRepositoryImpl implements ProductPriceRepository {

    @PersistenceContext(unitName = "week4_21140001")
    private EntityManager entityManager;

    @Override
    public ProductPrice findActivePriceByProduct(int productId) {
        List<ProductPrice> productPrices = entityManager.createNamedQuery("ProductPrice.findActivePriceByProduct", ProductPrice.class)
                .setParameter("productId", productId)
                .getResultList();
        return productPrices.isEmpty() ? null : productPrices.get(0);
    }

    @Override
    public ProductPrice findById(int id) {
        return entityManager.createNamedQuery("ProductPrice.findById", ProductPrice.class)
                .setParameter("priceId", id)
                .getSingleResult();
    }

    @Override
    public ProductPrice add(ProductPrice productPrice) {
        if (productPrice.getPriceId() == null) {
            entityManager.persist(productPrice);
        } else {
            productPrice = entityManager.merge(productPrice);
        }
        return productPrice;
    }

    @Override
    public void save(ProductPrice productPrice) {
        entityManager.merge(productPrice);
    }

    @Override
    public List<ProductPrice> findAllProductPriceByProductId(int productId) {
        return entityManager.createNamedQuery("ProductPrice.findAllProductPriceByProductId", ProductPrice.class)
                .setParameter("productId", productId)
                .getResultList();
    }
}

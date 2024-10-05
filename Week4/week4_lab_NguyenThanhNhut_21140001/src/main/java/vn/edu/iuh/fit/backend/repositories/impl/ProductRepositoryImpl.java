/*
 * @ (#) ProductRepositoryImpl.java       1.0     04/10/2024
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
import vn.edu.iuh.fit.backend.dtos.ProductDTO;
import vn.edu.iuh.fit.backend.entities.Product;
import vn.edu.iuh.fit.backend.repositories.ProductRepository;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext(unitName = "week4_21140001")
    private EntityManager entityManager;

    @Override
    public List<Product> getAll() {
        return entityManager.createNamedQuery("Product.findAll", Product.class).getResultList();
    }

    @Override
    public Product getById(int productId) {
        return entityManager.createNamedQuery("Product.findByProductId", Product.class).setParameter("productId", productId).getSingleResult();
    }

    @Override
    public Product add(Product product) {
        if (product.getProductId() == null) {
            entityManager.persist(product);
        } else {
            product = entityManager.merge(product);
        }
        return product;
    }
}

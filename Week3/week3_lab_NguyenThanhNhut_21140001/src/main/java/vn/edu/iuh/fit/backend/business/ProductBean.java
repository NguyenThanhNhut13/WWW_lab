package vn.edu.iuh.fit.backend.business;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vn.edu.iuh.fit.backend.repositories.entities.Product;

import java.util.List;

@Stateless
public class ProductBean implements ProductBeanRemote{

    @PersistenceContext(unitName = "week3_21140001")
    private EntityManager entityManager;

    @Override
    public List<Product> getAll() {
        return entityManager.createNamedQuery("Product.findAll").getResultList();
    }

    @Override
    public Product add(Product product) {
        return entityManager.merge(product);
    }

    @Override
    public Product getById(int id) {
        return entityManager.createNamedQuery("Product.findById", Product.class).setParameter("id", id).getSingleResult();
    }
}

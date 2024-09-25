package vn.edu.iuh.fit.backend.business;

import jakarta.ejb.Local;
import vn.edu.iuh.fit.backend.repositories.entities.Product;

import java.util.List;

@Local
public interface ProductBeanRemote {
    List<Product> getAll();
    Product add(Product product);
    Product getById(int id);
}

package vn.edu.iuh.fit.backend.business;

import jakarta.ejb.Local;
import vn.edu.iuh.fit.backend.dtos.ProductDTO;
import vn.edu.iuh.fit.backend.dtos.ProductPriceDTO;
import vn.edu.iuh.fit.backend.entities.Product;

import java.util.List;

@Local
public interface ProductBeanRemote {
    List<ProductDTO> getAll();
    Product add(Product product);
    ProductDTO getById(int productId);
    ProductDTO addPrice(int productId, ProductPriceDTO productPriceDTO);
    ProductDTO update(int productId, ProductDTO productDTO);
}

package vn.edu.iuh.fit.backend.business;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vn.edu.iuh.fit.backend.dtos.ProductDTO;
import vn.edu.iuh.fit.backend.entities.Product;
import vn.edu.iuh.fit.backend.entities.ProductPrice;
import vn.edu.iuh.fit.backend.repositories.ProductPriceRepository;
import vn.edu.iuh.fit.backend.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class ProductBean implements ProductBeanRemote{

    @Inject
    private ProductRepository productRepository;

    @Inject
    private ProductPriceRepository productPriceRepository;

    @Override
    public List<ProductDTO> getAll() {
        List<Product> products = productRepository.getAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        products.forEach(product -> {
            ProductPrice activePriceByProduct = productPriceRepository.findActivePriceByProduct(product.getProductId());
            double price = activePriceByProduct == null ? 0.0 : activePriceByProduct.getValue();
            ProductDTO productDTO = new ProductDTO(
                    product.getProductId(),
                    product.getName(),
                    product.getDescription(),
                    product.getImgPath(),
                    price
            );
            productDTOS.add(productDTO);
        });
        return productDTOS;
    }

    @Override
    public Product add(Product product) {
        return productRepository.add(product);
    }

    @Override
    public ProductDTO getById(int productId) {
        Product product = productRepository.getById(productId);
        ProductPrice activePriceByProduct = productPriceRepository.findActivePriceByProduct(productId);
        double price = activePriceByProduct == null ? 0.0 : activePriceByProduct.getValue();
        return new ProductDTO(
                product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getImgPath(),
                price
        );
    }
}

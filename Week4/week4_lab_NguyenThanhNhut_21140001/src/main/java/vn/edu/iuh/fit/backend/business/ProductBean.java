package vn.edu.iuh.fit.backend.business;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vn.edu.iuh.fit.backend.dtos.ProductDTO;
import vn.edu.iuh.fit.backend.dtos.ProductPriceDTO;
import vn.edu.iuh.fit.backend.entities.Product;
import vn.edu.iuh.fit.backend.entities.ProductPrice;
import vn.edu.iuh.fit.backend.mapper.ProductMapper;
import vn.edu.iuh.fit.backend.mapper.ProductPriceMapper;
import vn.edu.iuh.fit.backend.repositories.ProductPriceRepository;
import vn.edu.iuh.fit.backend.repositories.ProductRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class ProductBean implements ProductBeanRemote{

    @Inject
    private ProductRepository productRepository;

    @Inject
    private ProductPriceRepository productPriceRepository;

    @Inject
    private ProductMapper productMapper;

    @Inject
    private ProductPriceMapper productPriceMapper;

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

    @Override
    public ProductDTO addPrice(int productId, ProductPriceDTO productPriceDTO) {
        Product product = productRepository.getById(productId);
        System.out.println("Price trươc khi mapper" + productPriceDTO.getValue());

        if (product != null) {
            ProductPrice productPrice = productPriceMapper.productPriceDTOToProductPrice(productPriceDTO);
            productPrice.setProduct(product);
            productPrice.setApplyDate(Timestamp.valueOf(LocalDateTime.now()));

            ProductPrice activePrice = productPriceRepository.findActivePriceByProduct(productId);
            if (activePrice != null) {
                activePrice.setStatus(0);
                productPriceRepository.save(activePrice);
            }
            System.out.println("Price sau khi mapper" + productPrice.getValue());

            productPriceRepository.add(productPrice);
            return productMapper.productToProductDTO(product);
        } else {
            return null;
        }
    }
}

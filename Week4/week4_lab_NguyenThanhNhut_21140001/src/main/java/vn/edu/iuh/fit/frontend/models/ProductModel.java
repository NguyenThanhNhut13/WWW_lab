/*
 * @ (#) ProductModel.java       1.0     25/09/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.frontend.models;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 25/09/2024
 * @version:    1.0
 */

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.backend.dtos.ProductPriceDTO;
import vn.edu.iuh.fit.backend.entities.Product;

public class ProductModel {
    private final String ADD_URL = "http://localhost:8080/week3_lab-1.0-SNAPSHOT/api/products";
    private final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ProductModel.class.getName());
    public void createProduct(Product product) {
        try(Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(ADD_URL);

            Response response = target.request().post(Entity.json(product));
            if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
                System.out.println("Product added successfully");
            } else {
                System.out.println("Failed to add product");
            }
            response.close();
        }
    }

    public Product getProductById(int productId) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(ADD_URL).path(String.valueOf(productId));
            Response response = target.request().get();
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                return response.readEntity(Product.class);
            } else {
                System.out.println("Failed to get product");
            }
            response.close();
        } catch (Exception e) {
            logger.severe("An error occurred: " + e.getMessage());
        }
        return null;
    }

    public void addPrice(int productId, ProductPriceDTO productPriceDTO) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(ADD_URL).path(String.valueOf(productId)).path("add-price");
            Response response = target.request().post(Entity.json(productPriceDTO));
            if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
                System.out.println("Price added successfully");
            } else {
                System.out.println("Failed to add price");
            }
            response.close();
        } catch (Exception e) {
            logger.severe("An error occurred: " + e.getMessage());
        }
    }
}

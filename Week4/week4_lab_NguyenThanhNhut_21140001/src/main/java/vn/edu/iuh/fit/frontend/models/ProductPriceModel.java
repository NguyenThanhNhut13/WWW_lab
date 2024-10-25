/*
 * @ (#) ProductPriceModel.java       1.0     24/10/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.frontend.models;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 24/10/2024
 * @version:    1.0
 */

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.backend.dtos.ProductPriceDTO;

import java.util.List;

public class ProductPriceModel {
    private final String URL = "http://localhost:8080/week3_lab-1.0-SNAPSHOT/api/product-prices";
    private final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ProductPriceModel.class.getName());

    public List<ProductPriceDTO> findAllProductPriceByProductId(int productId) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(URL).path(String.valueOf(productId));
            Response response = target.request().get();
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                return response.readEntity(new GenericType<List<ProductPriceDTO>>() {});
            } else {
                System.out.println("Failed to get product prices");
            }
            response.close();
        } catch (Exception e) {
            logger.severe("An error occurred: " + e.getMessage());
        }
        return null;
    }
}

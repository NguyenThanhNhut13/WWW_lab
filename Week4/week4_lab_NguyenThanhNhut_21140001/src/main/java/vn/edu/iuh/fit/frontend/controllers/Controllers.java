/*
 * @ (#) Controllers.java       1.0     25/09/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.frontend.controllers;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 25/09/2024
 * @version:    1.0
 */

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.backend.dtos.ProductDTO;
import vn.edu.iuh.fit.backend.dtos.ProductPriceDTO;
import vn.edu.iuh.fit.backend.entities.Product;
import vn.edu.iuh.fit.frontend.models.ProductModel;
import vn.edu.iuh.fit.frontend.models.ProductPriceModel;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "Controller", value = "/controller")
public class Controllers extends HttpServlet {

    @Inject
    private ProductModel productModel;

    @Inject
    private ProductPriceModel productPriceModel;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println("Action: " + action);
        if (action.equals("add")) {
            String name = req.getParameter("product_name");
            String description = req.getParameter("product_description");
            String imgPath = req.getParameter("img_path");
            Product product = new Product(name, description, imgPath);
            productModel.createProduct(product);
            resp.sendRedirect("index.jsp");
        } else if (action.equals("edit")) {
            int productId = Integer.parseInt(req.getParameter("productId"));
            ProductDTO product = productModel.getProductById(productId);
            List<ProductPriceDTO> productPrices = productPriceModel.findAllProductPriceByProductId(productId);
            if (product != null) {
                req.setAttribute("product", product);
                req.setAttribute("productPrices", productPrices);
                req.getRequestDispatcher("products/edit-product.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("error.jsp");
            }
        } else if (action.equals("save")) {
            int productId = Integer.parseInt(req.getParameter("productId"));
            String name = req.getParameter("product_name");
            String description = req.getParameter("product_description");
            String imgPath = req.getParameter("img_path");
            double price = Double.parseDouble(req.getParameter("product_price"));
            ProductDTO product = new ProductDTO(productId, name, description, imgPath, price);
            productModel.updateProduct(product);
            resp.sendRedirect("products/ProductList.jsp");
        } else if (action.equals("addPrice")) {
            int productId = Integer.parseInt(req.getParameter("productId"));
            ProductDTO product = productModel.getProductById(productId);
            if (product != null) {
                req.setAttribute("product", product);
                req.getRequestDispatcher("products/addPrice.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("index.jsp");
            }
        } else if (action.equals("savePrice")) {
            try {
                int productId = Integer.parseInt(req.getParameter("productId"));
                double price = Double.parseDouble(req.getParameter("price"));
                ProductPriceDTO productPriceDTO = new ProductPriceDTO();
                productPriceDTO.setProductId(productId);
                productPriceDTO.setValue(price);
                productPriceDTO.setStatus(1);
                productModel.addPrice(productId, productPriceDTO);
                resp.sendRedirect("products/ProductList.jsp");
            } catch (Exception e) {
                resp.sendRedirect("error.jsp");
            }
        }

    }
}

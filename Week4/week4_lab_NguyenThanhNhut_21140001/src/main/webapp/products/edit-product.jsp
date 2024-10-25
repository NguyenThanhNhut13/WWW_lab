<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="vn.edu.iuh.fit.backend.dtos.ProductDTO" %>
<%@ page import="vn.edu.iuh.fit.backend.dtos.ProductPriceDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 24/10/2024
  Time: 11:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
        <%
            ProductDTO product = (ProductDTO) request.getAttribute("product");
            List<ProductPriceDTO> productPrices = (List<ProductPriceDTO>) request.getAttribute("productPrices");
        %>
    <div class="container w-50 mt-5">
        <h1>Edit Product</h1>
        <form action="http://localhost:8080/week3_lab-1.0-SNAPSHOT/controller" method="post">
            <input type="hidden" name="productId" value="<%= product.getProductId() %>">
            <input type="hidden" name="action" value="save">
            <div class="form-group mb-3">
                <label for="product_name" class="form-label">Product Name</label>
                <input type="text" class="form-control" id="product_name" name="product_name" value="<%= product.getName() %>">
            </div>
            <div class="form-group mb-3">
                <label for="product_description" class="form-label">Product Description</label>
                <input type="text" class="form-control" id="product_description" name="product_description" value="<%= product.getDescription() %>">
            </div>
            <div class="form-group mb-3">
                <label class="control-label" for="image_path">Image Path</label>
                <input id="image_path" name="img_path" class="form-control input-md" type="text" value="<%= product.getImgPath() %>">
            </div>
            <div class="form-group mb-3">
                <label for="product_price" class="form-label mb-3">Current Price</label>
                <input type="text" class="form-control" id="product_price" name="product_price" value="<%= product.getPrice() %>">
            </div>

            <%
                if (!productPrices.isEmpty()) {
            %>
            <h4>Price History:</h4>
            <ul>
                <c:forEach var="productPrice" items="${productPrices}">
                    <li>
                        <span class="old-price">${productPrice.value}</span> - Status: ${productPrice.status == 1 ? 'Active' : 'Inactive'} - Apply at: ${productPrice.applyDate} - Note: ${productPrice.note}
                    </li>
                </c:forEach>
            </ul>
            <% } %>
            <button type="submit" class="btn btn-primary">Update</button>
        </form>
    </div>
<script>
    const formatter = new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
    });
    const priceElements = document.querySelectorAll('.old-price');
    priceElements.forEach(priceElement => {
        const priceValue = parseFloat(priceElement. innerText);
        priceElement.innerText = formatter.format(priceValue);
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="../resources/js/products.js"></script>
</body>
</html>
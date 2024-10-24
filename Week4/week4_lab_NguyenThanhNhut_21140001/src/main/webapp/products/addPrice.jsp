<%@ page import="vn.edu.iuh.fit.backend.entities.Product" %><%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 23/10/2024
  Time: 1:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Price Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
    <div class="container w-50 mt-5">
        <h1>Add Price</h1>
        <%
            Product product = (Product) request.getAttribute("product");
            if (product != null) {
        %>
        <form action="http://localhost:8080/week3_lab-1.0-SNAPSHOT/controller" method="post">
            <div class="mb-3">
                <label for="price" class="form-label">Price</label>
                <input type="number" class="form-control" id="price" name="price" required>
            </div>
            <input type="hidden" name="action" value="savePrice">
            <input type="hidden" name="productId" value="<%= product.getProductId() %>">
            <button type="submit" class="btn btn-primary">Add</button>
        </form>
        <%
            } else {
        %>
            <div class="alert alert-danger" role="alert">
                Product not found
            </div>
        <%
            }
        %>
    </div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>

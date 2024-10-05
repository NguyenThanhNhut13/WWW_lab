<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 25/09/2024
  Time: 1:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
    <div class="container w-50">
        <form action="../controller" method="post">
            <input type="hidden" name="action" value="add">
            <!-- Text input-->
            <div class="form-group">
                <label class="control-label" for="product_id">Product ID</label>
                <input id="product_id" name="product_id" placeholder="Product Id" class="form-control input-md" type="text" disabled>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="control-label" for="product_name">Product Name</label>
                <input id="product_name" name="product_name" placeholder="Product Name" class="form-control input-md" required="" type="text">
            </div>

            <!-- Textarea -->
            <div class="form-group">
                <label class="control-label" for="product_description">Product Description</label>
                <textarea class="form-control" id="product_description" name="product_description"></textarea>
            </div>

            <div class="form-group">
                <label class="control-label" for="image_path">Image Path</label>
                <input id="image_path" name="img_path" placeholder="Image Path" class="form-control input-md" required="" type="text">
            </div>

            <!-- Button -->
            <div class="form-group my-2">
                <button type="submit" class="btn btn-primary">Submit</button>
                <button class="btn btn-secondary">Clear</button>
            </div>
        </form>
    </div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>

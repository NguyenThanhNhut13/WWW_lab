<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 13/09/2024
  Time: 5:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container w-25 mt-5">
    <form action="controller?action=login" method="post">
        <!-- Show error message -->
        <%
            String error = (String) request.getAttribute("error");
            String accountId = (String) request.getAttribute("accountId");
            if (error == null) {
                error = "";
            }
            if (accountId == null) {
                accountId = "";
            }

        %>
        <!-- User input -->
        <div data-mdb-input-init class="form-outline mb-4">
            <input type="text" id="accountId" name="accountId" class="form-control" value="<%=accountId%>"/>
            <label class="form-label" for="accountId">Username</label>
        </div>

        <!-- Password input -->
        <div data-mdb-input-init class="form-outline mb-2">
            <input type="password" id="password" name="password" class="form-control" />
            <label class="form-label" for="password">Password</label>
        </div>
        <div class="form-outline mb-4">
            <p style="color: red"><%= error %></p>
        </div>
        <!-- 2 column grid layout for inline styling -->
        <div class="row mb-4">
            <div class="col d-flex justify-content-center">
                <!-- Checkbox -->
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="form2Example31" checked />
                    <label class="form-check-label" for="form2Example31"> Remember me </label>
                </div>
            </div>

            <div class="col">
                <!-- Simple link -->
                <a href="#!">Forgot password?</a>
            </div>
        </div>

        <!-- Submit button -->
        <button type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-block mb-4">Sign in</button>

        <!-- Register buttons -->
        <div class="text-center">
            <p>Not a member? <a href="#!">Register</a></p>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
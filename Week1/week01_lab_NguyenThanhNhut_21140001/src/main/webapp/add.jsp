<%@ page import="vn.edu.iuh.fit.demo.entities.Account" %>
<%@ page import="vn.edu.iuh.fit.demo.services.AccountServices" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<%
    String action = request.getParameter("action");
    String accountId = request.getParameter("accountId") == null ? "" : request.getParameter("accountId");
    String fullName = request.getParameter("fullName") == null ? "" : request.getParameter("fullName");
    String password = request.getParameter("password") == null ? "" : request.getParameter("password");
    String phone = request.getParameter("phone") == null ? "" : request.getParameter("phone");
    String email = request.getParameter("email") == null ? "" : request.getParameter("email");
    String status = request.getParameter("status") == null ? "" : request.getParameter("status");
    String error = request.getAttribute("error") == null ? "" : request.getAttribute("error").toString();

    AccountServices accountServices = new AccountServices();
    if (!accountId.equals("")){
        Account account = accountServices.findAccountById(accountId);
        if (account != null && action.equals("update")) {
            accountId = account.getAccountId();
            fullName = account.getFullName();
            password = account.getPassword();
            phone = account.getPhone();
            email = account.getEmail();
            status = String.valueOf(account.getStatus());
        }
    }

%>
<div class="container w-50" >
    <h2>Add New Account</h2>
    <form action="controller" method="post">
        <input type="hidden" name="action" value="<%=action%>">
        <div class="mb-3">
            <label for="accountId" class="form-label">Id</label>
            <input type="text" class="form-control" id="accountId" name="accountId" value="<%= accountId %>" required <%= action.equals("update") ? "readonly" : "" %>>
        </div>
        <div class="mb-3">
            <label for="fullName" class="form-label">Full Name</label>
            <input type="text" class="form-control" id="fullName" name="fullName" value="<%= fullName %>" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password" name="password" value="<%= password %>" required>
        </div>
        <div class="mb-3">
            <label for="phone" class="form-label">Phone</label>
            <input type="text" class="form-control" id="phone" name="phone" value="<%= phone %>" required>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" value="<%= email %>" required>
        </div>
        <div class="mb-3">
            <label for="status" class="form-label">Status</label>
            <select class="form-select" id="status" name="status">
                <option value="1" <%= status.equals("1") ? "selected" : "" %>>Active</option>
                <option value="0" <%= status.equals("0") ? "selected" : "" %>>Inactive</option>
                <option value="-1" <%= status.equals("-1") ? "selected" : "" %>>Deleted</option>
            </select>
        </div>
        <div class="mb-3 text-danger">
            <%= error %>
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
    <a href="dashboard.jsp" class="btn btn-secondary">Cancel</a>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>

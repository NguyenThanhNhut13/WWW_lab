<%@ page import="vn.edu.iuh.fit.demo.repositories.AccountRepository" %>
<%@ page import="vn.edu.iuh.fit.demo.repositories.impl.AccountRepositoryImpl" %>
<%@ page import="vn.edu.iuh.fit.demo.entities.Account" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.demo.entities.Role" %>
<%@ page import="vn.edu.iuh.fit.demo.services.RoleServices" %>
<%@ page import="vn.edu.iuh.fit.demo.services.AccountServices" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 13/09/2024
  Time: 5:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<%
    RoleServices roleServices = new RoleServices();
    AccountServices accountServices = new AccountServices();
    String error = request.getAttribute("error") == null ? "" : request.getAttribute("error").toString();
    String success = request.getAttribute("success") == null ? "" : request.getAttribute("success").toString();
    String accountId = session.getAttribute("accountId").toString();
    String user = accountServices.findAccountById(accountId).getFullName();
    boolean isAdministrator = roleServices.isAdministrator(accountId);
    if (error != null && !error.isEmpty()) {
%>
<div class="alert alert-danger" role="alert">
    <%= error %>
</div>
<%
    }
    if (success != null && !success.isEmpty()) {
%>
<div class="alert alert-success" role="alert">
    <%= success %>
</div>
<%
    }
%>
<div class="container">
    <h1>Dashboard</h1>
    <div class="d-flex justify-content-between mb-2">
        <h4>Welcome, <%= user %></h4>
        <a href="controller?action=logout" class="btn btn-primary">Logout</a>
    </div>
    <%
        if (isAdministrator) {
    %>

    <h2>Account List</h2>
    <a href="controller?action=add" class="btn btn-primary mb-2">Add New</a>

    <form action="controller" method="get">
        <input type="hidden" name="action" value="filterByRole">
        <div class="form-group">
            <label for="roleSelect">Filter by Role:</label>
            <select id="roleSelect" name="roleId" class="form-control" onchange="this.form.submit()">
                <option value="">Select Role</option>
                <%-- Fetch roles and populate dropdown --%>
                <%
                    List<String> roles = roleServices.findAll();
                    for (String role : roles) {
                %>
                <option value="<%= role %>" <%= request.getParameter("roleId") != null && request.getParameter("roleId").equals(role) ? "selected" : "" %>><%= role %></option>
                <%
                    }
                %>
            </select>
        </div>
    </form>
    <%
        }
    %>
    <table class="table table-striped">
        <tr>
            <th>Id</th>
            <th>Full Name</th>
            <th>Password</th>
            <th>Phone</th>
            <th>Email</th>
            <th>Status</th>
            <th>Roles</th>
            <th colspan="2">Action</th>
        </tr>
        <%
            String selectedRole = request.getParameter("roleId");
            List<Account> accounts = (selectedRole == null || selectedRole.isEmpty())
                    ? accountServices.findAll()
                    : accountServices.findAccountByRoleName(selectedRole);
            if (!isAdministrator) {
                accounts = new ArrayList<>();
                accounts.add(accountServices.findAccountById(accountId));
            }
            for (Account account : accounts) {
        %>
        <tr>
            <td><%= account.getAccountId() %></td>
            <td><%= account.getFullName() %></td>
            <td><%= account.getPassword() %></td>
            <td><%= account.getPhone() %></td>
            <td><%= account.getEmail() %></td>
            <td><%= account.getStatus() == 1 ? "Active" : account.getStatus() == 0 ? "Inactive" : "Deleted" %></td>
            <td>
                <%
                    for (String role : roleServices.findRoleByAccountId(account.getAccountId())) {
                %>
                <span class="badge bg-primary"><%= role %></span>
                <%
                    }
                %>
            <td>
                <a href="controller?action=update&accountId=<%= account.getAccountId() %>" class="btn btn-warning">Edit</a>
                <%
                    if (isAdministrator) {
                %>
                <a href="javascript:void(0);" class="btn btn-danger" onclick="deleteAccount(<%=account.getAccountId()%>);">Delete</a>
                <a href="controller?action=grantRole&accountId=<%= account.getAccountId() %>" class="btn btn-success">Grant Role</a>
                <%
                    }
                %>
        </td>
        </tr>
        <%
            }
        %>
    </table>
</div>
<script>
    function deleteAccount(id) {
        if (confirm("Are you sure to delete this account?")) {
            const form = document.createElement("form");
            form.method = "post";
            form.action = "controller?action=delete";

            const action = document.createElement("input");
            action.type = "hidden";
            action.name = "accountId";
            action.value = id;
            form.appendChild(action);

            document.body.appendChild(form);
            form.submit();
        }
    }

    window.onload = function() {
        var alertElement = document.querySelector('.alert');
        if (alertElement) {
            // Set a timeout to hide the alert after 3 seconds
            setTimeout(function() {
                alertElement.style.display = 'none';
            }, 3000); // 3000 milliseconds = 3 seconds
        }
    };
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>

<%@ page import="vn.edu.iuh.fit.demo.repositories.AccountDAO" %>
<%@ page import="vn.edu.iuh.fit.demo.repositories.impl.AccountDAOImpl" %>
<%@ page import="vn.edu.iuh.fit.demo.entities.Account" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<%
    AccountDAO accountDAO = new AccountDAOImpl();
    List<Account> accounts = accountDAO.findAll();
%>
<div class="container">
    <h2>Account List</h2>
    <a href="controller?action=add" class="btn btn-primary mb-2">Add New</a>
    <table class="table table-striped">
        <tr>
            <th>Id</th>
            <th>Full Name</th>
            <th>Password</th>
            <th>Phone</th>
            <th>Email</th>
            <th>Status</th>
            <th colspan="2">Action</th>
        </tr>
        <%
            for (Account account : accounts) {
        %>
        <tr>
            <td><%= account.getAccountId() %></td>
            <td><%= account.getFullName() %></td>
            <td><%= account.getPassword() %></td>
            <td><%= account.getPhone() %></td>
            <td><%= account.getEmail() %></td>
            <td><%= account.getStatus() %></td>
            <td>
                <a href="action?id=<%= account.getAccountId() %>" class="btn btn-warning">Edit</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
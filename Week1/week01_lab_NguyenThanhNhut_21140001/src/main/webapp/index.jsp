<%@ page import="vn.edu.iuh.fit.demo.repositories.AccountRepository" %>
<%@ page import="vn.edu.iuh.fit.demo.repositories.impl.AccountRepositoryImpl" %>
<%@ page import="vn.edu.iuh.fit.demo.entities.Account" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.demo.repositories.AccountRepository" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<%
    AccountRepository accountRepository = new AccountRepositoryImpl();
    List<Account> accounts = accountRepository.findAll();
    String error = request.getAttribute("error") == null ? "" : request.getAttribute("error").toString();
    String success = request.getAttribute("success") == null ? "" : request.getAttribute("success").toString();
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
            <td><%= account.getStatus() == 1 ? "Active" : account.getStatus() == 0 ? "Inactive" : "Deleted" %></td>
            <td>
                <a href="controller?action=update&id=<%= account.getAccountId() %>" class="btn btn-warning">Edit</a>
            </td>
            <td>
                <a href="javascript:void(0);" class="btn btn-danger" onclick="deleteAccount(<%=account.getAccountId()%>);">Delete</a>
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
            action.name = "id";
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
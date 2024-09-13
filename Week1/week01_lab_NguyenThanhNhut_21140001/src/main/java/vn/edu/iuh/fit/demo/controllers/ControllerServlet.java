package vn.edu.iuh.fit.demo.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.demo.entities.Account;
import vn.edu.iuh.fit.demo.services.AccountServices;

import java.io.IOException;

@WebServlet(name = "controllerServlet", value = "/controller")
public class ControllerServlet extends HttpServlet {
    private AccountServices accountServices;

    public void init() {
        accountServices = new AccountServices();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String action = request.getParameter("action");
        if (action.equals("add")) {
            String accountId = request.getParameter("accountId");
            String fullName = request.getParameter("fullName");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String status = request.getParameter("status");

            Account account = new Account(accountId, fullName, password, email, phone, Byte.parseByte(status));

            if (accountServices.addAccount(account)) {
                response.sendRedirect("index.jsp");
            } else {
                request.setAttribute("error", "Add account failed");
                request.getRequestDispatcher("add.jsp").forward(request, response);
            }
        } else if (action.equals("update")) {
            String accountId = request.getParameter("accountId");
            String fullName = request.getParameter("fullName");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String status = request.getParameter("status");

            Account account = new Account(accountId, fullName, password, email, phone, Byte.parseByte(status));

            if (accountServices.updateAccount(account) != null) {
                response.sendRedirect("index.jsp");
            } else {
                request.setAttribute("error", "Update account failed");
                request.getRequestDispatcher("add.jsp").forward(request, response);
            }
        } else if (action.equals("delete")) {
            String id = request.getParameter("accountId");
            Account account = accountServices.findAccountById(id);
            if (accountServices.deleteAccount(account)) {
                request.setAttribute("success", "Delete account successfully");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Delete account failed");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("add")) {
            request.getRequestDispatcher("add.jsp").forward(request, response);
        } else if (action.equals("update")) {
            String id = request.getParameter("accountId");
            request.getRequestDispatcher("add.jsp").forward(request, response);
        }
    }


    public void destroy() {
    }

}

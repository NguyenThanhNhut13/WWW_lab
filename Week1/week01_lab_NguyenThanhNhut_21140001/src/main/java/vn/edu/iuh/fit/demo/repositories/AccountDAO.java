package vn.edu.iuh.fit.demo.repositories;

import jakarta.servlet.annotation.WebServlet;
import vn.edu.iuh.fit.demo.entities.Account;

import java.util.List;

public interface AccountDAO {
    public List<Account> findAll();
    public Account findById(String id);
    public boolean exists(String id);
    public void save(Account account);
}

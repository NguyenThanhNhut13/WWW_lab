package vn.edu.iuh.fit.demo.repositories;

import vn.edu.iuh.fit.demo.entities.Account;

public interface AccountDAO {
    public Account findById(String id);
}

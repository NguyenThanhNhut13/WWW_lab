package vn.edu.iuh.fit.demo.repositories;

import vn.edu.iuh.fit.demo.entities.Account;

import java.util.List;

public interface AccountRepository {
    public List<Account> findAll();
    public Account findById(String id);
    public boolean exists(String id);
    public void save(Account account);
    public Account update(Account account);
    public boolean delete(Account account);
}

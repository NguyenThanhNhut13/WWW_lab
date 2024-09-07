package vn.edu.iuh.fit.demo.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import vn.edu.iuh.fit.demo.entities.Account;
import vn.edu.iuh.fit.demo.repositories.AccountDAO;

public class AccountDAOImpl implements AccountDAO {
    @Override
    public Account findById(String id) {
        EntityManager em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
        Account account = em.createNamedQuery("Account.findByAccountId", Account.class).setParameter("accountId", id).getSingleResult();
        return account;
    }

}

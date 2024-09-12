package vn.edu.iuh.fit.demo.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import vn.edu.iuh.fit.demo.entities.Account;
import vn.edu.iuh.fit.demo.repositories.AccountDAO;

import java.util.List;

public class AccountDAOImpl implements AccountDAO {

    private EntityManager em;

    public AccountDAOImpl() {
        em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = em.createNamedQuery("Account.findAll", Account.class).getResultList();
        return accounts;
    }

    @Override
    public Account findById(String id) {
        Account account = em.createNamedQuery("Account.findByAccountId", Account.class).setParameter("accountId", id).getSingleResult();
        return account;
    }

    @Override
    public boolean exists(String id) {
        return em.createNamedQuery("Account.existsByAccountId", Boolean.class).setParameter("accountId", id).getSingleResult();
    }

    @Override
    public void save(Account account) {
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
    }



}

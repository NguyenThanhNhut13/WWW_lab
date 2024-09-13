package vn.edu.iuh.fit.demo.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import vn.edu.iuh.fit.demo.entities.Account;
import vn.edu.iuh.fit.demo.repositories.AccountRepository;

import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {

    private EntityManager em;

    public AccountRepositoryImpl() {
        em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = em.createNamedQuery("Account.findAll", Account.class).getResultList();
        return accounts;
    }

    @Override
    public Account findById(String id) {
       try {
           return em.createNamedQuery("Account.findByAccountId", Account.class).setParameter("accountId", id).getSingleResult();
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
    }

    @Override
    public boolean exists(String id) {
        return em.createNamedQuery("Account.existsByAccountId", Boolean.class).setParameter("accountId", id).getSingleResult();
    }

    @Override
    public void save(Account account) {
        em.getTransaction().begin();
        em.merge(account);
        em.getTransaction().commit();
    }

    @Override
    public Account update(Account account) {
        try {
            em.getTransaction().begin();
            int a = em.createNamedQuery("Account.updateAccountByAccountId").setParameter("accountId", account.getAccountId())
                    .setParameter("fullName", account.getFullName())
                    .setParameter("password", account.getPassword())
                    .setParameter("email", account.getEmail())
                    .setParameter("phone", account.getPhone())
                    .setParameter("status", account.getStatus())
                    .executeUpdate();
            em.getTransaction().commit();
            if (a == 0) {
                return null;
            } else {
                return em.find(Account.class, account.getAccountId());
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(Account account) {
        em.getTransaction().begin();
        int a = em.createNamedQuery("Account.deleteByAccountId").setParameter("accountId", account.getAccountId()).executeUpdate();
        em.flush();
        em.clear();
        em.getTransaction().commit();
        return a > 0;
    }

    @Override
    public List<Account> findAccountByRoleName(String roleName) {
        List<Account> accounts = em.createNamedQuery("Account.findByRoleName", Account.class).setParameter("roleName", roleName).getResultList();
        return accounts;
    }

    @Override
    public List<Account>  findAccountByRoleId(String roleId) {
        List<Account> accounts = em.createNamedQuery("Account.findByRoleId", Account.class).setParameter("roleId", roleId).getResultList();
        return accounts;
    }
}

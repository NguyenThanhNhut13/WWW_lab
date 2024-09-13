/*
 * @ (#) GrantAccessRepositoryImpl.java       1.0     13/09/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.demo.repositories.impl;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 13/09/2024
 * @version:    1.0
 */

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import vn.edu.iuh.fit.demo.entities.GrantAccess;
import vn.edu.iuh.fit.demo.repositories.GrantAccessRepository;

import java.util.List;

public class GrantAccessRepositoryImpl implements GrantAccessRepository {
    private EntityManager em;

    public GrantAccessRepositoryImpl() {
        em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
    }

    @Override
    public List<GrantAccess> findAccountByRoleId(String roleId) {
        return em.createNamedQuery("GrantAccess.findAccountByRoleId", GrantAccess.class).setParameter("roleId", roleId).getResultList();
    }

    @Override
    public List<GrantAccess> findRoleByAccountId(String accountId) {
        return em.createNamedQuery("GrantAccess.findRoleByAccountId", GrantAccess.class).setParameter("accountId", accountId).getResultList();
    }

    @Override
    public boolean save(GrantAccess grantAccess) {
        em.getTransaction().begin();
        em.merge(grantAccess);
        em.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(GrantAccess grantAccess) {
        em.getTransaction().begin();
        em.remove(grantAccess);
        em.getTransaction().commit();
        return true;
    }

    @Override
    public boolean exists(String accountId, String roleId) {
        return em.createNamedQuery("GrantAccess.exists", Boolean.class).setParameter("accountId", accountId).setParameter("roleId", roleId).getSingleResult();
    }
}

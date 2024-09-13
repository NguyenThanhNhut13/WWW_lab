/*
 * @ (#) RoleRepositoryImpl.java       1.0     13/09/2024
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
import vn.edu.iuh.fit.demo.entities.Role;
import vn.edu.iuh.fit.demo.repositories.RoleRepository;

import java.util.List;

public class RoleRepositoryImpl implements RoleRepository {
    private EntityManager em;

    public RoleRepositoryImpl() {
        em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = em.createNamedQuery("Role.findAll", Role.class).getResultList();
        return roles;
    }

    @Override
    public Role findById(String id) {
        Role role = em.createNamedQuery("Role.findByRoleId", Role.class).setParameter("roleId", id).getSingleResult();
        return role;
    }

    @Override
    public boolean exists(String id) {
        return em.createNamedQuery("Role.existsByRoleId", Boolean.class).setParameter("roleId", id).getSingleResult();
    }

    @Override
    public void save(Role role) {
        em.getTransaction().begin();
        em.merge(role);
        em.getTransaction().commit();
    }

    @Override
    public Role update(Role role) {
        em.getTransaction().begin();
        int a = em.createNamedQuery("Role.updateRoleByRoleId").setParameter("roleId", role.getRoleId())
                .setParameter("roleName", role.getRoleName())
                .executeUpdate();
        em.getTransaction().commit();
        if (a == 0) {
            return null;
        } else {
            return em.find(Role.class, role.getRoleId());
        }
    }

    @Override
    public boolean delete(Role role) {
        em.getTransaction().begin();
        int a = em.createNamedQuery("Role.deleteByRoleId").setParameter("roleId", role.getRoleId()).executeUpdate();
        em.getTransaction().commit();
        return a != 0;
    }

    @Override
    public boolean isAdministrator(String accountId) {
        return em.createNamedQuery("Role.isAdministrator", Boolean.class).setParameter("accountId", accountId).getSingleResult();
    }
}

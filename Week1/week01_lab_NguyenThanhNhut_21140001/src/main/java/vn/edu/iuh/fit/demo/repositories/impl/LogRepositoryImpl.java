/*
 * @ (#) LogRepositoryImpl.java       1.0     13/09/2024
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
import vn.edu.iuh.fit.demo.entities.Log;
import vn.edu.iuh.fit.demo.repositories.LogRepository;

import java.util.List;

public class LogRepositoryImpl implements LogRepository {
    private EntityManager em;

    public LogRepositoryImpl() {
        em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
    }

    @Override
    public List<Log> findAll() {
        List<Log> logs = em.createNamedQuery("Log.findAll", Log.class).getResultList();
        return logs;
    }

    @Override
    public Log findById(String id) {
        Log log = em.createNamedQuery("Log.findById", Log.class).setParameter("id", id).getSingleResult();
        return log;
    }

    @Override
    public boolean exists(String id) {
        return em.createNamedQuery("Log.existsById", Boolean.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public void save(Log log) {
        em.getTransaction().begin();
        em.persist(log);
        em.getTransaction().commit();
    }

    @Override
    public Log update(Log log) {
        em.getTransaction().begin();
        int a = em.createNamedQuery("Log.updateById").setParameter("id", log.getId())
                .setParameter("accountId", log.getAccountId())
                .setParameter("loginTime", log.getLoginTime())
                .setParameter("logoutTime", log.getLogoutTime())
                .setParameter("notes", log.getNotes())
                .executeUpdate();

        em.getTransaction().commit();
        if (a == 0) {
            return null;
        } else {
            return em.find(Log.class, log.getId());
        }
    }

    @Override
    public boolean delete(Log log) {
        em.getTransaction().begin();
        em.remove(log);
        em.getTransaction().commit();
        return true;
    }
}

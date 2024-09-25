/*
 * @ (#) BaseProcess.java       1.0     18/09/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.business;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 18/09/2024
 * @version:    1.0
 */

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import vn.edu.iuh.fit.models.Student;

import java.util.List;

public class BaseProcess {

    @PersistenceContext(unitName = "mypu")
    private EntityManager entityManager;

    public List<Student> getAll() {
        TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s", Student.class);
        return query.getResultList();
    }

    public Student getById(Long id) {
        TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s WHERE s.id = :id", Student.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Transactional
    public Student persist(Student student) {
        entityManager.merge(student);
        return student;
    }

}




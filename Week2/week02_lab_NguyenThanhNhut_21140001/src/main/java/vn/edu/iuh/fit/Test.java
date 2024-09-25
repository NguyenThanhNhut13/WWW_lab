/*
 * @ (#) Test.java       1.0     21/09/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 21/09/2024
 * @version:    1.0
 */

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import vn.edu.iuh.fit.models.Customer;

public class Test {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("week2_NguyenThanhNhut").createEntityManager();
        em.getTransaction().begin();
//        Customer customer = new Customer(1L, "Nguyen Thanh Nhut", "abc", "123", "123");
//        System.out.println(customer);
//        em.persist(customer);

        em.getTransaction().commit();
        em.close();
    }
}

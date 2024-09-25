/*
 * @ (#) Order.java       1.0     21/09/2024
 * 
 * Copyright (c) 2024 IUH. All rights reserved.
 */
 
package vn.edu.iuh.fit.models;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 21/09/2024
 * @version:    1.0
 */

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long orderId;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @ManyToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @JsonbTransient
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    public Order(long orderId, LocalDateTime orderDate, Employee employee) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", employee=" + employee +
                '}';
    }

}


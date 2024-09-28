/*
 * @ (#) Employee.java       1.0     26/09/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.models;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 26/09/2024
 * @version:    1.0
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.iuh.fit.enums.EmployeeStatus;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long id;

    @Column(name = "address", length = 250)
    private String address;

    @Column(name = "dob")
    private LocalDateTime dob;

    @Column(name = "email", length = 150)
    private String email;

    @Column(name = "full_name", length = 150)
    private String fullName;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private EmployeeStatus status;

    @OneToMany(mappedBy = "employee")
    private List<Order> orders;
}

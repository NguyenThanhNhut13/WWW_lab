/*
 * @ (#) EmployeeRepository.java       1.0     28/09/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.repositories;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 28/09/2024
 * @version:    1.0
 */

import vn.edu.iuh.fit.enums.EmployeeStatus;
import vn.edu.iuh.fit.models.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    void insertEmp(Employee employee);
    void setStatus(Employee employee, EmployeeStatus status);
    void update(Employee employee);
    Optional<Employee> findById(long id);
    List<Employee> getAllEmp();
    void delete(Employee employee);
}

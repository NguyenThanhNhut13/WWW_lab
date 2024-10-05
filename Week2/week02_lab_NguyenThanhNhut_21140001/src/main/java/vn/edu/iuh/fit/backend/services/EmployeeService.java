/*
 * @ (#) EmployeeService.java       1.0     28/09/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.services;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 28/09/2024
 * @version:    1.0
 */

import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

public class EmployeeService {

    @Inject
    private vn.edu.iuh.fit.repositories.EmployeeRepository employeeRepository;

    public void insertEmp(vn.edu.iuh.fit.models.Employee employee) {
        employeeRepository.insertEmp(employee);
    }

    public void setStatus(vn.edu.iuh.fit.models.Employee employee, vn.edu.iuh.fit.enums.EmployeeStatus status) {
        employeeRepository.setStatus(employee, status);
    }

    public void update(vn.edu.iuh.fit.models.Employee employee) {
        employeeRepository.update(employee);
    }

    public Optional<vn.edu.iuh.fit.models.Employee> findById(long id) {
        return employeeRepository.findById(id);
    }

    public List<vn.edu.iuh.fit.models.Employee> getAllEmp() {
        return employeeRepository.getAllEmp();
    }

    public void delete(vn.edu.iuh.fit.models.Employee employee) {
        employeeRepository.delete(employee);
    }
}

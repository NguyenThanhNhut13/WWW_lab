/*
 * @ (#) EmployeeResource.java       1.0     28/09/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.resources;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 28/09/2024
 * @version:    1.0
 */

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.backend.services.EmployeeService;
import vn.edu.iuh.fit.models.Employee;

@Path("/employees")
public class EmployeeResource {

    @Inject
    private EmployeeService employeeService;

    @GET
    public Response getAll() {
        try {
            return Response.ok(employeeService.getAllEmp()).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") long id) {
        try {
            return Response.ok(employeeService.findById(id)).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Transactional
    @Produces("application/json")
    @Consumes("application/json")
    public Response insert(Employee employee) {
        try {
            employeeService.insertEmp(employee);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @PUT
    @Transactional
    @Path("/{id}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response update(@PathParam("id") long id, Employee employee) {
        try {
            employee.setId(id);
            employeeService.update(employee);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        try {
            Employee employee = employeeService.findById(id).orElse(null);
            if (employee == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            employeeService.delete(employee);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }



}

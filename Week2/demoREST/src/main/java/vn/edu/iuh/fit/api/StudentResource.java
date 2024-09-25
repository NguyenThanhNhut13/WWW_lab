/*
 * @ (#) StudentResource.java       1.0     18/09/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.api;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 18/09/2024
 * @version:    1.0
 */

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.business.BaseProcess;
import vn.edu.iuh.fit.models.Student;

import java.util.List;

@Path("/students")
public class StudentResource {
    @Inject
    private BaseProcess baseProcess;

    @GET
    public List<Student> getAll(){
        return baseProcess.getAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Response.ResponseBuilder builder = Response.ok();
        builder.entity(baseProcess.getById(id));
        return builder.build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response persist(Student student) {
        Response.ResponseBuilder builder = Response.ok();
        builder.entity(baseProcess.persist(student));
        return builder.build();
    }
}

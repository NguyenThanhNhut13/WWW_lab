package vn.edu.iuh.fit.backend.api;

import jakarta.ejb.EJB;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.backend.business.ProductBeanRemote;
import vn.edu.iuh.fit.backend.dtos.ProductDTO;
import vn.edu.iuh.fit.backend.dtos.ProductPriceDTO;
import vn.edu.iuh.fit.backend.entities.Product;

@Path("/products")
public class ProductResource {
    @EJB
    private ProductBeanRemote productBeanRemote;

    @GET
    public Response getAll(){
        return Response.status(Response.Status.OK).entity(productBeanRemote.getAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") int id){
        return Response.status(Response.Status.OK).entity(productBeanRemote.getById(id)).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Transactional
    public Response add(Product product){
        try {
            Product p = productBeanRemote.add(product);
            return Response.status(Response.Status.CREATED).entity(p).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/{id}/add-price")
    @Consumes("application/json")
    @Produces("application/json")
    @Transactional
    public Response addPrice(@PathParam("id") int id, ProductPriceDTO productPriceDTO){
        try {
            ProductDTO p = productBeanRemote.addPrice(id, productPriceDTO);
            return Response.status(Response.Status.CREATED).entity(p).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    @Transactional
    public Response update(@PathParam("id") int id, ProductDTO productDTO){
        try {
            ProductDTO p = productBeanRemote.update(id, productDTO);
            return Response.status(Response.Status.OK).entity(p).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

}

/*
 * @ (#) ProductPriceResource.java       1.0     24/10/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.api;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 24/10/2024
 * @version:    1.0
 */

import jakarta.ejb.EJB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.backend.business.ProductPriceBeanRemote;

@Path("/product-prices")
public class ProductPriceResource {

    @EJB
    private ProductPriceBeanRemote productPriceBeanRemote;

    @GET
    @Path("/{id}")
    public Response findAllProductPriceByProductId(@PathParam("id") int id) {
        return Response.status(Response.Status.OK).entity(productPriceBeanRemote.findAllProductPriceByProductId(id)).build();
    }
}

package org.starterProj.Controller;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.starterProj.Service.CartService;
import org.starterProj.dto.CartDto;
import org.starterProj.entity.Cart;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("/carts")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "cart", description = "All the cart methods")
public class CartResource {

    @Inject
    CartService cartService;

    @GET
    public List<CartDto> findAll(){
        return this.cartService.findAll();
    }

    @GET @Path("/active")
    public List<CartDto> findAllActiveCarts() {
        return this.cartService.findAllActiveCarts();
    }

    @GET
    @Path("/customer/{id}")
    public CartDto getActiveCartForCustomer(@PathParam("id") Long customerId) {
        return this.cartService.getActiveCart(customerId);
    }

    @GET
    @Path("/{id}")
    public CartDto findById(@PathParam("id") Long id) {
        return this.cartService.findById(id);
    }

    @POST
    @Path("/customer/{id}")
    public CartDto create(@PathParam("id") Long customerId) {
        return this.cartService.createDto(customerId);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        this.cartService.delete(id);
    }
}

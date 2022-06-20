package org.starterProj.Controller;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.starterProj.Service.OrderService;
import org.starterProj.dto.OrderDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "order", description = "All the order methods")
public class OrderResource {

    @Inject
    OrderService orderService;

    @GET
    public List<OrderDto> findAll(){
        return this.orderService.findAll();
    }

    @GET
    @Path("/{id}")
    public OrderDto findById(@PathParam("id") Long id){
        return this.orderService.findById(id);
    }

    @GET
    @Path("/customer/{id}")
    public List<OrderDto> findAllByUser(@PathParam("id") Long id){
        return this.orderService.findAllByUser(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public OrderDto create(OrderDto orderDto){
        return this.orderService.create(orderDto);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id){
        this.orderService.delete(id);
    }

    @DELETE
    @Path("/exists/{id}")
    public boolean existsById(@PathParam("id") Long id){
        return this.orderService.existsById(id);
    }


}

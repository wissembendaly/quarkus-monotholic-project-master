package org.starterProj.Controller;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.starterProj.Service.OrderItemService;
import org.starterProj.dto.OrderItemDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("order-items")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "order-item", description = "All the order item methods")
public class OrderItemResource {

    @Inject
    OrderItemService itemService;

    @GET
    @Path("/order/{id}")
    public List<OrderItemDto> findByOrderId(@PathParam("id") Long id){
        return this.itemService.findByOrderId(id);
    }

    @GET
    @Path("/{id}")
    public OrderItemDto findById(@PathParam("id") Long id) {
        return this.itemService.findById(id);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public OrderItemDto create(OrderItemDto orderItemDto) {
        return this.itemService.create(orderItemDto);
    }
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        this.itemService.delete(id);
    }
}

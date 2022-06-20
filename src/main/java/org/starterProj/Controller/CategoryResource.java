package org.starterProj.Controller;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.starterProj.Service.CategoryService;
import org.starterProj.dto.CategoryDto;
import org.starterProj.dto.ProductDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "category", description = "All the category methods")
public class CategoryResource {

    @Inject
    CategoryService categoryService;

    @GET
    public List<CategoryDto> findAll(){
        return this.categoryService.findAll();
    }

    @GET
    @Path("/{id}")
    public CategoryDto findById(@PathParam("id") Long id) {
        return this.categoryService.findById(id);
    }
    @GET @Path("/{id}/products")
    public List<ProductDto> findProductsByCategoryId(@PathParam("id") Long id) {
        return this.categoryService.findProductsByCategoryId(id);
    }

    //le type des données est un JSON
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public CategoryDto create(CategoryDto categoryDto) {
        return this.categoryService.create(categoryDto);
    }
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        this.categoryService.delete(id);
    }
}

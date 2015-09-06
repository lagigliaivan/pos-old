package ar.com.terminal.service;

import ar.com.terminal.Controller;
import ar.com.terminal.dto.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/pos")
public class PointOfSaleService {

    private Controller controller = new Controller();

    @GET
    @Path("/product")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> listProducts(@QueryParam("page") Integer page){

        List<Product> product = new ArrayList<>();

        product = controller.getAll(page);

        return product;
    }

    @GET
    @Path("/product/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProduct(@PathParam("id") String id){

        Product product = controller.getProduct(id);

        return product;
    }

    @PUT
    @Path("/product/{id}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Product addProduct(@PathParam("id") String id, Product product){

        //Product product = controller.addProduct(id);

        System.out.println(product);
        return product;
    }
}

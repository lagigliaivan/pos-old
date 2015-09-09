package ar.com.terminal.service;

import ar.com.terminal.Controller;
import ar.com.terminal.dto.Product;
import ar.com.terminal.model.FullProductDescription;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Path("/pos")
public class PointOfSaleService {

    private Controller controller = new Controller();

    public PointOfSaleService(Controller controller){
        this.controller = controller;
    }

    public PointOfSaleService(){
        this.controller = new Controller();
    }

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
    @Path("/product")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response.ResponseBuilder addProduct(Product product){

        controller.addProduct(product);
        URI uri = null;

        try {
            uri = new URI("http://localhost/pos/product/" + product.getId());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return Response.created(uri);
    }

    @GET
    @Path("/product/{id}/info")
    @Produces(MediaType.APPLICATION_JSON)
    public FullProductDescription getFullProductInformation(@PathParam("id") String id) {

        return controller.getFullProductInformation(id);
    }
}

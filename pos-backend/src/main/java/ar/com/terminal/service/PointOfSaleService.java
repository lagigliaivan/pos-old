package ar.com.terminal.service;

import ar.com.terminal.Controller;
import ar.com.terminal.dto.Product;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.eclipse.jetty.http.HttpStatus;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Path("pos")
@Api(value = "pos", description = "Point of sell service")
public class PointOfSaleService {

    public static final String MSG_OK_200 = "OK";
    public static final String MSG_OK_201 = "CREATED";
    public static final String MSG_INTERNAL_SERVER_ERROR_500 = "SERVER ERROR";
    public static final String MSG_NOT_FOUND_404 = "NOT FOUND";

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
    @ApiOperation(value = "List products.", notes = "List all the available products.", response = javax.ws.rs.core.Response.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.OK_200, message = MSG_OK_200),
                           @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = MSG_INTERNAL_SERVER_ERROR_500)})
    public List<Product> listProducts(@QueryParam("page") Integer page){

        List<Product> product = new ArrayList<>();

        product = controller.getAll(page);

        return product;
    }

    @GET
    @Path("/product/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns a product.", notes = "Returns a product according to its id.", response = javax.ws.rs.core.Response.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.OK_200, message = MSG_OK_200),
            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = MSG_INTERNAL_SERVER_ERROR_500)})
    public Product getProduct(@PathParam("id") String id){

        Product product = controller.getProduct(id);

        return product;
    }

    @PUT
    @Path("/product")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Add a product to the catalog.", notes = "Add a product to the catalog.", response = javax.ws.rs.core.Response.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.CREATED_201, message = MSG_OK_201),
            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = MSG_INTERNAL_SERVER_ERROR_500)})
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
    @ApiOperation(value = "List products and their full information.", notes = "List products and their full information.", response = javax.ws.rs.core.Response.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.OK_200, message = MSG_OK_200),
            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = MSG_INTERNAL_SERVER_ERROR_500)})
    public Product getFullProductInformation(@PathParam("id") String id) {

        return controller.getProductWithFullInformation(id);
    }
}

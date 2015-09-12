package ar.com.terminal.service;

import ar.com.terminal.Controller;
import ar.com.terminal.db.DBMemory;
import ar.com.terminal.dto.ProductDto;
import ar.com.terminal.dto.ProfitPolicyDto;
import ar.com.terminal.model.Catalog;
import com.wordnik.swagger.annotations.*;
import org.eclipse.jetty.http.HttpStatus;


import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
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

    private Catalog catalog = new Catalog(DBMemory.getInstance());
    private Controller controller = new Controller(catalog);


    public PointOfSaleService(Controller controller){
        this.controller = controller;
    }

    public PointOfSaleService(){

    }

    @GET
    @Path("/product")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "List products.", notes = "List all the available products.", response = javax.ws.rs.core.Response.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.OK_200, message = MSG_OK_200),
                           @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = MSG_INTERNAL_SERVER_ERROR_500)})
    public List<ProductDto> listProducts(@QueryParam("page") Integer page){

        List<ProductDto> productDto = controller.getAll(page);

        return productDto;
    }

    @GET
    @Path("/product/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns a product.", notes = "Returns a product according to its id.", response = javax.ws.rs.core.Response.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.OK_200, message = MSG_OK_200),
            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = MSG_INTERNAL_SERVER_ERROR_500)})
    public ProductDto getProduct(@PathParam("id") String id){

        ProductDto productDto = controller.getProduct(id);

        return productDto;
    }

    @PUT
    @Path("/product")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Add a product to the catalog.", notes = "Add a product to the catalog.", response = javax.ws.rs.core.Response.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.CREATED_201, message = MSG_OK_201),
            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = MSG_INTERNAL_SERVER_ERROR_500)})
    public Response addProduct(@ApiParam(value = "Product to add", required = true) ProductDto product){

        controller.addProduct(product);
        URI uri = null;

        try {
            uri = new URI("http://localhost/pos/product/" + product.getId());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return formatResponse(HttpStatus.CREATED_201, uri.toASCIIString(), MediaType.TEXT_PLAIN);
    }

    @GET
    @Path("/product/{id}/info")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "List products and their full information.", notes = "List products and their full information.", response = javax.ws.rs.core.Response.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.OK_200, message = MSG_OK_200),
            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = MSG_INTERNAL_SERVER_ERROR_500)})
    public ProductDto getFullProductInformation(@PathParam("id") String id) {

        return controller.getProductWithFullInformation(id);
    }

    @GET
    @Path("/policy")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "List profit policies", notes = "List profit policies.", response = javax.ws.rs.core.Response.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.OK_200, message = MSG_OK_200),
            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = MSG_INTERNAL_SERVER_ERROR_500)})
    public List<ProfitPolicyDto> getAvailableProfitPolicies() {
        return controller.getProfitPolicies();
    }

    @PUT
    @Path("/policy")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Add a new policy to the catalog.", notes = "Add a new policy to the catalog.", response = javax.ws.rs.core.Response.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.CREATED_201, message = MSG_OK_201),
            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = MSG_INTERNAL_SERVER_ERROR_500)})
    public void addProfitPolicy(@ApiParam(value = "Message to add. \"id\" will be ignored", required = true) ProfitPolicyDto policy ) {
        controller.addProfitPolicy(policy);
    }

    protected javax.ws.rs.core.Response formatResponse(int statusCode, Object body, String type) {
        return javax.ws.rs.core.Response.status(statusCode).entity(body).type(type).build();
    }
}

package ar.com.terminal.service;

import ar.com.terminal.Controller;
import ar.com.terminal.db.DBMemory;
import ar.com.terminal.dto.ProfitPolicyDto;
import ar.com.terminal.model.Catalog;
import com.wordnik.swagger.annotations.*;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 13/09/15.
 */
@Path("pos/policy")
@Api(value = "policy", description = "Point of sell service")
public class PolicyService {

    private Catalog catalog = new Catalog(DBMemory.getInstance());
    private Controller controller = new Controller(catalog);


    public static final String MSG_OK_200 = "OK";
    public static final String MSG_OK_201 = "CREATED";
    public static final String MSG_INTERNAL_SERVER_ERROR_500 = "SERVER ERROR";
    public static final String MSG_NOT_FOUND_404 = "NOT FOUND";

    public PolicyService(){}

    public PolicyService(Controller controller){
        this.controller = controller;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "List profit policies", notes = "List profit policies.", response = javax.ws.rs.core.Response.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.OK_200, message = MSG_OK_200),
            @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = MSG_NOT_FOUND_404)})
    public List<ProfitPolicyDto> getAvailableProfitPolicies() {
        return controller.getProfitPolicies();
    }

    @PUT
    @Path("/")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Add a new policy to the catalog.", notes = "Add a new policy to the catalog.", response = javax.ws.rs.core.Response.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.CREATED_201, message = MSG_OK_201),
            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = MSG_INTERNAL_SERVER_ERROR_500)})
    public Response addProfitPolicy(@ApiParam(value = "Message to add. \"id\" will be ignored", required = true) ProfitPolicyDto policy ) {
        controller.addProfitPolicy(policy);
        return formatResponse(HttpStatus.CREATED_201, "http:\\urlpolicy", MediaType.TEXT_PLAIN);
    }

    @PUT
    @Path("/{id}/product/")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Add Products to a policy.", notes = "Add a product to a new policy.", response = javax.ws.rs.core.Response.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.CREATED_201, message = MSG_OK_201),
            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = MSG_INTERNAL_SERVER_ERROR_500)})
    public Response addProductToPolicy(@PathParam("id") String policyId, @ApiParam(value = "List of products to be associated to the policy", required = true) List<String> productsId) {

        controller.addProductToPolicy(policyId, productsId);
        return formatResponse(HttpStatus.CREATED_201, "http:\\urlpolicy", MediaType.TEXT_PLAIN);
    }

    protected javax.ws.rs.core.Response formatResponse(int statusCode, Object body, String type) {
        return javax.ws.rs.core.Response.status(statusCode).entity(body).type(type).build();
    }

}

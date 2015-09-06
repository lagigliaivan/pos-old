package ar.com.terminal.service;

import ar.com.terminal.Controller;
import ar.com.terminal.dto.Item;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/pos")
public class PointOfSaleService {

    private Controller controller = new Controller();

    @GET
    @Path("/item/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Item getItem(@PathParam("id") String id){

        Item item = controller.getItem(id);

        return item;
    }
}

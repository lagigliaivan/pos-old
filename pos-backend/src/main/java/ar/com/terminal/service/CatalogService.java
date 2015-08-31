package ar.com.terminal.service;

import ar.com.terminal.db.dto.Item;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;


public class CatalogService {

    @GET
    @Path("/item")
    public Item getItem(@QueryParam("id") String id){

        return new Item(id,0.0f,"an item");
    }
}

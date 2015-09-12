package ar.com.terminal;

import ar.com.terminal.service.PointOfSaleService;
import org.apache.log4j.spi.LoggerFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;

/**
 * Created by ivan on 23/08/15.
 */
public class Main {
    private static final String MICRO_SERVICES_NAME = "Point of Sale";
    private static final Logger log = org.slf4j.LoggerFactory.getILoggerFactory().getLogger(MICRO_SERVICES_NAME);

    public static void main( String args[]){


        Swagger swagger = new Swagger();
        swagger.build(MICRO_SERVICES_NAME, "This service allows you to add/remove/list different items in a DB and track their existence", PointOfSaleService.class.getPackage().getName());

        final HandlerList handlers = new HandlerList();
        // Handler for Swagger UI, static handler.
        try {
            handlers.addHandler(swagger.buildUI());
        } catch (Exception e) {
           log.error("Error adding Swagger UI handler", e);
        }

        // Handler for Entity Browser and Swagger
        handlers.addHandler(swagger.buildContextHandler(PointOfSaleService.class.getPackage().getName()));

        /*ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");*/

        Server server = new Server(8080);
        server.setHandler(handlers);

      /*  ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "*//*");
        jerseyServlet.setInitOrder(0);
        jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", PointOfSaleService.class.getCanonicalName());
*/

        try {
            server.start();
            server.join();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

package ar.com.terminal;

import ar.com.terminal.service.PointOfSaleService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Created by ivan on 23/08/15.
 */
public class Main {

    public static void main( String args[]){

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server server = new Server(8080);
        server.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);
        jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", PointOfSaleService.class.getCanonicalName());


        try {
            server.start();
            server.join();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

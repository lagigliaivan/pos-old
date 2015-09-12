/*
 Copyright 2015 ARRIS Group, Inc. All rights reserved.

 This program is confidential and proprietary to ARRIS Group, Inc. (ARRIS), and 
 may not be copied, reproduced, modified, disclosed to others, published or used, 
 in whole or in part, without the express prior written permission of ARRIS.
 */

package ar.com.terminal;

import com.wordnik.swagger.jaxrs.config.BeanConfig;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import java.net.URISyntaxException;



public class Swagger {

    public void build(String title, String description, String resourcesPackage) {
        // This configures Swagger
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setResourcePackage(resourcesPackage);
        beanConfig.setScan(true);
        beanConfig.setBasePath("/");
        beanConfig.setTitle(title);
        beanConfig.setDescription(description);
    }
    
    public ContextHandler buildContextHandler(String resourcesPackage) {
        ResourceConfig resourceConfig = new ResourceConfig();
        // Replace EntityBrowser with your resource class
        // com.wordnik.swagger.jaxrs.listing loads up Swagger resources
        resourceConfig.packages(resourcesPackage, "com.wordnik.swagger.jaxrs.listing");
        ServletContainer servletContainer = new ServletContainer(resourceConfig);
        ServletHolder entityBrowser = new ServletHolder(servletContainer);

        ServletContextHandler entityBrowserContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
        entityBrowserContext.setContextPath("/");
        entityBrowserContext.addServlet(entityBrowser, "/*");

        return entityBrowserContext;
    }
    
    public ContextHandler buildUI() throws URISyntaxException {
        final ResourceHandler swaggerUIResourceHandler = new ResourceHandler();
        swaggerUIResourceHandler.setResourceBase(Swagger.class.getClassLoader().getResource("webapp").toURI().toString());
        final ContextHandler swaggerUIContext = new ContextHandler();
        swaggerUIContext.setContextPath("/docs/");
        swaggerUIContext.setHandler(swaggerUIResourceHandler);

        return swaggerUIContext;
    }
}

package org.gonevertical.server.endpoints.config;

import java.util.List;

import org.gonevertical.server.guice.utils.ClassFinder;

import com.google.api.server.spi.guice.GuiceSystemServiceServletModule;
import com.googlecode.objectify.ObjectifyFilter;

/**
 * This starts in web.xml. All requests are intercepted and sent here.
 */
public class ServletsPathForEndpointClassesModule extends GuiceSystemServiceServletModule {

  @Override
  public void configureServlets() {
    super.configureServlets();

    Class<?> endpointsModule = RegisterEndpointsModule.class;
    Class<?> servletsPathForEndpointClassesModule = ServletsPathForEndpointClassesModule.class;

    List<Class<?>> endpointClasses = ClassFinder.find("org.gonevertical.server.endpoints");
    // Remove no @Api endpoint classes from the servlets endpoint
    endpointClasses.remove(endpointsModule);
    endpointClasses.remove(servletsPathForEndpointClassesModule);
    
    // Endpoints classes
    serveGuiceSystemServiceServlet("/_ah/spi/*", endpointClasses);
  }

}

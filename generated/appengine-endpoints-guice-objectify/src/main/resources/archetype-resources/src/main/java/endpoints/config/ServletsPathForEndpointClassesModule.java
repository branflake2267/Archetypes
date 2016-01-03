#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.endpoints.config;

import java.util.List;

import ${package}.guice.utils.ClassFinder;

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

    List<Class<?>> endpointClasses = ClassFinder.find("${package}.endpoints");
    // Remove no @Api endpoint classes from the servlets endpoint
    endpointClasses.remove(endpointsModule);
    endpointClasses.remove(servletsPathForEndpointClassesModule);
    
    // Endpoints classes
    serveGuiceSystemServiceServlet("/_ah/spi/*", endpointClasses);
  }

}

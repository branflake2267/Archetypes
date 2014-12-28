#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.guice;

import java.util.List;

import ${package}.endpoints.EndpointsModule;
import ${package}.guice.utils.ClassFinder;

import com.google.api.server.spi.guice.GuiceSystemServiceServletModule;

/**
 * This starts in web.xml. All requests are intercepted and sent here.
 */
public class EndpointClassesModule extends GuiceSystemServiceServletModule {

  @Override
  public void configureServlets() {
    super.configureServlets();

    Class<?> removeModule = EndpointsModule.class;

    List<Class<?>> endpointClasses = ClassFinder.find("${package}.endpoints");
    endpointClasses.remove(removeModule);
    serveGuiceSystemServiceServlet("/_ah/spi/*", endpointClasses);
  }

}

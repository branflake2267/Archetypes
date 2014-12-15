package org.gonevertical.server.guice;

import java.util.List;

import org.gonevertical.server.guice.utils.ClassFinder;

import com.google.api.server.spi.guice.GuiceSystemServiceServletModule;

/**
 * This starts in web.xml. All requests are intercepted and sent here.
 */
public class EndpointClassesModule extends GuiceSystemServiceServletModule {

  @Override
  public void configureServlets() {
    super.configureServlets();
    
    List<Class<?>> endpointClasses = ClassFinder.find("org.gonevertical.server.endpoints");
    serveGuiceSystemServiceServlet("/_ah/spi/*", endpointClasses);
  }

  
}

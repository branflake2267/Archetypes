package org.gonevertical.server.servlets.paths;

import org.gonevertical.server.servlets.HomeServlet;

import com.google.inject.servlet.ServletModule;

/**
 * This starts in web.xml. All requests are intercepted and sent here.
 */
public class ServletsPathModule extends ServletModule {

  @Override
  public void configureServlets() {
    super.configureServlets();
        
    // ignore _ah (http://localhost:8888/_ah/admin)
    serveRegex("^/(?!_ah.*)home").with(HomeServlet.class);
  }

  
}

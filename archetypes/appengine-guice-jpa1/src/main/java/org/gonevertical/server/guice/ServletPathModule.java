package org.gonevertical.server.guice;


import org.gonevertical.server.servlets.HomeServlet;

import com.google.inject.servlet.ServletModule;

/**
 * This starts in web.xml. All requests are intercepted and sent here.
 */
public class ServletPathModule extends ServletModule {
  
  @Override
  public void configureServlets() {
    // ignore _ah
    serveRegex("^/(?!_ah.*)").with(HomeServlet.class);
  }
  
}
package org.gonevertical.project.server.guice;

import org.gonevertical.project.server.rpc.RpcServiceImpl;
import org.gonevertical.project.server.servlets.HomeServlet;

import com.google.inject.servlet.ServletModule;

/**
 * This starts in web.xml. All requests are intercepted and sent here.
 */
public class ServletPathModule extends ServletModule {
  
  @Override
  public void configureServlets() {
    serve("/rpcService*").with(RpcServiceImpl.class);
    // ignore _ah
    serveRegex("^/(?!_ah.*)").with(HomeServlet.class);
  }
  
}
package org.gonevertical.project.server.guice;

import org.gonevertical.project.server.servlets.HomeServlet;
import org.gonevertical.project.server.servlets.rpc.RpcServiceImpl;

import com.google.inject.servlet.ServletModule;

/**
 * This starts in web.xml. All requests are intercepted and sent here.
 */
public class ServletPathModule extends ServletModule {
  
  @Override
  public void configureServlets() {
    // rpc servlet
    serve("/project/rpcService*").with(RpcServiceImpl.class);
    // ignore _ah
    serveRegex("^/(?!_ah.*)").with(HomeServlet.class);
  }
  
}
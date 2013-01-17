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
    // GWT RPC request path
    serve("/project/rpcService*").with(RpcServiceImpl.class);
    
    // ignore _ah which is google built in servlets base path
    serveRegex("^/(?!_ah.*)").with(HomeServlet.class);
  }
  
}

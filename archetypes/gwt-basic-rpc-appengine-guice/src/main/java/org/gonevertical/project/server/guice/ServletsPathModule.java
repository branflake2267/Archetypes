package org.gonevertical.project.server.guice;

import org.gonevertical.project.server.servlets.HomeServlet;
import org.gonevertical.project.server.servlets.rpc.RpcServiceImpl;

import com.google.inject.servlet.ServletModule;

/**
 * This starts in web.xml. All requests are intercepted and sent here.
 * 
 * http://localhost:8888/_ah/admin - Goto the App Engine Dashboard.
 */
public class ServletsPathModule extends ServletModule {

  @Override
  public void configureServlets() {
    // GWT RPC requests path
    serve("/project/rpcService*").with(RpcServiceImpl.class);

    // ignore _ah (http://localhost:8888/_ah/*)
    serveRegex("^/(?!_ah.*)home").with(HomeServlet.class);
  }

}

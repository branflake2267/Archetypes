#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package org.gonevertical.project.server.guice;

import org.gonevertical.project.server.rpc.RpcServiceImpl;
import org.gonevertical.project.server.servlets.SomethingServlet;

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
    serve("/${module}/rpcService*").with(RpcServiceImpl.class);

    // ignore _ah (http://localhost:8888/_ah/*)
    serveRegex("^/(?!_ah.*)something").with(SomethingServlet.class);
  }

}

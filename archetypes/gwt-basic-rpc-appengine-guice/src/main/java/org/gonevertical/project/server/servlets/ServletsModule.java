package org.gonevertical.project.server.servlets;

import org.gonevertical.project.server.rpc.RpcServiceImpl;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * Bind servlets in singleton here
 */
public class ServletsModule extends AbstractModule {
  
  @Override
  protected void configure() {
    bind(RpcServiceImpl.class).in(Singleton.class);
    bind(SomethingServlet.class).in(Singleton.class);
  }
  
}

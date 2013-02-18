package org.gonevertical.server.servlets;


import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * bind servlets in singleton here
 */
public class ServletsModule extends AbstractModule {
  
  @Override
  protected void configure() {
    bind(HomeServlet.class).in(Singleton.class);
  }
  
}

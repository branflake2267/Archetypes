package org.gonevertical.project.server.guice;

import org.gonevertical.project.server.servlets.ServletsModule;

import com.google.inject.AbstractModule;

/**
 * Main Server Guice Module
 */
public class ServerModule extends AbstractModule {
  
  @Override
  protected void configure() {
    install(new ServletsPathModule());
    install(new ServletsModule());
  }
  
}

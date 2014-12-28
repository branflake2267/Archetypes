package org.gonevertical.project.server.guice;

import org.gonevertical.project.server.dao.DaoModule;
import org.gonevertical.project.server.servlets.ServletsModule;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;

/**
 * Main Server Guice Module
 */
public class ServerModule extends AbstractModule {
  
  @Override
  protected void configure() {
    install(new JpaPersistModule("transactions-optional"));
    bind(JpaInitilization.class).asEagerSingleton();
    
    install(new ServletPathModule());
    install(new ServletsModule());
    install(new DaoModule());
  }
  
}

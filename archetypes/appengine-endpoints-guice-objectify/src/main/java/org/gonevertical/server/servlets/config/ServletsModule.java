package org.gonevertical.server.servlets.config;

import org.gonevertical.server.servlets.HomeServlet;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * bind servlets in singleton here
 */
public class ServletsModule extends AbstractModule {

  /**
   * Bind the servlets to be used in the application here.
   */
  @Override
  protected void configure() {
    bind(HomeServlet.class).in(Singleton.class);
  }

}

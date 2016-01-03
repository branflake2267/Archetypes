package org.gonevertical.server.endpoints.config;

import org.gonevertical.server.endpoints.SystemUserEndpoint;
import org.gonevertical.server.endpoints.TodoEndpoint;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * bind DAOs here
 */
public class RegisterEndpointsModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(SystemUserEndpoint.class).in(Singleton.class);
    bind(TodoEndpoint.class).in(Singleton.class);
  }

}

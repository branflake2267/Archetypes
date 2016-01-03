package org.gonevertical.server.guice;

import org.gonevertical.server.endpoints.config.ServletsPathForEndpointClassesModule;
import org.gonevertical.server.endpoints.config.EndpointsModule;
import org.gonevertical.server.entities.config.RegisterObjectifyDataModule;
import org.gonevertical.server.servlets.config.ServletsModule;
import org.gonevertical.server.servlets.config.ServletsPathModule;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/**
 * Main Server Guice Module
 */
public class ServerModule extends AbstractModule {

  @Override
  protected void configure() {
    install(new ServletsPathForEndpointClassesModule());
    install(new ServletsPathModule());
    install(new ServletsModule());
    install(new EndpointsModule());
    install(new RegisterObjectifyDataModule());
  }

  @Provides
  @Singleton
  UserService provideUserService() {
    return UserServiceFactory.getUserService();
  }

  @Provides
  User provideGoogleSysUser() {
    return provideUserService().getCurrentUser();
  }

}

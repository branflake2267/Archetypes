package org.gonevertical.server.guice;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.gonevertical.server.endpoints.EndpointsModule;
import org.gonevertical.server.servlets.ServletsModule;

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
    install(new EndpointClassesModule());
    install(new ServletsPathModule());
    install(new ServletsModule());
    install(new EndpointsModule());
  }

  @Provides
  @Singleton
  PersistenceManagerFactory providePersistenceManager() {
    return JDOHelper.getPersistenceManagerFactory("transactions-optional");
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

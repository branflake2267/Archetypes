#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.guice;

import ${package}.endpoints.config.ServletsPathForEndpointClassesModule;
import ${package}.endpoints.config.RegisterEndpointsModule;
import ${package}.entities.config.RegisterObjectifyDataModule;
import ${package}.servlets.config.RegisterServletsModule;
import ${package}.servlets.config.ServletsPathModule;

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
    install(new RegisterServletsModule());
    install(new RegisterEndpointsModule());
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

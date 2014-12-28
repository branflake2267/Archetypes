#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.guice;

import ${package}.endpoints.EndpointsModule;
import ${package}.servlets.ServletsModule;

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

    install(new EndpointClassesModule());
    install(new ServletsPathModule());
    install(new ServletsModule());
    install(new EndpointsModule());
  }

}

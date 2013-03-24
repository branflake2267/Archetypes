#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.server.guice;

import ${package}.server.servlets.ServletsModule;

import com.google.inject.AbstractModule;

/**
 * Main Server Guice Module
 */
public class ServerModule extends AbstractModule {
  
  @Override
  protected void configure() {
    install(new ServletPathModule());
    install(new ServletsModule());
  }
  
}

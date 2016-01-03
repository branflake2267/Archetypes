#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.servlets.config;

import ${package}.servlets.HomeServlet;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * bind servlets in singleton here
 */
public class RegisterServletsModule extends AbstractModule {

  /**
   * Bind the servlets to be used in the application here.
   */
  @Override
  protected void configure() {
    bind(HomeServlet.class).in(Singleton.class);
  }

}

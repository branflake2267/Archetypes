#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.endpoints;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * bind DAOs here
 */
public class EndpointsModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(SystemUserEndpoint.class).in(Singleton.class);
    bind(TodoEndpoint.class).in(Singleton.class);
  }

}

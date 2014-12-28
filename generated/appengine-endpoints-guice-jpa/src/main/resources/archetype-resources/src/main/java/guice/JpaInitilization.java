#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.guice;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

public class JpaInitilization {

  @Inject
  public JpaInitilization(PersistService service) {
    service.start();
  }

}

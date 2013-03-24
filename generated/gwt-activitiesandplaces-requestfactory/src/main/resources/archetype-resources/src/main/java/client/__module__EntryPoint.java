#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ${module}EntryPoint implements EntryPoint {
  
  /**
   * This loads the application...
   */
  private ClientFactory clientFactory = GWT.create(ClientFactory.class);
  
  @Override
  public void onModuleLoad() {
    // The client factory takes care of the places logic
  }
  
}

#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.application;

import ${package}.client.ClientFactory;

import com.google.gwt.place.shared.Place;

public interface Presenter {
  
  /**
   * Navigate to a new Place in the browser
   */
  void goTo(Place place);
  
  /**
   * Setting the activity as running, then if navigation event occurs a warning dialog will occur
   */
  void setRunning(boolean running);
  
  ClientFactory getClientFactory();
  
}

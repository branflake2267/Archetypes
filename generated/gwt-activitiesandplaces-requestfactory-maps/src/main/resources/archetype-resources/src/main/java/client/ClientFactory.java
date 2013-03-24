#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client;

import ${package}.client.application.home.HomePlace;
import ${package}.client.application.login.LoginPlace;
import ${package}.client.application.map.MapPlace;
import ${package}.client.requestfactory.ApplicationRequestFactory;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

public interface ClientFactory {
  
	EventBus getEventBus();
	
	ApplicationRequestFactory getRequestFactory();
	
	PlaceController getPlaceController();

  ActivityManager getActivityManager();
  
  void track();
  
  HomePlace.Tokenizer getHomeTokenizer();
  
  LoginPlace.Tokenizer getLogInTokenizer();

  MapPlace.Tokenizer getMapTokenizer();
  
}

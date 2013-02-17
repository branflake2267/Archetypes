package org.gonevertical.project.client;

import org.gonevertical.project.client.application.home.HomePlace;
import org.gonevertical.project.client.application.login.LoginPlace;
import org.gonevertical.project.client.application.map.MapPlace;
import org.gonevertical.project.client.requestfactory.ApplicationRequestFactory;

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

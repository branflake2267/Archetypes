package org.gonevertical.project.client.activity;

import org.gonevertical.project.client.ClientFactory;
import org.gonevertical.project.client.application.home.HomeActivity;
import org.gonevertical.project.client.application.home.HomePlace;
import org.gonevertical.project.client.application.login.LoginActivity;
import org.gonevertical.project.client.application.login.LoginPlace;
import org.gonevertical.project.client.application.map.MapActivity;
import org.gonevertical.project.client.application.map.MapPlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class ApplicationActivityMapper implements ActivityMapper {
  
  private ClientFactory clientFactory;

  /**
   * AppActivityMapper associates each Place with its corresponding
   * {@link Activity}
   * 
   * @param clientFactory Factory to be passed to activities
   * @param walleteditview 
   * @param walletlistview 
   * @param signinview 
   */
  public ApplicationActivityMapper(ClientFactory clientFactory) {
    super();
    
    this.clientFactory = clientFactory;
  }
  
  /**
   * Map each Place to its corresponding Activity. 
   */
  @Override
  public Activity getActivity(Place place) {
    Activity activity = null;
    
    if (place instanceof HomePlace) {
      activity = new HomeActivity((HomePlace) place, clientFactory);
      
    } else if (place instanceof LoginPlace) {
      activity = new LoginActivity((LoginPlace) place, clientFactory);
      
    } else if (place instanceof MapPlace) {
      activity = new MapActivity((MapPlace) place, clientFactory);
    }
    
    clientFactory.track();
    
    return activity;
  }

}

package org.gonevertical.project.client.application.home;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.web.bindery.requestfactory.shared.RequestFactory;

public class HomePlace extends Place {
  
  @Prefix("home")
  public static class Tokenizer implements PlaceTokenizer<HomePlace> {

    private RequestFactory requestFactory;

    public Tokenizer(RequestFactory requestFactory) {
      this.requestFactory = requestFactory;
    }
    
    @Override
    public String getToken(HomePlace place) {
      String token = place.getToken();
      return token;
    }

    @Override
    public HomePlace getPlace(String token) {
      return new HomePlace(token);
    }

  }
  
  private String token;

  public HomePlace(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }
  
}

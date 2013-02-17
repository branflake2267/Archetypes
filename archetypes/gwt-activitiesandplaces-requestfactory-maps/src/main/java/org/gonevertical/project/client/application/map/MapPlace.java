package org.gonevertical.project.client.application.map;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.web.bindery.requestfactory.shared.RequestFactory;

public class MapPlace extends Place {
  
  /** 
   * I'm not really using the tokenizer here, but good for example
   */
  @Prefix("map")
  public static class Tokenizer implements PlaceTokenizer<MapPlace> {

    private RequestFactory requestFactory;

    public Tokenizer(RequestFactory requestFactory) {
      this.requestFactory = requestFactory;
    }
    
    @Override
    public String getToken(MapPlace place) {
      String token = place.getToken();
      return token;
    }

    @Override
    public MapPlace getPlace(String token) {
      return new MapPlace(token);
    }

  }
  
  private String token;

  public MapPlace(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }
  
}

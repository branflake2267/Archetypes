package org.gonevertical.project.client.application.login;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.web.bindery.requestfactory.shared.RequestFactory;

public class LoginPlace extends Place {
  
  /** 
   * I'm not really using the tokenizer here, but good for example
   */
  @Prefix("login")
  public static class Tokenizer implements PlaceTokenizer<LoginPlace> {

    private RequestFactory requestFactory;

    public Tokenizer(RequestFactory requestFactory) {
      this.requestFactory = requestFactory;
    }
    
    @Override
    public String getToken(LoginPlace place) {
      String token = place.getToken();
      return token;
    }

    @Override
    public LoginPlace getPlace(String token) {
      return new LoginPlace(token);
    }

  }
  
  private String token;

  public LoginPlace(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }
  
}

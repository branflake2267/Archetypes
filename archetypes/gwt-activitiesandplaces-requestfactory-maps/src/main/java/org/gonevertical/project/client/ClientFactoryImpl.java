package org.gonevertical.project.client;

import org.gonevertical.project.client.activity.ApplicationActivityMapper;
import org.gonevertical.project.client.activity.ApplicationPlaceHistoryMapper;
import org.gonevertical.project.client.application.MainLayout;
import org.gonevertical.project.client.application.home.HomePlace;
import org.gonevertical.project.client.application.login.LoginPlace;
import org.gonevertical.project.client.application.map.MapPlace;
import org.gonevertical.project.client.requestfactory.ApplicationRequestFactory;
import org.gonevertical.project.client.resources.Resources;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;

public class ClientFactoryImpl implements ClientFactory {

  /**
   * shell for the app - includes LoginWidget - processes the credentials
   */
  private MainLayout mainLayout = new MainLayout();

  /**
   * default start point in application - if nothing is in url
   */
  private Place defaultPlace = new HomePlace("");

  /**
   * for app's global events
   */
  private static final EventBus eventBus = new SimpleEventBus();

  /**
   * for datastore persistence calls
   */
  private final ApplicationRequestFactory requestFactory = GWT.create(ApplicationRequestFactory.class);

  /**
   * place's directory
   */
  private final ActivityMapper activityMapper = new ApplicationActivityMapper(this);

  /**
   * place's director (internal flagger|flagman for places)
   */
  private final ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);

  /**
   * URL tokenizer directory
   */
  private final ApplicationPlaceHistoryMapper historyMapper = GWT.create(ApplicationPlaceHistoryMapper.class);

  /**
   * URL tokenizer's director (URL event flagger|flagman)
   */
  private final PlaceHistoryHandler placeHistoryHandler;

  /**
   * app place's director (internal flagger|flagman) (not deprecated if your seeing it)
   */
  private final PlaceController placeController = new PlaceController(eventBus);

  public ClientFactoryImpl() {
    Resources.INSTANCE.styles().ensureInjected();
    
    initGoogleAnalytics();

    RootPanel.get().add(mainLayout);

    activityManager.setDisplay(mainLayout.getContentPanel());

    requestFactory.initialize(eventBus);

    // tell the historyMapper there are tokenizers (below) to use in here. 
    historyMapper.setFactory(this);

    placeHistoryHandler = new PlaceHistoryHandler(historyMapper);
    placeHistoryHandler.register(placeController, eventBus, defaultPlace);

    // the loginWidget needs all the stuff setup to fetch userData - inits credentials process
    mainLayout.setClientFactory(this);

    // Goes to the place represented on URL else default place
    placeHistoryHandler.handleCurrentHistory();
  }

  @Override
  public EventBus getEventBus() {
    return eventBus;
  }

  @Override
  public ApplicationRequestFactory getRequestFactory() {
    return requestFactory;
  }

  @Override
  public PlaceController getPlaceController() {
    return placeController;
  }

  @Override
  public ActivityManager getActivityManager() {
    return activityManager;
  }

  private void initGoogleAnalytics() {
    setGaVars();
    
    Document doc = Document.get();
    ScriptElement script = doc.createScriptElement();
    script.setSrc("https://ssl.google-analytics.com/ga.js");
    script.setType("text/javascript");
    script.setLang("javascript");
    doc.getBody().appendChild(script);
  }
  
  private static native void setGaVars() /*-{
    $wnd._gaq = $wnd._gaq || [];
    $wnd._gaq.push(['_setAccount', 'UA-xxxxx-xx']);
  }-*/;

  public void track() {
    pushEvent();
  }

  private native void pushEvent() /*-{
    try {
      $wnd._gaq.push(['_trackPageview']);
    } catch (e) {
    }
  }-*/;

  /***
   * Used by the HistoryMapper { @link ApplicationPlaceHistoryMapper }
   * Picked up by the historyMapper when needed.
   * this way, I can send in objects like the requestFactory into them and use it
   *  otherwise, the HistoryMapper runs decoupled with this here we can push objects into it.
   */
  @Override
  public HomePlace.Tokenizer getHomeTokenizer() {
    return new HomePlace.Tokenizer(requestFactory);
  }
  
  @Override
  public LoginPlace.Tokenizer getLogInTokenizer() {
    return new LoginPlace.Tokenizer(requestFactory);
  }
  
  @Override
  public MapPlace.Tokenizer getMapTokenizer() {
    return new MapPlace.Tokenizer(requestFactory);
  }


}

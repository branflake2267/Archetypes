package org.gonevertical.project.client.application.login;

import org.gonevertical.project.client.ClientFactory;
import org.gonevertical.project.client.requestfactory.proxy.SystemUserProxy;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.Receiver;

public class LoginActivity extends AbstractActivity implements LoginPresenter {

  private LoginView view;
  private ClientFactory clientFactory;
  private boolean running;

  public LoginActivity(LoginPlace place, ClientFactory clientFactory) {
    this.clientFactory = clientFactory;
  }

  /**
   * Invoked by the ActivityManager to start a new Activity
   */
  @Override
  public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
    if (view == null) {
      view = new LoginView();
    }

    view.setPresenter(this);
    containerWidget.setWidget(view.asWidget());
    view.start();
    
    // For example...
    findSystemUserRfTest();
  }

  @Override
  public String mayStop() {
    String s = null;
    if (running == true) {
      s = "Please hold on. This activity is stopping.";
    }
    return s;
  }

  @Override
  public void goTo(Place place) {
    clientFactory.getPlaceController().goTo(place);
  }

  @Override
  public void setRunning(boolean running) {
    this.running = running;
  }

  @Override
  public ClientFactory getClientFactory() {
    return clientFactory;
  }

  private void findSystemUserRfTest() {
    Long id = 1l;
    clientFactory.getRequestFactory().getSystemUserRequest().findSystemUser(id).fire(new Receiver<SystemUserProxy>() {
      @Override
      public void onSuccess(SystemUserProxy response) {
        view.displaySystemUser(response);
      }
    });
  }

}

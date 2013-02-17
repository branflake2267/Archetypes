 package org.gonevertical.project.client.application.login;

import org.gonevertical.project.client.application.Presenter;
import org.gonevertical.project.client.requestfactory.proxy.SystemUserProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SimpleHtmlSanitizer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class LoginView extends Composite {

  private static Binder uiBinder = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, LoginView> {}
  
  @UiField
  HTML name;

  private Presenter presenter;

  public LoginView() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }
  
  public void start() {
    
  }

  public void displaySystemUser(SystemUserProxy response) {
    String name = response.getGoogleNickname();
    this.name.setHTML(SimpleHtmlSanitizer.sanitizeHtml(name));
  }

}

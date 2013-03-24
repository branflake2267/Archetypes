#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
 package ${package}.client.application.login;

import ${package}.client.application.Presenter;
import ${package}.client.requestfactory.proxy.SystemUserProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SimpleHtmlSanitizer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class LoginView extends Composite {

  private static SignInViewImplUiBinder uiBinder = GWT.create(SignInViewImplUiBinder.class);

  interface SignInViewImplUiBinder extends UiBinder<Widget, LoginView> {}
  
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

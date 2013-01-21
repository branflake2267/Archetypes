 package org.gonevertical.project.client.application.home;

import org.gonevertical.project.client.application.Presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class HomeView extends Composite {

  private static SignInViewImplUiBinder uiBinder = GWT.create(SignInViewImplUiBinder.class);

  interface SignInViewImplUiBinder extends UiBinder<Widget, HomeView> {}

  private Presenter presenter;

  public HomeView() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }
  
  public void start() {
   
  }

}

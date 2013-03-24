#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
 package ${package}.client.application.home;

import ${package}.client.application.Presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class HomeView extends Composite {

  private static Binder uiBinder = GWT.create(Binder.class);

  interface Binder extends UiBinder<Widget, HomeView> {}

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

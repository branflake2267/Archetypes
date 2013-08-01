#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.application.west;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class WestModule extends AbstractPresenterModule {
  @Override
  protected void configure() {
    bindPresenter(WestPresenter.class, WestPresenter.MyView.class, WestView.class, WestPresenter.MyProxy.class);
  }
}

#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.application.east;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class EastModule extends AbstractPresenterModule {
  @Override
  protected void configure() {
    bindPresenter(EastPresenter.class, EastPresenter.MyView.class, EastView.class, EastPresenter.MyProxy.class);
  }
}

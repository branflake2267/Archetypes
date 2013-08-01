#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.application.home;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class HomeModule extends AbstractPresenterModule {
  @Override
  protected void configure() {
    bindPresenter(HomePagePresenter.class, HomePagePresenter.MyView.class, HomePageView.class,
        HomePagePresenter.MyProxy.class);
  }
}

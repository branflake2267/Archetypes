#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.application.links;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class LinksModule extends AbstractPresenterModule {
  @Override
  protected void configure() {
    bindPresenter(LinksPresenter.class, LinksPresenter.MyView.class, LinksView.class, LinksPresenter.MyProxy.class);
  }
}

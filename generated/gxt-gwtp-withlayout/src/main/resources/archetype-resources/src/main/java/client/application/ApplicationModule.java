#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.application;

import ${package}.client.application.home.HomeModule;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import ${package}.client.application.error.ErrorModule;
import ${package}.client.application.north.NorthModule;
import ${package}.client.application.east.EastModule;
import ${package}.client.application.west.WestModule;
import ${package}.client.application.links.LinksModule;

public class ApplicationModule extends AbstractPresenterModule {
  @Override
  protected void configure() {
    install(new LinksModule());
    install(new WestModule());
    install(new EastModule());
    install(new NorthModule());
    install(new ErrorModule());
    install(new HomeModule());

    bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
        ApplicationPresenter.MyProxy.class);
  }
}

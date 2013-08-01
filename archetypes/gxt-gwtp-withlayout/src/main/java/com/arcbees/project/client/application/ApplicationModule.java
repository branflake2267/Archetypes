package com.arcbees.project.client.application;

import com.arcbees.project.client.application.home.HomeModule;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.arcbees.project.client.application.error.ErrorModule;
import com.arcbees.project.client.application.north.NorthModule;
import com.arcbees.project.client.application.east.EastModule;
import com.arcbees.project.client.application.west.WestModule;

public class ApplicationModule extends AbstractPresenterModule {
  @Override
  protected void configure() {
    install(new WestModule());
    install(new EastModule());
    install(new NorthModule());
    install(new ErrorModule());
    install(new HomeModule());

    bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
        ApplicationPresenter.MyProxy.class);
  }
}

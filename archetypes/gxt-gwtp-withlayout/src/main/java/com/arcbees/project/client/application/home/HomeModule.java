package com.arcbees.project.client.application.home;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class HomeModule extends AbstractPresenterModule {
  @Override
  protected void configure() {
    bindPresenter(HomePagePresenter.class, HomePagePresenter.MyView.class, HomePageView.class,
        HomePagePresenter.MyProxy.class);
  }
}

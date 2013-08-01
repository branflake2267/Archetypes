package com.arcbees.project.client.application.east;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class EastModule extends AbstractPresenterModule {
  @Override
  protected void configure() {
    bindPresenter(EastPresenter.class, EastPresenter.MyView.class, EastView.class, EastPresenter.MyProxy.class);
  }
}

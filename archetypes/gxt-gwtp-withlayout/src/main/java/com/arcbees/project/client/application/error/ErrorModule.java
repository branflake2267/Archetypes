package com.arcbees.project.client.application.error;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ErrorModule extends AbstractPresenterModule {
  @Override
  protected void configure() {
    bindPresenter(ErrorPresenter.class, ErrorPresenter.MyView.class, ErrorView.class, ErrorPresenter.MyProxy.class);
  }
}

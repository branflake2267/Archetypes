package com.arcbees.project.client.application.error;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class ErrorView extends ViewImpl implements ErrorPresenter.MyView {
  interface Binder extends UiBinder<Widget, ErrorView> {
  }

  @UiField
  SimplePanel main;

  @Inject
  ErrorView(Binder uiBinder) {
    initWidget(uiBinder.createAndBindUi(this));
  }

  @Override
  public void setInSlot(Object slot, IsWidget content) {
    if (slot == ErrorPresenter.SLOT_Error) {
      main.setWidget(content);
    } else {
      super.setInSlot(slot, content);
    }
  }
}

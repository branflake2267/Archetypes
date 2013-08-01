package com.arcbees.project.client.application.north;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class NorthView extends ViewImpl implements NorthPresenter.MyView {
  interface Binder extends UiBinder<Widget, NorthView> {
  }

  @UiField
  SimplePanel main;

  @Inject
  NorthView(Binder uiBinder) {
    initWidget(uiBinder.createAndBindUi(this));
  }

  @Override
  public void setInSlot(Object slot, IsWidget content) {
    if (slot == NorthPresenter.SLOT_North) {
      main.setWidget(content);
    } else {
      super.setInSlot(slot, content);
    }
  }
}

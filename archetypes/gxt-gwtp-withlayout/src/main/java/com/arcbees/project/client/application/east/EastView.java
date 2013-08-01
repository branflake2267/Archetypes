package com.arcbees.project.client.application.east;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;

public class EastView extends ViewImpl implements EastPresenter.MyView {
  interface Binder extends UiBinder<Widget, EastView> {
  }

  @UiField
  SimpleContainer main;

  @Inject
  EastView(Binder uiBinder) {
    initWidget(uiBinder.createAndBindUi(this));
    
    main.setWidget(new HTML("East Presenter"));
  }

  @Override
  public void setInSlot(Object slot, IsWidget content) {
    if (slot == EastPresenter.SLOT_East) {
      main.setWidget(content);
    } else {
      super.setInSlot(slot, content);
    }
  }
}

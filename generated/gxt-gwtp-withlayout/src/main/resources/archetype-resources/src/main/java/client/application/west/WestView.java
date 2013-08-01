#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.application.west;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;

public class WestView extends ViewImpl implements WestPresenter.MyView {
  interface Binder extends UiBinder<Widget, WestView> {
  }

  @UiField
  SimpleContainer main;

  @Inject
  WestView(Binder uiBinder) {
    initWidget(uiBinder.createAndBindUi(this));
    
    main.setWidget(new HTML("West Presenter"));
  }

  @Override
  public void setInSlot(Object slot, IsWidget content) {
    if (slot == WestPresenter.SLOT_West) {
      main.setWidget(content);
    } else {
      super.setInSlot(slot, content);
    }
  }
}

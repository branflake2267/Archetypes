#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.application.north;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;

public class NorthView extends ViewImpl implements NorthPresenter.MyView {
  interface Binder extends UiBinder<Widget, NorthView> {
  }

  @UiField
  SimpleContainer main;

  @Inject
  NorthView(Binder uiBinder) {
    initWidget(uiBinder.createAndBindUi(this));
    
    main.setWidget(new HTML("North Presenter"));
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

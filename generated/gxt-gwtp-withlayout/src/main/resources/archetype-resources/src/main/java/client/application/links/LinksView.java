#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.application.links;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class LinksView extends ViewImpl implements LinksPresenter.MyView {
  interface Binder extends UiBinder<Widget, LinksView> {
  }
  
  @Inject
  LinksView(Binder uiBinder) {
    initWidget(uiBinder.createAndBindUi(this));
  }

}

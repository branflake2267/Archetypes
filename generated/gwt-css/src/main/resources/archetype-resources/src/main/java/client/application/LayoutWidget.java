#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.application;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class LayoutWidget extends Composite {

  private static LayoutWidgetUiBinder uiBinder = GWT.create(LayoutWidgetUiBinder.class);

  interface LayoutWidgetUiBinder extends UiBinder<Widget, LayoutWidget> {
  }

  public LayoutWidget() {
    initWidget(uiBinder.createAndBindUi(this));
  }

}

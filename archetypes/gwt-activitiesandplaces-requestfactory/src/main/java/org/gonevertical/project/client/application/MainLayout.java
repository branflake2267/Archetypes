package org.gonevertical.project.client.application;

import org.gonevertical.project.client.ClientFactory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class MainLayout extends Composite {
  private static LayoutUiBinder uiBinder = GWT.create(LayoutUiBinder.class);
  
  interface LayoutUiBinder extends UiBinder<Widget, MainLayout> {}
  @UiField
  FlowPanel header;
  @UiField
  SimplePanel contentPanel;
  @UiField
  FlowPanel footer;
  
  private ClientFactory clientFactory;

  public MainLayout() {
    initWidget(uiBinder.createAndBindUi(this));
    
    header.add(new HTML("Header"));
    footer.add(new HTML("Footer"));
  }
  
  public void setClientFactory(ClientFactory clientFactory) {
    this.clientFactory = clientFactory;
  }

  public AcceptsOneWidget getContentPanel() {
    return contentPanel;
  }

}

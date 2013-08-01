package com.arcbees.project.client.application.home;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.sencha.gxt.core.client.GXT;

/**
 * See more on GXT <a href="http://docs.sencha.com/gxt-guides/3/ui/layout/LayoutContainers.html">Layout Containers</a>
 */
public class HomePageView extends ViewImpl implements HomePagePresenter.MyView {
  public interface Binder extends UiBinder<Widget, HomePageView> {
  }

  @UiField
  Label version;
  
  @Inject
  public HomePageView(Binder uiBinder) {
    initWidget(uiBinder.createAndBindUi(this));
    
    version.setText("GXT Version: " + GXT.getVersion().getRelease());
  }
}

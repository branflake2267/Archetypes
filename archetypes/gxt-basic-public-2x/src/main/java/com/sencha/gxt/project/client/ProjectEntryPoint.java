package com.sencha.gxt.project.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.widget.client.TextButton;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ProjectEntryPoint implements EntryPoint {

  @Override
  public void onModuleLoad() {
    TextButton textButton = new TextButton("Verify GXT Works");
    RootPanel.get().add(textButton);
  }
  
}

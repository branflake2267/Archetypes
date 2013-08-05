package com.sencha.gxt.project.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.GXT;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ProjectEntryPoint implements EntryPoint {

  @Override
  public void onModuleLoad() {
    String version = GXT.getVersion().getRelease();
    TextButton textButton = new TextButton("Verify GXT Works: Version=" + version);
    RootPanel.get().add(textButton);
    textButton.addSelectHandler(new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        MessageBox messageBox = new MessageBox("GXT Works.");
        messageBox.show();
      }
    });
  }
  
}

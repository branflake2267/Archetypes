package org.gonevertical.project.client;

import org.gonevertical.project.client.requestfactory.ApplicationRequestFactory;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ProjectEntryPoint implements EntryPoint {
  
  private final ApplicationRequestFactory requestFactory = GWT.create(ApplicationRequestFactory.class);
  
  @Override
  public void onModuleLoad() {
    initializeRequestFactory();
    
    RootPanel.get().add(new HTML("Applicaiton has loaded"));
  }

  private void initializeRequestFactory() {
    EventBus eventBus = new SimpleEventBus(); 
    requestFactory.initialize(eventBus);
  }
  
}

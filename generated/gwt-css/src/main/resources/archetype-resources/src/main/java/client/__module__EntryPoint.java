#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client;

import ${package}.client.application.LayoutWidget;
import ${package}.client.resources.Resources;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ${module}EntryPoint implements EntryPoint {
  
  @Override
  public void onModuleLoad() {
    Resources.INSTANCE.layoutStyles().ensureInjected();
    
    RootPanel.get().add(new HTML("GWT App has loaded.<br/><br/>"));
    
    RootPanel.get().add(new LayoutWidget());
  }
  
}

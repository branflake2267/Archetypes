#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.widget.client.TextButton;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ${module}EntryPoint implements EntryPoint {

  @Override
  public void onModuleLoad() {
    TextButton textButton = new TextButton("Verify GXT Works");
    RootPanel.get().add(textButton);
  }
  
}

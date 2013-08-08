#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client;

import com.google.gwt.junit.DoNotRunWith;
import com.google.gwt.junit.Platform;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.Viewport;

public class SeleniumUnitIdeGwtTest extends GWTTestCase { 
  @Override
  public String getModuleName() {
    return "${package}.${module}";
  }
  
  public void testWorks() {
    assertTrue(true);
  }

  /**
   * To Debug:
   * 1. Right click and debug once
   * 2. Then goto the debug configurations for this test
   * 3. Then select the tab 'Arguments' in the debug configuration for this test
   * 4. Then add -Dgwt.args="-runStyle Manual:1" to 'VM Arguments'
   * 5. Then select debug or rerun the debug for this test
   * 6. Copy debug url from console to FireFox.
   * Optional: Keep the test running by goign to 'Test' tab and select Keep JUnit running after test run when debugging 
   */
  @DoNotRunWith({Platform.HtmlUnitLayout})
  public void testLayout() {
    Viewport vp = new Viewport();
    RootPanel.get().add(vp);
    
    ContentPanel contentPanel = new ContentPanel();
    contentPanel.setBorders(true);
    contentPanel.setHeadingText("Title");
    contentPanel.add(new HTML("works"));
    contentPanel.setPixelSize(400, 400);
    
    vp.add(contentPanel);
    
    assertTrue(contentPanel.isVisible());
  }
}

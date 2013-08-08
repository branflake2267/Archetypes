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

public class RemoteWebUnitGwtTest extends GWTTestCase {
  @Override
  public String getModuleName() {
    return "${package}.${module}";
  }

  public void testWorks() {
    assertTrue(true);
  }

  /**
   * To Debug: 
   * 1. Debug RemoteWeb server on a windows computer
   * 2. Debug this test once so the debug configuration is created
   * 3. Edit this debug configuration and add to the VM arguments -Dgwt.args="-runStyle RemoteWeb:rmi://10.211.55.3/ie"
   * 4. Debug this test again
   */
  @DoNotRunWith({ Platform.HtmlUnitLayout })
  public void testRemoteWeb() {
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

package com.arcbees.project.client;

import com.google.gwt.junit.DoNotRunWith;
import com.google.gwt.junit.Platform;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.Viewport;

public class SeleniumGwtTest extends GWTTestCase {
  @Override
  public String getModuleName() {
    return "com.arcbees.project.Project";
  }

  public void testWorks() {
    assertTrue(true);
  }

  /**
   * To Debug: 1. First run this once 2. Then edit this 'Debug Configuration' 3.
   * Then add -Dgwt.args="-runStyle Selenium:0.0.0:4444/*firefox" to the 'VM
   * Arguments' 4. Then run
   * {@link RunLocalHostSeleniumServer#testRunSeleniumServer()} 5. Then run this
   * again and it will use the local selenium server
   */
  @DoNotRunWith({ Platform.HtmlUnitLayout })
  public void testSeleniumServer() {
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

package org.gonevertical.project.client;

import com.google.gwt.junit.client.GWTTestCase;

public class CompileGwtTest extends GWTTestCase {
  
  @Override
  public String getModuleName() {
    return "org.gonevertical.project.Project";
  }

  public void testSandbox() {
    assertTrue(true);
  }
  
}

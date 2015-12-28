package org.gonevertical.project.client;

import com.google.gwt.junit.client.GWTTestCase;

public class SandboxGwtTest extends GWTTestCase {
  
  @Override
  public String getModuleName() {
    return "org.gonevertical.project.module";
  }

  public void testSandbox() {
    assertTrue(true);
  }
  
}

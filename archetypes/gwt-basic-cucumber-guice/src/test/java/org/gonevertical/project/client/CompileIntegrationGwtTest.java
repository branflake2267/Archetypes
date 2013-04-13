package org.gonevertical.project.client;

import com.google.gwt.junit.client.GWTTestCase;

public class CompileIntegrationGwtTest extends GWTTestCase {
  
  @Override
  public String getModuleName() {
    return "org.gonevertical.project.IntegrationTesting";
  }

  public void testSandbox() {
    assertTrue(true);
  }
  
}

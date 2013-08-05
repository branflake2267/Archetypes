package com.sencha.gxt.project.client;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.google.gwt.junit.tools.GWTTestSuite;

public class RunAllGwtTestSuite extends GWTTestSuite {
  
  public static Test suite() {
    TestSuite suite = new TestSuite("Application Tests");
    suite.addTestSuite(CompileGwtTest.class);
    
    return suite;
  }
  
}

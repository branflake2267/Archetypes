#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.google.gwt.junit.tools.GWTTestSuite;

public class RunAllGwtTestSuite extends GWTTestSuite { 
  public static Test suite() {
    TestSuite suite = new TestSuite("Application Tests");
    suite.addTestSuite(SimpleUnitTestGwtTest.class);
    suite.addTestSuite(SeleniumGwtTest.class);
    suite.addTestSuite(ManualGwtTest.class);
    // Add more tests here
    
    return suite;
  }
}

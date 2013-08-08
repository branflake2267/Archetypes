#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client;

import com.google.gwt.junit.client.GWTTestCase;

public class HtmlUnitTestGwtTest extends GWTTestCase {
  @Override
  public String getModuleName() {
    return "${package}.${module}";
  }

  public void testWorks() {
    assertTrue(true);
  }
}

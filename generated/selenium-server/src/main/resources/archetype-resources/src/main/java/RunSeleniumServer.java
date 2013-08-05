#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

public class RunSeleniumServer {

  private static SeleniumServer seleniumServer;

  public static void main(String[] args) {
    try {
      new RunSeleniumServer().run();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void run() throws Exception {
    RemoteControlConfiguration rc = new RemoteControlConfiguration();
    rc.setAvoidProxy(true);
    rc.setSingleWindow(true);
    rc.setReuseBrowserSessions(true);
  
    seleniumServer = new SeleniumServer(rc);
    seleniumServer.start(); 
  }

}

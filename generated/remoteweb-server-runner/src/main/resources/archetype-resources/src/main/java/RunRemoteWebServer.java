#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import com.google.gwt.junit.remote.BrowserManagerServer;

public class RunRemoteWebServer {
  public static void main(String[] args) {
    try {
      new RunRemoteWebServer().run();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void run() throws Exception {
    String[] args = new String[2];
    args[0] = "ie";
    args[1] = "C:${symbol_escape}${symbol_escape}Program Files${symbol_escape}${symbol_escape}Internet Explorer${symbol_escape}${symbol_escape}iexplore.exe";
    
    BrowserManagerServer.main(args);
  }
}

package com.sencha.gxt.remotewebserver;

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
    args[1] = "C:\\Program Files\\Internet Explorer\\iexplore.exe";
    
    BrowserManagerServer.main(args);
  }
}

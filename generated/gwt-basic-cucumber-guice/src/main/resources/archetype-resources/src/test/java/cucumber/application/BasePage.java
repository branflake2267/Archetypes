#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.cucumber.application;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
  
  private static final long TIME_OUT_IN_SECONDS = 20;
  protected final WebDriver webDriver;

  protected BasePage(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public WebDriverWait webDriverWait() {
    return new WebDriverWait(webDriver, TIME_OUT_IN_SECONDS);
  }
  
  public void getUrl(String url) {
    webDriver.get(url);
  }
  
  public void close() {
    webDriver.close();
    webDriver.quit();
  }
  
}

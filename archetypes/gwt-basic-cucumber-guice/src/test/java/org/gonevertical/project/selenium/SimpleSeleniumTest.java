package org.gonevertical.project.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SimpleSeleniumTest {

  @Test
  public void simpleFireFoxDriverTest() {
    // Create a new instance of the Firefox driver
    // Notice that the remainder of the code relies on the interface, 
    // not the implementation.
    WebDriver driver = new FirefoxDriver();

    runBasicTest(driver);
  }
  
  @Test
  public void simpleChromeDriverTest() {
    // Create a new instance of the Firefox driver
    // Notice that the remainder of the code relies on the interface, 
    // not the implementation.
    WebDriver driver = new ChromeDriver();

    runBasicTest(driver);
  }
  
  private void runBasicTest(WebDriver driver) {
    // Find the text input element by its name
    WebElement element = driver.findElement(By.name("q"));

    // Enter something to search for
    element.sendKeys("Cheese!");

    // Now submit the form. WebDriver will find the form for us from the element
    element.submit();

    // Check the title of the page
    System.out.println("Page title is: " + driver.getTitle());
    
    // Google's search is rendered dynamically with JavaScript.
    // Wait for the page to load, timeout after 10 seconds
    (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
        public Boolean apply(WebDriver d) {
            return d.getTitle().toLowerCase().startsWith("cheese!");
        }
    });

    // Should see: "cheese! - Google Search"
    System.out.println("Page title is: " + driver.getTitle());
    
    //Close the browser
    driver.quit();
  }
  
}


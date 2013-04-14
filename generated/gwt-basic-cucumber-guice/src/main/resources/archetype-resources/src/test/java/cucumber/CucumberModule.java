#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.cucumber;

import ${package}.cucumber.application.ApplicationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class CucumberModule extends AbstractModule {
  
    @Override
    protected void configure() {
    }

    @Provides
    @Singleton
    WebDriver getDefaultWebDriver() {
        return new ChromeDriver();
    }

    @Provides
    ApplicationPage getApplicationPage(WebDriver webDriver) {
        return PageFactory.initElements(webDriver, ApplicationPage.class);
    }
    
}

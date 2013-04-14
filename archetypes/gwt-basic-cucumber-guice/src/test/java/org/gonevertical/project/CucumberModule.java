package org.gonevertical.project;

import org.gonevertical.project.cucumber.application.ApplicationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
        return new FirefoxDriver();
    }

    @Provides
    ApplicationPage getApplicationPage(WebDriver webDriver) {
        return PageFactory.initElements(webDriver, ApplicationPage.class);
    }
    
}

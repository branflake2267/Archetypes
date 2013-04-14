#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.cucumber.stepdefs;

import javax.inject.Inject;

import ${package}.cucumber.application.ApplicationPage;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BasicStepdefs {

  private static final String baseUrl = "http://127.0.0.1:8888";

  private final ApplicationPage applicationPage;

  @Inject
  public BasicStepdefs(ApplicationPage applicationPage) {
    this.applicationPage = applicationPage;
  }

  @After
  public void cleanup() {
    applicationPage.close();
  }

  @Given("^the application loads${symbol_dollar}")
  public void theAppLoads() {
    applicationPage.getUrl(baseUrl);
    applicationPage.doesDebugIdExist("loaded");
  }
  
  @When("^I navigate to (${symbol_escape}${symbol_escape}S+)${symbol_dollar}")
  public void iNavigateTo(String nameToken) {
    String url = baseUrl + "${symbol_pound}" + nameToken;

    applicationPage.getUrl(url);
  }

  @Then("^it works${symbol_dollar}")
  public void thenItWorks() {
    // TODO
  }

}

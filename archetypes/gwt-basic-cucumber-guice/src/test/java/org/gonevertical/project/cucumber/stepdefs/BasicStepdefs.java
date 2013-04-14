package org.gonevertical.project.cucumber.stepdefs;

import javax.inject.Inject;

import org.gonevertical.project.cucumber.application.ApplicationPage;

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

  @Given("^the application loads$")
  public void theAppLoads() {
    applicationPage.getUrl(baseUrl);
    applicationPage.doesDebugIdExist("loaded");
  }
  
  @When("^I navigate to (\\S+)$")
  public void iNavigateTo(String nameToken) {
    String url = baseUrl + "#" + nameToken;

    applicationPage.getUrl(url);
  }

  @Then("^it works$")
  public void thenItWorks() {
    // TODO
  }

}

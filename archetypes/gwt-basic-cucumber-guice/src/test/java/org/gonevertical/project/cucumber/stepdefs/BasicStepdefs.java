package org.gonevertical.project.cucumber.stepdefs;

import javax.inject.Inject;

import org.gonevertical.project.cucumber.application.ApplicationPage;


import cucumber.annotation.After;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

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

  @Given("^I navigate to (\\S+)$")
  public void iNavigateTo(String nameToken) {
    String url = baseUrl + "#" + nameToken;

    applicationPage.getUrl(url);
    applicationPage.waitUntilDomIsLoaded(nameToken);
  }

  @When("^I click on the GWT button$")
  public void iClickOnTheButton() {
    // TODO
  }

  @Then("^then the popup displays$")
  public void enterValidCredential() {
    // TODO
  }

}

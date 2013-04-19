package org.gonevertical.project.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

/**
 * The cucumber features are located in src/test/resources same package.
 */
@RunWith(Cucumber.class)
@Cucumber.Options(features = { "." })
public class RunCucumberTestInt {
}

package org.gonevertical.archetypes;

import java.util.ArrayList;
import java.util.List;

import org.gonevertical.archetypes.generator.GwtArchetypeGenerator;

public class RunArchetypesGenerator {

  public static void main(String[] args) {
    new RunArchetypesGenerator().run();
  }

  private void run() {
    List<String> projects = new ArrayList<String>();
    // Testing
    // projects.add("gwt-basic-snapshot");
    // projects.add("gwt-basic-beta");

    // Upgraded
    // projects.add("gwt-basic"); // Upgraded
    // projects.add("gwt-basic-rpc"); // Upgraded
    // projects.add("gwt-basic-rpc-appengine"); // Upgraded

    // new
    // projects.add("appengine-endpoints-guice-objectify");

    // projects.add("gwt-basic-rpc-appengine-guice");
    // projects.add("gwt-basic-requestfactory");
    // projects.add("gwt-activitiesandplaces-requestfactory");
    // projects.add("gwt-activitiesandplaces-requestfactory-maps");
    // projects.add("gwt-css");
    // projects.add("gwt-basic-cucumber-guice");

    // App Engine (Updated Feb, 15 2015)
    // projects.add("appengine-basic-guice");
    // projects.add("appengine-endpoints-guice-jpa");
    // projects.add("appengine-endpoints-guice-jdo");
    // projects.add("gwt-basic-rpc-appengine"); // good 5/9/2015
    // projects.add("gwt-basic-rpc-appengine-guice");
    // projects.add("gwt-basic-rpc-tomcat"); // good 5/9/2015

    // testing
    projects.add("gwt-test-gwt27"); // gwt-eclipse plugin testing
    // projects.add("gwt-basic");

    GwtArchetypeGenerator generate = new GwtArchetypeGenerator();
    generate.setProjects(projects);
    generate.setDeploy(true);
    generate.run();
  }

}

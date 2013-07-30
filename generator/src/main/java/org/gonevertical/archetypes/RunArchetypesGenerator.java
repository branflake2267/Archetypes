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
    projects.add("gwt-basic");
    projects.add("gwt-basic-rpc");
    projects.add("gwt-basic-rpc-appengine-guice");
    projects.add("gwt-basic-requestfactory");
    projects.add("gwt-activitiesandplaces-requestfactory");
    projects.add("gwt-activitiesandplaces-requestfactory-maps");
    projects.add("gwt-css");
    projects.add("gwt-basic-cucumber-guice");
    
    GwtArchetypeGenerator generate = new GwtArchetypeGenerator();
    generate.setProjects(projects);
    generate.setDeploy(true);
    generate.run();
  }

}

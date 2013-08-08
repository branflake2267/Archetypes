package org.gonevertical.archetypes;

import java.util.ArrayList;
import java.util.List;

import org.gonevertical.archetypes.generator.GwtArchetypeGenerator;

public class RunArchetypesGeneratorGxt {

  public static void main(String[] args) {
    new RunArchetypesGeneratorGxt().run();
  }

  private void run() {
    List<String> projects = new ArrayList<String>();
//    projects.add("gxt-basic-public-2x");
//    projects.add("gxt-basic-public-3x");
//    projects.add("gxt-basic-support-3x");
    projects.add("gxt-gwtp-withlayout");
//    projects.add("gxt-basic-3x");
    projects.add("selenium-server-runner");
    projects.add("remoteweb-server-runner");    

    
    GwtArchetypeGenerator generate = new GwtArchetypeGenerator();
    generate.setProjects(projects);
    generate.setDeploy(true);
    generate.run();
  }

}

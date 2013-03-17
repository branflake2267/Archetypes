package org.gonevertical.archetypes.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.FileUtils;
import org.gonevertical.archetypes.generator.utils.FileCleaner;
import org.gonevertical.archetypes.generator.utils.FileRegex;
import org.gonevertical.archetypes.generator.utils.MoveFile;

public class RunGWTArchetypeGenerator {

  public static void main(String[] args) {
    new RunGWTArchetypeGenerator().run(args);
  }

  private String baseWorkingDir = null;

  public RunGWTArchetypeGenerator() {
  }

  private void run(String[] args) {
    // TODO
    baseWorkingDir = "/Users/branflake2267/git/Archetypes/archetypes/gwt-css/";

    runMvnClean();
    runMvnArchetypeCreateFromProject();
    cleanGeneratedArchetype();
    replaceTextWithArchetypeVars();
    renameProjectFiles();
    addDeployToSonaTypePomElements();
  }

  private void runMvnClean() {
    runCommand(baseWorkingDir, "mvn", "clean");
  }

  private void runMvnArchetypeCreateFromProject() {
    runCommand(baseWorkingDir, "mvn", "archetype:create-from-project");
  }

  private void cleanGeneratedArchetype() {
    cleanArchetypeExt(".sh");
    cleanArchetypeExt(".DS_Store");
    cleanArchetypeExt(".iml");
    cleanArchetypeExt(".gwt");
    cleanArchetypeExt(".settings");
    cleanArchetypeExt("bin");
    cleanArchetypeExt("www-test");
    cleanArchetypeExt("war");
    cleanArchetypeExt("gwt-unitCache");
    cleanArchetypeExt(".gwt-tmp");
    cleanArchetypeExt("README.md");
    cleanArchetypeExt("EmptyNess.java");
  }

  private void replaceTextWithArchetypeVars() {
    regexFile(".xml", "<module>.*\\.(.*)</module>", "<module>\\${package}.$1</module>");

    // Only do cap Project in .xml files
    regexFile(".xml", "\\.Project", ".\\${module}");
    regexFile(".xml", "'project'", "'\\${module}'");

    regexFile(".java", "Project", "\\${module}");
    regexFile(".java", "project", "\\${module}");
    regexFile(".html", "Project", "\\${module}");
    regexFile(".html", "project", "\\${module}");

    regexFile(".xml", "ProjectEntryPoint", "\\${module}EntryPoint");
  }

  private void renameProjectFiles() {
    renameProjectFile("ProjectEntryPoint.java", "__module__EntryPoint.java");
    renameProjectFile("Project.html", "__module__.html");
    renameProjectFile("Project.css", "__module__.css");
    renameProjectFile("Project.gwt.xml", "__module__.gwt.xml");
  }
  
  private void addDeployToSonaTypePomElements() {
    
  }

  private void renameProjectFile(String src, String dest) {
    String archetypeBase = baseWorkingDir + "target/generated-sources";
    File startDir = new File(archetypeBase);
    
    MoveFile mf = new MoveFile(src, dest);
    mf.start(startDir);
  }

  private void regexFile(String name, String regexFind, String regexReplace) {
    String archetypeBase = baseWorkingDir + "target/generated-sources";
    File startDir = new File(archetypeBase);

    FileRegex fr = new FileRegex(name, regexFind, regexReplace);
    fr.start(startDir);
  }

  private void cleanArchetypeExt(String ext) {
    String archetypeBase = baseWorkingDir + "target/generated-sources"; 
    File startDir = new File(archetypeBase);

    FileCleaner fc = new FileCleaner(ext);
    fc.start(startDir);
  }

  private void runCommand(String directory, String... command) {
    ProcessBuilder pb = new ProcessBuilder(command);
    pb.directory(new File(directory));

    try {
      Process p = pb.start();
      String s;
      BufferedReader stdout = new BufferedReader(new InputStreamReader(p.getInputStream()));
      while ((s = stdout.readLine()) != null) {
        System.out.println(s);
      }
      System.out.println("Exit value: " + p.waitFor());
      p.getInputStream().close();
      p.getOutputStream().close();
      p.getErrorStream().close();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}

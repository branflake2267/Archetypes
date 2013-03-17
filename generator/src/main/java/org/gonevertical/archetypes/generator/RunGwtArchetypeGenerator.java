package org.gonevertical.archetypes.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.FileUtils;
import org.gonevertical.archetypes.generator.utils.FileCleaner;
import org.gonevertical.archetypes.generator.utils.FileRegex;
import org.gonevertical.archetypes.generator.utils.MoveFile;
import org.gonevertical.archetypes.generator.utils.XmlNodeCleaner;

public class RunGwtArchetypeGenerator {

  public static void main(String[] args) {
    new RunGwtArchetypeGenerator().run(args);
  }

  private String baseWorkingDir = null;

  public RunGwtArchetypeGenerator() {
  }

  /**
   * TODO setup params or config file?
   */
  private void run(String[] args) {
    buildArchetypes("gwt-basic");
    buildArchetypes("gwt-basic-rpc");
    buildArchetypes("gwt-basic-rpc-appengine-guice");
    buildArchetypes("gwt-basic-requestfactory");
    buildArchetypes("gwt-activitiesandplaces-requestfactory");
    buildArchetypes("gwt-activitiesandplaces-requestfactory-maps");
    buildArchetypes("gwt-css");
  }
  
  private void buildArchetypes(String path) {
    String base = null;
    try {
      base = new File("..").getCanonicalPath();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    baseWorkingDir = base + "/archetypes/" + path + "/";
    
    File file = new File(baseWorkingDir);
    boolean isDir = file.isDirectory();
    if (!isDir) {
      System.out.println("Exiting, dir problem");
      System.exit(0);
    }
    
    System.out.println("baseWorkingDir=" + baseWorkingDir);

    runSteps();
  }
  
  private void runSteps() {
    runMvnClean();
    runMvnArchetypeCreateFromProject();
    cleanGeneratedArchetype();
    cleanArchetypeMetaData();
    setupRequiredArchetypeVars();
    replaceTextWithArchetypeVars();
    setupArchetypeIntegrationTestParameter();
    renameProjectFiles();
    addDeployToSonaTypePomElements();
    deploy();
    
    System.out.println("Finished generating pom for " + baseWorkingDir);
  }
  
  private void cleanArchetypeMetaData() {
    cleanArchetypeMetaData("target/generated-sources/archetype/target/classes/META-INF/maven/archetype-metadata.xml");
    cleanArchetypeMetaData("target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml");
  }
  
  private void cleanArchetypeMetaData(String path) {
    String filePath = baseWorkingDir + path;
    
    XmlNodeCleaner xnc = new XmlNodeCleaner();
    
    // remove with directory
    xnc.removeParentNodeWithExpression(filePath, "//fileSet//*[contains(text(),\"www-test\")]");
    xnc.removeParentNodeWithExpression(filePath, "//fileSet//*[contains(text(),\".settings\")]");
    xnc.removeParentNodeWithExpression(filePath, "//fileSet//*[contains(text(),\".gwt\")]");
    xnc.removeParentNodeWithExpression(filePath, "//fileSet//*[contains(text(),\"gwt-unitCache\")]");
    
    // remove includes
    xnc.removeNode(filePath, "//includes/*[contains(text(),\".classpath\")]");
    xnc.removeNode(filePath, "//includes/*[contains(text(),\".project\")]");
    xnc.removeNode(filePath, "//includes/*[contains(text(),\"README.md\")]");
    xnc.removeNode(filePath, "//includes/*[contains(text(),\"test-archtype.sh\")]");
  }

  private void deploy() {
    String pathToArchetypePom = baseWorkingDir + "target/generated-sources/archetype";
    runCommand(pathToArchetypePom, "mvn", "deploy");
  }

  private void runMvnClean() {
    runCommand(baseWorkingDir, "mvn", "clean");
  }

  private void runMvnArchetypeCreateFromProject() {
    runCommand(baseWorkingDir, "mvn", "archetype:create-from-project", "-Darchetype.properties=archetype.properties");
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
    cleanArchetypeExt(".project");
    cleanArchetypeExt(".classpath");
  }

  private void setupRequiredArchetypeVars() {
    String find = "<fileSets>";
    
    String replace = "";
    replace += "<requiredProperties>\n";
    replace += "    <requiredProperty key=\"module\">\n";
    replace += "        <defaultValue>Project</defaultValue>\n";
    replace += "    </requiredProperty>\n";
    replace += "</requiredProperties>\n\n";
    replace += "<fileSets>\n";
    
    regexFindAndReplaceFiles("archetype-metadata.xml", find, replace);
  }
  
  private void setupArchetypeIntegrationTestParameter() {
    String find = "(artifactId=.*)";
    
    String replace = "$1\n";
    replace += "module=Project\n";
    
    regexFindAndReplaceFiles("archetype.properties", find, replace);
  }
  
  private void replaceTextWithArchetypeVars() {
    regexFindAndReplaceFiles(".xml", "<module>.*\\.(.*)</module>", "<module>\\${package}.$1</module>");

    // Only do cap Project in .xml files
    regexFindAndReplaceFiles(".xml", "\\.Project", ".\\${module}");
    regexFindAndReplaceFiles(".xml", "'project'", "'\\${module}'");
    regexFindAndReplaceFiles(".xml", "Project.html", "\\${module}.html");

    regexFindAndReplaceFiles(".java", "Project", "\\${module}");
    regexFindAndReplaceFiles(".java", "project", "\\${module}");
    regexFindAndReplaceFiles(".html", "Project", "\\${module}");
    regexFindAndReplaceFiles(".html", "project", "\\${module}");
    

    regexFindAndReplaceFiles(".xml", "ProjectEntryPoint", "\\${module}EntryPoint");
  }

  private void renameProjectFiles() {
    renameProjectFile("ProjectEntryPoint.java", "__module__EntryPoint.java");
    renameProjectFile("Project.html", "__module__.html");
    renameProjectFile("Project.css", "__module__.css");
    renameProjectFile("Project.gwt.xml", "__module__.gwt.xml");
  }
  
  private void addDeployToSonaTypePomElements() {
    addPomParent();
    addDeployFileContents();
  }
  
  private void addDeployFileContents() {
    String base = "";
    try {
      base = new File(".").getCanonicalPath();
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    String path = base + "/src/main/java/org/gonevertical/archetypes/generator/template/deploy-pom-template.txt";
    
    String find = "</project>";
    
    String replace = "";
    try {
      replace = FileUtils.readFileToString(new File(path), "UTF-8");
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    String pathToArchetypePom = "target/generated-sources/archetype/pom.xml";
    
    repalceInFile(pathToArchetypePom, find, replace);
  }

  private void addPomParent() {
    String find = "<modelVersion>4.0.0</modelVersion>";
    
    String replace = "";
    replace += "<modelVersion>4.0.0</modelVersion>\n\n";
    replace += "<parent>\n";
    replace += "  <groupId>org.sonatype.oss</groupId>\n";
    replace += "  <artifactId>oss-parent</artifactId>\n";
    replace += "  <version>7</version>\n";
    replace += "</parent>\n";
    
    String pathToArchetypePom = "target/generated-sources/archetype/pom.xml";
    
    repalceInFile(pathToArchetypePom, find, replace);
  }
  
  private void repalceInFile(String path, String find, String replace) {
    path = baseWorkingDir + path;
    File file = new File(path);
    
    org.gonevertical.archetypes.generator.utils.FileUtils.replaceInFileByLine(file, find, replace);
  }

  private void renameProjectFile(String src, String dest) {
    String archetypeBase = baseWorkingDir + "target/generated-sources";
    File startDir = new File(archetypeBase);
    
    MoveFile mf = new MoveFile(src, dest);
    mf.start(startDir);
  }

  private void regexFindAndReplaceFiles(String name, String regexFind, String regexReplace) {
    String archetypeBase = baseWorkingDir + "target/generated-sources";
    File startDir = new File(archetypeBase);

    FileRegex fr = new FileRegex(name, regexFind, regexReplace);
    fr.start(startDir);
  }

  private void cleanArchetypeExt(String name) {
    String archetypeBase = baseWorkingDir + "target/generated-sources"; 
    File startDir = new File(archetypeBase);

    FileCleaner fc = new FileCleaner(name);
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

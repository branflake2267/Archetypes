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
  
  private boolean deploy = false;

  /**
   * base path for working directory of the repo/archetypes
   */
  private String baseWorkingDir;

  /**
   * base path for the generated archetypes repo/generated
   */
  private String baseGenerated;

  /**
   * Archetype directory like "gwt-basic"
   */
  private String archetypeDirectory;

  /**
   * Generated directory path with archetype directory
   */
  private String baseGeneratedArchetypeDir;

  /**
   * base path of the repo
   */
  private String base;

  public RunGwtArchetypeGenerator() {
  }

  /**
   * TODO setup params or config file?
   */
  private void run(String[] args) {
    deploy = true;
    
    buildArchetypes("gwt-basic");
    buildArchetypes("gwt-basic-rpc");
    buildArchetypes("gwt-basic-rpc-appengine-guice");
    buildArchetypes("gwt-basic-requestfactory");
    buildArchetypes("gwt-activitiesandplaces-requestfactory");
    buildArchetypes("gwt-activitiesandplaces-requestfactory-maps");
    buildArchetypes("gwt-css");
    buildArchetypes("gwt-basic-cucumber-guice");
    
    System.out.println("Finished Everything!");
  }

  private void buildArchetypes(String path) {
    try {
      base = new File("..").getCanonicalPath();
    } catch (IOException e) {
      e.printStackTrace();
    }

    archetypeDirectory = path;
    baseWorkingDir = base + "/archetypes/" + path + "/";
    baseGenerated = base + "/generated/";
    baseGeneratedArchetypeDir = baseGenerated + archetypeDirectory;

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
    // generate
    runMvnClean();
    runMvnArchetypeCreateFromProject();

    // clean
    cleanGeneratedArchetype();
    cleanArchetypeMetaData();

    // ${module}lize velocity variables
    setupRequiredArchetypeVars();
    replaceTextWithArchetypeVars();
    setupArchetypeIntegrationTestParameter();
    renameProjectFiles();

    // deploy items
    addPomParent();
    moveArchetypeToGeneratedDirectory();
    
    if (deploy) {
      deploy();
    }

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

    // remove includes won't work
    // xnc.removeNode(filePath, "//includes/*[contains(text(),\".classpath\")]");
    // xnc.removeNode(filePath, "//includes/*[contains(text(),\".project\")]");
    // xnc.removeNode(filePath, "//includes/*[contains(text(),\"README.md\")]");
    // xnc.removeNode(filePath, "//includes/*[contains(text(),\"test-archtype.sh\")]");

    // TODO won't work
    // xnc.removeParentNodeWithExpression(filePath, "//includes/*[contains(text(),\".classpath\")]");
    // xnc.removeParentNodeWithExpression(filePath, "//includes/*[contains(text(),\"README.md\")]");

    xnc.removeParentParentParentNodeWithExpression(filePath, "//includes/*[contains(text(),\"README.md\")]");
    xnc.removeParentParentParentNodeWithExpression(filePath, "//includes/*[contains(text(),\".classpath\")]");
  }

  private void deploy() {    
    runCommand(baseGeneratedArchetypeDir, "mvn", "deploy");
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
    replace += "module=AppModTest\n";

    regexFindAndReplaceFiles("archetype.properties", find, replace);
  }

  private void replaceTextWithArchetypeVars() {
    regexFindAndReplaceFiles(".xml", "<module>.*\\.(.*)</module>", "<module>\\${package}.$1</module>");

    // Only do cap Project in .xml files
    regexFindAndReplaceFiles(".xml", "\\.Project", ".\\${module}");
    regexFindAndReplaceFiles(".xml", "'project'", "'\\${module}'");
    regexFindAndReplaceFiles(".xml", "Project.html", "\\${module}.html");
    regexFindAndReplaceFiles(".xml", "/project/", "/\\${module}/"); // web.xml

    regexFindAndReplaceFiles(".java", "Project", "\\${module}");
    regexFindAndReplaceFiles(".java", "project", "\\${module}");
    regexFindAndReplaceFiles(".html", "Project", "\\${module}");
    regexFindAndReplaceFiles(".html", "project", "\\${module}");
    regexFindAndReplaceFiles(".properties", "project", "\\${module}");

    regexFindAndReplaceFiles(".xml", "ProjectEntryPoint", "\\${module}EntryPoint");
  }

  private void renameProjectFiles() {
    renameProjectFile("ProjectEntryPoint.java", "__module__EntryPoint.java");
    renameProjectFile("Project.html", "__module__.html");
    renameProjectFile("Project.css", "__module__.css");
    renameProjectFile("Project.gwt.xml", "__module__.gwt.xml");
  }

  // TODO get parent's version and update it?
  private void addPomParent() {
    String find = "<modelVersion>4.0.0</modelVersion>";

    String version = getParentPomVersion();
    
    String replace = "";
    replace += "<modelVersion>4.0.0</modelVersion>\n\n";
    replace += "<parent>\n";
    replace += "  <groupId>com.github.branflake2267.archetypes</groupId>\n";
    replace += "  <artifactId>generated</artifactId>\n";
    replace += "  " + version + "\n";
    replace += "</parent>\n";

    String pathToArchetypePom = "target/generated-sources/archetype/pom.xml";

    repalceInFile(pathToArchetypePom, find, replace);
  }

  private String getParentPomVersion() {
    String path = base + "/pom.xml";
    String regex = "<version>(.*?-SNAPSHOT)</version>";
    String version = org.gonevertical.archetypes.generator.utils.FileUtils.findInFileAndReturnLine(new File(path), regex).trim();
    return version;
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

  private void moveArchetypeToGeneratedDirectory() {
    String archetypeBase = baseWorkingDir + "target/generated-sources/archetype";

    try {
      FileUtils.deleteDirectory(new File(baseGeneratedArchetypeDir));
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    
    try {
      FileUtils.moveDirectory(new File(archetypeBase), new File(baseGeneratedArchetypeDir));
    } catch (IOException e) {
      e.printStackTrace();
    }
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

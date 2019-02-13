package org.gonevertical.archetypes.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.gonevertical.archetypes.generator.domain.FindInReplace;
import org.gonevertical.archetypes.generator.utils.FileCleaner;
import org.gonevertical.archetypes.generator.utils.FileRegex;
import org.gonevertical.archetypes.generator.utils.FileRegexByLine;
import org.gonevertical.archetypes.generator.utils.FindFiles;
import org.gonevertical.archetypes.generator.utils.MoveFile;
import org.gonevertical.archetypes.generator.utils.XmlNodeCleaner;
import org.gonevertical.archetypes.generator.utils.XmlNodeTurnOnFiltered;
import org.gonevertical.archetypes.generator.utils.XmlNodeUtils;

public class GwtArchetypeGenerator {

  /**
   * deploy to sonatype
   */
  private boolean deploy = false;

  /**
   * baseProjects path for working directory of the repo/archetypes
   */
  protected String baseWorkingProjectDir;

  /**
   * baseProjects path for the generated archetypes repo/generated
   */
  protected String baseGeneratedDir;

  /**
   * Build archetype for project directory like "gwt-basic", not the entire directory path only "gwt-basic-project"
   */
  protected String workOnProjectDir;

  /**
   * Generated directory path with archetype directory
   */
  protected String baseGeneratedArchetypeDir;

  /**
   * baseProjects path of the repo
   */
  protected String baseProjects;

  /**
   * projects to generate archetypes for
   */
  protected List<String> projects;

  /**
   * custom find in replace
   */
  protected List<FindInReplace> findInReplaceCustomList;

  /**
   * Found GWT client package tld.domain.project[.client]
   */
  protected String gwtClientPackage;

  public GwtArchetypeGenerator() {
  }

  public void setProjects(List<String> projects) {
    this.projects = projects;
  }

  public void setDeploy(boolean deploy) {
    this.deploy = deploy;
  }

  public void setFindInReplace(List<FindInReplace> findInReplaceList) {
    this.findInReplaceCustomList = findInReplaceList;
  }

  public void run() {
    if (projects == null || projects.size() == 0) {
      System.out.println("No projects were given to build archetypes for.");
      System.exit(0);
    }

    for (String projectDir : projects) {
      buildProject(projectDir);
    }

    System.out.println("Finished generated all the projects!!!");
  }

  private void buildProject(String workOnProjectDir) {
    try {
      baseProjects = new File("..").getCanonicalPath();
    } catch (IOException e) {
      e.printStackTrace();
    }

    this.workOnProjectDir = workOnProjectDir;
    baseWorkingProjectDir = baseProjects + "/archetypes/" + workOnProjectDir + "/";
    baseGeneratedDir = baseProjects + "/generated/";
    baseGeneratedArchetypeDir = baseGeneratedDir + workOnProjectDir;

    File file = new File(baseWorkingProjectDir);
    boolean isDir = file.isDirectory();
    if (!isDir) {
      System.out
          .println("Exiting, directory problem. Could not find the archetype project directory. baseWorkingProjectDir="
              + baseWorkingProjectDir);
      System.exit(0);
    }

    System.out.println("baseWorkingProjectDir=" + baseWorkingProjectDir);

    try {
      runSteps();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void runSteps() throws Exception {
    // Be sure to use a archetype.properties
    System.out.println("\n\nDo you have a: archetype.properties?\n\n");

    runMvnClean();

    parseGwtClientPackage();

    // generate
    runMvnArchetypeCreateFromProject();

    // clean
    cleanCopyrightInFiles();
    cleanRemoveFilesFromGeneratedArchetype();
    cleanRemoveAnnotatedFilesFromArchetypeMetaData();

    // archetype-metadata.xml resources
    turnOnFilteredForPaths();

    // Disabling, this is provided through archetype.properties
    // setupRequiredArchetypeVars();

    // setupTestResourcesPackaging();

    // ${module}lize velocity variables
    findAndReplaceInFiletupesCustom();
    findAndReplaceInFileTypes();
    setupArchetypeIntegrationTestParameter();
    renameProjectFiles();

    // deploy items
    // addPomParent(); // Don't add this
    moveArchetypeToGeneratedDirectory();

    appendProfilesForRelease();

    System.out.println("Finished generating pom for " + baseWorkingProjectDir);
  }

  private void parseGwtClientPackage() {
    FindFiles findGwtClient = new FindFiles("(implements.*?EntryPoint)");
    List<File> found = findGwtClient.start(new File(baseWorkingProjectDir));
    if (found == null || found.isEmpty()) {
      gwtClientPackage = "tld.domain.project";
      System.out.println("\n\n\n~~~~~~~\nCould not find gwtClientPackage... Setting default. \n~~~~~~~\n\n\n");
      return;
    }

    File foundFile = found.get(0);

    String packageName = org.gonevertical.archetypes.generator.utils.FileUtils.findInFileAndReturnLine(foundFile,
        "package(.*?;)");
    packageName = packageName.replaceFirst("package", "");
    packageName = packageName.replace(";", "");
    packageName = packageName.replace(".client", "");

    gwtClientPackage = packageName.trim();

    if (found == null || found.isEmpty()) {
      gwtClientPackage = "tld.domain.project";
      System.out.println("\n\n\n~~~~~~~\nCould not find gwtClientPackage... Setting default. \n~~~~~~~\n\n\n");
      return;
    }
  }

  private void cleanCopyrightInFiles() {
    String archetypeBase = baseWorkingProjectDir + "target/generated-sources";

    FileRegex fileRegex = new FileRegex("\\.java", "(\\/\\*\\*.*Copy.*\\*\\/.*?)package");
    fileRegex.start(new File(archetypeBase));
  }

  private void cleanRemoveAnnotatedFilesFromArchetypeMetaData() {
    cleanArchetypeMetaData("target/generated-sources/archetype/target/classes/META-INF/maven/archetype-metadata.xml");
    cleanArchetypeMetaData(
        "target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml");
  }

  private void cleanArchetypeMetaData(String path) {
    String filePath = baseWorkingProjectDir + path;
    XmlNodeCleaner xnc = new XmlNodeCleaner();

    // remove with directory
    xnc.removeParentNodeWithExpression(filePath, "//fileSet//*[contains(text(),\"www-test\")]");
    xnc.removeParentNodeWithExpression(filePath, "//fileSet//*[contains(text(),\".settings\")]");
    xnc.removeParentNodeWithExpression(filePath, "//fileSet//*[contains(text(),\".idea\")]");
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

  /**
   * Turn on filtered in archetype-metadata.xml for a file set path.
   * 
   * <fileSet encoding="UTF-8" filtered="true" packaged="true">
   */
  private void turnOnFilteredForPaths() {
    turnOnFilteredForPaths("target/generated-sources/archetype/target/classes/META-INF/maven/archetype-metadata.xml");
    turnOnFilteredForPaths(
        "target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml");
  }

  private void turnOnFilteredForPaths(String path) {
    String filePath = baseWorkingProjectDir + path;
    XmlNodeTurnOnFiltered xnto = new XmlNodeTurnOnFiltered();

    xnto.turnOnFilteredForPath(filePath, "//*[contains(text(),\"src/main/resources\")]");
  }

  private void deploy() throws Exception {
    System.out.println("----------------------");
    System.out.println("Running: mvn deploy");
    runCommand(baseGeneratedArchetypeDir, "/usr/local/bin/mvn", "deploy", "-X");
  }

  private void runMvnClean() throws Exception {
    System.out.println("----------------------");
    System.out.println("Running: mvn clean");
    runCommand(baseWorkingProjectDir, "/usr/local/bin/mvn", "clean");
  }

  private void runMvnArchetypeCreateFromProject() throws Exception {
    System.out.println("----------------------");
    System.out.println("Running: archetype:create-from-project");
    runCommand(baseWorkingProjectDir, "/usr/local/bin/mvn", "archetype:create-from-project",
        "-Darchetype.properties=archetype.properties");
  }

  protected void cleanRemoveFilesFromGeneratedArchetype() {
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
    cleanArchetypeExt("EmptyNesss.java");
    cleanArchetypeExt(".idea");
    cleanArchetypeExt(".project");
    cleanArchetypeExt(".classpath");
    cleanArchetypeExt("chromedriver.log");
    cleanArchetypeExt("gwt-unitCache");

    // TODO cleanArchetypeExt(".api"); // app-engine endpoints
  }

  /**
   * archetype-metadata.xml resources
   */
  private void setupRequiredArchetypeVars() {
    String find = "<fileSets>";

    String replace = "";
    replace += "<requiredProperties>\n";
    replace += "    <requiredProperty key=\"module\">\n";
    replace += "        <defaultValue>Project</defaultValue>\n";
    replace += "    </requiredProperty>\n";
    replace += "</requiredProperties>\n\n";
    replace += "<fileSets>\n";

    String pathToArchetypePom = "/target/generated-sources/archetype/target/classes/META-INF/maven/archetype-metadata.xml";

    repalceInFile(pathToArchetypePom, find, replace);
  }

  private void setupArchetypeIntegrationTestParameter() {
    String find = "(artifactId=.*)";

    String replace = "$1\n";
    replace += "module=AppModTest\n";

    regexFindAndReplaceFiles("archetype.properties", find, replace);
  }

  private void findAndReplaceInFiletupesCustom() {
    if (findInReplaceCustomList == null || findInReplaceCustomList.size() == 0) {
      return;
    }

    for (FindInReplace findInReplace : findInReplaceCustomList) {
      System.out.println("findInReplace: findFiles=" + findInReplace.getFindFiles() + " find=" + findInReplace.getFind()
          + " replace=" + findInReplace.getReplace());
      regexFindAndReplaceFiles(findInReplace.getFindFiles(), findInReplace.getFind(), findInReplace.getReplace());
    }
  }

  protected void findAndReplaceInFileTypes() {
    regexFindAndReplaceFiles(".xml", "<module>.*\\.(.*)</module>", "<module>\\${package}.$1</module>"); // pom.xml
    regexFindAndReplaceFiles(".xml", "<moduleName>.*\\.(.*)</moduleName>", "<moduleName>\\${package}.$1</moduleName>"); // pom.xml

    // This is done by the default generator
    // For some reason the ${package} is not getting replaced in the entrypoint
    // regexFindAndReplaceFiles(".xml", gwtClientPackage, "\\${package}"); // pom.xml entrypoint

    // Only do cap Project in .xml files
    regexFindAndReplaceFiles(".xml", "\\.Project", ".\\${module}");
    regexFindAndReplaceFiles(".xml", "Project.html", "\\${module}.html");
    regexFindAndReplaceFiles(".xml", "'project'", "'\\${module}'"); // module
    regexFindAndReplaceFiles(".xml", "<display-name>Project</display-name>",
        "<display-name>\\${module}</display-name>"); // web.xml
    regexFindAndReplaceFiles(".xml", "<welcome-file>Project.html</welcome-file>",
        "<welcome-file>\\${module}.html</welcome-file>"); // web.xml
    regexFindAndReplaceFiles(".xml", "/project/", "/\\${module}/"); // rpc

    regexFindAndReplaceFiles(".java", "/project/", "/\\${module}/"); // rpc
    regexFindAndReplaceFiles(".java", "Project", "\\${module}");
    regexFindAndReplaceFiles(".html", "Project", "\\${module}");
    regexFindAndReplaceFiles(".html", "project", "\\${module}");

    regexFindAndReplaceFiles(".xml", "ProjectEntryPoint", "\\${module}EntryPoint");
  }

  protected void renameProjectFiles() {
    renameProjectFile("ProjectEntryPoint.java", "__module__EntryPoint.java");
    renameProjectFile("Project.html", "__module__.html");
    renameProjectFile("Project.css", "__module__.css");
    renameProjectFile("Project.gwt.xml", "__module__.gwt.xml");
  }

  private String getParentPomVersion() {
    String path = baseProjects + "/generated/pom.xml";
    String regex = "<version>(.*?-SNAPSHOT)</version>";
    String version = org.gonevertical.archetypes.generator.utils.FileUtils
        .findInFileAndReturnLine(new File(path), regex).trim();
    return version;
  }

  private String getParentPomGroupId() {
    String filePath = baseProjects + "/generated/pom.xml";
    XmlNodeUtils nodeUtils = new XmlNodeUtils();
    String groupId = nodeUtils.findNodeValue(filePath, "(//groupId)[1]");
    return groupId;
  }

  private String getParentPomArtifactId() {
    String filePath = baseProjects + "/generated/pom.xml";
    XmlNodeUtils nodeUtils = new XmlNodeUtils();
    String artifactId = nodeUtils.findNodeValue(filePath, "(//artifactId)[2]");
    return artifactId;
  }

  private void repalceInFile(String path, String find, String replace) {
    path = baseWorkingProjectDir + path;
    File file = new File(path);

    System.out.println("repalceInFile: path=" + path + " find=" + find + " replace=" + replace);

    org.gonevertical.archetypes.generator.utils.FileUtils.replaceInFileByLine(file, find, replace);
  }

  private void renameProjectFile(String src, String dest) {
    String archetypeBase = baseWorkingProjectDir + "target/generated-sources";
    File startDir = new File(archetypeBase);

    MoveFile mf = new MoveFile(src, dest);
    mf.start(startDir);
  }

  private void moveArchetypeToGeneratedDirectory() {
    String archetypeBase = baseWorkingProjectDir + "target/generated-sources/archetype";

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

  protected void regexFindAndReplaceFiles(String name, String regexFind, String regexReplace) {
    String archetypeBase = baseWorkingProjectDir + "target/generated-sources";
    File startDir = new File(archetypeBase);

    FileRegexByLine fr = new FileRegexByLine(name, regexFind, regexReplace);
    fr.start(startDir);
  }

  private void cleanArchetypeExt(String name) {
    String archetypeBase = baseWorkingProjectDir + "target/generated-sources";
    File startDir = new File(archetypeBase);

    FileCleaner fc = new FileCleaner(name);
    fc.start(startDir);
  }

  private void runCommand(String directory, String... command) throws Exception {
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
      throw new Exception(e.getMessage());
    } catch (InterruptedException e) {
      throw new Exception(e.getMessage());
    }
  }

  /**
   * archetype-metadata.xml resources
   */
  // TODO remove, maybe keep for testing...
  private void setupTestResourcesPackaging() {
    String find = "</fileSets>";

    String replace = "";
    replace += "<fileSet encoding=\"UTF-8\" filtered=\"true\" packaged=\"true\">\n";
    replace += "<directory>src/test/resources</directory>\n";
    replace += "  <includes>\n";
    replace += "    <include>**/*.java</include>\n";
    replace += "    <include>**/*.properties</include>\n";
    replace += "    <include>**/*.feature</include>\n";
    replace += "  </includes>\n";
    replace += "</fileSet>\n";
    replace += "\n\n\n</fileSets>\n";

    String pathToArchetypePom = "/target/generated-sources/archetype/target/classes/META-INF/maven/archetype-metadata.xml";

    repalceInFile(pathToArchetypePom, find, replace);
  }

  private void appendProfilesForRelease() {
    String path = baseGeneratedArchetypeDir +  "/pom.xml";
    String find = "</project>";

    String replace = readPomReleaseContents();
    
    replace += "</project>\n";

    org.gonevertical.archetypes.generator.utils.FileUtils.replaceInFileByLineOnce(new File(path), find, replace);
  }

  private String readPomReleaseContents() {
    String text = new Scanner(GwtArchetypeGenerator.class.getResourceAsStream("release-pom.xml"), "UTF-8")
        .useDelimiter("\\A").next();

    return text;
  }

}

package org.gonevertical.archetypes.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.gonevertical.archetypes.generator.domain.FindInReplace;
import org.gonevertical.archetypes.generator.utils.FileCleaner;
import org.gonevertical.archetypes.generator.utils.FileRegex;
import org.gonevertical.archetypes.generator.utils.FileRegexByLine;
import org.gonevertical.archetypes.generator.utils.MoveFile;
import org.gonevertical.archetypes.generator.utils.XmlNodeCleaner;
import org.gonevertical.archetypes.generator.utils.XmlNodeUtils;

public class GwtArchetypeGenerator {

  /**
   * deploy to sonatype
   */
  private boolean deploy = false;

  /**
   * baseProjects path for working directory of the repo/archetypes
   */
  private String baseWorkingProjectDir;

  /**
   * baseProjects path for the generated archetypes repo/generated
   */
  private String baseGeneratedDir;

  /**
   * Build archetype for project directory like "gwt-basic", not the entire directory path only "gwt-basic-project"
   */
  private String workOnProjectDir;

  /**
   * Generated directory path with archetype directory
   */
  private String baseGeneratedArchetypeDir;

  /**
   * baseProjects path of the repo
   */
  private String baseProjects;

  /**
   * projects to generate archetypes for
   */
  private List<String> projects;

  /**
   * custom find in replace
   */
  private List<FindInReplace> findInReplaceCustomList;

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
      System.out.println("Exiting, directory problem");
      System.exit(0);
    }

    System.out.println("baseWorkingProjectDir=" + baseWorkingProjectDir);

    runSteps();
  }

  private void runSteps() {
    // generate
    runMvnClean();
    runMvnArchetypeCreateFromProject();

    // clean
    cleanCopyrightInFiles();
    cleanRemoveFilesFromGeneratedArchetype();
    cleanRemoveAnnotatedFilesFromArchetypeMetaData();

    // archetype-metadata.xml resources
    setupRequiredArchetypeVars();
    // setupTestResourcesPackaging();

    // ${module}lize velocity variables
    findAndReplaceInFiletupesCustom();
    findAndReplaceInFileTypes();
    setupArchetypeIntegrationTestParameter();
    renameProjectFiles();

    // deploy items
    addPomParent();
    moveArchetypeToGeneratedDirectory();

    if (deploy) {
      deploy();
    }

    System.out.println("Finished generating pom for " + baseWorkingProjectDir);
  }

  private void cleanCopyrightInFiles() {
    String archetypeBase = baseWorkingProjectDir + "target/generated-sources";
    
    FileRegex fileRegex = new FileRegex("\\.java", "(\\/\\*\\*.*Copy.*\\*\\/.*?)package");
    fileRegex.start(new File(archetypeBase));
  }

  private void cleanRemoveAnnotatedFilesFromArchetypeMetaData() {
    cleanArchetypeMetaData("target/generated-sources/archetype/target/classes/META-INF/maven/archetype-metadata.xml");
    cleanArchetypeMetaData("target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml");
  }

  private void cleanArchetypeMetaData(String path) {
    String filePath = baseWorkingProjectDir + path;

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
    runCommand(baseWorkingProjectDir, "mvn", "clean");
  }

  private void runMvnArchetypeCreateFromProject() {
    runCommand(baseWorkingProjectDir, "mvn", "archetype:create-from-project", "-Darchetype.properties=archetype.properties");
  }

  private void cleanRemoveFilesFromGeneratedArchetype() {
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
    cleanArchetypeExt("chromedriver.log");
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
      System.out.println("findInReplace: findFiles=" + findInReplace.getFindFiles() + " find="
          + findInReplace.getFind() + " replace=" + findInReplace.getReplace());
      regexFindAndReplaceFiles(findInReplace.getFindFiles(), findInReplace.getFind(), findInReplace.getReplace());
    }
  }

  private void findAndReplaceInFileTypes() {
    regexFindAndReplaceFiles(".xml", "<module>.*\\.(.*)</module>", "<module>\\${package}.$1</module>");

    // Only do cap Project in .xml files
    regexFindAndReplaceFiles(".xml", "\\.Project", ".\\${module}");
    regexFindAndReplaceFiles(".xml", "Project.html", "\\${module}.html");
    regexFindAndReplaceFiles(".xml", "'project'", "'\\${module}'"); // module
    regexFindAndReplaceFiles(".xml", "<display-name>Project</display-name>", "<display-name>\\${module}</display-name>"); // web.xml 
    regexFindAndReplaceFiles(".xml", "<welcome-file>Project.html</welcome-file>", "<welcome-file>\\${module}.html</welcome-file>"); // web.xml
    regexFindAndReplaceFiles(".xml", "/project/", "/\\${module}/"); // rpc
    
    regexFindAndReplaceFiles(".java", "/project/", "/\\${module}/"); // rpc
    regexFindAndReplaceFiles(".java", "Project", "\\${module}");
    regexFindAndReplaceFiles(".html", "Project", "\\${module}");
    regexFindAndReplaceFiles(".html", "project", "\\${module}");
//    regexFindAndReplaceFiles(".properties", "workOnProjectDir", "\\${module}"); // TODO ?

    regexFindAndReplaceFiles(".xml", "ProjectEntryPoint", "\\${module}EntryPoint");
  }

  private void renameProjectFiles() {
    renameProjectFile("ProjectEntryPoint.java", "__module__EntryPoint.java");
    renameProjectFile("Project.html", "__module__.html");
    renameProjectFile("Project.css", "__module__.css");
    renameProjectFile("Project.gwt.xml", "__module__.gwt.xml");
  }

  private void addPomParent() {
    String find = "  <modelVersion>4.0.0</modelVersion>";

    String version = getParentPomVersion();
    String groupId = getParentPomGroupId();
    String artifactId = getParentPomArtifactId();

    String replace = "";
    replace += "  <modelVersion>4.0.0</modelVersion>\n\n";
    replace += "  <parent>\n";
    replace += "    <groupId>" + groupId + "</groupId>\n";
    replace += "    <artifactId>" + artifactId + "</artifactId>\n";
    replace += "    " + version + "\n";
    replace += "  </parent>\n";

    String pathToArchetypePom = "target/generated-sources/archetype/pom.xml";

    repalceInFile(pathToArchetypePom, find, replace);
  }

  private String getParentPomVersion() {
    String path = baseProjects + "/generated/pom.xml";
    String regex = "<version>(.*?-SNAPSHOT)</version>";
    String version = org.gonevertical.archetypes.generator.utils.FileUtils.findInFileAndReturnLine(new File(path),
        regex).trim();
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

  private void regexFindAndReplaceFiles(String name, String regexFind, String regexReplace) {
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

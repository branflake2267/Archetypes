#GWT Basic Cucumber Archetype

This is a very basic GWT Archetype. 

##Highlights
* TODO

##Build Server Testing
Running the integration tests on a server will require to run the browsers headlessly in a virtual frame buffer on the server. 

* [Setup Headless Running on Ubuntu](https://sites.google.com/site/mygwtexamples/home/testing/headless-running)

##Maven Archetype Usage

1. Goto directory you want the project.
2. Rename parameter below `com.projectname.project` to a package naming scheme you like.
3. Rename parameter `new-project-name` to a project title you like.
4. Run the mvn archetype generator below.

* This project Project.gwt.xml module name is hard coded and will will not be changed from the parameters below at this time.

```
mvn archetype:generate -DarchetypeGroupId=com.github.branflake2267.archetypes \
-DarchetypeRepository=https://oss.sonatype.org/content/repositories/snapshots \
-DarchetypeArtifactId=gwt-basic-cucumber-guice-archetype \
-DarchetypeVersion=1.6-SNAPSHOT \
-DgroupId=com.projectname.project \
-DartifactId=new-project-name \
-Dmodule=Project
```

##Selenium Web Drivers
A few things are required. Chrome, firefox and the Chrome web driver. Be sure these are installed before running the integration-tests.

##Run Tests
Import the maven project and run the tests.

```
mvn integration-test -Pintegration-test
```

#GWT Basic Archetype

This is a very basic GWT archetype. 

##Highlights
* [pom.xml](https://github.com/branflake2267/Archetypes/blob/master/archetypes/gwt-css/pom.xml)
* [Project Directory](https://github.com/branflake2267/Archetypes/tree/master/archetypes/gwt-css/src/main/java/org/gonevertical/project)
* [Entry Point Class](https://github.com/branflake2267/Archetypes/blob/master/archetypes/gwt-css/src/main/java/org/gonevertical/project/client/ProjectEntryPoint.java)
* [web.xml](https://github.com/branflake2267/Archetypes/blob/master/archetypes/gwt-css/src/main/webapp/WEB-INF/web.xml)
* [Welcome Page Project.html](https://github.com/branflake2267/Archetypes/blob/master/archetypes/gwt-css/src/main/webapp/Project.html)
* [GWT Test Case](https://github.com/branflake2267/Archetypes/tree/master/archetypes/gwt-css/src/test/java/org/gonevertical/project/client)

##Maven Archetype Usage

1. Goto directory you want the project.
2. Rename parameter below `com.projectname.project` to a package naming scheme you like.
3. Rename parameter `new-project-name` to a project title you like.
4. Run the mvn archetype generator below.

* This project Project.gwt.xml module name is hard coded and will will not be changed from the parameters below at this time.

```
mvn archetype:generate -DarchetypeGroupId=com.github.branflake2267.archetypes \
-DarchetypeArtifactId=gwt-css-archetype \
-DarchetypeVersion=1.0-SNAPSHOT \
-DgroupId=com.projectname.project \
-DartifactId=new-project-name \
-DarchetypeRepository=https://oss.sonatype.org/content/repositories/snapshots
```

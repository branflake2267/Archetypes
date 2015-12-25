#GWT Basic Archetype

This is a very basic GWT archetype. 

##Highlights
* [pom.xml](https://github.com/branflake2267/Archetypes/blob/master/archetypes/gwt-basic/pom.xml)
* [Project Directory](https://github.com/branflake2267/Archetypes/tree/master/archetypes/gwt-basic/src/main/java/org/gonevertical/project)
* [Entry Point Class](https://github.com/branflake2267/Archetypes/blob/master/archetypes/gwt-basic/src/main/java/org/gonevertical/project/client/ProjectEntryPoint.java)
* [web.xml](https://github.com/branflake2267/Archetypes/blob/master/archetypes/gwt-basic/src/main/webapp/WEB-INF/web.xml)
* [Welcome Page Project.html](https://github.com/branflake2267/Archetypes/blob/master/archetypes/gwt-basic/src/main/webapp/Project.html)
* [GWT Test Case](https://github.com/branflake2267/Archetypes/tree/master/archetypes/gwt-basic/src/test/java/org/gonevertical/project/client)

##Maven Archetype Usage

1. Goto directory you want the project.
2. Rename parameter below `com.projectname.project` to a package naming scheme you like.
3. Rename parameter `new-project-name` to a project title you like.
4. Rename parameter `-dmodule=Project` to a name that starts with a capital. Name it like `MyAppModule` or `Application`.
5. Run the mvn archetype generator below.

* This project Project.gwt.xml module name is hard coded and will will not be changed from the parameters below at this time.

```
mvn archetype:generate \
-DarchetypeRepository=https://oss.sonatype.org/content/repositories/snapshots \
-DarchetypeGroupId=com.github.branflake2267.archetypes \
-DarchetypeArtifactId=gwt-basic-archetype \
-DarchetypeVersion=2.0-SNAPSHOT \
-DgroupId=com.example.project \
-DartifactId=new-project-name
```

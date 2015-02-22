#GWT Basic with RPC using Tomcat
This is a very basic GWT archetype and has a simple RPC setup using Tomcat

##Highlights

* [web.xml](src/main/webapp/WEB-INF/web.xml)
* [Main Project Directory](src/main/java/org/gonevertical/project)
* [Welcome Page Project.html](src/main/webapp/Project.html)
* [Entry Point Class](src/main/java/org/gonevertical/project/client/ProjectEntryPoint.java)
* [RPC Client Directory](src/main/java/org/gonevertical/project/client/rpc)
* [RPC Server Directory](src/main/java/org/gonevertical/project/server/servlets/rpc)
* [GWT Test Cases](src/test/java/org/gonevertical/project/client)

##Maven Archetype Usage

1. Goto directory you want the project.
2. Rename parameter below `com.projectname.project` to a package naming scheme you like.
3. Rename parameter `new-project-name` to a project title you like.
4. Rename parameter `-Dmodule=Project` to a name that starts with a capital. Name it like `MyAppModule` or `Application`.
5. Run the mvn archetype generator below.


```
mvn archetype:generate -DarchetypeGroupId=com.github.branflake2267.archetypes \
-DarchetypeRepository=https://oss.sonatype.org/content/repositories/snapshots \
-DarchetypeArtifactId=gwt-basic-rpc-tomcat-archetype \
-DarchetypeVersion=1.0-SNAPSHOT \
-DgroupId=com.projectname.project \
-DartifactId=new-project-name \
-Dmodule=Project
```

##Running
1. `mvn clean install`
2. `TODO` - start web server
3. `mvn gwt:run-codeserver` - starg gwt code server
4. goto http://localhost:8080 
5. `TODO` - stop web server
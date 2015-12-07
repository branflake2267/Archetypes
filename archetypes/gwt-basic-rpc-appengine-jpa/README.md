#GWT Basic with RPC using App Engine and JPA
This is a very basic GWT archetype and has a simple RPC setup using App Engine and RPC.

##Maven Archetype Usage

1. Goto directory you want the project.
2. Rename parameter below `com.projectname.project` to a package naming scheme you like.
3. Rename parameter `new-project-name` to a project title you like.
4. Rename parameter `-Dmodule=Project` to a name that starts with a capital. Name it like `MyAppModule` or `Application`.
5. Run the mvn archetype generator below.


```
mvn archetype:generate -DarchetypeGroupId=com.github.branflake2267.archetypes \
-DarchetypeRepository=https://oss.sonatype.org/content/repositories/snapshots \
-DarchetypeArtifactId=gwt-basic-rpc-appengine-jpa-archetype \
-DarchetypeVersion=1.0-SNAPSHOT \
-DgroupId=com.projectname.project \
-DartifactId=new-project-name \
-Dmodule=Project
```

##Running
1. `mvn clean install`
2. `mvn appengine:devserver_start` - start the web server
3. `mvn gwt:run-codeserver` - start the GWT SDM code server
4. goto http://localhost:8080 
5. `mvn appengine:devserver_stop` - stop the web server

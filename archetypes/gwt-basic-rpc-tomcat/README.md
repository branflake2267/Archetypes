#GWT Basic with RPC using Tomcat
This is a very basic GWT archetype and has a simple RPC setup using Tomcat

##Videos

* [Installing the Tomcat Runtime Server](https://www.youtube.com/watch?v=6w87lVYT-MM&list=PLBbgqtDgdc_THrws_LhHs84ARxy7pUhkv)
* [Super Dev Mode debugging with GWT and the Tomcat Rumtime Server](https://www.youtube.com/watch?v=w9270Yqt-5I&list=PLBbgqtDgdc_THrws_LhHs84ARxy7pUhkv&index=2)

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
2. `mvn tomcat7:start` - start web server
3. `mvn gwt:run-codeserver` - starg gwt code server
4. goto http://localhost:8080 
5. `mvn tomcat7:stop` - stop web server

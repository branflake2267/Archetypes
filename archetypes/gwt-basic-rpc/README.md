#GWT Basic Archetype with RPC

This is a very basic GWT archetype and has a simple RPC setup. 

##Highlights
* [pom.xml](https://github.com/branflake2267/Archetypes/blob/master/archetypes/gwt-basic-rpc/pom.xml)
* [Project Directory](https://github.com/branflake2267/Archetypes/tree/master/archetypes/gwt-basic-rpc/src/main/java/org/gonevertical/project)
* [Entry Point Class](https://github.com/branflake2267/Archetypes/blob/master/archetypes/gwt-basic-rpc/src/main/java/org/gonevertical/project/client/ProjectEntryPoint.java)
* [RPC Client Directory](https://github.com/branflake2267/Archetypes/tree/master/archetypes/gwt-basic-rpc/src/main/java/org/gonevertical/project/client/rpc)
* [RPC Server Directory](https://github.com/branflake2267/Archetypes/blob/master/archetypes/gwt-basic-rpc/src/main/java/org/gonevertical/project/server/rpc/RpcServiceImpl.java)
* [web.xml](https://github.com/branflake2267/Archetypes/blob/master/archetypes/gwt-basic-rpc/src/main/webapp/WEB-INF/web.xml)
* [Welcome Page Project.html](https://github.com/branflake2267/Archetypes/blob/master/archetypes/gwt-basic-rpc/src/main/webapp/Project.html)
* [GWT Test Case](https://github.com/branflake2267/Archetypes/tree/master/archetypes/gwt-basic-rpc/src/test/java/org/gonevertical/project/client)

##Maven Archetype Usage

1. Goto directory you want the project.
2. Rename parameter below `com.projectname.project` to a package naming scheme you like.
3. Rename parameter `new-project-name` to a project title you like.
4. Run the mvn archetype generator below.

* This project Project.gwt.xml module name is hard coded and will will not be changed from the parameters below at this time.

## *Nix
```
mvn archetype:generate \
-DarchetypeRepository=https://oss.sonatype.org/content/repositories/snapshots \
-DarchetypeGroupId=com.github.branflake2267.archetypes \
-DarchetypeArtifactId=gwt-basic-rpc-archetype \
-DarchetypeVersion=2.0-SNAPSHOT \
-DgroupId=com.projectname.project.ChangeMe \
-DartifactId=new-project-name-ChangeMe
```

## *Windows
```
mvn archetype:generate ^
-DarchetypeRepository=https://oss.sonatype.org/content/repositories/snapshots ^
-DarchetypeGroupId=com.github.branflake2267.archetypes ^
-DarchetypeArtifactId=gwt-basic-rpc-archetype ^
-DarchetypeVersion=2.0-SNAPSHOT ^
-DgroupId=com.projectname.project.ChangeMe ^
-DartifactId=new-project-name-ChangeMe
```
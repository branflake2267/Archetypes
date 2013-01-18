#GWT Basic Archetype with RequestFactory

This is a very basic GWT archetype. 

##Highlights
* [Project directory](https://github.com/branflake2267/Archetypes/tree/master/archetypes/gwt-basic-requestfactory/src/main/java/org/gonevertical/project)
* [RequestFactory Client Side](https://github.com/branflake2267/Archetypes/tree/master/archetypes/gwt-basic-requestfactory/src/main/java/org/gonevertical/project/client/requestfactory)
* [RequestFactory Server Side](https://github.com/branflake2267/Archetypes/tree/master/archetypes/gwt-basic-requestfactory/src/main/java/org/gonevertical/project/server/domain)
* [web.xml](https://github.com/branflake2267/Archetypes/blob/master/archetypes/gwt-basic-requestfactory/src/main/webapp/WEB-INF/web.xml)

##Maven Archetype Usage

1. Goto directory you want the project.
2. Rename parameter below `com.projectname.project` to a package naming scheme you like.
3. Rename parameter `new-project-name` to a project title you like.
4. Run the mvn archetype generator below.

* This project Project.gwt.xml module name is hard coded and will will not be changed from the parameters below at this time.

```
mvn archetype:generate -DarchetypeGroupId=com.github.branflake2267.archetypes \
-DarchetypeArtifactId=gwt-basic-requestfactory-archetype \
-DarchetypeVersion=1.0-SNAPSHOT \
-DgroupId=com.projectname.project \
-DartifactId=new-project-name \
-DarchetypeRepository=https://oss.sonatype.org/content/repositories/snapshots
```

##Request Factory Annotation Processor Setup
* [Request Factory Annotation Processor Setup for Eclipse](http://c.gwt-examples.com/home/data-transport/request-factory/annotation-processor)
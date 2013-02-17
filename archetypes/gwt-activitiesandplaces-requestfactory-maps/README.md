#GWT Activities and Places using RequestFactory with Google Maps.

This is a using Activities and Places with Request Factory and Google Maps.

##GWT Google Maps Api
[GWT Maps V3 API](https://github.com/branflake2267/GWT-Maps-V3-Api) - This api was used for Google Maps. 

##Highlights
* [Client Server Directories](https://github.com/branflake2267/Archetypes/tree/master/archetypes/gwt-activitiesandplaces-requestfactory-maps/src/main/java/org/gonevertical/project)
* [Map Activity](https://github.com/branflake2267/Archetypes/tree/master/archetypes/gwt-activitiesandplaces-requestfactory-maps/src/main/java/org/gonevertical/project/client/application/map)
* [RequestFactory Directory](https://github.com/branflake2267/Archetypes/tree/master/archetypes/gwt-activitiesandplaces-requestfactory-maps/src/main/java/org/gonevertical/project/client/requestfactory)

##Maven Archetype Usage

1. Goto directory you want the project.
2. Rename parameter below `com.projectname.project` to a package naming scheme you like.
3. Rename parameter `new-project-name` to a project title you like.
4. Run the mvn archetype generator below.

* This project Project.gwt.xml module name is hard coded and will will not be changed from the parameters below at this time.

```
mvn archetype:generate -DarchetypeGroupId=com.github.branflake2267.archetypes \
-DarchetypeRepository=https://oss.sonatype.org/content/repositories/snapshots \
-DarchetypeArtifactId=gwt-activitiesandplaces-requestfactory-maps-archetype \
-DarchetypeVersion=1.0-SNAPSHOT \
-DgroupId=com.projectname.project \
-DartifactId=new-project-name 
```

##Request Factory Annotation Processor Setup
* [Request Factory Annotation Processor Setup for Eclipse](http://c.gwt-examples.com/home/data-transport/request-factory/annotation-processor)

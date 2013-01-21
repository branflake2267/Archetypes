#GWT Basic Archetype

This is a very basic GWT archetype. 

##Highlights
* [Project Client & Server Directory](https://github.com/branflake2267/Archetypes/tree/master/archetypes/gwt-activitiesandplaces-requestfactory/src/main/java/org/gonevertical/project)
* [ClientFactoryImpl](https://github.com/branflake2267/Archetypes/blob/master/archetypes/gwt-activitiesandplaces-requestfactory/src/main/java/org/gonevertical/project/client/ClientFactoryImpl.java)
* [Activity Mapper](https://github.com/branflake2267/Archetypes/blob/master/archetypes/gwt-activitiesandplaces-requestfactory/src/main/java/org/gonevertical/project/client/activity/ApplicationActivityMapper.java)
* [Login Presenter & View](https://github.com/branflake2267/Archetypes/tree/master/archetypes/gwt-activitiesandplaces-requestfactory/src/main/java/org/gonevertical/project/client/application/login)
* [Request Factory](https://github.com/branflake2267/Archetypes/tree/master/archetypes/gwt-activitiesandplaces-requestfactory/src/main/java/org/gonevertical/project/client/requestfactory)
* [Domain](https://github.com/branflake2267/Archetypes/tree/master/archetypes/gwt-activitiesandplaces-requestfactory/src/main/java/org/gonevertical/project/server/domain)

##Maven Archetype Usage

1. Goto directory you want the project.
2. Rename parameter below `com.projectname.project` to a package naming scheme you like.
3. Rename parameter `new-project-name` to a project title you like.
4. Run the mvn archetype generator below.

* This project Project.gwt.xml module name is hard coded and will will not be changed from the parameters below at this time.

```
mvn archetype:generate -DarchetypeGroupId=com.github.branflake2267.archetypes \
-DarchetypeRepository=https://oss.sonatype.org/content/repositories/snapshots \
-DarchetypeArtifactId=gwt-activityandplaces-requestfactory-archetype \
-DarchetypeVersion=1.0-SNAPSHOT \
-DgroupId=com.projectname.project \
-DartifactId=new-project-name \
```

##Request Factory Annotation Processor Setup
* [Request Factory Annotation Processor Setup for Eclipse](http://c.gwt-examples.com/home/data-transport/request-factory/annotation-processor)

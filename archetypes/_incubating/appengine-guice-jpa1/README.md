#App Engine Guice JPAv1

This is a simple App Engine application using Guice injection with JPA version 1. 

##Highlights
* [DAO](https://github.com/branflake2267/Archetypes/tree/master/archetypes/appengine-guice-jpa1/src/main/java/org/gonevertical/server/dao) - Data access using the entity manager
* [Domain](https://github.com/branflake2267/Archetypes/tree/master/archetypes/appengine-guice-jpa1/src/main/java/org/gonevertical/server/domain) - Domain objects that are persisted to App Engine
* [Guice](https://github.com/branflake2267/Archetypes/tree/master/archetypes/appengine-guice-jpa1/src/main/java/org/gonevertical/server/guice) - Guice configuration which initializes JPA's entity manager
* [Servlets](https://github.com/branflake2267/Archetypes/tree/master/archetypes/appengine-guice-jpa1/src/main/java/org/gonevertical/server/servlets) - Example servlets


##Articles
* [Ultimate GWT + GAE + Datanucleus + Eclipse + Maven Guide](http://bpossolo.blogspot.com/2013/01/the-ultimate-guide-to-gwt-gae-maven.html)


##Maven Archetype Usage

1. Goto directory you want the project.
2. Rename parameter below `com.projectname.project` to a package naming scheme you like.
3. Rename parameter `new-project-name` to a project title you like.
4. Run the mvn archetype generator

```
mvn archetype:generate -DarchetypeGroupId=com.github.branflake2267.archetypes \
-DarchetypeArtifactId=appengine-guice-jpa1-archetype \
-DarchetypeVersion=1.0-SNAPSHOT \
-DgroupId=com.projectname.project \
-DartifactId=new-project-name \
-DarchetypeRepository=https://oss.sonatype.org/content/repositories/snapshots
```
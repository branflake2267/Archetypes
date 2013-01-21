#GWT App Engine Guice JPAv1

This is a simple GWT App Engine application using Guice injection with JPA version 1. 

##Highlights
* [Project Directory](https://github.com/branflake2267/Archetypes/tree/master/archetypes/gwt-appengine-guice-jpa1/src/main/java/org/gonevertical/project)

###Client
* [Project Entry Point](https://github.com/branflake2267/Archetypes/blob/master/archetypes/gwt-appengine-guice-jpa1/src/main/java/org/gonevertical/project/client/ProjectEntryPoint.java)
* [RPC Directory](https://github.com/branflake2267/Archetypes/tree/master/archetypes/gwt-appengine-guice-jpa1/src/main/java/org/gonevertical/project/client/rpc)
* [Project.gwt.xml](https://github.com/branflake2267/Archetypes/blob/master/archetypes/gwt-appengine-guice-jpa1/src/main/java/org/gonevertical/project/Project.gwt.xml)

###Server
* [DAO](https://github.com/branflake2267/Archetypes/tree/master/archetypes/gwt-appengine-guice-jpa1/src/main/java/org/gonevertical/project/server/dao) - Data access using the entity manager
* [Domain](https://github.com/branflake2267/Archetypes/tree/master/archetypes/gwt-appengine-guice-jpa1/src/main/java/org/gonevertical/project/server/domain) - Domain objects that are persisted to App Engine
* [Guice](https://github.com/branflake2267/Archetypes/tree/master/archetypes/gwt-appengine-guice-jpa1/src/main/java/org/gonevertical/project/server/guice) - Guice configuration which initializes JPA's entity manager
* [Servlets](https://github.com/branflake2267/Archetypes/tree/master/archetypes/gwt-appengine-guice-jpa1/src/main/java/org/gonevertical/project/server/servlets) - Example servlets

##Articles
* [Ultimate GWT + GAE + Datanucleus + Eclipse + Maven Guide](http://bpossolo.blogspot.com/2013/01/the-ultimate-guide-to-gwt-gae-maven.html)

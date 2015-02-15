#App Engine Endpoints using JDO
This is a simple App Engine application with Maven, Guice, JDO, and Datanucleus.

##More App Engine Archetypes

 * [Google Maven App Engine Archetypes](https://cloud.google.com/appengine/docs/java/tools/maven#maven_app_engine_archetypes)

##Eclipse WTP

* [Install WTP for Eclipse](http://wiki.eclipse.org/WTP_FAQ#How_do_I_install_WTP.3F)
* [Google WTP for Eclipse](https://cloud.google.com/appengine/docs/java/webtoolsplatform)

##Maven Archetype Usage

1. Goto directory you want the project.
2. Rename parameter below `com.projectname.project` to a package naming scheme you like.
3. Rename parameter `new-project-name` to a project title you like.
4. Run the mvn archetype generator.

```
mvn archetype:generate \
-DarchetypeRepository=https://oss.sonatype.org/content/repositories/snapshots \
-DarchetypeGroupId=com.github.branflake2267.archetypes \
-DarchetypeArtifactId=appengine-endpoints-guice-jdo-archetype \
-DarchetypeVersion=1.0-SNAPSHOT \
-DgroupId=com.projectname.project \
-DartifactId=new-project-name
```

###IDE Import

* [Create Archetype in Eclipse Video](https://www.youtube.com/watch?v=5QPOAXLGB2Y&list=PLBbgqtDgdc_RBdHY5TpQRRvjo1_1BTVkh&index=1)
* [Create Archetype in IntelliJ IDEA Video](https://www.youtube.com/watch?v=XD9anp_p4mc&list=PLBbgqtDgdc_RBdHY5TpQRRvjo1_1BTVkh&index=2)


##After Import
1. Either run `mvn clean install` or Eclipse `Project Clean`


##Running
Running App Engine from the terminal. 

* `mvn clean install`
* `mvn mvn appengine:devserver_start` - start web server
* `mvn mvn appengine:devserver_stop` - stop web server

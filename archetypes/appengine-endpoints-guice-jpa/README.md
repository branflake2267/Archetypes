#App Engine Endpoints using JPA
This is a simple App Engine application with Maven, Guice, JPA, and Datanucleus.

##More App Engine Archetypes
 * [Google Maven App Engine Archetypes](https://cloud.google.com/appengine/docs/java/tools/maven#maven_app_engine_archetypes)
 * [Good App Engine, JPA, Maven blog post](http://www.loop81.com/2013/02/gae-google-app-engine-jpa2-maven-and.html)

##Eclipse WTP
* [Install WTP for Eclipse](http://wiki.eclipse.org/WTP_FAQ#How_do_I_install_WTP.3F)
* [Google WTP for Eclipse](https://cloud.google.com/appengine/docs/java/webtoolsplatform)

##Maven Archetype Usage

1. Goto directory you want the project.
2. Rename parameter below `com.projectname.project` to a package naming scheme you like.
3. Rename parameter `new-project-name` to a project title you like.
4. Run the mvn archetype generator

```
mvn archetype:generate \
-DarchetypeRepository=https://oss.sonatype.org/content/repositories/snapshots \
-DarchetypeGroupId=com.github.branflake2267.archetypes \
-DarchetypeArtifactId=appengine-endpoints-archetype \
-DarchetypeVersion=1.0-SNAPSHOT \
-DgroupId=com.projectname.project \
-DartifactId=new-project-name
```

###IDE Import

* [Create Archetype in Eclipse Video](https://www.youtube.com/watch?v=5QPOAXLGB2Y&list=PLBbgqtDgdc_RBdHY5TpQRRvjo1_1BTVkh&index=1)
* [Create Archetype in IntelliJ IDEA Video](https://www.youtube.com/watch?v=XD9anp_p4mc&list=PLBbgqtDgdc_RBdHY5TpQRRvjo1_1BTVkh&index=2)


## More about endpoints (from the endpoints-sekeleton archetype)


A skeleton application for Google Cloud Endpoints in Java.

- [App Engine][1]
- [Java][2]
- [Google Cloud Endpoints][3]
- [Google App Engine Maven plugin][4]


1. Update the value of `application` in `appengine-web.xml` to the app
   ID you have registered in the App Engine admin console and would
   like to use to host your instance of this sample.

1. Add your API method to `src/main/java/com/gonevertical/YourFirstAPI.java`.

1. Optional step: These sub steps are not required but you need this
   if you want to have auth protected methods.

    1. Update the values in `src/main/java/com/gonevertical/Constants.java`
       to reflect the respective client IDs you have registered in the
       [APIs Console][6]. 

    1. You also need to supply the web client ID you have registered
       in the [APIs Console][4] to your client of choice (web, Android,
       iOS).

1. Run the application with `mvn appengine:devserver`, and ensure it's
   running by visiting your local server's api explorer's address (by
   default [localhost:8080/_ah/api/explorer][5].)

1. Get the client library with

   $ mvnappengine:endpoints_get_client_lib

   It will generate a client library jar file under the
   `target/endpoints-client-libs/<api-name>/target` directory of your
   project, as well as install the artifact into your local maven
   repository.

1. Deploy your application to Google App Engine with

   $ mvn appengine:update

[1]: https://developers.google.com/appengine
[2]: http://java.com/en/
[3]: https://developers.google.com/appengine/docs/java/endpoints/
[4]: https://developers.google.com/appengine/docs/java/tools/maven
[5]: https://localhost:8080/_ah/api/explorer
[6]: https://console.developers.google.com/



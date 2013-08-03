#GWTP Basic with GXT library

This is a basic GWTP Archetype with the GXT library setup.

##GXT Guides
* [GXT Home](http://www.sencha.com/products/gxt/)
* [Examples and Demos](http://www.sencha.com/products/gxt/examples/)
* [Guides](http://docs.sencha.com/gxt-guides/3/)
* [LayoutContainers](http://docs.sencha.com/gxt-guides/3/ui/layout/LayoutContainers.html)

##Highlights
* [Presenters Directory](https://github.com/branflake2267/Archetypes/tree/master/archetypes/gxt-gwtp-withlayout/src/main/java/com/arcbees/project/client/application)
* [Nested Presenter Content Slots](https://github.com/branflake2267/Archetypes/blob/master/archetypes/gxt-gwtp-withlayout/src/main/java/com/arcbees/project/client/application/ApplicationPresenter.java#L20)
* [DockLayout with GXT ContentPanels](https://github.com/branflake2267/Archetypes/blob/master/archetypes/gxt-gwtp-withlayout/src/main/java/com/arcbees/project/client/application/ApplicationView.ui.xml#L10)

##Maven Archetype Usage

1. Goto directory you want the project.
2. Rename parameter below `com.projectname.project` to a package naming scheme you like.
3. Rename parameter `new-project-name` to a project title you like.
4. Rename parameter `-dmodule=Project` to a name that starts with a capital. Name it like `MyAppModule` or `Application`.
5. Run the mvn archetype generator below.

* This project Project.gwt.xml module name is hard coded and will will not be changed from the parameters below at this time.

```
mvn archetype:generate -DarchetypeGroupId=com.github.branflake2267.archetypes \
-DarchetypeRepository=https://oss.sonatype.org/content/repositories/snapshots/ \
-DarchetypeArtifactId=gxt-gwtp-withlayout-archetype \
-DarchetypeVersion=1.0-SNAPSHOT \
-DgroupId=com.projectname.project \
-DartifactId=new-project-name \
-Dmodule=Project
```

##Thanks to
[![Arcbees.com](http://arcbees-ads.appspot.com/ad.png)](http://arcbees.com)

[![IntelliJ](https://lh6.googleusercontent.com/--QIIJfKrjSk/UJJ6X-UohII/AAAAAAAAAVM/cOW7EjnH778/s800/banner_IDEA.png)](http://www.jetbrains.com/idea/index.html)

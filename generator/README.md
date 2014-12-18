#GWT Archetype Generator
Generate GWT Archetypes from projects


##Deploy generator snapshot

* Run `mvn deploy`


##Maven Configuration

```
<!-- Turn on snapshots for this dependency. -->
<dependency>
  <groupId>com.github.branflake2267.archetypes</groupId>
  <artifactId>archetype-generator</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency>
```


##Running

```
List<String> projects = new ArrayList<String>();
projects.add("gwt-basic"); // directory in archetypes

GwtArchetypeGenerator generate = new GwtArchetypeGenerator();
generate.setProjects(projects);
generate.setDeploy(true);
generate.run();
```


## Reference

###Directory structure

* `repo/archetypes/[archetype projects]`
* `repo/generator/[generator runner]`
* `repo/generated/[generated archetypes]`

###Reserved
Reserved words in generator.

1. `Project` will be renamed to a variable for module
2. If the project package naming matches library package naming, there could be a problem. 

###No Server Classes
When there are no server classes add: `server/EmptyNess.java`, add this will get removed. 
#!/bin/sh
# Test import archetype

mvn clean
mkdir target
mkdir target/test
mkdir target/test/gwtp-basic
cd target/test/gwtp-basic

mvn archetype:generate -DarchetypeGroupId=com.github.branflake2267.archetypes \
-DarchetypeRepository=https://oss.sonatype.org/content/repositories/snapshots \
-DarchetypeArtifactId=gwt-activityandplaces-requestfactory-archetype \
-DarchetypeVersion=1.0-SNAPSHOT \
-DgroupId=com.projectname.project \
-DartifactId=new-project-name \
-DinteractiveMode=false

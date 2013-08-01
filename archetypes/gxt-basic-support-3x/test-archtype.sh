#!/bin/sh
# Test import archetype

mvn clean
mkdir target
mkdir target/test
cd target/test

mvn archetype:generate -DarchetypeGroupId=com.github.branflake2267.archetypes \
-DarchetypeRepository=https://oss.sonatype.org/content/repositories/snapshots \
-DarchetypeArtifactId=gxt-basic-support-3x-archetype \
-DarchetypeVersion=1.0-SNAPSHOT \
-DgroupId=com.projectname.project \
-DartifactId=new-project-name \
-DinteractiveMode=false

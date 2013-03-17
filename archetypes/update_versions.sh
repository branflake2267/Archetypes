#!/bin/sh
# Update pom versions

# GWT
find . -name 'pom.xml' -type f -exec sed -i '' 's/<gwt.version>.*<\/gwt.version>/<gwt.version>2.5.1<\/gwt.version>/g' {} \;

# Google App engine
find . -name 'pom.xml' -type f -exec sed -i '' 's/<gae.version>.*<\/gae.version>/<gae.version>1.7.5<\/gae.version>/g' {} \;
find . -name 'pom.xml' -type f -exec sed -i '' 's/<maven-gae-plugin.version>.*<\/maven-gae-plugin.version>/<maven-gae-plugin.version>0.9.5<\/maven-gae-plugin.version>/g' {} \;

echo "Update Versions Finished"
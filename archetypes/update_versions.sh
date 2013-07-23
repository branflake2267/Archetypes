#!/bin/sh
# Update pom versions

# GWT
find . -name 'pom.xml' -type f -exec sed -i '' 's/<gwt.version>.*<\/gwt.version>/<gwt.version>2.5.1<\/gwt.version>/g' {} \;
find . -name 'pom.xml' -type f -exec sed -i '' 's/<gwt-maven-plugin.version>.*<\/gwt-maven-plugin.version>/<gwt-maven-plugin.version>2.5.1<\/gwt-maven-plugin.version>/g' {} \;

# Google App engine
find . -name 'pom.xml' -type f -exec sed -i '' 's/<gae.version>.*<\/gae.version>/<gae.version>1.8.0<\/gae.version>/g' {} \;
find . -name 'pom.xml' -type f -exec sed -i '' 's/<maven-gae-plugin.version>.*<\/maven-gae-plugin.version>/<maven-gae-plugin.version>0.9.6<\/maven-gae-plugin.version>/g' {} \;

# GWTP
find . -name 'pom.xml' -type f -exec sed -i '' 's/<gwtp.version>.*<\/gwtp.version>/<gwtp.version>1.0<\/gwtp.version>/g' {} \;

echo "Update Versions Finished"
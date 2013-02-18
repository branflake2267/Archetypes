#!/bin/sh
# Generate a new archetype using this project as a template.
# Once the archetype has been created run 'mvn archetype:generate -DarchetypeCatalog=local' in a new directory
# http://www.automatedbusinesslogic.com/articles/deploying-a-maven-archetype

echo "Started"

BuildArchetypeInDirectory()
{
    PROJECTDIR=$1
    echo "Working in::: $PROJECTDIR";

    # move to the archetype
    cd $PROJECTDIR

    # clean house
    mvn clean
    mvn idea:clean
    mvn eclipse:clean
    rm -R .settings
    rm -R bin
    rm -R *.iml
    rm -R .idea
    rm -R .gwt
    rm .DS_Store
    rm -R war
    rm -R www-test
    rm -R gwt-unitCache
    rm -R .gwt-tmp

    # generate archetype
    echo "mvn archetype:create-from-project"
    mvn archetype:create-from-project

    # move to generated archetype base
    cd target/generated-sources/archetype/

    # clean up files in project.
    find . -name "*.sh" -type f -exec rm -f {} \;

    # sed -i works differently on mac and linux.
    # work around b/c com.arcbees inherits conflicts
    if [ $(uname) = "Darwin" ]; then
        find . -name '*.xml' -type f -exec sed -i '' 's/<module>.*\.\(.*\)<\/module>/<module>${package}.\1<\/module>/g' {} \;
    else
        find . -name '*.xml' -type f -exec sed -i 's/<module>.*\.\(.*\)<\/module>/<module>${package}.\1<\/module>/g' {} \;
    fi

    cd $CURRENTDIR

    # add deployment to pom.xml for deployment to sonatype
    SONATYPE="<distributionManagement><repository><id>sona-nexus-deploy<\/id><url>https:\/\/oss.sonatype.org\/service\/local\/staging\/deploy\/maven2<\/url><\/repository><snapshotRepository><id>sona-nexus-deploy<\/id><url>https:\/\/oss.sonatype.org\/content\/repositories\/snapshots<\/url><\/snapshotRepository><\/distributionManagement><\/project>"
    echo $SONATYPE

    sed -ie "s@<\/project>@${SONATYPE}@g" $PROJECTDIR/target/generated-sources/archetype/pom.xml
    cd $PROJECTDIR/target/generated-sources/archetype

    # deploy to sonatype
    mvn deploy
}

LoopDirectory()
{
    echo "find . -maxdepth 1 -type d -name '[a-z]*'"
    for dir in `find .  -maxdepth 1 -type d -name '[a-z]*'`; do
    echo "~~~~~ Processing::: $dir ~~~~~~~~"
    #BuildArchetypeInDirectory $dir
    done
}

CURRENTDIR=`pwd`

if [ ! -z $1 ] ;
then
    echo "~~~~~ Processing::: $1 ~~~~~~"
    BuildArchetypeInDirectory $1
else
    LoopDirectory
fi

echo "Finished"

# TODO produce a catalog
#mvn archetype:crawl -Dcatalog=$CURRENTDIR/archetype-catalog.xml
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
    rm -R .factorypath
    rm -R .apt_generated

    # generate archetype
    echo "mvn archetype:create-from-project"
    mvn archetype:create-from-project

    cd target/generated-sources/archetype/
    mvn install

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

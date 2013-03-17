#!/bin/sh
# Generate a new archetype using this project as a template.
# Once the archetype has been created run 'mvn archetype:generate -DarchetypeCatalog=local' in a new directory
# http://www.automatedbusinesslogic.com/articles/deploying-a-maven-archetype

RemoveFiles() 
{
    find . -name $1 -type f -exec rm -rf {} \;
}

ModifyPom() 
{
    # Add package var to the module declaration
    # sed -i works differently on mac and linux.
    # work around b/c com.arcbees inherits conflicts
    if [ $(uname) = "Darwin" ]; then
        find . -name '*.xml' -type f -exec sed -i '' 's/<module>.*\.\(.*\)<\/module>/<module>${package}.\1<\/module>/g' {} \;
    else
        find . -name '*.xml' -type f -exec sed -i 's/<module>.*\.\(.*\)<\/module>/<module>${package}.\1<\/module>/g' {} \;
    fi
    
    # Rename Project to __module__ 
    if [ $(uname) = "Darwin" ]; then
        find . -name '*.html' -type f -exec sed -i '' 's/project/${module}/g' {} \;
        find . -name '*.xml' -type f -exec sed -i '' 's/project/${module}/g' {} \;
        find . -name '*.xml' -type f -exec sed -i '' 's/Project/${module}/g' {} \;
        find . -name '*.java' -type f -exec sed -i '' 's/Project/${module}/g' {} \;
        find . -name '*.xml' -type f -exec sed -i '' 's/ProjectEntryPoint/${module}EntryPoint/g' {} \;
    else
        find . -name '*.html' -type f -exec sed -i 's/project/${module}/g' {} \;
        find . -name '*.xml' -type f -exec sed -i 's/project/${module}/g' {} \;
        find . -name '*.xml' -type f -exec sed -i 's/Project/${module}/g' {} \;
        find . -name '*.java' -type f -exec sed -i 's/Project/${module}/g' {} \;
        find . -name '*.xml' -type f -exec sed -i 's/ProjectEntryPoint/${module}EntryPoint/g' {} \;
    fi
}

ModifyArchetypeMetaData() 
{
    #Add required property "module"
    METAMODULE="<requiredProperties><requiredProperty key=\"module\"><defaultValue>Project<\/defaultValue><\/requiredProperty><\/requiredProperties><fileSets>"
    
    if [ $(uname) = "Darwin" ]; then
        find . -name '*archetype-metadata.xml' -type f -exec sed -i '' 's/<fileSets>/'"$METAMODULE"'/g' {} \;
    else
        find . -name '*archetype-metadata.xml' -type f -exec sed -i 's/<fileSets>/'"$METAMODULE"'/g' {} \;
    fi
}

RenameModuleFiles() 
{
    mv src/main/resources/archetype-resources/src/main/java/client/ProjectEntryPoint.java src/main/resources/archetype-resources/src/main/java/client/__module__EntryPoint.java
    mv src/main/resources/archetype-resources/src/main/webapp/Project.html src/main/resources/archetype-resources/src/main/webapp/__module__.html
    mv src/main/resources/archetype-resources/src/main/webapp/Project.css src/main/resources/archetype-resources/src/main/webapp/__module__.css
    mv src/main/resources/archetype-resources/src/main/java/Project.gwt.xml src/main/resources/archetype-resources/src/main/java/__module__.gwt.xml
}

DeployToSonatype()
{
    cd $SCRIPTDIR

    # add deployment to pom.xml for deployment to sonatype
    SONATYPE=( $( ./deploy-pom-template.txt ) )
    echo $SONATYPE

    sed -ie 's/<\/project>/'"$SONATYPE"'/g' $PROJECTDIR/target/generated-sources/archetype/pom.xml
    cd $PROJECTDIR/target/generated-sources/archetype

    #TODO deploy to sonatype
    #mvn deploy
}

ArchetypeCleaning()
{
    # house cleaning
    RemoveFiles "*.sh"
    RemoveFiles "*.DS_Store"
    RemoveFiles ".settings"
    RemoveFiles "bin"
    RemoveFiles "*.iml"
    RemoveFiles ".gwt"
    RemoveFiles "www-test"
    RemoveFiles "war"
    RemoveFiles "gwt-unitCache"
    RemoveFiles ".gwt-tmp"
    RemoveFiles "README.md"
    RemoveFiles "EmptyNess.java"
}

BuildArchetypeInDirectory()
{
    PROJECTDIR=$1
    echo "Working in directory=$PROJECTDIR";

    # move to the archetype
    cd $PROJECTDIR
    
    # clean previous build
    mvn clean

    # generate archetype
    echo "mvn archetype:create-from-project"
    mvn archetype:create-from-project

    # move to generated archetype base
    cd target/generated-sources/archetype/
 
    ArchetypeCleaning

    ModifyPom
    
    ModifyArchetypeMetaData
    
    RenameModuleFiles
    
    DeployToSonatype
}

echo "Started"

SCRIPTDIR=`pwd`

if [ ! -z $1 ] ;
then
    echo "~~~~~ Processing::: $1 ~~~~~~"
    BuildArchetypeInDirectory $1
fi

echo "Finished"


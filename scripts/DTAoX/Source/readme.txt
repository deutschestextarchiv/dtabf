CLEAN, COMPILE and BUILD instructions for the DTA-oXygen-Framework DTAoX

Copyright 2013 Deutsches Textarchiv (DTA, http://www.deutschestextarchiv.de)

BUILD

1) Copy the latest version of oxygen.jar - API-Library to the ./lib - Directory
You can download the API-Library-File with Oxygen from http://www.oxygenxml.com/download.html and find the file in [Oxygen Main Directory]/lib/oxygen.jar

2) Make sure Java Development Kit is installed

3) Make sure Apache Ant is successfully installed
3.a) You can download Apache Ant from http://ant.apache.org/bindownload.cgi
3.b) For configuration infos look at http://ant.apache.org/manualdownload.cgi

4) Open top directory of source download in Windows command line tool and process build.xml with Ant to build the DTAoX.jar - file
> ant makejar

To implement the build srouce to the installed framework, copy the DTAoX.jar from ./build - Directory to [Oxygen Main Directory]/frameworks/DTAoX/Extension - Directory


COMPILE

1-3) Follow steps 1, 2 and 3 of BUILD instructions

4) Open top directory of source download in Windows command line tool and process build.xml with Ant to compile the *.class files in ./bin - directory
> ant compile


CLEAN

1) Open top directory of source download in Windows command line tool and process build.xml with Ant to delete all build files
> ant cleanall
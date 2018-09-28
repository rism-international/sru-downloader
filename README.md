Description
------------
This dead simple basic program simplifies the download of MARCXML-records at the SRU-interface of RISM. It takes the current limitation of 100 records in account, so the startRecord-parameter of the SRU-interface is taken for harvesting the complete set.
Output-file is output.xml at the working directory.

Requirements
-------------
Java 8 JRE, see eg. http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html .

Install
--------
__For a more complete installation and how-to description - especially with windows - see this [tutorial](tutorial.pdf)__.

If you only want to install the jar-file:
* on Linux
```bash
wget https://github.com/rism-international/sru-downloader/raw/master/SRUDownloader.jar
```
* on Windows
Open this link in a browser:
```bash
https://github.com/rism-international/sru-downloader/raw/master/SRUDownloader.jar
```

Example Usage
-------------
E.g. to download all records from D-NLa:
```bash
$> java -jar SRUDownloader.jar "http://muscat.rism.info/sru/sources?operation=searchRetrieve&version=1.1&query=siglum=D-NLa&maximumRecords=100"
```
For a complete description of the SRU-interface see https://github.com/rism-ch/muscat/wiki/SRU .

Modifications
-------------
After modifying the source file:
```bash
javac SRUDownloader.java && jar cvfm SRUDownloader.jar MANIFEST.MF *.class
```

Muscats HTTPS Connection
------------------------

Since 2018 the Muscat-Server is using an Secure Socket connection. In the consequence of it, you have to make sure, that the *.rism.info certificate is part of the trusted certificates on your system. Java usually maintains its own trust Store, which is usually in `<java-home>/lib/security/cacerts`. Please consult the java documentation on how to add a certificate to this file.

If you don't want to or cannot change this file, you can make use of the InstallCert Class which is provided in this repository. InstallCert will make a copy of the trust store in a local folder and add our certificat to it. The InstallCert programme is quite well documented here:

http://nodsw.com/blog/leeland/2006/12/06-no-more-unable-find-valid-certification-path-requested-target

The file is compiled like this:

```
javac InstallCert.java
```

After that you run it like this:

```
java InstallCert muscat.rism.info
```

Finally you just have to add the following to you call to the SRUDownloader:

`-Djavax.net.ssl.trustStore=$PWD/jssecacerts` if you are on linux

`-Djavax.net.ssl.trustStore="C:\The\Path\To\Your\Local\Directory\jssecacerts` if you are on Windows.

Our above example would look like this:

```
$> java -Djavax.net.ssl.trustStore=$PWD/jssecacerts -jar SRUDownloader.jar "https://muscat.rism.info/sru/sources?operation=searchRetrieve&version=1.1&query=siglum=D-NLa&maximumRecords=100"
```

Please note the 's' in https!!!

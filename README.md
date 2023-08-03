Description
------------
This dead simple basic program simplifies the download of MARCXML-records at the SRU-interface of RISM. It takes the current limitation of 100 records in account, so the startRecord-parameter of the SRU-interface is taken for harvesting the complete set.
Output-file is output.xml at the working directory.

Requirements
-------------
Java 8 JRE

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
$> java -jar SRUDownloader.jar "https://muscat.rism.info/sru/sources?operation=searchRetrieve&version=1.1&query=siglum=D-NLa&maximumRecords=100"
```
For a complete description of the SRU-interface see https://github.com/rism-ch/muscat/wiki/SRU .

Modifications
-------------
After modifying the source file:
```bash
javac SRUDownloader.java && jar cvfm SRUDownloader.jar MANIFEST.MF *.class
```

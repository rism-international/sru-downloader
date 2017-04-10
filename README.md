Description
------------
This program simplifies the download of MARCXML-records at the SRU-interface of RISM. It takes the current limitation of 100 records in account, so the startRecord-parameter of the SRU-interface is taken for harvesting the complete file.
Output-file is output.xml at the working directory.

Requirements
-------------
Java 7 or higher.

Example Usage
-------------
E.g. to download all records from D-NLa:
```bash
$java -jar SRUDownloader.jar "http://muscat.rism.info/sru/sources?operation=searchRetrieve&version=1.1&query=siglum=D-NLa&maximumRecords=100"
```

Modifications
-------------
After modifying the source file:
```bash
javac SRUDownloader.java && jar cvfm SRUDownloader.jar MANIFEST.MF *.class
```

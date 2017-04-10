import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.io.File;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class SRUDownloader
{
  public static void main(String[] args) 
  {
    try {
      new SRUDownloader().start(args[0]);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void start(String srurl) throws Exception
  {
    URL turl = new URL(srurl);
    URLConnection tconnection = turl.openConnection();

    Document tdoc = parseXML(tconnection.getInputStream());
    int No = Integer.parseInt(tdoc.getElementsByTagName("zs:numberOfRecords").item(0).getTextContent());
    System.out.println(No);
    
    FileOutputStream ofile = new FileOutputStream("output.xml", true);
    String startcontent = "<zs:searchRetrieveResponse xmlns:zs='http://www.loc.gov/zing/srw/' xmlns:marc='http://www.loc.gov/MARC21/slim'><zs:version>1.1</zs:version><zs:numberOfRecords>"+No+"</zs:numberOfRecords><zs:records>";
    byte[] scontent = startcontent.getBytes();
    ofile.write(scontent);

    Result output = new StreamResult(ofile);
    for(int i=1; i<No;i+=100)
    {
      String startRecord = "&startRecord="+i;
      System.out.println(i);
      URL url = new URL(srurl + startRecord);
      URLConnection connection = url.openConnection();
      Transformer transformer = TransformerFactory.newInstance().newTransformer();
      Document doc = parseXML(connection.getInputStream());
      NodeList descNodes = doc.getElementsByTagName("zs:record");

      for(int x=0; x<descNodes.getLength();x++)
      {
        DOMSource source = new DOMSource(descNodes.item(x));
        transformer.transform(source, output);
      }
    }
    String endcontent = "</zs:records><zs:echoedSearchRetrieveRequest><zs:version>1.1</zs:version><zs:query>NoN</zs:query><zs:maximumRecords>10</zs:maximumRecords><zs:recordPacking>xml</zs:recordPacking></zs:echoedSearchRetrieveRequest></zs:searchRetrieveResponse>";
     byte[] econtent = endcontent.getBytes();
     ofile.write(econtent);
  }

  private Document parseXML(InputStream stream)
    throws Exception
  {
    DocumentBuilderFactory objDocumentBuilderFactory = null;
    DocumentBuilder objDocumentBuilder = null;
    Document doc = null;
   try
    {
      objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
      objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

      doc = objDocumentBuilder.parse(stream);
    }
    catch(Exception ex)
    {
      throw ex;
    }       
    return doc;
  }
}


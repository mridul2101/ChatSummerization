package GoldSummary;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
  
public class CreateTestData { 
  
    public static void main(String args[]){ 
          
        String uri="E:\\Dropbox\\Dump\\bc3\\annotation.xml";
          
        try{ 
            SAXParserFactory factory = SAXParserFactory.newInstance(); 
            SAXParser saxParser = factory.newSAXParser(); 
            final FileWriter fw; 
            File file; 
            file = new File("E:\\Dropbox\\Dump\\bc3\\gold_summary.html"); 
            fw = new FileWriter(file.getAbsoluteFile()); 
            fw.append("<html>\n<head><title>goldsummary.html</title></head>\n<body bgcolor = \"white\">\n"); 
            DefaultHandler handler = new DefaultHandler() { 
                   
                boolean fsent = false; 
                StringBuilder vsent; 
                int num1 = 0; 
                public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException { 
               
                    //System.out.println("Start Element :" + qName); 
                      
                    if (qName.equalsIgnoreCase("SENT")) {                    
                        fsent = true; 
                        vsent = new StringBuilder(); 
                    } 
                } 
               
                public void endElement(String uri, String localName, 
                    String qName) throws SAXException { 
                    if (qName.equalsIgnoreCase("SENT")) { 
                        try { 
                        	if(vsent.length() != 0){
                        		num1++; 
                        		fw.append("<a name=\"" + num1 +"\">[" + num1 +"]</a>"); 
                        		fw.append(" <a href=\"#" + num1 + "\" id="+ num1 + ">" + vsent.toString() + "</a>\n");
                        	}
                        } catch (IOException e) { 
                            // TODO Auto-generated catch block 
                            e.printStackTrace(); 
                        } 
                          
                        fsent = false; 
                    } 
                } 
                public void characters(char ch[], int start, int length) throws SAXException { 
               
                    //System.out.println(new String(ch, start, length)); 
                    if (fsent) { 
                        vsent.append(new String(ch, start, length)); 
                    } 
                } 
              }; 
              saxParser.parse(uri, handler); 
              fw.append("</body>\n</html>"); 
              fw.close(); 
                   
        } 
        catch(ParserConfigurationException e){ 
            e.printStackTrace(); 
               System.out.println("EXCEPTION : "+e.getMessage()); 
        } 
        catch(IOException e){ 
            e.printStackTrace(); 
               System.out.println("EXCEPTION : "+e.getMessage()); 
        } 
        catch (SAXParseException e)  
        { 
            e.printStackTrace(); 
           System.out.println("EXCEPTION : "+e.getMessage()); 
        } 
        catch (SAXException e)  
        { 
            e.printStackTrace(); 
           System.out.println("EXCEPTION : "+e.getMessage()); 
        }   
        catch(Exception e){ 
            e.printStackTrace(); 
            System.out.println("EXCEPTION : "+e.getMessage()); 
        } 
    } 
} 

package org.newdawn.slick.util.xml;

import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.ResourceLoader;
import org.w3c.dom.Document;

public class XMLParser
{
  private static DocumentBuilderFactory factory;
  
  public XMLElement parse(String ref)
    throws SlickException
  {
    return parse(ref, ResourceLoader.getResourceAsStream(ref));
  }
  
  public XMLElement parse(String name, InputStream local_in)
    throws SlickXMLException
  {
    try
    {
      if (factory == null) {
        factory = DocumentBuilderFactory.newInstance();
      }
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(local_in);
      return new XMLElement(doc.getDocumentElement());
    }
    catch (Exception builder)
    {
      throw new SlickXMLException("Failed to parse document: " + name, builder);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.util.xml.XMLParser
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
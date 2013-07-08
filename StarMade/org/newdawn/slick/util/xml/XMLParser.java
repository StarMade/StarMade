/*  1:   */package org.newdawn.slick.util.xml;
/*  2:   */
/*  3:   */import java.io.InputStream;
/*  4:   */import javax.xml.parsers.DocumentBuilder;
/*  5:   */import javax.xml.parsers.DocumentBuilderFactory;
/*  6:   */import org.newdawn.slick.SlickException;
/*  7:   */import org.newdawn.slick.util.ResourceLoader;
/*  8:   */import org.w3c.dom.Document;
/*  9:   */
/* 31:   */public class XMLParser
/* 32:   */{
/* 33:   */  private static DocumentBuilderFactory factory;
/* 34:   */  
/* 35:   */  public XMLElement parse(String ref)
/* 36:   */    throws SlickException
/* 37:   */  {
/* 38:38 */    return parse(ref, ResourceLoader.getResourceAsStream(ref));
/* 39:   */  }
/* 40:   */  
/* 47:   */  public XMLElement parse(String name, InputStream in)
/* 48:   */    throws SlickXMLException
/* 49:   */  {
/* 50:   */    try
/* 51:   */    {
/* 52:52 */      if (factory == null) {
/* 53:53 */        factory = DocumentBuilderFactory.newInstance();
/* 54:   */      }
/* 55:55 */      DocumentBuilder builder = factory.newDocumentBuilder();
/* 56:56 */      Document doc = builder.parse(in);
/* 57:   */      
/* 58:58 */      return new XMLElement(doc.getDocumentElement());
/* 59:   */    } catch (Exception e) {
/* 60:60 */      throw new SlickXMLException("Failed to parse document: " + name, e);
/* 61:   */    }
/* 62:   */  }
/* 63:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.xml.XMLParser
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
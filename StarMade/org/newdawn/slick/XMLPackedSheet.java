package org.newdawn.slick;

import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.newdawn.slick.util.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLPackedSheet
{
  private Image image;
  private HashMap sprites = new HashMap();
  
  public XMLPackedSheet(String imageRef, String xmlRef)
    throws SlickException
  {
    this.image = new Image(imageRef, false, 2);
    try
    {
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document doc = builder.parse(ResourceLoader.getResourceAsStream(xmlRef));
      NodeList list = doc.getElementsByTagName("sprite");
      for (int local_i = 0; local_i < list.getLength(); local_i++)
      {
        Element element = (Element)list.item(local_i);
        String name = element.getAttribute("name");
        int local_x = Integer.parseInt(element.getAttribute("x"));
        int local_y = Integer.parseInt(element.getAttribute("y"));
        int width = Integer.parseInt(element.getAttribute("width"));
        int height = Integer.parseInt(element.getAttribute("height"));
        this.sprites.put(name, this.image.getSubImage(local_x, local_y, width, height));
      }
    }
    catch (Exception builder)
    {
      throw new SlickException("Failed to parse sprite sheet XML", builder);
    }
  }
  
  public Image getSprite(String name)
  {
    return (Image)this.sprites.get(name);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.XMLPackedSheet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
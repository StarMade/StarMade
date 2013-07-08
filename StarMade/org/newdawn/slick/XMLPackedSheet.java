/*  1:   */package org.newdawn.slick;
/*  2:   */
/*  3:   */import java.util.HashMap;
/*  4:   */import javax.xml.parsers.DocumentBuilder;
/*  5:   */import javax.xml.parsers.DocumentBuilderFactory;
/*  6:   */import org.newdawn.slick.util.ResourceLoader;
/*  7:   */import org.w3c.dom.Document;
/*  8:   */import org.w3c.dom.Element;
/*  9:   */import org.w3c.dom.NodeList;
/* 10:   */
/* 19:   */public class XMLPackedSheet
/* 20:   */{
/* 21:   */  private Image image;
/* 22:22 */  private HashMap sprites = new HashMap();
/* 23:   */  
/* 30:   */  public XMLPackedSheet(String imageRef, String xmlRef)
/* 31:   */    throws SlickException
/* 32:   */  {
/* 33:33 */    this.image = new Image(imageRef, false, 2);
/* 34:   */    try
/* 35:   */    {
/* 36:36 */      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
/* 37:37 */      Document doc = builder.parse(ResourceLoader.getResourceAsStream(xmlRef));
/* 38:   */      
/* 39:39 */      NodeList list = doc.getElementsByTagName("sprite");
/* 40:40 */      for (int i = 0; i < list.getLength(); i++) {
/* 41:41 */        Element element = (Element)list.item(i);
/* 42:   */        
/* 43:43 */        String name = element.getAttribute("name");
/* 44:44 */        int x = Integer.parseInt(element.getAttribute("x"));
/* 45:45 */        int y = Integer.parseInt(element.getAttribute("y"));
/* 46:46 */        int width = Integer.parseInt(element.getAttribute("width"));
/* 47:47 */        int height = Integer.parseInt(element.getAttribute("height"));
/* 48:   */        
/* 49:49 */        this.sprites.put(name, this.image.getSubImage(x, y, width, height));
/* 50:   */      }
/* 51:   */    } catch (Exception e) {
/* 52:52 */      throw new SlickException("Failed to parse sprite sheet XML", e);
/* 53:   */    }
/* 54:   */  }
/* 55:   */  
/* 61:   */  public Image getSprite(String name)
/* 62:   */  {
/* 63:63 */    return (Image)this.sprites.get(name);
/* 64:   */  }
/* 65:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.XMLPackedSheet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
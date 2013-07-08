package org.newdawn.slick.tiled;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.util.Log;
import org.newdawn.slick.util.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class TileSet
{
  private final TiledMap map;
  public int index;
  public String name;
  public int firstGID;
  public int lastGID = 2147483647;
  public int tileWidth;
  public int tileHeight;
  public SpriteSheet tiles;
  public int tilesAcross;
  public int tilesDown;
  private HashMap props = new HashMap();
  protected int tileSpacing = 0;
  protected int tileMargin = 0;
  
  public TileSet(TiledMap map, Element element, boolean loadImage)
    throws SlickException
  {
    this.map = map;
    this.name = element.getAttribute("name");
    this.firstGID = Integer.parseInt(element.getAttribute("firstgid"));
    String source = element.getAttribute("source");
    if ((source != null) && (!source.equals(""))) {
      try
      {
        InputStream local_in = ResourceLoader.getResourceAsStream(map.getTilesLocation() + "/" + source);
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(local_in);
        Element docElement = doc.getDocumentElement();
        element = docElement;
      }
      catch (Exception local_in)
      {
        Log.error(local_in);
        throw new SlickException("Unable to load or parse sourced tileset: " + this.map.tilesLocation + "/" + source);
      }
    }
    String local_in = element.getAttribute("tilewidth");
    String builder = element.getAttribute("tileheight");
    if ((local_in.length() == 0) || (builder.length() == 0)) {
      throw new SlickException("TiledMap requires that the map be created with tilesets that use a single image.  Check the WiKi for more complete information.");
    }
    this.tileWidth = Integer.parseInt(local_in);
    this.tileHeight = Integer.parseInt(builder);
    String doc = element.getAttribute("spacing");
    if ((doc != null) && (!doc.equals(""))) {
      this.tileSpacing = Integer.parseInt(doc);
    }
    String docElement = element.getAttribute("margin");
    if ((docElement != null) && (!docElement.equals(""))) {
      this.tileMargin = Integer.parseInt(docElement);
    }
    NodeList list = element.getElementsByTagName("image");
    Element imageNode = (Element)list.item(0);
    String ref = imageNode.getAttribute("source");
    Color trans = null;
    String local_t = imageNode.getAttribute("trans");
    if ((local_t != null) && (local_t.length() > 0))
    {
      int local_c = Integer.parseInt(local_t, 16);
      trans = new Color(local_c);
    }
    if (loadImage)
    {
      Image local_c = new Image(map.getTilesLocation() + "/" + ref, false, 2, trans);
      setTileSetImage(local_c);
    }
    NodeList local_c = element.getElementsByTagName("tile");
    for (int local_i = 0; local_i < local_c.getLength(); local_i++)
    {
      Element tileElement = (Element)local_c.item(local_i);
      int local_id = Integer.parseInt(tileElement.getAttribute("id"));
      local_id += this.firstGID;
      Properties tileProps = new Properties();
      Element propsElement = (Element)tileElement.getElementsByTagName("properties").item(0);
      NodeList properties = propsElement.getElementsByTagName("property");
      for (int local_p = 0; local_p < properties.getLength(); local_p++)
      {
        Element propElement = (Element)properties.item(local_p);
        String name = propElement.getAttribute("name");
        String value = propElement.getAttribute("value");
        tileProps.setProperty(name, value);
      }
      this.props.put(new Integer(local_id), tileProps);
    }
  }
  
  public int getTileWidth()
  {
    return this.tileWidth;
  }
  
  public int getTileHeight()
  {
    return this.tileHeight;
  }
  
  public int getTileSpacing()
  {
    return this.tileSpacing;
  }
  
  public int getTileMargin()
  {
    return this.tileMargin;
  }
  
  public void setTileSetImage(Image image)
  {
    this.tiles = new SpriteSheet(image, this.tileWidth, this.tileHeight, this.tileSpacing, this.tileMargin);
    this.tilesAcross = this.tiles.getHorizontalCount();
    this.tilesDown = this.tiles.getVerticalCount();
    if (this.tilesAcross <= 0) {
      this.tilesAcross = 1;
    }
    if (this.tilesDown <= 0) {
      this.tilesDown = 1;
    }
    this.lastGID = (this.tilesAcross * this.tilesDown + this.firstGID - 1);
  }
  
  public Properties getProperties(int globalID)
  {
    return (Properties)this.props.get(new Integer(globalID));
  }
  
  public int getTileX(int local_id)
  {
    return local_id % this.tilesAcross;
  }
  
  public int getTileY(int local_id)
  {
    return local_id / this.tilesAcross;
  }
  
  public void setLimit(int limit)
  {
    this.lastGID = limit;
  }
  
  public boolean contains(int gid)
  {
    return (gid >= this.firstGID) && (gid <= this.lastGID);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tiled.TileSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
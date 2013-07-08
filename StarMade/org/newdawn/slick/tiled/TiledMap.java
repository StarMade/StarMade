package org.newdawn.slick.tiled;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.util.Log;
import org.newdawn.slick.util.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class TiledMap
{
  private static boolean headless;
  protected int width;
  protected int height;
  protected int tileWidth;
  protected int tileHeight;
  protected String tilesLocation;
  protected Properties props;
  protected ArrayList tileSets = new ArrayList();
  protected ArrayList layers = new ArrayList();
  protected ArrayList objectGroups = new ArrayList();
  protected static final int ORTHOGONAL = 1;
  protected static final int ISOMETRIC = 2;
  protected int orientation;
  private boolean loadTileSets = true;
  
  private static void setHeadless(boolean local_h)
  {
    headless = local_h;
  }
  
  public TiledMap(String ref)
    throws SlickException
  {
    this(ref, true);
  }
  
  public TiledMap(String ref, boolean loadTileSets)
    throws SlickException
  {
    this.loadTileSets = loadTileSets;
    ref = ref.replace('\\', '/');
    load(ResourceLoader.getResourceAsStream(ref), ref.substring(0, ref.lastIndexOf("/")));
  }
  
  public TiledMap(String ref, String tileSetsLocation)
    throws SlickException
  {
    load(ResourceLoader.getResourceAsStream(ref), tileSetsLocation);
  }
  
  public TiledMap(InputStream local_in)
    throws SlickException
  {
    load(local_in, "");
  }
  
  public TiledMap(InputStream local_in, String tileSetsLocation)
    throws SlickException
  {
    load(local_in, tileSetsLocation);
  }
  
  public String getTilesLocation()
  {
    return this.tilesLocation;
  }
  
  public int getLayerIndex(String name)
  {
    int idx = 0;
    for (int local_i = 0; local_i < this.layers.size(); local_i++)
    {
      Layer layer = (Layer)this.layers.get(local_i);
      if (layer.name.equals(name)) {
        return local_i;
      }
    }
    return -1;
  }
  
  public Image getTileImage(int local_x, int local_y, int layerIndex)
  {
    Layer layer = (Layer)this.layers.get(layerIndex);
    int tileSetIndex = layer.data[local_x][local_y][0];
    if ((tileSetIndex >= 0) && (tileSetIndex < this.tileSets.size()))
    {
      TileSet tileSet = (TileSet)this.tileSets.get(tileSetIndex);
      int sheetX = tileSet.getTileX(layer.data[local_x][local_y][1]);
      int sheetY = tileSet.getTileY(layer.data[local_x][local_y][1]);
      return tileSet.tiles.getSprite(sheetX, sheetY);
    }
    return null;
  }
  
  public int getWidth()
  {
    return this.width;
  }
  
  public int getHeight()
  {
    return this.height;
  }
  
  public int getTileHeight()
  {
    return this.tileHeight;
  }
  
  public int getTileWidth()
  {
    return this.tileWidth;
  }
  
  public int getTileId(int local_x, int local_y, int layerIndex)
  {
    Layer layer = (Layer)this.layers.get(layerIndex);
    return layer.getTileID(local_x, local_y);
  }
  
  public void setTileId(int local_x, int local_y, int layerIndex, int tileid)
  {
    Layer layer = (Layer)this.layers.get(layerIndex);
    layer.setTileID(local_x, local_y, tileid);
  }
  
  public String getMapProperty(String propertyName, String def)
  {
    if (this.props == null) {
      return def;
    }
    return this.props.getProperty(propertyName, def);
  }
  
  public String getLayerProperty(int layerIndex, String propertyName, String def)
  {
    Layer layer = (Layer)this.layers.get(layerIndex);
    if ((layer == null) || (layer.props == null)) {
      return def;
    }
    return layer.props.getProperty(propertyName, def);
  }
  
  public String getTileProperty(int tileID, String propertyName, String def)
  {
    if (tileID == 0) {
      return def;
    }
    TileSet set = findTileSet(tileID);
    Properties props = set.getProperties(tileID);
    if (props == null) {
      return def;
    }
    return props.getProperty(propertyName, def);
  }
  
  public void render(int local_x, int local_y)
  {
    render(local_x, local_y, 0, 0, this.width, this.height, false);
  }
  
  public void render(int local_x, int local_y, int layer)
  {
    render(local_x, local_y, 0, 0, getWidth(), getHeight(), layer, false);
  }
  
  public void render(int local_x, int local_y, int local_sx, int local_sy, int width, int height)
  {
    render(local_x, local_y, local_sx, local_sy, width, height, false);
  }
  
  public void render(int local_x, int local_y, int local_sx, int local_sy, int width, int height, int local_l, boolean lineByLine)
  {
    Layer layer = (Layer)this.layers.get(local_l);
    switch (this.orientation)
    {
    case 1: 
      for (int local_ty = 0; local_ty < height; local_ty++) {
        layer.render(local_x, local_y, local_sx, local_sy, width, local_ty, lineByLine, this.tileWidth, this.tileHeight);
      }
      break;
    case 2: 
      renderIsometricMap(local_x, local_y, local_sx, local_sy, width, height, layer, lineByLine);
      break;
    }
  }
  
  public void render(int local_x, int local_y, int local_sx, int local_sy, int width, int height, boolean lineByLine)
  {
    switch (this.orientation)
    {
    case 1: 
      for (int local_ty = 0; local_ty < height; local_ty++) {
        for (int local_i = 0; local_i < this.layers.size(); local_i++)
        {
          Layer layer = (Layer)this.layers.get(local_i);
          layer.render(local_x, local_y, local_sx, local_sy, width, local_ty, lineByLine, this.tileWidth, this.tileHeight);
        }
      }
      break;
    case 2: 
      renderIsometricMap(local_x, local_y, local_sx, local_sy, width, height, null, lineByLine);
      break;
    }
  }
  
  protected void renderIsometricMap(int local_x, int local_y, int local_sx, int local_sy, int width, int height, Layer layer, boolean lineByLine)
  {
    ArrayList drawLayers = this.layers;
    if (layer != null)
    {
      drawLayers = new ArrayList();
      drawLayers.add(layer);
    }
    int maxCount = width * height;
    int allCount = 0;
    boolean allProcessed = false;
    int initialLineX = local_x;
    int initialLineY = local_y;
    int startLineTileX = 0;
    int startLineTileY = 0;
    while (!allProcessed)
    {
      int currentTileX = startLineTileX;
      int currentTileY = startLineTileY;
      int currentLineX = initialLineX;
      int min = 0;
      if (height > width) {
        min = width - currentTileX < height ? width - currentTileX - 1 : startLineTileY < width - 1 ? startLineTileY : width - 1;
      } else {
        min = width - currentTileX < height ? width - currentTileX - 1 : startLineTileY < height - 1 ? startLineTileY : height - 1;
      }
      for (int burner = 0; burner <= min; burner++)
      {
        for (int layerIdx = 0; layerIdx < drawLayers.size(); layerIdx++)
        {
          Layer currentLayer = (Layer)drawLayers.get(layerIdx);
          currentLayer.render(currentLineX, initialLineY, currentTileX, currentTileY, 1, 0, lineByLine, this.tileWidth, this.tileHeight);
        }
        currentLineX += this.tileWidth;
        allCount++;
        currentTileX++;
        currentTileY--;
      }
      if (startLineTileY < height - 1)
      {
        startLineTileY++;
        initialLineX -= this.tileWidth / 2;
        initialLineY += this.tileHeight / 2;
      }
      else
      {
        startLineTileX++;
        initialLineX += this.tileWidth / 2;
        initialLineY += this.tileHeight / 2;
      }
      if (allCount >= maxCount) {
        allProcessed = true;
      }
    }
  }
  
  public int getLayerCount()
  {
    return this.layers.size();
  }
  
  private int parseInt(String value)
  {
    try
    {
      return Integer.parseInt(value);
    }
    catch (NumberFormatException local_e) {}
    return 0;
  }
  
  private void load(InputStream local_in, String tileSetsLocation)
    throws SlickException
  {
    this.tilesLocation = tileSetsLocation;
    try
    {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setValidating(false);
      DocumentBuilder builder = factory.newDocumentBuilder();
      builder.setEntityResolver(new EntityResolver()
      {
        public InputSource resolveEntity(String publicId, String systemId)
          throws SAXException, IOException
        {
          return new InputSource(new ByteArrayInputStream(new byte[0]));
        }
      });
      Document doc = builder.parse(local_in);
      Element docElement = doc.getDocumentElement();
      if (docElement.getAttribute("orientation").equals("orthogonal")) {
        this.orientation = 1;
      } else {
        this.orientation = 2;
      }
      this.width = parseInt(docElement.getAttribute("width"));
      this.height = parseInt(docElement.getAttribute("height"));
      this.tileWidth = parseInt(docElement.getAttribute("tilewidth"));
      this.tileHeight = parseInt(docElement.getAttribute("tileheight"));
      Element propsElement = (Element)docElement.getElementsByTagName("properties").item(0);
      if (propsElement != null)
      {
        NodeList properties = propsElement.getElementsByTagName("property");
        if (properties != null)
        {
          this.props = new Properties();
          for (int local_p = 0; local_p < properties.getLength(); local_p++)
          {
            Element propElement = (Element)properties.item(local_p);
            String name = propElement.getAttribute("name");
            String value = propElement.getAttribute("value");
            this.props.setProperty(name, value);
          }
        }
      }
      if (this.loadTileSets)
      {
        TileSet properties = null;
        TileSet local_p = null;
        NodeList propElement = docElement.getElementsByTagName("tileset");
        for (int name = 0; name < propElement.getLength(); name++)
        {
          Element value = (Element)propElement.item(name);
          properties = new TileSet(this, value, !headless);
          properties.index = name;
          if (local_p != null) {
            local_p.setLimit(properties.firstGID - 1);
          }
          local_p = properties;
          this.tileSets.add(properties);
        }
      }
      NodeList properties = docElement.getElementsByTagName("layer");
      for (int local_p = 0; local_p < properties.getLength(); local_p++)
      {
        Element propElement = (Element)properties.item(local_p);
        Layer name = new Layer(this, propElement);
        name.index = local_p;
        this.layers.add(name);
      }
      NodeList local_p = docElement.getElementsByTagName("objectgroup");
      for (int propElement = 0; propElement < local_p.getLength(); propElement++)
      {
        Element name = (Element)local_p.item(propElement);
        ObjectGroup value = new ObjectGroup(name);
        value.index = propElement;
        this.objectGroups.add(value);
      }
    }
    catch (Exception factory)
    {
      Log.error(factory);
      throw new SlickException("Failed to parse tilemap", factory);
    }
  }
  
  public int getTileSetCount()
  {
    return this.tileSets.size();
  }
  
  public TileSet getTileSet(int index)
  {
    return (TileSet)this.tileSets.get(index);
  }
  
  public TileSet getTileSetByGID(int gid)
  {
    for (int local_i = 0; local_i < this.tileSets.size(); local_i++)
    {
      TileSet set = (TileSet)this.tileSets.get(local_i);
      if (set.contains(gid)) {
        return set;
      }
    }
    return null;
  }
  
  public TileSet findTileSet(int gid)
  {
    for (int local_i = 0; local_i < this.tileSets.size(); local_i++)
    {
      TileSet set = (TileSet)this.tileSets.get(local_i);
      if (set.contains(gid)) {
        return set;
      }
    }
    return null;
  }
  
  protected void renderedLine(int visualY, int mapY, int layer) {}
  
  public int getObjectGroupCount()
  {
    return this.objectGroups.size();
  }
  
  public int getObjectCount(int groupID)
  {
    if ((groupID >= 0) && (groupID < this.objectGroups.size()))
    {
      ObjectGroup grp = (ObjectGroup)this.objectGroups.get(groupID);
      return grp.objects.size();
    }
    return -1;
  }
  
  public String getObjectName(int groupID, int objectID)
  {
    if ((groupID >= 0) && (groupID < this.objectGroups.size()))
    {
      ObjectGroup grp = (ObjectGroup)this.objectGroups.get(groupID);
      if ((objectID >= 0) && (objectID < grp.objects.size()))
      {
        GroupObject object = (GroupObject)grp.objects.get(objectID);
        return object.name;
      }
    }
    return null;
  }
  
  public String getObjectType(int groupID, int objectID)
  {
    if ((groupID >= 0) && (groupID < this.objectGroups.size()))
    {
      ObjectGroup grp = (ObjectGroup)this.objectGroups.get(groupID);
      if ((objectID >= 0) && (objectID < grp.objects.size()))
      {
        GroupObject object = (GroupObject)grp.objects.get(objectID);
        return object.type;
      }
    }
    return null;
  }
  
  public int getObjectX(int groupID, int objectID)
  {
    if ((groupID >= 0) && (groupID < this.objectGroups.size()))
    {
      ObjectGroup grp = (ObjectGroup)this.objectGroups.get(groupID);
      if ((objectID >= 0) && (objectID < grp.objects.size()))
      {
        GroupObject object = (GroupObject)grp.objects.get(objectID);
        return object.field_610;
      }
    }
    return -1;
  }
  
  public int getObjectY(int groupID, int objectID)
  {
    if ((groupID >= 0) && (groupID < this.objectGroups.size()))
    {
      ObjectGroup grp = (ObjectGroup)this.objectGroups.get(groupID);
      if ((objectID >= 0) && (objectID < grp.objects.size()))
      {
        GroupObject object = (GroupObject)grp.objects.get(objectID);
        return object.field_611;
      }
    }
    return -1;
  }
  
  public int getObjectWidth(int groupID, int objectID)
  {
    if ((groupID >= 0) && (groupID < this.objectGroups.size()))
    {
      ObjectGroup grp = (ObjectGroup)this.objectGroups.get(groupID);
      if ((objectID >= 0) && (objectID < grp.objects.size()))
      {
        GroupObject object = (GroupObject)grp.objects.get(objectID);
        return object.width;
      }
    }
    return -1;
  }
  
  public int getObjectHeight(int groupID, int objectID)
  {
    if ((groupID >= 0) && (groupID < this.objectGroups.size()))
    {
      ObjectGroup grp = (ObjectGroup)this.objectGroups.get(groupID);
      if ((objectID >= 0) && (objectID < grp.objects.size()))
      {
        GroupObject object = (GroupObject)grp.objects.get(objectID);
        return object.height;
      }
    }
    return -1;
  }
  
  public String getObjectImage(int groupID, int objectID)
  {
    if ((groupID >= 0) && (groupID < this.objectGroups.size()))
    {
      ObjectGroup grp = (ObjectGroup)this.objectGroups.get(groupID);
      if ((objectID >= 0) && (objectID < grp.objects.size()))
      {
        GroupObject object = (GroupObject)grp.objects.get(objectID);
        if (object == null) {
          return null;
        }
        return object.image;
      }
    }
    return null;
  }
  
  public String getObjectProperty(int groupID, int objectID, String propertyName, String def)
  {
    if ((groupID >= 0) && (groupID < this.objectGroups.size()))
    {
      ObjectGroup grp = (ObjectGroup)this.objectGroups.get(groupID);
      if ((objectID >= 0) && (objectID < grp.objects.size()))
      {
        GroupObject object = (GroupObject)grp.objects.get(objectID);
        if (object == null) {
          return def;
        }
        if (object.props == null) {
          return def;
        }
        return object.props.getProperty(propertyName, def);
      }
    }
    return def;
  }
  
  protected class GroupObject
  {
    public int index;
    public String name;
    public String type;
    public int field_610;
    public int field_611;
    public int width;
    public int height;
    private String image;
    public Properties props;
    
    public GroupObject(Element element)
      throws SlickException
    {
      this.name = element.getAttribute("name");
      this.type = element.getAttribute("type");
      this.field_610 = Integer.parseInt(element.getAttribute("x"));
      this.field_611 = Integer.parseInt(element.getAttribute("y"));
      this.width = Integer.parseInt(element.getAttribute("width"));
      this.height = Integer.parseInt(element.getAttribute("height"));
      Element imageElement = (Element)element.getElementsByTagName("image").item(0);
      if (imageElement != null) {
        this.image = imageElement.getAttribute("source");
      }
      Element propsElement = (Element)element.getElementsByTagName("properties").item(0);
      if (propsElement != null)
      {
        NodeList properties = propsElement.getElementsByTagName("property");
        if (properties != null)
        {
          this.props = new Properties();
          for (int local_p = 0; local_p < properties.getLength(); local_p++)
          {
            Element propElement = (Element)properties.item(local_p);
            String name = propElement.getAttribute("name");
            String value = propElement.getAttribute("value");
            this.props.setProperty(name, value);
          }
        }
      }
    }
  }
  
  protected class ObjectGroup
  {
    public int index;
    public String name;
    public ArrayList objects;
    public int width;
    public int height;
    public Properties props;
    
    public ObjectGroup(Element element)
      throws SlickException
    {
      this.name = element.getAttribute("name");
      this.width = Integer.parseInt(element.getAttribute("width"));
      this.height = Integer.parseInt(element.getAttribute("height"));
      this.objects = new ArrayList();
      Element propsElement = (Element)element.getElementsByTagName("properties").item(0);
      if (propsElement != null)
      {
        NodeList properties = propsElement.getElementsByTagName("property");
        if (properties != null)
        {
          this.props = new Properties();
          for (int local_p = 0; local_p < properties.getLength(); local_p++)
          {
            Element propElement = (Element)properties.item(local_p);
            String name = propElement.getAttribute("name");
            String value = propElement.getAttribute("value");
            this.props.setProperty(name, value);
          }
        }
      }
      NodeList properties = element.getElementsByTagName("object");
      for (int local_p = 0; local_p < properties.getLength(); local_p++)
      {
        Element propElement = (Element)properties.item(local_p);
        TiledMap.GroupObject name = new TiledMap.GroupObject(TiledMap.this, propElement);
        name.index = local_p;
        this.objects.add(name);
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tiled.TiledMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
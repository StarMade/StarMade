/*   1:    */package org.newdawn.slick.tiled;
/*   2:    */
/*   3:    */import java.io.InputStream;
/*   4:    */import java.util.HashMap;
/*   5:    */import java.util.Properties;
/*   6:    */import javax.xml.parsers.DocumentBuilder;
/*   7:    */import javax.xml.parsers.DocumentBuilderFactory;
/*   8:    */import org.newdawn.slick.Color;
/*   9:    */import org.newdawn.slick.Image;
/*  10:    */import org.newdawn.slick.SlickException;
/*  11:    */import org.newdawn.slick.SpriteSheet;
/*  12:    */import org.newdawn.slick.util.Log;
/*  13:    */import org.newdawn.slick.util.ResourceLoader;
/*  14:    */import org.w3c.dom.Document;
/*  15:    */import org.w3c.dom.Element;
/*  16:    */import org.w3c.dom.NodeList;
/*  17:    */
/*  29:    */public class TileSet
/*  30:    */{
/*  31:    */  private final TiledMap map;
/*  32:    */  public int index;
/*  33:    */  public String name;
/*  34:    */  public int firstGID;
/*  35: 35 */  public int lastGID = 2147483647;
/*  36:    */  
/*  38:    */  public int tileWidth;
/*  39:    */  
/*  41:    */  public int tileHeight;
/*  42:    */  
/*  43:    */  public SpriteSheet tiles;
/*  44:    */  
/*  45:    */  public int tilesAcross;
/*  46:    */  
/*  47:    */  public int tilesDown;
/*  48:    */  
/*  49: 49 */  private HashMap props = new HashMap();
/*  50:    */  
/*  51: 51 */  protected int tileSpacing = 0;
/*  52:    */  
/*  53: 53 */  protected int tileMargin = 0;
/*  54:    */  
/*  61:    */  public TileSet(TiledMap map, Element element, boolean loadImage)
/*  62:    */    throws SlickException
/*  63:    */  {
/*  64: 64 */    this.map = map;
/*  65: 65 */    this.name = element.getAttribute("name");
/*  66: 66 */    this.firstGID = Integer.parseInt(element.getAttribute("firstgid"));
/*  67: 67 */    String source = element.getAttribute("source");
/*  68:    */    
/*  69: 69 */    if ((source != null) && (!source.equals(""))) {
/*  70:    */      try {
/*  71: 71 */        InputStream in = ResourceLoader.getResourceAsStream(map.getTilesLocation() + "/" + source);
/*  72: 72 */        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
/*  73: 73 */        Document doc = builder.parse(in);
/*  74: 74 */        Element docElement = doc.getDocumentElement();
/*  75: 75 */        element = docElement;
/*  76:    */      } catch (Exception e) {
/*  77: 77 */        Log.error(e);
/*  78: 78 */        throw new SlickException("Unable to load or parse sourced tileset: " + this.map.tilesLocation + "/" + source);
/*  79:    */      }
/*  80:    */    }
/*  81: 81 */    String tileWidthString = element.getAttribute("tilewidth");
/*  82: 82 */    String tileHeightString = element.getAttribute("tileheight");
/*  83: 83 */    if ((tileWidthString.length() == 0) || (tileHeightString.length() == 0)) {
/*  84: 84 */      throw new SlickException("TiledMap requires that the map be created with tilesets that use a single image.  Check the WiKi for more complete information.");
/*  85:    */    }
/*  86:    */    
/*  87: 87 */    this.tileWidth = Integer.parseInt(tileWidthString);
/*  88: 88 */    this.tileHeight = Integer.parseInt(tileHeightString);
/*  89:    */    
/*  90: 90 */    String sv = element.getAttribute("spacing");
/*  91: 91 */    if ((sv != null) && (!sv.equals(""))) {
/*  92: 92 */      this.tileSpacing = Integer.parseInt(sv);
/*  93:    */    }
/*  94:    */    
/*  95: 95 */    String mv = element.getAttribute("margin");
/*  96: 96 */    if ((mv != null) && (!mv.equals(""))) {
/*  97: 97 */      this.tileMargin = Integer.parseInt(mv);
/*  98:    */    }
/*  99:    */    
/* 100:100 */    NodeList list = element.getElementsByTagName("image");
/* 101:101 */    Element imageNode = (Element)list.item(0);
/* 102:102 */    String ref = imageNode.getAttribute("source");
/* 103:    */    
/* 104:104 */    Color trans = null;
/* 105:105 */    String t = imageNode.getAttribute("trans");
/* 106:106 */    if ((t != null) && (t.length() > 0)) {
/* 107:107 */      int c = Integer.parseInt(t, 16);
/* 108:    */      
/* 109:109 */      trans = new Color(c);
/* 110:    */    }
/* 111:    */    
/* 112:112 */    if (loadImage) {
/* 113:113 */      Image image = new Image(map.getTilesLocation() + "/" + ref, false, 2, trans);
/* 114:114 */      setTileSetImage(image);
/* 115:    */    }
/* 116:    */    
/* 117:117 */    NodeList pElements = element.getElementsByTagName("tile");
/* 118:118 */    for (int i = 0; i < pElements.getLength(); i++) {
/* 119:119 */      Element tileElement = (Element)pElements.item(i);
/* 120:    */      
/* 121:121 */      int id = Integer.parseInt(tileElement.getAttribute("id"));
/* 122:122 */      id += this.firstGID;
/* 123:123 */      Properties tileProps = new Properties();
/* 124:    */      
/* 125:125 */      Element propsElement = (Element)tileElement.getElementsByTagName("properties").item(0);
/* 126:126 */      NodeList properties = propsElement.getElementsByTagName("property");
/* 127:127 */      for (int p = 0; p < properties.getLength(); p++) {
/* 128:128 */        Element propElement = (Element)properties.item(p);
/* 129:    */        
/* 130:130 */        String name = propElement.getAttribute("name");
/* 131:131 */        String value = propElement.getAttribute("value");
/* 132:    */        
/* 133:133 */        tileProps.setProperty(name, value);
/* 134:    */      }
/* 135:    */      
/* 136:136 */      this.props.put(new Integer(id), tileProps);
/* 137:    */    }
/* 138:    */  }
/* 139:    */  
/* 144:    */  public int getTileWidth()
/* 145:    */  {
/* 146:146 */    return this.tileWidth;
/* 147:    */  }
/* 148:    */  
/* 153:    */  public int getTileHeight()
/* 154:    */  {
/* 155:155 */    return this.tileHeight;
/* 156:    */  }
/* 157:    */  
/* 162:    */  public int getTileSpacing()
/* 163:    */  {
/* 164:164 */    return this.tileSpacing;
/* 165:    */  }
/* 166:    */  
/* 171:    */  public int getTileMargin()
/* 172:    */  {
/* 173:173 */    return this.tileMargin;
/* 174:    */  }
/* 175:    */  
/* 180:    */  public void setTileSetImage(Image image)
/* 181:    */  {
/* 182:182 */    this.tiles = new SpriteSheet(image, this.tileWidth, this.tileHeight, this.tileSpacing, this.tileMargin);
/* 183:183 */    this.tilesAcross = this.tiles.getHorizontalCount();
/* 184:184 */    this.tilesDown = this.tiles.getVerticalCount();
/* 185:    */    
/* 186:186 */    if (this.tilesAcross <= 0) {
/* 187:187 */      this.tilesAcross = 1;
/* 188:    */    }
/* 189:189 */    if (this.tilesDown <= 0) {
/* 190:190 */      this.tilesDown = 1;
/* 191:    */    }
/* 192:    */    
/* 193:193 */    this.lastGID = (this.tilesAcross * this.tilesDown + this.firstGID - 1);
/* 194:    */  }
/* 195:    */  
/* 201:    */  public Properties getProperties(int globalID)
/* 202:    */  {
/* 203:203 */    return (Properties)this.props.get(new Integer(globalID));
/* 204:    */  }
/* 205:    */  
/* 211:    */  public int getTileX(int id)
/* 212:    */  {
/* 213:213 */    return id % this.tilesAcross;
/* 214:    */  }
/* 215:    */  
/* 221:    */  public int getTileY(int id)
/* 222:    */  {
/* 223:223 */    return id / this.tilesAcross;
/* 224:    */  }
/* 225:    */  
/* 230:    */  public void setLimit(int limit)
/* 231:    */  {
/* 232:232 */    this.lastGID = limit;
/* 233:    */  }
/* 234:    */  
/* 240:    */  public boolean contains(int gid)
/* 241:    */  {
/* 242:242 */    return (gid >= this.firstGID) && (gid <= this.lastGID);
/* 243:    */  }
/* 244:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tiled.TileSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
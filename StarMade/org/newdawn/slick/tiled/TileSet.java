/*     */ package org.newdawn.slick.tiled;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Properties;
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import org.newdawn.slick.Color;
/*     */ import org.newdawn.slick.Image;
/*     */ import org.newdawn.slick.SlickException;
/*     */ import org.newdawn.slick.SpriteSheet;
/*     */ import org.newdawn.slick.util.Log;
/*     */ import org.newdawn.slick.util.ResourceLoader;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ public class TileSet
/*     */ {
/*     */   private final TiledMap map;
/*     */   public int index;
/*     */   public String name;
/*     */   public int firstGID;
/*  35 */   public int lastGID = 2147483647;
/*     */   public int tileWidth;
/*     */   public int tileHeight;
/*     */   public SpriteSheet tiles;
/*     */   public int tilesAcross;
/*     */   public int tilesDown;
/*  49 */   private HashMap props = new HashMap();
/*     */ 
/*  51 */   protected int tileSpacing = 0;
/*     */ 
/*  53 */   protected int tileMargin = 0;
/*     */ 
/*     */   public TileSet(TiledMap map, Element element, boolean loadImage)
/*     */     throws SlickException
/*     */   {
/*  64 */     this.map = map;
/*  65 */     this.name = element.getAttribute("name");
/*  66 */     this.firstGID = Integer.parseInt(element.getAttribute("firstgid"));
/*  67 */     String source = element.getAttribute("source");
/*     */ 
/*  69 */     if ((source != null) && (!source.equals(""))) {
/*     */       try {
/*  71 */         InputStream in = ResourceLoader.getResourceAsStream(map.getTilesLocation() + "/" + source);
/*  72 */         DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
/*  73 */         Document doc = builder.parse(in);
/*  74 */         Element docElement = doc.getDocumentElement();
/*  75 */         element = docElement;
/*     */       } catch (Exception e) {
/*  77 */         Log.error(e);
/*  78 */         throw new SlickException("Unable to load or parse sourced tileset: " + this.map.tilesLocation + "/" + source);
/*     */       }
/*     */     }
/*  81 */     String tileWidthString = element.getAttribute("tilewidth");
/*  82 */     String tileHeightString = element.getAttribute("tileheight");
/*  83 */     if ((tileWidthString.length() == 0) || (tileHeightString.length() == 0)) {
/*  84 */       throw new SlickException("TiledMap requires that the map be created with tilesets that use a single image.  Check the WiKi for more complete information.");
/*     */     }
/*     */ 
/*  87 */     this.tileWidth = Integer.parseInt(tileWidthString);
/*  88 */     this.tileHeight = Integer.parseInt(tileHeightString);
/*     */ 
/*  90 */     String sv = element.getAttribute("spacing");
/*  91 */     if ((sv != null) && (!sv.equals(""))) {
/*  92 */       this.tileSpacing = Integer.parseInt(sv);
/*     */     }
/*     */ 
/*  95 */     String mv = element.getAttribute("margin");
/*  96 */     if ((mv != null) && (!mv.equals(""))) {
/*  97 */       this.tileMargin = Integer.parseInt(mv);
/*     */     }
/*     */ 
/* 100 */     NodeList list = element.getElementsByTagName("image");
/* 101 */     Element imageNode = (Element)list.item(0);
/* 102 */     String ref = imageNode.getAttribute("source");
/*     */ 
/* 104 */     Color trans = null;
/* 105 */     String t = imageNode.getAttribute("trans");
/* 106 */     if ((t != null) && (t.length() > 0)) {
/* 107 */       int c = Integer.parseInt(t, 16);
/*     */ 
/* 109 */       trans = new Color(c);
/*     */     }
/*     */ 
/* 112 */     if (loadImage) {
/* 113 */       Image image = new Image(map.getTilesLocation() + "/" + ref, false, 2, trans);
/* 114 */       setTileSetImage(image);
/*     */     }
/*     */ 
/* 117 */     NodeList pElements = element.getElementsByTagName("tile");
/* 118 */     for (int i = 0; i < pElements.getLength(); i++) {
/* 119 */       Element tileElement = (Element)pElements.item(i);
/*     */ 
/* 121 */       int id = Integer.parseInt(tileElement.getAttribute("id"));
/* 122 */       id += this.firstGID;
/* 123 */       Properties tileProps = new Properties();
/*     */ 
/* 125 */       Element propsElement = (Element)tileElement.getElementsByTagName("properties").item(0);
/* 126 */       NodeList properties = propsElement.getElementsByTagName("property");
/* 127 */       for (int p = 0; p < properties.getLength(); p++) {
/* 128 */         Element propElement = (Element)properties.item(p);
/*     */ 
/* 130 */         String name = propElement.getAttribute("name");
/* 131 */         String value = propElement.getAttribute("value");
/*     */ 
/* 133 */         tileProps.setProperty(name, value);
/*     */       }
/*     */ 
/* 136 */       this.props.put(new Integer(id), tileProps);
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getTileWidth()
/*     */   {
/* 146 */     return this.tileWidth;
/*     */   }
/*     */ 
/*     */   public int getTileHeight()
/*     */   {
/* 155 */     return this.tileHeight;
/*     */   }
/*     */ 
/*     */   public int getTileSpacing()
/*     */   {
/* 164 */     return this.tileSpacing;
/*     */   }
/*     */ 
/*     */   public int getTileMargin()
/*     */   {
/* 173 */     return this.tileMargin;
/*     */   }
/*     */ 
/*     */   public void setTileSetImage(Image image)
/*     */   {
/* 182 */     this.tiles = new SpriteSheet(image, this.tileWidth, this.tileHeight, this.tileSpacing, this.tileMargin);
/* 183 */     this.tilesAcross = this.tiles.getHorizontalCount();
/* 184 */     this.tilesDown = this.tiles.getVerticalCount();
/*     */ 
/* 186 */     if (this.tilesAcross <= 0) {
/* 187 */       this.tilesAcross = 1;
/*     */     }
/* 189 */     if (this.tilesDown <= 0) {
/* 190 */       this.tilesDown = 1;
/*     */     }
/*     */ 
/* 193 */     this.lastGID = (this.tilesAcross * this.tilesDown + this.firstGID - 1);
/*     */   }
/*     */ 
/*     */   public Properties getProperties(int globalID)
/*     */   {
/* 203 */     return (Properties)this.props.get(new Integer(globalID));
/*     */   }
/*     */ 
/*     */   public int getTileX(int id)
/*     */   {
/* 213 */     return id % this.tilesAcross;
/*     */   }
/*     */ 
/*     */   public int getTileY(int id)
/*     */   {
/* 223 */     return id / this.tilesAcross;
/*     */   }
/*     */ 
/*     */   public void setLimit(int limit)
/*     */   {
/* 232 */     this.lastGID = limit;
/*     */   }
/*     */ 
/*     */   public boolean contains(int gid)
/*     */   {
/* 242 */     return (gid >= this.firstGID) && (gid <= this.lastGID);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tiled.TileSet
 * JD-Core Version:    0.6.2
 */
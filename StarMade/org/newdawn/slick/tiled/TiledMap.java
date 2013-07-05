/*     */ package org.newdawn.slick.tiled;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Properties;
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import org.newdawn.slick.Image;
/*     */ import org.newdawn.slick.SlickException;
/*     */ import org.newdawn.slick.SpriteSheet;
/*     */ import org.newdawn.slick.util.Log;
/*     */ import org.newdawn.slick.util.ResourceLoader;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.xml.sax.EntityResolver;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ public class TiledMap
/*     */ {
/*     */   private static boolean headless;
/*     */   protected int width;
/*     */   protected int height;
/*     */   protected int tileWidth;
/*     */   protected int tileHeight;
/*     */   protected String tilesLocation;
/*     */   protected Properties props;
/*  63 */   protected ArrayList tileSets = new ArrayList();
/*     */ 
/*  65 */   protected ArrayList layers = new ArrayList();
/*     */ 
/*  67 */   protected ArrayList objectGroups = new ArrayList();
/*     */   protected static final int ORTHOGONAL = 1;
/*     */   protected static final int ISOMETRIC = 2;
/*     */   protected int orientation;
/*  78 */   private boolean loadTileSets = true;
/*     */ 
/*     */   private static void setHeadless(boolean h)
/*     */   {
/*  44 */     headless = h;
/*     */   }
/*     */ 
/*     */   public TiledMap(String ref)
/*     */     throws SlickException
/*     */   {
/*  87 */     this(ref, true);
/*     */   }
/*     */ 
/*     */   public TiledMap(String ref, boolean loadTileSets)
/*     */     throws SlickException
/*     */   {
/*  98 */     this.loadTileSets = loadTileSets;
/*  99 */     ref = ref.replace('\\', '/');
/* 100 */     load(ResourceLoader.getResourceAsStream(ref), ref.substring(0, ref.lastIndexOf("/")));
/*     */   }
/*     */ 
/*     */   public TiledMap(String ref, String tileSetsLocation)
/*     */     throws SlickException
/*     */   {
/* 111 */     load(ResourceLoader.getResourceAsStream(ref), tileSetsLocation);
/*     */   }
/*     */ 
/*     */   public TiledMap(InputStream in)
/*     */     throws SlickException
/*     */   {
/* 121 */     load(in, "");
/*     */   }
/*     */ 
/*     */   public TiledMap(InputStream in, String tileSetsLocation)
/*     */     throws SlickException
/*     */   {
/* 132 */     load(in, tileSetsLocation);
/*     */   }
/*     */ 
/*     */   public String getTilesLocation()
/*     */   {
/* 141 */     return this.tilesLocation;
/*     */   }
/*     */ 
/*     */   public int getLayerIndex(String name)
/*     */   {
/* 151 */     int idx = 0;
/*     */ 
/* 153 */     for (int i = 0; i < this.layers.size(); i++) {
/* 154 */       Layer layer = (Layer)this.layers.get(i);
/*     */ 
/* 156 */       if (layer.name.equals(name)) {
/* 157 */         return i;
/*     */       }
/*     */     }
/*     */ 
/* 161 */     return -1;
/*     */   }
/*     */ 
/*     */   public Image getTileImage(int x, int y, int layerIndex)
/*     */   {
/* 174 */     Layer layer = (Layer)this.layers.get(layerIndex);
/*     */ 
/* 176 */     int tileSetIndex = layer.data[x][y][0];
/* 177 */     if ((tileSetIndex >= 0) && (tileSetIndex < this.tileSets.size())) {
/* 178 */       TileSet tileSet = (TileSet)this.tileSets.get(tileSetIndex);
/*     */ 
/* 180 */       int sheetX = tileSet.getTileX(layer.data[x][y][1]);
/* 181 */       int sheetY = tileSet.getTileY(layer.data[x][y][1]);
/*     */ 
/* 183 */       return tileSet.tiles.getSprite(sheetX, sheetY);
/*     */     }
/*     */ 
/* 186 */     return null;
/*     */   }
/*     */ 
/*     */   public int getWidth()
/*     */   {
/* 195 */     return this.width;
/*     */   }
/*     */ 
/*     */   public int getHeight()
/*     */   {
/* 204 */     return this.height;
/*     */   }
/*     */ 
/*     */   public int getTileHeight()
/*     */   {
/* 213 */     return this.tileHeight;
/*     */   }
/*     */ 
/*     */   public int getTileWidth()
/*     */   {
/* 222 */     return this.tileWidth;
/*     */   }
/*     */ 
/*     */   public int getTileId(int x, int y, int layerIndex)
/*     */   {
/* 237 */     Layer layer = (Layer)this.layers.get(layerIndex);
/* 238 */     return layer.getTileID(x, y);
/*     */   }
/*     */ 
/*     */   public void setTileId(int x, int y, int layerIndex, int tileid)
/*     */   {
/* 253 */     Layer layer = (Layer)this.layers.get(layerIndex);
/* 254 */     layer.setTileID(x, y, tileid);
/*     */   }
/*     */ 
/*     */   public String getMapProperty(String propertyName, String def)
/*     */   {
/* 267 */     if (this.props == null)
/* 268 */       return def;
/* 269 */     return this.props.getProperty(propertyName, def);
/*     */   }
/*     */ 
/*     */   public String getLayerProperty(int layerIndex, String propertyName, String def)
/*     */   {
/* 283 */     Layer layer = (Layer)this.layers.get(layerIndex);
/* 284 */     if ((layer == null) || (layer.props == null))
/* 285 */       return def;
/* 286 */     return layer.props.getProperty(propertyName, def);
/*     */   }
/*     */ 
/*     */   public String getTileProperty(int tileID, String propertyName, String def)
/*     */   {
/* 301 */     if (tileID == 0) {
/* 302 */       return def;
/*     */     }
/*     */ 
/* 305 */     TileSet set = findTileSet(tileID);
/*     */ 
/* 307 */     Properties props = set.getProperties(tileID);
/* 308 */     if (props == null) {
/* 309 */       return def;
/*     */     }
/* 311 */     return props.getProperty(propertyName, def);
/*     */   }
/*     */ 
/*     */   public void render(int x, int y)
/*     */   {
/* 321 */     render(x, y, 0, 0, this.width, this.height, false);
/*     */   }
/*     */ 
/*     */   public void render(int x, int y, int layer)
/*     */   {
/* 332 */     render(x, y, 0, 0, getWidth(), getHeight(), layer, false);
/*     */   }
/*     */ 
/*     */   public void render(int x, int y, int sx, int sy, int width, int height)
/*     */   {
/* 346 */     render(x, y, sx, sy, width, height, false);
/*     */   }
/*     */ 
/*     */   public void render(int x, int y, int sx, int sy, int width, int height, int l, boolean lineByLine)
/*     */   {
/* 363 */     Layer layer = (Layer)this.layers.get(l);
/*     */ 
/* 365 */     switch (this.orientation) {
/*     */     case 1:
/* 367 */       for (int ty = 0; ty < height; ty++) {
/* 368 */         layer.render(x, y, sx, sy, width, ty, lineByLine, this.tileWidth, this.tileHeight);
/*     */       }
/* 370 */       break;
/*     */     case 2:
/* 372 */       renderIsometricMap(x, y, sx, sy, width, height, layer, lineByLine);
/* 373 */       break;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void render(int x, int y, int sx, int sy, int width, int height, boolean lineByLine)
/*     */   {
/* 392 */     switch (this.orientation) {
/*     */     case 1:
/* 394 */       for (int ty = 0; ty < height; ty++) {
/* 395 */         for (int i = 0; i < this.layers.size(); i++) {
/* 396 */           Layer layer = (Layer)this.layers.get(i);
/* 397 */           layer.render(x, y, sx, sy, width, ty, lineByLine, this.tileWidth, this.tileHeight);
/*     */         }
/*     */       }
/* 400 */       break;
/*     */     case 2:
/* 402 */       renderIsometricMap(x, y, sx, sy, width, height, null, lineByLine);
/* 403 */       break;
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void renderIsometricMap(int x, int y, int sx, int sy, int width, int height, Layer layer, boolean lineByLine)
/*     */   {
/* 425 */     ArrayList drawLayers = this.layers;
/* 426 */     if (layer != null) {
/* 427 */       drawLayers = new ArrayList();
/* 428 */       drawLayers.add(layer);
/*     */     }
/*     */ 
/* 431 */     int maxCount = width * height;
/* 432 */     int allCount = 0;
/*     */ 
/* 434 */     boolean allProcessed = false;
/*     */ 
/* 436 */     int initialLineX = x;
/* 437 */     int initialLineY = y;
/*     */ 
/* 439 */     int startLineTileX = 0;
/* 440 */     int startLineTileY = 0;
/* 441 */     while (!allProcessed)
/*     */     {
/* 443 */       int currentTileX = startLineTileX;
/* 444 */       int currentTileY = startLineTileY;
/* 445 */       int currentLineX = initialLineX;
/*     */ 
/* 447 */       int min = 0;
/* 448 */       if (height > width)
/* 449 */         min = width - currentTileX < height ? width - currentTileX - 1 : startLineTileY < width - 1 ? startLineTileY : width - 1;
/*     */       else {
/* 451 */         min = width - currentTileX < height ? width - currentTileX - 1 : startLineTileY < height - 1 ? startLineTileY : height - 1;
/*     */       }
/* 453 */       for (int burner = 0; burner <= min; burner++) {
/* 454 */         for (int layerIdx = 0; layerIdx < drawLayers.size(); layerIdx++) {
/* 455 */           Layer currentLayer = (Layer)drawLayers.get(layerIdx);
/* 456 */           currentLayer.render(currentLineX, initialLineY, currentTileX, currentTileY, 1, 0, lineByLine, this.tileWidth, this.tileHeight);
/*     */         }
/* 458 */         currentLineX += this.tileWidth;
/*     */ 
/* 460 */         allCount++;
/*     */ 
/* 453 */         currentTileX++; currentTileY--;
/*     */       }
/*     */ 
/* 467 */       if (startLineTileY < height - 1) {
/* 468 */         startLineTileY++;
/* 469 */         initialLineX -= this.tileWidth / 2;
/* 470 */         initialLineY += this.tileHeight / 2;
/*     */       } else {
/* 472 */         startLineTileX++;
/* 473 */         initialLineX += this.tileWidth / 2;
/* 474 */         initialLineY += this.tileHeight / 2;
/*     */       }
/*     */ 
/* 477 */       if (allCount >= maxCount)
/* 478 */         allProcessed = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getLayerCount()
/*     */   {
/* 488 */     return this.layers.size();
/*     */   }
/*     */ 
/*     */   private int parseInt(String value)
/*     */   {
/*     */     try
/*     */     {
/* 499 */       return Integer.parseInt(value); } catch (NumberFormatException e) {
/*     */     }
/* 501 */     return 0;
/*     */   }
/*     */ 
/*     */   private void load(InputStream in, String tileSetsLocation)
/*     */     throws SlickException
/*     */   {
/* 513 */     this.tilesLocation = tileSetsLocation;
/*     */     try
/*     */     {
/* 516 */       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
/* 517 */       factory.setValidating(false);
/* 518 */       DocumentBuilder builder = factory.newDocumentBuilder();
/* 519 */       builder.setEntityResolver(new EntityResolver()
/*     */       {
/*     */         public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
/* 522 */           return new InputSource(new ByteArrayInputStream(new byte[0]));
/*     */         }
/*     */       });
/* 526 */       Document doc = builder.parse(in);
/* 527 */       Element docElement = doc.getDocumentElement();
/*     */ 
/* 529 */       if (docElement.getAttribute("orientation").equals("orthogonal"))
/* 530 */         this.orientation = 1;
/*     */       else {
/* 532 */         this.orientation = 2;
/*     */       }
/*     */ 
/* 538 */       this.width = parseInt(docElement.getAttribute("width"));
/* 539 */       this.height = parseInt(docElement.getAttribute("height"));
/* 540 */       this.tileWidth = parseInt(docElement.getAttribute("tilewidth"));
/* 541 */       this.tileHeight = parseInt(docElement.getAttribute("tileheight"));
/*     */ 
/* 544 */       Element propsElement = (Element)docElement.getElementsByTagName("properties").item(0);
/* 545 */       if (propsElement != null) {
/* 546 */         NodeList properties = propsElement.getElementsByTagName("property");
/* 547 */         if (properties != null) {
/* 548 */           this.props = new Properties();
/* 549 */           for (int p = 0; p < properties.getLength(); p++) {
/* 550 */             Element propElement = (Element)properties.item(p);
/*     */ 
/* 552 */             String name = propElement.getAttribute("name");
/* 553 */             String value = propElement.getAttribute("value");
/* 554 */             this.props.setProperty(name, value);
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 559 */       if (this.loadTileSets) {
/* 560 */         TileSet tileSet = null;
/* 561 */         TileSet lastSet = null;
/*     */ 
/* 563 */         NodeList setNodes = docElement.getElementsByTagName("tileset");
/* 564 */         for (int i = 0; i < setNodes.getLength(); i++) {
/* 565 */           Element current = (Element)setNodes.item(i);
/*     */ 
/* 567 */           tileSet = new TileSet(this, current, !headless);
/* 568 */           tileSet.index = i;
/*     */ 
/* 570 */           if (lastSet != null) {
/* 571 */             lastSet.setLimit(tileSet.firstGID - 1);
/*     */           }
/* 573 */           lastSet = tileSet;
/*     */ 
/* 575 */           this.tileSets.add(tileSet);
/*     */         }
/*     */       }
/*     */ 
/* 579 */       NodeList layerNodes = docElement.getElementsByTagName("layer");
/* 580 */       for (int i = 0; i < layerNodes.getLength(); i++) {
/* 581 */         Element current = (Element)layerNodes.item(i);
/* 582 */         Layer layer = new Layer(this, current);
/* 583 */         layer.index = i;
/*     */ 
/* 585 */         this.layers.add(layer);
/*     */       }
/*     */ 
/* 589 */       NodeList objectGroupNodes = docElement.getElementsByTagName("objectgroup");
/*     */ 
/* 591 */       for (int i = 0; i < objectGroupNodes.getLength(); i++) {
/* 592 */         Element current = (Element)objectGroupNodes.item(i);
/* 593 */         ObjectGroup objectGroup = new ObjectGroup(current);
/* 594 */         objectGroup.index = i;
/*     */ 
/* 596 */         this.objectGroups.add(objectGroup);
/*     */       }
/*     */     } catch (Exception e) {
/* 599 */       Log.error(e);
/* 600 */       throw new SlickException("Failed to parse tilemap", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getTileSetCount()
/*     */   {
/* 610 */     return this.tileSets.size();
/*     */   }
/*     */ 
/*     */   public TileSet getTileSet(int index)
/*     */   {
/* 620 */     return (TileSet)this.tileSets.get(index);
/*     */   }
/*     */ 
/*     */   public TileSet getTileSetByGID(int gid)
/*     */   {
/* 630 */     for (int i = 0; i < this.tileSets.size(); i++) {
/* 631 */       TileSet set = (TileSet)this.tileSets.get(i);
/*     */ 
/* 633 */       if (set.contains(gid)) {
/* 634 */         return set;
/*     */       }
/*     */     }
/*     */ 
/* 638 */     return null;
/*     */   }
/*     */ 
/*     */   public TileSet findTileSet(int gid)
/*     */   {
/* 648 */     for (int i = 0; i < this.tileSets.size(); i++) {
/* 649 */       TileSet set = (TileSet)this.tileSets.get(i);
/*     */ 
/* 651 */       if (set.contains(gid)) {
/* 652 */         return set;
/*     */       }
/*     */     }
/*     */ 
/* 656 */     return null;
/*     */   }
/*     */ 
/*     */   protected void renderedLine(int visualY, int mapY, int layer)
/*     */   {
/*     */   }
/*     */ 
/*     */   public int getObjectGroupCount()
/*     */   {
/* 675 */     return this.objectGroups.size();
/*     */   }
/*     */ 
/*     */   public int getObjectCount(int groupID)
/*     */   {
/* 684 */     if ((groupID >= 0) && (groupID < this.objectGroups.size())) {
/* 685 */       ObjectGroup grp = (ObjectGroup)this.objectGroups.get(groupID);
/* 686 */       return grp.objects.size();
/*     */     }
/* 688 */     return -1;
/*     */   }
/*     */ 
/*     */   public String getObjectName(int groupID, int objectID)
/*     */   {
/* 698 */     if ((groupID >= 0) && (groupID < this.objectGroups.size())) {
/* 699 */       ObjectGroup grp = (ObjectGroup)this.objectGroups.get(groupID);
/* 700 */       if ((objectID >= 0) && (objectID < grp.objects.size())) {
/* 701 */         GroupObject object = (GroupObject)grp.objects.get(objectID);
/* 702 */         return object.name;
/*     */       }
/*     */     }
/* 705 */     return null;
/*     */   }
/*     */ 
/*     */   public String getObjectType(int groupID, int objectID)
/*     */   {
/* 715 */     if ((groupID >= 0) && (groupID < this.objectGroups.size())) {
/* 716 */       ObjectGroup grp = (ObjectGroup)this.objectGroups.get(groupID);
/* 717 */       if ((objectID >= 0) && (objectID < grp.objects.size())) {
/* 718 */         GroupObject object = (GroupObject)grp.objects.get(objectID);
/* 719 */         return object.type;
/*     */       }
/*     */     }
/* 722 */     return null;
/*     */   }
/*     */ 
/*     */   public int getObjectX(int groupID, int objectID)
/*     */   {
/* 732 */     if ((groupID >= 0) && (groupID < this.objectGroups.size())) {
/* 733 */       ObjectGroup grp = (ObjectGroup)this.objectGroups.get(groupID);
/* 734 */       if ((objectID >= 0) && (objectID < grp.objects.size())) {
/* 735 */         GroupObject object = (GroupObject)grp.objects.get(objectID);
/* 736 */         return object.x;
/*     */       }
/*     */     }
/* 739 */     return -1;
/*     */   }
/*     */ 
/*     */   public int getObjectY(int groupID, int objectID)
/*     */   {
/* 749 */     if ((groupID >= 0) && (groupID < this.objectGroups.size())) {
/* 750 */       ObjectGroup grp = (ObjectGroup)this.objectGroups.get(groupID);
/* 751 */       if ((objectID >= 0) && (objectID < grp.objects.size())) {
/* 752 */         GroupObject object = (GroupObject)grp.objects.get(objectID);
/* 753 */         return object.y;
/*     */       }
/*     */     }
/* 756 */     return -1;
/*     */   }
/*     */ 
/*     */   public int getObjectWidth(int groupID, int objectID)
/*     */   {
/* 766 */     if ((groupID >= 0) && (groupID < this.objectGroups.size())) {
/* 767 */       ObjectGroup grp = (ObjectGroup)this.objectGroups.get(groupID);
/* 768 */       if ((objectID >= 0) && (objectID < grp.objects.size())) {
/* 769 */         GroupObject object = (GroupObject)grp.objects.get(objectID);
/* 770 */         return object.width;
/*     */       }
/*     */     }
/* 773 */     return -1;
/*     */   }
/*     */ 
/*     */   public int getObjectHeight(int groupID, int objectID)
/*     */   {
/* 783 */     if ((groupID >= 0) && (groupID < this.objectGroups.size())) {
/* 784 */       ObjectGroup grp = (ObjectGroup)this.objectGroups.get(groupID);
/* 785 */       if ((objectID >= 0) && (objectID < grp.objects.size())) {
/* 786 */         GroupObject object = (GroupObject)grp.objects.get(objectID);
/* 787 */         return object.height;
/*     */       }
/*     */     }
/* 790 */     return -1;
/*     */   }
/*     */ 
/*     */   public String getObjectImage(int groupID, int objectID)
/*     */   {
/* 801 */     if ((groupID >= 0) && (groupID < this.objectGroups.size())) {
/* 802 */       ObjectGroup grp = (ObjectGroup)this.objectGroups.get(groupID);
/* 803 */       if ((objectID >= 0) && (objectID < grp.objects.size())) {
/* 804 */         GroupObject object = (GroupObject)grp.objects.get(objectID);
/*     */ 
/* 806 */         if (object == null) {
/* 807 */           return null;
/*     */         }
/*     */ 
/* 810 */         return object.image;
/*     */       }
/*     */     }
/*     */ 
/* 814 */     return null;
/*     */   }
/*     */ 
/*     */   public String getObjectProperty(int groupID, int objectID, String propertyName, String def)
/*     */   {
/* 828 */     if ((groupID >= 0) && (groupID < this.objectGroups.size())) {
/* 829 */       ObjectGroup grp = (ObjectGroup)this.objectGroups.get(groupID);
/* 830 */       if ((objectID >= 0) && (objectID < grp.objects.size())) {
/* 831 */         GroupObject object = (GroupObject)grp.objects.get(objectID);
/*     */ 
/* 833 */         if (object == null) {
/* 834 */           return def;
/*     */         }
/* 836 */         if (object.props == null) {
/* 837 */           return def;
/*     */         }
/*     */ 
/* 840 */         return object.props.getProperty(propertyName, def);
/*     */       }
/*     */     }
/* 843 */     return def;
/*     */   }
/*     */ 
/*     */   protected class GroupObject
/*     */   {
/*     */     public int index;
/*     */     public String name;
/*     */     public String type;
/*     */     public int x;
/*     */     public int y;
/*     */     public int width;
/*     */     public int height;
/*     */     private String image;
/*     */     public Properties props;
/*     */ 
/*     */     public GroupObject(Element element)
/*     */       throws SlickException
/*     */     {
/* 939 */       this.name = element.getAttribute("name");
/* 940 */       this.type = element.getAttribute("type");
/* 941 */       this.x = Integer.parseInt(element.getAttribute("x"));
/* 942 */       this.y = Integer.parseInt(element.getAttribute("y"));
/* 943 */       this.width = Integer.parseInt(element.getAttribute("width"));
/* 944 */       this.height = Integer.parseInt(element.getAttribute("height"));
/*     */ 
/* 946 */       Element imageElement = (Element)element.getElementsByTagName("image").item(0);
/*     */ 
/* 948 */       if (imageElement != null) {
/* 949 */         this.image = imageElement.getAttribute("source");
/*     */       }
/*     */ 
/* 953 */       Element propsElement = (Element)element.getElementsByTagName("properties").item(0);
/*     */ 
/* 955 */       if (propsElement != null) {
/* 956 */         NodeList properties = propsElement.getElementsByTagName("property");
/*     */ 
/* 958 */         if (properties != null) {
/* 959 */           this.props = new Properties();
/* 960 */           for (int p = 0; p < properties.getLength(); p++) {
/* 961 */             Element propElement = (Element)properties.item(p);
/*     */ 
/* 963 */             String name = propElement.getAttribute("name");
/* 964 */             String value = propElement.getAttribute("value");
/* 965 */             this.props.setProperty(name, value);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   protected class ObjectGroup
/*     */   {
/*     */     public int index;
/*     */     public String name;
/*     */     public ArrayList objects;
/*     */     public int width;
/*     */     public int height;
/*     */     public Properties props;
/*     */ 
/*     */     public ObjectGroup(Element element)
/*     */       throws SlickException
/*     */     {
/* 873 */       this.name = element.getAttribute("name");
/* 874 */       this.width = Integer.parseInt(element.getAttribute("width"));
/* 875 */       this.height = Integer.parseInt(element.getAttribute("height"));
/* 876 */       this.objects = new ArrayList();
/*     */ 
/* 879 */       Element propsElement = (Element)element.getElementsByTagName("properties").item(0);
/*     */ 
/* 881 */       if (propsElement != null) {
/* 882 */         NodeList properties = propsElement.getElementsByTagName("property");
/*     */ 
/* 884 */         if (properties != null) {
/* 885 */           this.props = new Properties();
/* 886 */           for (int p = 0; p < properties.getLength(); p++) {
/* 887 */             Element propElement = (Element)properties.item(p);
/*     */ 
/* 889 */             String name = propElement.getAttribute("name");
/* 890 */             String value = propElement.getAttribute("value");
/* 891 */             this.props.setProperty(name, value);
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 896 */       NodeList objectNodes = element.getElementsByTagName("object");
/* 897 */       for (int i = 0; i < objectNodes.getLength(); i++) {
/* 898 */         Element objElement = (Element)objectNodes.item(i);
/* 899 */         TiledMap.GroupObject object = new TiledMap.GroupObject(TiledMap.this, objElement);
/* 900 */         object.index = i;
/* 901 */         this.objects.add(object);
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tiled.TiledMap
 * JD-Core Version:    0.6.2
 */
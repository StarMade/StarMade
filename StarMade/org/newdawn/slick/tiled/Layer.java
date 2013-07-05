/*     */ package org.newdawn.slick.tiled;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.Properties;
/*     */ import java.util.zip.GZIPInputStream;
/*     */ import org.newdawn.slick.SlickException;
/*     */ import org.newdawn.slick.SpriteSheet;
/*     */ import org.newdawn.slick.util.Log;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ public class Layer
/*     */ {
/*  21 */   private static byte[] baseCodes = new byte[256];
/*     */   private final TiledMap map;
/*     */   public int index;
/*     */   public String name;
/*     */   public int[][][] data;
/*     */   public int width;
/*     */   public int height;
/*     */   public Properties props;
/*     */ 
/*     */   public Layer(TiledMap map, Element element)
/*     */     throws SlickException
/*     */   {
/*  63 */     this.map = map;
/*  64 */     this.name = element.getAttribute("name");
/*  65 */     this.width = Integer.parseInt(element.getAttribute("width"));
/*  66 */     this.height = Integer.parseInt(element.getAttribute("height"));
/*  67 */     this.data = new int[this.width][this.height][3];
/*     */ 
/*  70 */     Element propsElement = (Element)element.getElementsByTagName("properties").item(0);
/*  71 */     if (propsElement != null) {
/*  72 */       NodeList properties = propsElement.getElementsByTagName("property");
/*  73 */       if (properties != null) {
/*  74 */         this.props = new Properties();
/*  75 */         for (int p = 0; p < properties.getLength(); p++) {
/*  76 */           Element propElement = (Element)properties.item(p);
/*     */ 
/*  78 */           String name = propElement.getAttribute("name");
/*  79 */           String value = propElement.getAttribute("value");
/*  80 */           this.props.setProperty(name, value);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*  85 */     Element dataNode = (Element)element.getElementsByTagName("data").item(0);
/*  86 */     String encoding = dataNode.getAttribute("encoding");
/*  87 */     String compression = dataNode.getAttribute("compression");
/*     */ 
/*  89 */     if ((encoding.equals("base64")) && (compression.equals("gzip")))
/*     */       try {
/*  91 */         Node cdata = dataNode.getFirstChild();
/*  92 */         char[] enc = cdata.getNodeValue().trim().toCharArray();
/*  93 */         byte[] dec = decodeBase64(enc);
/*  94 */         GZIPInputStream is = new GZIPInputStream(new ByteArrayInputStream(dec));
/*     */ 
/*  96 */         for (int y = 0; y < this.height; y++)
/*  97 */           for (int x = 0; x < this.width; x++) {
/*  98 */             int tileId = 0;
/*  99 */             tileId |= is.read();
/* 100 */             tileId |= is.read() << 8;
/* 101 */             tileId |= is.read() << 16;
/* 102 */             tileId |= is.read() << 24;
/*     */ 
/* 104 */             if (tileId == 0) {
/* 105 */               this.data[x][y][0] = -1;
/* 106 */               this.data[x][y][1] = 0;
/* 107 */               this.data[x][y][2] = 0;
/*     */             } else {
/* 109 */               TileSet set = map.findTileSet(tileId);
/*     */ 
/* 111 */               if (set != null) {
/* 112 */                 this.data[x][y][0] = set.index;
/* 113 */                 this.data[x][y][1] = (tileId - set.firstGID);
/*     */               }
/* 115 */               this.data[x][y][2] = tileId;
/*     */             }
/*     */           }
/*     */       }
/*     */       catch (IOException e) {
/* 120 */         Log.error(e);
/* 121 */         throw new SlickException("Unable to decode base 64 block");
/*     */       }
/*     */     else
/* 124 */       throw new SlickException("Unsupport tiled map type: " + encoding + "," + compression + " (only gzip base64 supported)");
/*     */   }
/*     */ 
/*     */   public int getTileID(int x, int y)
/*     */   {
/* 137 */     return this.data[x][y][2];
/*     */   }
/*     */ 
/*     */   public void setTileID(int x, int y, int tile)
/*     */   {
/* 148 */     if (tile == 0) {
/* 149 */       this.data[x][y][0] = -1;
/* 150 */       this.data[x][y][1] = 0;
/* 151 */       this.data[x][y][2] = 0;
/*     */     } else {
/* 153 */       TileSet set = this.map.findTileSet(tile);
/*     */ 
/* 155 */       this.data[x][y][0] = set.index;
/* 156 */       this.data[x][y][1] = (tile - set.firstGID);
/* 157 */       this.data[x][y][2] = tile;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void render(int x, int y, int sx, int sy, int width, int ty, boolean lineByLine, int mapTileWidth, int mapTileHeight)
/*     */   {
/* 181 */     for (int tileset = 0; tileset < this.map.getTileSetCount(); tileset++) {
/* 182 */       TileSet set = null;
/*     */ 
/* 184 */       for (int tx = 0; tx < width; tx++)
/* 185 */         if ((sx + tx >= 0) && (sy + ty >= 0))
/*     */         {
/* 188 */           if ((sx + tx < this.width) && (sy + ty < this.height))
/*     */           {
/* 192 */             if (this.data[(sx + tx)][(sy + ty)][0] == tileset) {
/* 193 */               if (set == null) {
/* 194 */                 set = this.map.getTileSet(tileset);
/* 195 */                 set.tiles.startUse();
/*     */               }
/*     */ 
/* 198 */               int sheetX = set.getTileX(this.data[(sx + tx)][(sy + ty)][1]);
/* 199 */               int sheetY = set.getTileY(this.data[(sx + tx)][(sy + ty)][1]);
/*     */ 
/* 201 */               int tileOffsetY = set.tileHeight - mapTileHeight;
/*     */ 
/* 204 */               set.tiles.renderInUse(x + tx * mapTileWidth, y + ty * mapTileHeight - tileOffsetY, sheetX, sheetY);
/*     */             }
/*     */           }
/*     */         }
/* 208 */       if (lineByLine) {
/* 209 */         if (set != null) {
/* 210 */           set.tiles.endUse();
/* 211 */           set = null;
/*     */         }
/* 213 */         this.map.renderedLine(ty, ty + sy, this.index);
/*     */       }
/*     */ 
/* 216 */       if (set != null)
/* 217 */         set.tiles.endUse();
/*     */     }
/*     */   }
/*     */ 
/*     */   private byte[] decodeBase64(char[] data)
/*     */   {
/* 229 */     int temp = data.length;
/* 230 */     for (int ix = 0; ix < data.length; ix++) {
/* 231 */       if ((data[ix] > 'ÿ') || (baseCodes[data[ix]] < 0)) {
/* 232 */         temp--;
/*     */       }
/*     */     }
/*     */ 
/* 236 */     int len = temp / 4 * 3;
/* 237 */     if (temp % 4 == 3)
/* 238 */       len += 2;
/* 239 */     if (temp % 4 == 2) {
/* 240 */       len++;
/*     */     }
/* 242 */     byte[] out = new byte[len];
/*     */ 
/* 244 */     int shift = 0;
/* 245 */     int accum = 0;
/* 246 */     int index = 0;
/*     */ 
/* 248 */     for (int ix = 0; ix < data.length; ix++) {
/* 249 */       int value = data[ix] > 'ÿ' ? -1 : baseCodes[data[ix]];
/*     */ 
/* 251 */       if (value >= 0) {
/* 252 */         accum <<= 6;
/* 253 */         shift += 6;
/* 254 */         accum |= value;
/* 255 */         if (shift >= 8) {
/* 256 */           shift -= 8;
/* 257 */           out[(index++)] = ((byte)(accum >> shift & 0xFF));
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 262 */     if (index != out.length) {
/* 263 */       throw new RuntimeException("Data length appears to be wrong (wrote " + index + " should be " + out.length + ")");
/*     */     }
/*     */ 
/* 268 */     return out;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  27 */     for (int i = 0; i < 256; i++)
/*  28 */       baseCodes[i] = -1;
/*  29 */     for (int i = 65; i <= 90; i++)
/*  30 */       baseCodes[i] = ((byte)(i - 65));
/*  31 */     for (int i = 97; i <= 122; i++)
/*  32 */       baseCodes[i] = ((byte)(26 + i - 97));
/*  33 */     for (int i = 48; i <= 57; i++)
/*  34 */       baseCodes[i] = ((byte)(52 + i - 48));
/*  35 */     baseCodes[43] = 62;
/*  36 */     baseCodes[47] = 63;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tiled.Layer
 * JD-Core Version:    0.6.2
 */
/*   1:    */package org.newdawn.slick.tiled;
/*   2:    */
/*   3:    */import java.io.ByteArrayInputStream;
/*   4:    */import java.io.IOException;
/*   5:    */import java.util.Properties;
/*   6:    */import java.util.zip.GZIPInputStream;
/*   7:    */import org.newdawn.slick.SlickException;
/*   8:    */import org.newdawn.slick.SpriteSheet;
/*   9:    */import org.newdawn.slick.util.Log;
/*  10:    */import org.w3c.dom.Element;
/*  11:    */import org.w3c.dom.Node;
/*  12:    */import org.w3c.dom.NodeList;
/*  13:    */
/*  19:    */public class Layer
/*  20:    */{
/*  21: 21 */  private static byte[] baseCodes = new byte[256];
/*  22:    */  private final TiledMap map;
/*  23:    */  public int index;
/*  24:    */  public String name;
/*  25:    */  
/*  26:    */  static {
/*  27: 27 */    for (int i = 0; i < 256; i++)
/*  28: 28 */      baseCodes[i] = -1;
/*  29: 29 */    for (int i = 65; i <= 90; i++)
/*  30: 30 */      baseCodes[i] = ((byte)(i - 65));
/*  31: 31 */    for (int i = 97; i <= 122; i++)
/*  32: 32 */      baseCodes[i] = ((byte)(26 + i - 97));
/*  33: 33 */    for (int i = 48; i <= 57; i++)
/*  34: 34 */      baseCodes[i] = ((byte)(52 + i - 48));
/*  35: 35 */    baseCodes[43] = 62;
/*  36: 36 */    baseCodes[47] = 63;
/*  37:    */  }
/*  38:    */  
/*  43:    */  public int[][][] data;
/*  44:    */  
/*  48:    */  public int width;
/*  49:    */  
/*  52:    */  public int height;
/*  53:    */  
/*  56:    */  public Properties props;
/*  57:    */  
/*  60:    */  public Layer(TiledMap map, Element element)
/*  61:    */    throws SlickException
/*  62:    */  {
/*  63: 63 */    this.map = map;
/*  64: 64 */    this.name = element.getAttribute("name");
/*  65: 65 */    this.width = Integer.parseInt(element.getAttribute("width"));
/*  66: 66 */    this.height = Integer.parseInt(element.getAttribute("height"));
/*  67: 67 */    this.data = new int[this.width][this.height][3];
/*  68:    */    
/*  70: 70 */    Element propsElement = (Element)element.getElementsByTagName("properties").item(0);
/*  71: 71 */    if (propsElement != null) {
/*  72: 72 */      NodeList properties = propsElement.getElementsByTagName("property");
/*  73: 73 */      if (properties != null) {
/*  74: 74 */        this.props = new Properties();
/*  75: 75 */        for (int p = 0; p < properties.getLength(); p++) {
/*  76: 76 */          Element propElement = (Element)properties.item(p);
/*  77:    */          
/*  78: 78 */          String name = propElement.getAttribute("name");
/*  79: 79 */          String value = propElement.getAttribute("value");
/*  80: 80 */          this.props.setProperty(name, value);
/*  81:    */        }
/*  82:    */      }
/*  83:    */    }
/*  84:    */    
/*  85: 85 */    Element dataNode = (Element)element.getElementsByTagName("data").item(0);
/*  86: 86 */    String encoding = dataNode.getAttribute("encoding");
/*  87: 87 */    String compression = dataNode.getAttribute("compression");
/*  88:    */    
/*  89: 89 */    if ((encoding.equals("base64")) && (compression.equals("gzip"))) {
/*  90:    */      try {
/*  91: 91 */        Node cdata = dataNode.getFirstChild();
/*  92: 92 */        char[] enc = cdata.getNodeValue().trim().toCharArray();
/*  93: 93 */        byte[] dec = decodeBase64(enc);
/*  94: 94 */        GZIPInputStream is = new GZIPInputStream(new ByteArrayInputStream(dec));
/*  95:    */        
/*  96: 96 */        for (int y = 0; y < this.height; y++) {
/*  97: 97 */          for (int x = 0; x < this.width; x++) {
/*  98: 98 */            int tileId = 0;
/*  99: 99 */            tileId |= is.read();
/* 100:100 */            tileId |= is.read() << 8;
/* 101:101 */            tileId |= is.read() << 16;
/* 102:102 */            tileId |= is.read() << 24;
/* 103:    */            
/* 104:104 */            if (tileId == 0) {
/* 105:105 */              this.data[x][y][0] = -1;
/* 106:106 */              this.data[x][y][1] = 0;
/* 107:107 */              this.data[x][y][2] = 0;
/* 108:    */            } else {
/* 109:109 */              TileSet set = map.findTileSet(tileId);
/* 110:    */              
/* 111:111 */              if (set != null) {
/* 112:112 */                this.data[x][y][0] = set.index;
/* 113:113 */                this.data[x][y][1] = (tileId - set.firstGID);
/* 114:    */              }
/* 115:115 */              this.data[x][y][2] = tileId;
/* 116:    */            }
/* 117:    */          }
/* 118:    */        }
/* 119:    */      } catch (IOException e) {
/* 120:120 */        Log.error(e);
/* 121:121 */        throw new SlickException("Unable to decode base 64 block");
/* 122:    */      }
/* 123:    */    } else {
/* 124:124 */      throw new SlickException("Unsupport tiled map type: " + encoding + "," + compression + " (only gzip base64 supported)");
/* 125:    */    }
/* 126:    */  }
/* 127:    */  
/* 135:    */  public int getTileID(int x, int y)
/* 136:    */  {
/* 137:137 */    return this.data[x][y][2];
/* 138:    */  }
/* 139:    */  
/* 146:    */  public void setTileID(int x, int y, int tile)
/* 147:    */  {
/* 148:148 */    if (tile == 0) {
/* 149:149 */      this.data[x][y][0] = -1;
/* 150:150 */      this.data[x][y][1] = 0;
/* 151:151 */      this.data[x][y][2] = 0;
/* 152:    */    } else {
/* 153:153 */      TileSet set = this.map.findTileSet(tile);
/* 154:    */      
/* 155:155 */      this.data[x][y][0] = set.index;
/* 156:156 */      this.data[x][y][1] = (tile - set.firstGID);
/* 157:157 */      this.data[x][y][2] = tile;
/* 158:    */    }
/* 159:    */  }
/* 160:    */  
/* 179:    */  public void render(int x, int y, int sx, int sy, int width, int ty, boolean lineByLine, int mapTileWidth, int mapTileHeight)
/* 180:    */  {
/* 181:181 */    for (int tileset = 0; tileset < this.map.getTileSetCount(); tileset++) {
/* 182:182 */      TileSet set = null;
/* 183:    */      
/* 184:184 */      for (int tx = 0; tx < width; tx++) {
/* 185:185 */        if ((sx + tx >= 0) && (sy + ty >= 0))
/* 186:    */        {
/* 188:188 */          if ((sx + tx < this.width) && (sy + ty < this.height))
/* 189:    */          {
/* 192:192 */            if (this.data[(sx + tx)][(sy + ty)][0] == tileset) {
/* 193:193 */              if (set == null) {
/* 194:194 */                set = this.map.getTileSet(tileset);
/* 195:195 */                set.tiles.startUse();
/* 196:    */              }
/* 197:    */              
/* 198:198 */              int sheetX = set.getTileX(this.data[(sx + tx)][(sy + ty)][1]);
/* 199:199 */              int sheetY = set.getTileY(this.data[(sx + tx)][(sy + ty)][1]);
/* 200:    */              
/* 201:201 */              int tileOffsetY = set.tileHeight - mapTileHeight;
/* 202:    */              
/* 204:204 */              set.tiles.renderInUse(x + tx * mapTileWidth, y + ty * mapTileHeight - tileOffsetY, sheetX, sheetY);
/* 205:    */            } }
/* 206:    */        }
/* 207:    */      }
/* 208:208 */      if (lineByLine) {
/* 209:209 */        if (set != null) {
/* 210:210 */          set.tiles.endUse();
/* 211:211 */          set = null;
/* 212:    */        }
/* 213:213 */        this.map.renderedLine(ty, ty + sy, this.index);
/* 214:    */      }
/* 215:    */      
/* 216:216 */      if (set != null) {
/* 217:217 */        set.tiles.endUse();
/* 218:    */      }
/* 219:    */    }
/* 220:    */  }
/* 221:    */  
/* 227:    */  private byte[] decodeBase64(char[] data)
/* 228:    */  {
/* 229:229 */    int temp = data.length;
/* 230:230 */    for (int ix = 0; ix < data.length; ix++) {
/* 231:231 */      if ((data[ix] > 'ÿ') || (baseCodes[data[ix]] < 0)) {
/* 232:232 */        temp--;
/* 233:    */      }
/* 234:    */    }
/* 235:    */    
/* 236:236 */    int len = temp / 4 * 3;
/* 237:237 */    if (temp % 4 == 3)
/* 238:238 */      len += 2;
/* 239:239 */    if (temp % 4 == 2) {
/* 240:240 */      len++;
/* 241:    */    }
/* 242:242 */    byte[] out = new byte[len];
/* 243:    */    
/* 244:244 */    int shift = 0;
/* 245:245 */    int accum = 0;
/* 246:246 */    int index = 0;
/* 247:    */    
/* 248:248 */    for (int ix = 0; ix < data.length; ix++) {
/* 249:249 */      int value = data[ix] > 'ÿ' ? -1 : baseCodes[data[ix]];
/* 250:    */      
/* 251:251 */      if (value >= 0) {
/* 252:252 */        accum <<= 6;
/* 253:253 */        shift += 6;
/* 254:254 */        accum |= value;
/* 255:255 */        if (shift >= 8) {
/* 256:256 */          shift -= 8;
/* 257:257 */          out[(index++)] = ((byte)(accum >> shift & 0xFF));
/* 258:    */        }
/* 259:    */      }
/* 260:    */    }
/* 261:    */    
/* 262:262 */    if (index != out.length) {
/* 263:263 */      throw new RuntimeException("Data length appears to be wrong (wrote " + index + " should be " + out.length + ")");
/* 264:    */    }
/* 265:    */    
/* 268:268 */    return out;
/* 269:    */  }
/* 270:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tiled.Layer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
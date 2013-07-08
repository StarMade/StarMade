/*   1:    */package org.newdawn.slick;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.InputStream;
/*   5:    */import java.net.URL;
/*   6:    */import org.newdawn.slick.opengl.Texture;
/*   7:    */
/*  15:    */public class SpriteSheet
/*  16:    */  extends Image
/*  17:    */{
/*  18:    */  private int tw;
/*  19:    */  private int th;
/*  20: 20 */  private int margin = 0;
/*  21:    */  
/*  24:    */  private Image[][] subImages;
/*  25:    */  
/*  28:    */  private int spacing;
/*  29:    */  
/*  31:    */  private Image target;
/*  32:    */  
/*  35:    */  public SpriteSheet(URL ref, int tw, int th)
/*  36:    */    throws SlickException, IOException
/*  37:    */  {
/*  38: 38 */    this(new Image(ref.openStream(), ref.toString(), false), tw, th);
/*  39:    */  }
/*  40:    */  
/*  47:    */  public SpriteSheet(Image image, int tw, int th)
/*  48:    */  {
/*  49: 49 */    super(image);
/*  50:    */    
/*  51: 51 */    this.target = image;
/*  52: 52 */    this.tw = tw;
/*  53: 53 */    this.th = th;
/*  54:    */    
/*  57: 57 */    initImpl();
/*  58:    */  }
/*  59:    */  
/*  68:    */  public SpriteSheet(Image image, int tw, int th, int spacing, int margin)
/*  69:    */  {
/*  70: 70 */    super(image);
/*  71:    */    
/*  72: 72 */    this.target = image;
/*  73: 73 */    this.tw = tw;
/*  74: 74 */    this.th = th;
/*  75: 75 */    this.spacing = spacing;
/*  76: 76 */    this.margin = margin;
/*  77:    */    
/*  80: 80 */    initImpl();
/*  81:    */  }
/*  82:    */  
/*  90:    */  public SpriteSheet(Image image, int tw, int th, int spacing)
/*  91:    */  {
/*  92: 92 */    this(image, tw, th, spacing, 0);
/*  93:    */  }
/*  94:    */  
/* 102:    */  public SpriteSheet(String ref, int tw, int th, int spacing)
/* 103:    */    throws SlickException
/* 104:    */  {
/* 105:105 */    this(ref, tw, th, null, spacing);
/* 106:    */  }
/* 107:    */  
/* 114:    */  public SpriteSheet(String ref, int tw, int th)
/* 115:    */    throws SlickException
/* 116:    */  {
/* 117:117 */    this(ref, tw, th, null);
/* 118:    */  }
/* 119:    */  
/* 127:    */  public SpriteSheet(String ref, int tw, int th, Color col)
/* 128:    */    throws SlickException
/* 129:    */  {
/* 130:130 */    this(ref, tw, th, col, 0);
/* 131:    */  }
/* 132:    */  
/* 141:    */  public SpriteSheet(String ref, int tw, int th, Color col, int spacing)
/* 142:    */    throws SlickException
/* 143:    */  {
/* 144:144 */    super(ref, false, 2, col);
/* 145:    */    
/* 146:146 */    this.target = this;
/* 147:147 */    this.tw = tw;
/* 148:148 */    this.th = th;
/* 149:149 */    this.spacing = spacing;
/* 150:    */  }
/* 151:    */  
/* 159:    */  public SpriteSheet(String name, InputStream ref, int tw, int th)
/* 160:    */    throws SlickException
/* 161:    */  {
/* 162:162 */    super(ref, name, false);
/* 163:    */    
/* 164:164 */    this.target = this;
/* 165:165 */    this.tw = tw;
/* 166:166 */    this.th = th;
/* 167:    */  }
/* 168:    */  
/* 171:    */  protected void initImpl()
/* 172:    */  {
/* 173:173 */    if (this.subImages != null) {
/* 174:174 */      return;
/* 175:    */    }
/* 176:    */    
/* 177:177 */    int tilesAcross = (getWidth() - this.margin * 2 - this.tw) / (this.tw + this.spacing) + 1;
/* 178:178 */    int tilesDown = (getHeight() - this.margin * 2 - this.th) / (this.th + this.spacing) + 1;
/* 179:179 */    if ((getHeight() - this.th) % (this.th + this.spacing) != 0) {
/* 180:180 */      tilesDown++;
/* 181:    */    }
/* 182:    */    
/* 183:183 */    this.subImages = new Image[tilesAcross][tilesDown];
/* 184:184 */    for (int x = 0; x < tilesAcross; x++) {
/* 185:185 */      for (int y = 0; y < tilesDown; y++) {
/* 186:186 */        this.subImages[x][y] = getSprite(x, y);
/* 187:    */      }
/* 188:    */    }
/* 189:    */  }
/* 190:    */  
/* 197:    */  public Image getSubImage(int x, int y)
/* 198:    */  {
/* 199:199 */    init();
/* 200:    */    
/* 201:201 */    if ((x < 0) || (x >= this.subImages.length)) {
/* 202:202 */      throw new RuntimeException("SubImage out of sheet bounds: " + x + "," + y);
/* 203:    */    }
/* 204:204 */    if ((y < 0) || (y >= this.subImages[0].length)) {
/* 205:205 */      throw new RuntimeException("SubImage out of sheet bounds: " + x + "," + y);
/* 206:    */    }
/* 207:    */    
/* 208:208 */    return this.subImages[x][y];
/* 209:    */  }
/* 210:    */  
/* 217:    */  public Image getSprite(int x, int y)
/* 218:    */  {
/* 219:219 */    this.target.init();
/* 220:220 */    initImpl();
/* 221:    */    
/* 222:222 */    if ((x < 0) || (x >= this.subImages.length)) {
/* 223:223 */      throw new RuntimeException("SubImage out of sheet bounds: " + x + "," + y);
/* 224:    */    }
/* 225:225 */    if ((y < 0) || (y >= this.subImages[0].length)) {
/* 226:226 */      throw new RuntimeException("SubImage out of sheet bounds: " + x + "," + y);
/* 227:    */    }
/* 228:    */    
/* 229:229 */    return this.target.getSubImage(x * (this.tw + this.spacing) + this.margin, y * (this.th + this.spacing) + this.margin, this.tw, this.th);
/* 230:    */  }
/* 231:    */  
/* 236:    */  public int getHorizontalCount()
/* 237:    */  {
/* 238:238 */    this.target.init();
/* 239:239 */    initImpl();
/* 240:    */    
/* 241:241 */    return this.subImages.length;
/* 242:    */  }
/* 243:    */  
/* 248:    */  public int getVerticalCount()
/* 249:    */  {
/* 250:250 */    this.target.init();
/* 251:251 */    initImpl();
/* 252:    */    
/* 253:253 */    return this.subImages[0].length;
/* 254:    */  }
/* 255:    */  
/* 266:    */  public void renderInUse(int x, int y, int sx, int sy)
/* 267:    */  {
/* 268:268 */    this.subImages[sx][sy].drawEmbedded(x, y, this.tw, this.th);
/* 269:    */  }
/* 270:    */  
/* 273:    */  public void endUse()
/* 274:    */  {
/* 275:275 */    if (this.target == this) {
/* 276:276 */      super.endUse();
/* 277:277 */      return;
/* 278:    */    }
/* 279:279 */    this.target.endUse();
/* 280:    */  }
/* 281:    */  
/* 284:    */  public void startUse()
/* 285:    */  {
/* 286:286 */    if (this.target == this) {
/* 287:287 */      super.startUse();
/* 288:288 */      return;
/* 289:    */    }
/* 290:290 */    this.target.startUse();
/* 291:    */  }
/* 292:    */  
/* 295:    */  public void setTexture(Texture texture)
/* 296:    */  {
/* 297:297 */    if (this.target == this) {
/* 298:298 */      super.setTexture(texture);
/* 299:299 */      return;
/* 300:    */    }
/* 301:301 */    this.target.setTexture(texture);
/* 302:    */  }
/* 303:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.SpriteSheet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
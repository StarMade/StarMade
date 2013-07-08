/*   1:    */package org.newdawn.slick;
/*   2:    */
/*   3:    */import java.awt.FontMetrics;
/*   4:    */import java.awt.Graphics2D;
/*   5:    */import java.awt.RenderingHints;
/*   6:    */import java.awt.image.BufferedImage;
/*   7:    */import java.io.IOException;
/*   8:    */import java.io.PrintStream;
/*   9:    */import java.util.HashMap;
/*  10:    */import java.util.Map;
/*  11:    */import org.newdawn.slick.opengl.GLUtils;
/*  12:    */import org.newdawn.slick.opengl.Texture;
/*  13:    */import org.newdawn.slick.opengl.renderer.Renderer;
/*  14:    */import org.newdawn.slick.opengl.renderer.SGL;
/*  15:    */import org.newdawn.slick.util.BufferedImageUtil;
/*  16:    */
/*  25:    */public class TrueTypeFont
/*  26:    */  implements Font
/*  27:    */{
/*  28: 28 */  private static final SGL GL = ;
/*  29:    */  
/*  31: 31 */  private IntObject[] charArray = new IntObject[256];
/*  32:    */  
/*  34: 34 */  private Map customChars = new HashMap();
/*  35:    */  
/*  37:    */  private boolean antiAlias;
/*  38:    */  
/*  40: 40 */  private int fontSize = 0;
/*  41:    */  
/*  43: 43 */  private int fontHeight = 0;
/*  44:    */  
/*  46:    */  private Texture fontTexture;
/*  47:    */  
/*  49: 49 */  private int textureWidth = 512;
/*  50:    */  
/*  52: 52 */  private int textureHeight = 512;
/*  53:    */  
/*  65:    */  private java.awt.Font font;
/*  66:    */  
/*  77:    */  private FontMetrics fontMetrics;
/*  78:    */  
/*  90:    */  public TrueTypeFont(java.awt.Font font, boolean antiAlias, char[] additionalChars)
/*  91:    */  {
/*  92: 92 */    GLUtils.checkGLContext();
/*  93:    */    
/*  94: 94 */    this.font = font;
/*  95: 95 */    this.fontSize = font.getSize();
/*  96: 96 */    this.antiAlias = antiAlias;
/*  97:    */    
/*  98: 98 */    createSet(additionalChars);
/*  99:    */  }
/* 100:    */  
/* 110:    */  public TrueTypeFont(java.awt.Font font, boolean antiAlias)
/* 111:    */  {
/* 112:112 */    this(font, antiAlias, null);
/* 113:    */  }
/* 114:    */  
/* 123:    */  private BufferedImage getFontImage(char ch)
/* 124:    */  {
/* 125:125 */    BufferedImage tempfontImage = new BufferedImage(1, 1, 2);
/* 126:    */    
/* 127:127 */    Graphics2D g = (Graphics2D)tempfontImage.getGraphics();
/* 128:128 */    if (this.antiAlias == true) {
/* 129:129 */      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 130:    */    }
/* 131:    */    
/* 132:132 */    g.setFont(this.font);
/* 133:133 */    this.fontMetrics = g.getFontMetrics();
/* 134:134 */    int charwidth = this.fontMetrics.charWidth(ch);
/* 135:    */    
/* 136:136 */    if (charwidth <= 0) {
/* 137:137 */      charwidth = 1;
/* 138:    */    }
/* 139:139 */    int charheight = this.fontMetrics.getHeight();
/* 140:140 */    if (charheight <= 0) {
/* 141:141 */      charheight = this.fontSize;
/* 142:    */    }
/* 143:    */    
/* 146:146 */    BufferedImage fontImage = new BufferedImage(charwidth, charheight, 2);
/* 147:    */    
/* 148:148 */    Graphics2D gt = (Graphics2D)fontImage.getGraphics();
/* 149:149 */    if (this.antiAlias == true) {
/* 150:150 */      gt.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 151:    */    }
/* 152:    */    
/* 153:153 */    gt.setFont(this.font);
/* 154:    */    
/* 155:155 */    gt.setColor(java.awt.Color.WHITE);
/* 156:156 */    int charx = 0;
/* 157:157 */    int chary = 0;
/* 158:158 */    gt.drawString(String.valueOf(ch), charx, chary + this.fontMetrics.getAscent());
/* 159:    */    
/* 161:161 */    return fontImage;
/* 162:    */  }
/* 163:    */  
/* 170:    */  private void createSet(char[] customCharsArray)
/* 171:    */  {
/* 172:172 */    if ((customCharsArray != null) && (customCharsArray.length > 0)) {
/* 173:173 */      this.textureWidth *= 2;
/* 174:    */    }
/* 175:    */    
/* 180:    */    try
/* 181:    */    {
/* 182:182 */      BufferedImage imgTemp = new BufferedImage(this.textureWidth, this.textureHeight, 2);
/* 183:183 */      Graphics2D g = (Graphics2D)imgTemp.getGraphics();
/* 184:    */      
/* 185:185 */      g.setColor(new java.awt.Color(255, 255, 255, 1));
/* 186:186 */      g.fillRect(0, 0, this.textureWidth, this.textureHeight);
/* 187:    */      
/* 188:188 */      int rowHeight = 0;
/* 189:189 */      int positionX = 0;
/* 190:190 */      int positionY = 0;
/* 191:    */      
/* 192:192 */      int customCharsLength = customCharsArray != null ? customCharsArray.length : 0;
/* 193:    */      
/* 194:194 */      for (int i = 0; i < 256 + customCharsLength; i++)
/* 195:    */      {
/* 197:197 */        char ch = i < 256 ? (char)i : customCharsArray[(i - 256)];
/* 198:    */        
/* 199:199 */        BufferedImage fontImage = getFontImage(ch);
/* 200:    */        
/* 201:201 */        IntObject newIntObject = new IntObject(null);
/* 202:    */        
/* 203:203 */        newIntObject.width = fontImage.getWidth();
/* 204:204 */        newIntObject.height = fontImage.getHeight();
/* 205:    */        
/* 206:206 */        if (positionX + newIntObject.width >= this.textureWidth) {
/* 207:207 */          positionX = 0;
/* 208:208 */          positionY += rowHeight;
/* 209:209 */          rowHeight = 0;
/* 210:    */        }
/* 211:    */        
/* 212:212 */        newIntObject.storedX = positionX;
/* 213:213 */        newIntObject.storedY = positionY;
/* 214:    */        
/* 215:215 */        if (newIntObject.height > this.fontHeight) {
/* 216:216 */          this.fontHeight = newIntObject.height;
/* 217:    */        }
/* 218:    */        
/* 219:219 */        if (newIntObject.height > rowHeight) {
/* 220:220 */          rowHeight = newIntObject.height;
/* 221:    */        }
/* 222:    */        
/* 224:224 */        g.drawImage(fontImage, positionX, positionY, null);
/* 225:    */        
/* 226:226 */        positionX += newIntObject.width;
/* 227:    */        
/* 228:228 */        if (i < 256) {
/* 229:229 */          this.charArray[i] = newIntObject;
/* 230:    */        } else {
/* 231:231 */          this.customChars.put(new Character(ch), newIntObject);
/* 232:    */        }
/* 233:    */        
/* 234:234 */        fontImage = null;
/* 235:    */      }
/* 236:    */      
/* 237:237 */      this.fontTexture = BufferedImageUtil.getTexture(this.font.toString(), imgTemp);
/* 238:    */    }
/* 239:    */    catch (IOException e)
/* 240:    */    {
/* 241:241 */      System.err.println("Failed to create font.");
/* 242:242 */      e.printStackTrace();
/* 243:    */    }
/* 244:    */  }
/* 245:    */  
/* 267:    */  private void drawQuad(float drawX, float drawY, float drawX2, float drawY2, float srcX, float srcY, float srcX2, float srcY2)
/* 268:    */  {
/* 269:269 */    float DrawWidth = drawX2 - drawX;
/* 270:270 */    float DrawHeight = drawY2 - drawY;
/* 271:271 */    float TextureSrcX = srcX / this.textureWidth;
/* 272:272 */    float TextureSrcY = srcY / this.textureHeight;
/* 273:273 */    float SrcWidth = srcX2 - srcX;
/* 274:274 */    float SrcHeight = srcY2 - srcY;
/* 275:275 */    float RenderWidth = SrcWidth / this.textureWidth;
/* 276:276 */    float RenderHeight = SrcHeight / this.textureHeight;
/* 277:    */    
/* 278:278 */    GL.glTexCoord2f(TextureSrcX, TextureSrcY);
/* 279:279 */    GL.glVertex2f(drawX, drawY);
/* 280:280 */    GL.glTexCoord2f(TextureSrcX, TextureSrcY + RenderHeight);
/* 281:281 */    GL.glVertex2f(drawX, drawY + DrawHeight);
/* 282:282 */    GL.glTexCoord2f(TextureSrcX + RenderWidth, TextureSrcY + RenderHeight);
/* 283:283 */    GL.glVertex2f(drawX + DrawWidth, drawY + DrawHeight);
/* 284:284 */    GL.glTexCoord2f(TextureSrcX + RenderWidth, TextureSrcY);
/* 285:285 */    GL.glVertex2f(drawX + DrawWidth, drawY);
/* 286:    */  }
/* 287:    */  
/* 295:    */  public int getWidth(String whatchars)
/* 296:    */  {
/* 297:297 */    int totalwidth = 0;
/* 298:298 */    IntObject intObject = null;
/* 299:299 */    int currentChar = 0;
/* 300:300 */    for (int i = 0; i < whatchars.length(); i++) {
/* 301:301 */      currentChar = whatchars.charAt(i);
/* 302:302 */      if (currentChar < 256) {
/* 303:303 */        intObject = this.charArray[currentChar];
/* 304:    */      } else {
/* 305:305 */        intObject = (IntObject)this.customChars.get(new Character((char)currentChar));
/* 306:    */      }
/* 307:    */      
/* 308:308 */      if (intObject != null)
/* 309:309 */        totalwidth += intObject.width;
/* 310:    */    }
/* 311:311 */    return totalwidth;
/* 312:    */  }
/* 313:    */  
/* 318:    */  public int getHeight()
/* 319:    */  {
/* 320:320 */    return this.fontHeight;
/* 321:    */  }
/* 322:    */  
/* 327:    */  public int getHeight(String HeightString)
/* 328:    */  {
/* 329:329 */    return this.fontHeight;
/* 330:    */  }
/* 331:    */  
/* 336:    */  public int getLineHeight()
/* 337:    */  {
/* 338:338 */    return this.fontHeight;
/* 339:    */  }
/* 340:    */  
/* 353:    */  public void drawString(float x, float y, String whatchars, Color color)
/* 354:    */  {
/* 355:355 */    drawString(x, y, whatchars, color, 0, whatchars.length() - 1);
/* 356:    */  }
/* 357:    */  
/* 361:    */  public void drawString(float x, float y, String whatchars, Color color, int startIndex, int endIndex)
/* 362:    */  {
/* 363:363 */    color.bind();
/* 364:364 */    this.fontTexture.bind();
/* 365:    */    
/* 366:366 */    IntObject intObject = null;
/* 367:    */    
/* 369:369 */    GL.glBegin(7);
/* 370:    */    
/* 371:371 */    int totalwidth = 0;
/* 372:372 */    for (int i = 0; i < whatchars.length(); i++) {
/* 373:373 */      int charCurrent = whatchars.charAt(i);
/* 374:374 */      if (charCurrent < 256) {
/* 375:375 */        intObject = this.charArray[charCurrent];
/* 376:    */      } else {
/* 377:377 */        intObject = (IntObject)this.customChars.get(new Character((char)charCurrent));
/* 378:    */      }
/* 379:    */      
/* 380:380 */      if (intObject != null) {
/* 381:381 */        if ((i >= startIndex) || (i <= endIndex)) {
/* 382:382 */          drawQuad(x + totalwidth, y, x + totalwidth + intObject.width, y + intObject.height, intObject.storedX, intObject.storedY, intObject.storedX + intObject.width, intObject.storedY + intObject.height);
/* 383:    */        }
/* 384:    */        
/* 388:388 */        totalwidth += intObject.width;
/* 389:    */      }
/* 390:    */    }
/* 391:    */    
/* 392:392 */    GL.glEnd();
/* 393:    */  }
/* 394:    */  
/* 404:    */  public void drawString(float x, float y, String whatchars)
/* 405:    */  {
/* 406:406 */    drawString(x, y, whatchars, Color.white);
/* 407:    */  }
/* 408:    */  
/* 409:    */  private class IntObject
/* 410:    */  {
/* 411:    */    public int width;
/* 412:    */    public int height;
/* 413:    */    public int storedX;
/* 414:    */    public int storedY;
/* 415:    */    
/* 416:    */    private IntObject() {}
/* 417:    */  }
/* 418:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.TrueTypeFont
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
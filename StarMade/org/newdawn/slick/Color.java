/*   1:    */package org.newdawn.slick;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */import org.newdawn.slick.opengl.renderer.Renderer;
/*   6:    */import org.newdawn.slick.opengl.renderer.SGL;
/*   7:    */
/*  15:    */public class Color
/*  16:    */  implements Serializable
/*  17:    */{
/*  18:    */  private static final long serialVersionUID = 1393939L;
/*  19: 19 */  protected transient SGL GL = Renderer.get();
/*  20:    */  
/*  22: 22 */  public static final Color transparent = new Color(0.0F, 0.0F, 0.0F, 0.0F);
/*  23:    */  
/*  24: 24 */  public static final Color white = new Color(1.0F, 1.0F, 1.0F, 1.0F);
/*  25:    */  
/*  26: 26 */  public static final Color yellow = new Color(1.0F, 1.0F, 0.0F, 1.0F);
/*  27:    */  
/*  28: 28 */  public static final Color red = new Color(1.0F, 0.0F, 0.0F, 1.0F);
/*  29:    */  
/*  30: 30 */  public static final Color blue = new Color(0.0F, 0.0F, 1.0F, 1.0F);
/*  31:    */  
/*  32: 32 */  public static final Color green = new Color(0.0F, 1.0F, 0.0F, 1.0F);
/*  33:    */  
/*  34: 34 */  public static final Color black = new Color(0.0F, 0.0F, 0.0F, 1.0F);
/*  35:    */  
/*  36: 36 */  public static final Color gray = new Color(0.5F, 0.5F, 0.5F, 1.0F);
/*  37:    */  
/*  38: 38 */  public static final Color cyan = new Color(0.0F, 1.0F, 1.0F, 1.0F);
/*  39:    */  
/*  40: 40 */  public static final Color darkGray = new Color(0.3F, 0.3F, 0.3F, 1.0F);
/*  41:    */  
/*  42: 42 */  public static final Color lightGray = new Color(0.7F, 0.7F, 0.7F, 1.0F);
/*  43:    */  
/*  44: 44 */  public static final Color pink = new Color(255, 175, 175, 255);
/*  45:    */  
/*  46: 46 */  public static final Color orange = new Color(255, 200, 0, 255);
/*  47:    */  
/*  48: 48 */  public static final Color magenta = new Color(255, 0, 255, 255);
/*  49:    */  
/*  51:    */  public float r;
/*  52:    */  
/*  53:    */  public float g;
/*  54:    */  
/*  55:    */  public float b;
/*  56:    */  
/*  57: 57 */  public float a = 1.0F;
/*  58:    */  
/*  63:    */  public Color(Color color)
/*  64:    */  {
/*  65: 65 */    this.r = color.r;
/*  66: 66 */    this.g = color.g;
/*  67: 67 */    this.b = color.b;
/*  68: 68 */    this.a = color.a;
/*  69:    */  }
/*  70:    */  
/*  75:    */  public Color(FloatBuffer buffer)
/*  76:    */  {
/*  77: 77 */    this.r = buffer.get();
/*  78: 78 */    this.g = buffer.get();
/*  79: 79 */    this.b = buffer.get();
/*  80: 80 */    this.a = buffer.get();
/*  81:    */  }
/*  82:    */  
/*  89:    */  public Color(float r, float g, float b)
/*  90:    */  {
/*  91: 91 */    this.r = r;
/*  92: 92 */    this.g = g;
/*  93: 93 */    this.b = b;
/*  94: 94 */    this.a = 1.0F;
/*  95:    */  }
/*  96:    */  
/* 104:    */  public Color(float r, float g, float b, float a)
/* 105:    */  {
/* 106:106 */    this.r = Math.min(r, 1.0F);
/* 107:107 */    this.g = Math.min(g, 1.0F);
/* 108:108 */    this.b = Math.min(b, 1.0F);
/* 109:109 */    this.a = Math.min(a, 1.0F);
/* 110:    */  }
/* 111:    */  
/* 118:    */  public Color(int r, int g, int b)
/* 119:    */  {
/* 120:120 */    this.r = (r / 255.0F);
/* 121:121 */    this.g = (g / 255.0F);
/* 122:122 */    this.b = (b / 255.0F);
/* 123:123 */    this.a = 1.0F;
/* 124:    */  }
/* 125:    */  
/* 133:    */  public Color(int r, int g, int b, int a)
/* 134:    */  {
/* 135:135 */    this.r = (r / 255.0F);
/* 136:136 */    this.g = (g / 255.0F);
/* 137:137 */    this.b = (b / 255.0F);
/* 138:138 */    this.a = (a / 255.0F);
/* 139:    */  }
/* 140:    */  
/* 147:    */  public Color(int value)
/* 148:    */  {
/* 149:149 */    int r = (value & 0xFF0000) >> 16;
/* 150:150 */    int g = (value & 0xFF00) >> 8;
/* 151:151 */    int b = value & 0xFF;
/* 152:152 */    int a = (value & 0xFF000000) >> 24;
/* 153:    */    
/* 154:154 */    if (a < 0) {
/* 155:155 */      a += 256;
/* 156:    */    }
/* 157:157 */    if (a == 0) {
/* 158:158 */      a = 255;
/* 159:    */    }
/* 160:    */    
/* 161:161 */    this.r = (r / 255.0F);
/* 162:162 */    this.g = (g / 255.0F);
/* 163:163 */    this.b = (b / 255.0F);
/* 164:164 */    this.a = (a / 255.0F);
/* 165:    */  }
/* 166:    */  
/* 173:    */  public static Color decode(String nm)
/* 174:    */  {
/* 175:175 */    return new Color(Integer.decode(nm).intValue());
/* 176:    */  }
/* 177:    */  
/* 180:    */  public void bind()
/* 181:    */  {
/* 182:182 */    this.GL.glColor4f(this.r, this.g, this.b, this.a);
/* 183:    */  }
/* 184:    */  
/* 187:    */  public int hashCode()
/* 188:    */  {
/* 189:189 */    return (int)(this.r + this.g + this.b + this.a) * 255;
/* 190:    */  }
/* 191:    */  
/* 194:    */  public boolean equals(Object other)
/* 195:    */  {
/* 196:196 */    if ((other instanceof Color)) {
/* 197:197 */      Color o = (Color)other;
/* 198:198 */      return (o.r == this.r) && (o.g == this.g) && (o.b == this.b) && (o.a == this.a);
/* 199:    */    }
/* 200:    */    
/* 201:201 */    return false;
/* 202:    */  }
/* 203:    */  
/* 206:    */  public String toString()
/* 207:    */  {
/* 208:208 */    return "Color (" + this.r + "," + this.g + "," + this.b + "," + this.a + ")";
/* 209:    */  }
/* 210:    */  
/* 215:    */  public Color darker()
/* 216:    */  {
/* 217:217 */    return darker(0.5F);
/* 218:    */  }
/* 219:    */  
/* 225:    */  public Color darker(float scale)
/* 226:    */  {
/* 227:227 */    scale = 1.0F - scale;
/* 228:228 */    Color temp = new Color(this.r * scale, this.g * scale, this.b * scale, this.a);
/* 229:    */    
/* 230:230 */    return temp;
/* 231:    */  }
/* 232:    */  
/* 237:    */  public Color brighter()
/* 238:    */  {
/* 239:239 */    return brighter(0.2F);
/* 240:    */  }
/* 241:    */  
/* 246:    */  public int getRed()
/* 247:    */  {
/* 248:248 */    return (int)(this.r * 255.0F);
/* 249:    */  }
/* 250:    */  
/* 255:    */  public int getGreen()
/* 256:    */  {
/* 257:257 */    return (int)(this.g * 255.0F);
/* 258:    */  }
/* 259:    */  
/* 264:    */  public int getBlue()
/* 265:    */  {
/* 266:266 */    return (int)(this.b * 255.0F);
/* 267:    */  }
/* 268:    */  
/* 273:    */  public int getAlpha()
/* 274:    */  {
/* 275:275 */    return (int)(this.a * 255.0F);
/* 276:    */  }
/* 277:    */  
/* 282:    */  public int getRedByte()
/* 283:    */  {
/* 284:284 */    return (int)(this.r * 255.0F);
/* 285:    */  }
/* 286:    */  
/* 291:    */  public int getGreenByte()
/* 292:    */  {
/* 293:293 */    return (int)(this.g * 255.0F);
/* 294:    */  }
/* 295:    */  
/* 300:    */  public int getBlueByte()
/* 301:    */  {
/* 302:302 */    return (int)(this.b * 255.0F);
/* 303:    */  }
/* 304:    */  
/* 309:    */  public int getAlphaByte()
/* 310:    */  {
/* 311:311 */    return (int)(this.a * 255.0F);
/* 312:    */  }
/* 313:    */  
/* 319:    */  public Color brighter(float scale)
/* 320:    */  {
/* 321:321 */    scale += 1.0F;
/* 322:322 */    Color temp = new Color(this.r * scale, this.g * scale, this.b * scale, this.a);
/* 323:    */    
/* 324:324 */    return temp;
/* 325:    */  }
/* 326:    */  
/* 332:    */  public Color multiply(Color c)
/* 333:    */  {
/* 334:334 */    return new Color(this.r * c.r, this.g * c.g, this.b * c.b, this.a * c.a);
/* 335:    */  }
/* 336:    */  
/* 341:    */  public void add(Color c)
/* 342:    */  {
/* 343:343 */    this.r += c.r;
/* 344:344 */    this.g += c.g;
/* 345:345 */    this.b += c.b;
/* 346:346 */    this.a += c.a;
/* 347:    */  }
/* 348:    */  
/* 353:    */  public void scale(float value)
/* 354:    */  {
/* 355:355 */    this.r *= value;
/* 356:356 */    this.g *= value;
/* 357:357 */    this.b *= value;
/* 358:358 */    this.a *= value;
/* 359:    */  }
/* 360:    */  
/* 366:    */  public Color addToCopy(Color c)
/* 367:    */  {
/* 368:368 */    Color copy = new Color(this.r, this.g, this.b, this.a);
/* 369:369 */    copy.r += c.r;
/* 370:370 */    copy.g += c.g;
/* 371:371 */    copy.b += c.b;
/* 372:372 */    copy.a += c.a;
/* 373:    */    
/* 374:374 */    return copy;
/* 375:    */  }
/* 376:    */  
/* 382:    */  public Color scaleCopy(float value)
/* 383:    */  {
/* 384:384 */    Color copy = new Color(this.r, this.g, this.b, this.a);
/* 385:385 */    copy.r *= value;
/* 386:386 */    copy.g *= value;
/* 387:387 */    copy.b *= value;
/* 388:388 */    copy.a *= value;
/* 389:    */    
/* 390:390 */    return copy;
/* 391:    */  }
/* 392:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.Color
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
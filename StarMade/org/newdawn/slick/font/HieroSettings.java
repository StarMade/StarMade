/*   1:    */package org.newdawn.slick.font;
/*   2:    */
/*   3:    */import java.io.BufferedReader;
/*   4:    */import java.io.File;
/*   5:    */import java.io.FileOutputStream;
/*   6:    */import java.io.IOException;
/*   7:    */import java.io.InputStream;
/*   8:    */import java.io.InputStreamReader;
/*   9:    */import java.io.PrintStream;
/*  10:    */import java.util.ArrayList;
/*  11:    */import java.util.Iterator;
/*  12:    */import java.util.List;
/*  13:    */import org.newdawn.slick.SlickException;
/*  14:    */import org.newdawn.slick.font.effects.ConfigurableEffect;
/*  15:    */import org.newdawn.slick.font.effects.ConfigurableEffect.Value;
/*  16:    */import org.newdawn.slick.util.ResourceLoader;
/*  17:    */
/*  26:    */public class HieroSettings
/*  27:    */{
/*  28: 28 */  private int fontSize = 12;
/*  29:    */  
/*  30: 30 */  private boolean bold = false;
/*  31:    */  
/*  32: 32 */  private boolean italic = false;
/*  33:    */  
/*  34:    */  private int paddingTop;
/*  35:    */  
/*  36:    */  private int paddingLeft;
/*  37:    */  
/*  38:    */  private int paddingBottom;
/*  39:    */  
/*  40:    */  private int paddingRight;
/*  41:    */  
/*  42:    */  private int paddingAdvanceX;
/*  43:    */  
/*  44:    */  private int paddingAdvanceY;
/*  45:    */  
/*  46: 46 */  private int glyphPageWidth = 512;
/*  47:    */  
/*  48: 48 */  private int glyphPageHeight = 512;
/*  49:    */  
/*  50: 50 */  private final List effects = new ArrayList();
/*  51:    */  
/*  56:    */  public HieroSettings() {}
/*  57:    */  
/*  62:    */  public HieroSettings(String hieroFileRef)
/*  63:    */    throws SlickException
/*  64:    */  {
/*  65: 65 */    this(ResourceLoader.getResourceAsStream(hieroFileRef));
/*  66:    */  }
/*  67:    */  
/*  71:    */  public HieroSettings(InputStream in)
/*  72:    */    throws SlickException
/*  73:    */  {
/*  74:    */    try
/*  75:    */    {
/*  76: 76 */      BufferedReader reader = new BufferedReader(new InputStreamReader(in));
/*  77:    */      for (;;) {
/*  78: 78 */        String line = reader.readLine();
/*  79: 79 */        if (line == null) break;
/*  80: 80 */        line = line.trim();
/*  81: 81 */        if (line.length() != 0) {
/*  82: 82 */          String[] pieces = line.split("=", 2);
/*  83: 83 */          String name = pieces[0].trim();
/*  84: 84 */          String value = pieces[1];
/*  85: 85 */          if (name.equals("font.size")) {
/*  86: 86 */            this.fontSize = Integer.parseInt(value);
/*  87: 87 */          } else if (name.equals("font.bold")) {
/*  88: 88 */            this.bold = Boolean.valueOf(value).booleanValue();
/*  89: 89 */          } else if (name.equals("font.italic")) {
/*  90: 90 */            this.italic = Boolean.valueOf(value).booleanValue();
/*  91: 91 */          } else if (name.equals("pad.top")) {
/*  92: 92 */            this.paddingTop = Integer.parseInt(value);
/*  93: 93 */          } else if (name.equals("pad.right")) {
/*  94: 94 */            this.paddingRight = Integer.parseInt(value);
/*  95: 95 */          } else if (name.equals("pad.bottom")) {
/*  96: 96 */            this.paddingBottom = Integer.parseInt(value);
/*  97: 97 */          } else if (name.equals("pad.left")) {
/*  98: 98 */            this.paddingLeft = Integer.parseInt(value);
/*  99: 99 */          } else if (name.equals("pad.advance.x")) {
/* 100:100 */            this.paddingAdvanceX = Integer.parseInt(value);
/* 101:101 */          } else if (name.equals("pad.advance.y")) {
/* 102:102 */            this.paddingAdvanceY = Integer.parseInt(value);
/* 103:103 */          } else if (name.equals("glyph.page.width")) {
/* 104:104 */            this.glyphPageWidth = Integer.parseInt(value);
/* 105:105 */          } else if (name.equals("glyph.page.height")) {
/* 106:106 */            this.glyphPageHeight = Integer.parseInt(value);
/* 107:107 */          } else if (name.equals("effect.class")) {
/* 108:    */            try {
/* 109:109 */              this.effects.add(Class.forName(value).newInstance());
/* 110:    */            } catch (Exception ex) {
/* 111:111 */              throw new SlickException("Unable to create effect instance: " + value, ex);
/* 112:    */            }
/* 113:113 */          } else if (name.startsWith("effect."))
/* 114:    */          {
/* 115:115 */            name = name.substring(7);
/* 116:116 */            ConfigurableEffect effect = (ConfigurableEffect)this.effects.get(this.effects.size() - 1);
/* 117:117 */            List values = effect.getValues();
/* 118:118 */            for (Iterator iter = values.iterator(); iter.hasNext();) {
/* 119:119 */              ConfigurableEffect.Value effectValue = (ConfigurableEffect.Value)iter.next();
/* 120:120 */              if (effectValue.getName().equals(name)) {
/* 121:121 */                effectValue.setString(value);
/* 122:122 */                break;
/* 123:    */              }
/* 124:    */            }
/* 125:125 */            effect.setValues(values);
/* 126:    */          }
/* 127:    */        } }
/* 128:128 */      reader.close();
/* 129:    */    } catch (Exception ex) {
/* 130:130 */      throw new SlickException("Unable to load Hiero font file", ex);
/* 131:    */    }
/* 132:    */  }
/* 133:    */  
/* 138:    */  public int getPaddingTop()
/* 139:    */  {
/* 140:140 */    return this.paddingTop;
/* 141:    */  }
/* 142:    */  
/* 147:    */  public void setPaddingTop(int paddingTop)
/* 148:    */  {
/* 149:149 */    this.paddingTop = paddingTop;
/* 150:    */  }
/* 151:    */  
/* 156:    */  public int getPaddingLeft()
/* 157:    */  {
/* 158:158 */    return this.paddingLeft;
/* 159:    */  }
/* 160:    */  
/* 165:    */  public void setPaddingLeft(int paddingLeft)
/* 166:    */  {
/* 167:167 */    this.paddingLeft = paddingLeft;
/* 168:    */  }
/* 169:    */  
/* 174:    */  public int getPaddingBottom()
/* 175:    */  {
/* 176:176 */    return this.paddingBottom;
/* 177:    */  }
/* 178:    */  
/* 183:    */  public void setPaddingBottom(int paddingBottom)
/* 184:    */  {
/* 185:185 */    this.paddingBottom = paddingBottom;
/* 186:    */  }
/* 187:    */  
/* 192:    */  public int getPaddingRight()
/* 193:    */  {
/* 194:194 */    return this.paddingRight;
/* 195:    */  }
/* 196:    */  
/* 201:    */  public void setPaddingRight(int paddingRight)
/* 202:    */  {
/* 203:203 */    this.paddingRight = paddingRight;
/* 204:    */  }
/* 205:    */  
/* 210:    */  public int getPaddingAdvanceX()
/* 211:    */  {
/* 212:212 */    return this.paddingAdvanceX;
/* 213:    */  }
/* 214:    */  
/* 219:    */  public void setPaddingAdvanceX(int paddingAdvanceX)
/* 220:    */  {
/* 221:221 */    this.paddingAdvanceX = paddingAdvanceX;
/* 222:    */  }
/* 223:    */  
/* 228:    */  public int getPaddingAdvanceY()
/* 229:    */  {
/* 230:230 */    return this.paddingAdvanceY;
/* 231:    */  }
/* 232:    */  
/* 237:    */  public void setPaddingAdvanceY(int paddingAdvanceY)
/* 238:    */  {
/* 239:239 */    this.paddingAdvanceY = paddingAdvanceY;
/* 240:    */  }
/* 241:    */  
/* 246:    */  public int getGlyphPageWidth()
/* 247:    */  {
/* 248:248 */    return this.glyphPageWidth;
/* 249:    */  }
/* 250:    */  
/* 255:    */  public void setGlyphPageWidth(int glyphPageWidth)
/* 256:    */  {
/* 257:257 */    this.glyphPageWidth = glyphPageWidth;
/* 258:    */  }
/* 259:    */  
/* 264:    */  public int getGlyphPageHeight()
/* 265:    */  {
/* 266:266 */    return this.glyphPageHeight;
/* 267:    */  }
/* 268:    */  
/* 273:    */  public void setGlyphPageHeight(int glyphPageHeight)
/* 274:    */  {
/* 275:275 */    this.glyphPageHeight = glyphPageHeight;
/* 276:    */  }
/* 277:    */  
/* 283:    */  public int getFontSize()
/* 284:    */  {
/* 285:285 */    return this.fontSize;
/* 286:    */  }
/* 287:    */  
/* 293:    */  public void setFontSize(int fontSize)
/* 294:    */  {
/* 295:295 */    this.fontSize = fontSize;
/* 296:    */  }
/* 297:    */  
/* 303:    */  public boolean isBold()
/* 304:    */  {
/* 305:305 */    return this.bold;
/* 306:    */  }
/* 307:    */  
/* 313:    */  public void setBold(boolean bold)
/* 314:    */  {
/* 315:315 */    this.bold = bold;
/* 316:    */  }
/* 317:    */  
/* 323:    */  public boolean isItalic()
/* 324:    */  {
/* 325:325 */    return this.italic;
/* 326:    */  }
/* 327:    */  
/* 333:    */  public void setItalic(boolean italic)
/* 334:    */  {
/* 335:335 */    this.italic = italic;
/* 336:    */  }
/* 337:    */  
/* 342:    */  public List getEffects()
/* 343:    */  {
/* 344:344 */    return this.effects;
/* 345:    */  }
/* 346:    */  
/* 351:    */  public void save(File file)
/* 352:    */    throws IOException
/* 353:    */  {
/* 354:354 */    PrintStream out = new PrintStream(new FileOutputStream(file));
/* 355:355 */    out.println("font.size=" + this.fontSize);
/* 356:356 */    out.println("font.bold=" + this.bold);
/* 357:357 */    out.println("font.italic=" + this.italic);
/* 358:358 */    out.println();
/* 359:359 */    out.println("pad.top=" + this.paddingTop);
/* 360:360 */    out.println("pad.right=" + this.paddingRight);
/* 361:361 */    out.println("pad.bottom=" + this.paddingBottom);
/* 362:362 */    out.println("pad.left=" + this.paddingLeft);
/* 363:363 */    out.println("pad.advance.x=" + this.paddingAdvanceX);
/* 364:364 */    out.println("pad.advance.y=" + this.paddingAdvanceY);
/* 365:365 */    out.println();
/* 366:366 */    out.println("glyph.page.width=" + this.glyphPageWidth);
/* 367:367 */    out.println("glyph.page.height=" + this.glyphPageHeight);
/* 368:368 */    out.println();
/* 369:369 */    for (Iterator iter = this.effects.iterator(); iter.hasNext();) {
/* 370:370 */      ConfigurableEffect effect = (ConfigurableEffect)iter.next();
/* 371:371 */      out.println("effect.class=" + effect.getClass().getName());
/* 372:372 */      for (Iterator iter2 = effect.getValues().iterator(); iter2.hasNext();) {
/* 373:373 */        ConfigurableEffect.Value value = (ConfigurableEffect.Value)iter2.next();
/* 374:374 */        out.println("effect." + value.getName() + "=" + value.getString());
/* 375:    */      }
/* 376:376 */      out.println();
/* 377:    */    }
/* 378:378 */    out.close();
/* 379:    */  }
/* 380:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.font.HieroSettings
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
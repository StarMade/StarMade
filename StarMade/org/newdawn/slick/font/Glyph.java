/*   1:    */package org.newdawn.slick.font;
/*   2:    */
/*   3:    */import java.awt.Font;
/*   4:    */import java.awt.Rectangle;
/*   5:    */import java.awt.Shape;
/*   6:    */import java.awt.font.GlyphMetrics;
/*   7:    */import java.awt.font.GlyphVector;
/*   8:    */import org.newdawn.slick.Image;
/*   9:    */import org.newdawn.slick.UnicodeFont;
/*  10:    */
/*  31:    */public class Glyph
/*  32:    */{
/*  33:    */  private int codePoint;
/*  34:    */  private short width;
/*  35:    */  private short height;
/*  36:    */  private short yOffset;
/*  37:    */  private boolean isMissing;
/*  38:    */  private Shape shape;
/*  39:    */  private Image image;
/*  40:    */  
/*  41:    */  public Glyph(int codePoint, Rectangle bounds, GlyphVector vector, int index, UnicodeFont unicodeFont)
/*  42:    */  {
/*  43: 43 */    this.codePoint = codePoint;
/*  44:    */    
/*  45: 45 */    GlyphMetrics metrics = vector.getGlyphMetrics(index);
/*  46: 46 */    int lsb = (int)metrics.getLSB();
/*  47: 47 */    if (lsb > 0) lsb = 0;
/*  48: 48 */    int rsb = (int)metrics.getRSB();
/*  49: 49 */    if (rsb > 0) { rsb = 0;
/*  50:    */    }
/*  51: 51 */    int glyphWidth = bounds.width - lsb - rsb;
/*  52: 52 */    int glyphHeight = bounds.height;
/*  53: 53 */    if ((glyphWidth > 0) && (glyphHeight > 0)) {
/*  54: 54 */      int padTop = unicodeFont.getPaddingTop();
/*  55: 55 */      int padRight = unicodeFont.getPaddingRight();
/*  56: 56 */      int padBottom = unicodeFont.getPaddingBottom();
/*  57: 57 */      int padLeft = unicodeFont.getPaddingLeft();
/*  58: 58 */      int glyphSpacing = 1;
/*  59: 59 */      this.width = ((short)(glyphWidth + padLeft + padRight + glyphSpacing));
/*  60: 60 */      this.height = ((short)(glyphHeight + padTop + padBottom + glyphSpacing));
/*  61: 61 */      this.yOffset = ((short)(unicodeFont.getAscent() + bounds.y - padTop));
/*  62:    */    }
/*  63:    */    
/*  64: 64 */    this.shape = vector.getGlyphOutline(index, -bounds.x + unicodeFont.getPaddingLeft(), -bounds.y + unicodeFont.getPaddingTop());
/*  65:    */    
/*  66: 66 */    this.isMissing = (!unicodeFont.getFont().canDisplay((char)codePoint));
/*  67:    */  }
/*  68:    */  
/*  73:    */  public int getCodePoint()
/*  74:    */  {
/*  75: 75 */    return this.codePoint;
/*  76:    */  }
/*  77:    */  
/*  82:    */  public boolean isMissing()
/*  83:    */  {
/*  84: 84 */    return this.isMissing;
/*  85:    */  }
/*  86:    */  
/*  91:    */  public int getWidth()
/*  92:    */  {
/*  93: 93 */    return this.width;
/*  94:    */  }
/*  95:    */  
/* 100:    */  public int getHeight()
/* 101:    */  {
/* 102:102 */    return this.height;
/* 103:    */  }
/* 104:    */  
/* 110:    */  public Shape getShape()
/* 111:    */  {
/* 112:112 */    return this.shape;
/* 113:    */  }
/* 114:    */  
/* 119:    */  public void setShape(Shape shape)
/* 120:    */  {
/* 121:121 */    this.shape = shape;
/* 122:    */  }
/* 123:    */  
/* 129:    */  public Image getImage()
/* 130:    */  {
/* 131:131 */    return this.image;
/* 132:    */  }
/* 133:    */  
/* 138:    */  public void setImage(Image image)
/* 139:    */  {
/* 140:140 */    this.image = image;
/* 141:    */  }
/* 142:    */  
/* 148:    */  public int getYOffset()
/* 149:    */  {
/* 150:150 */    return this.yOffset;
/* 151:    */  }
/* 152:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.font.Glyph
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
/*  1:   */package org.newdawn.slick.font.effects;
/*  2:   */
/*  3:   */import java.awt.Graphics;
/*  4:   */import java.awt.Graphics2D;
/*  5:   */import java.awt.image.BufferedImage;
/*  6:   */import java.awt.image.BufferedImageOp;
/*  7:   */import org.newdawn.slick.UnicodeFont;
/*  8:   */import org.newdawn.slick.font.Glyph;
/*  9:   */
/* 24:   */public class FilterEffect
/* 25:   */  implements Effect
/* 26:   */{
/* 27:   */  private BufferedImageOp filter;
/* 28:   */  
/* 29:   */  public FilterEffect() {}
/* 30:   */  
/* 31:   */  public FilterEffect(BufferedImageOp filter)
/* 32:   */  {
/* 33:33 */    this.filter = filter;
/* 34:   */  }
/* 35:   */  
/* 38:   */  public void draw(BufferedImage image, Graphics2D g, UnicodeFont unicodeFont, Glyph glyph)
/* 39:   */  {
/* 40:40 */    BufferedImage scratchImage = EffectUtil.getScratchImage();
/* 41:41 */    this.filter.filter(image, scratchImage);
/* 42:42 */    image.getGraphics().drawImage(scratchImage, 0, 0, null);
/* 43:   */  }
/* 44:   */  
/* 49:   */  public BufferedImageOp getFilter()
/* 50:   */  {
/* 51:51 */    return this.filter;
/* 52:   */  }
/* 53:   */  
/* 58:   */  public void setFilter(BufferedImageOp filter)
/* 59:   */  {
/* 60:60 */    this.filter = filter;
/* 61:   */  }
/* 62:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.font.effects.FilterEffect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
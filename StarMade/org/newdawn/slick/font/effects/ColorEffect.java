/*  1:   */package org.newdawn.slick.font.effects;
/*  2:   */
/*  3:   */import java.awt.Color;
/*  4:   */import java.awt.Graphics2D;
/*  5:   */import java.awt.image.BufferedImage;
/*  6:   */import java.util.ArrayList;
/*  7:   */import java.util.Iterator;
/*  8:   */import java.util.List;
/*  9:   */import org.newdawn.slick.UnicodeFont;
/* 10:   */import org.newdawn.slick.font.Glyph;
/* 11:   */
/* 18:   */public class ColorEffect
/* 19:   */  implements ConfigurableEffect
/* 20:   */{
/* 21:21 */  private Color color = Color.white;
/* 22:   */  
/* 27:   */  public ColorEffect() {}
/* 28:   */  
/* 33:   */  public ColorEffect(Color color)
/* 34:   */  {
/* 35:35 */    this.color = color;
/* 36:   */  }
/* 37:   */  
/* 40:   */  public void draw(BufferedImage image, Graphics2D g, UnicodeFont unicodeFont, Glyph glyph)
/* 41:   */  {
/* 42:42 */    g.setColor(this.color);
/* 43:43 */    g.fill(glyph.getShape());
/* 44:   */  }
/* 45:   */  
/* 50:   */  public Color getColor()
/* 51:   */  {
/* 52:52 */    return this.color;
/* 53:   */  }
/* 54:   */  
/* 59:   */  public void setColor(Color color)
/* 60:   */  {
/* 61:61 */    if (color == null) throw new IllegalArgumentException("color cannot be null.");
/* 62:62 */    this.color = color;
/* 63:   */  }
/* 64:   */  
/* 67:   */  public String toString()
/* 68:   */  {
/* 69:69 */    return "Color";
/* 70:   */  }
/* 71:   */  
/* 74:   */  public List getValues()
/* 75:   */  {
/* 76:76 */    List values = new ArrayList();
/* 77:77 */    values.add(EffectUtil.colorValue("Color", this.color));
/* 78:78 */    return values;
/* 79:   */  }
/* 80:   */  
/* 83:   */  public void setValues(List values)
/* 84:   */  {
/* 85:85 */    for (Iterator iter = values.iterator(); iter.hasNext();) {
/* 86:86 */      ConfigurableEffect.Value value = (ConfigurableEffect.Value)iter.next();
/* 87:87 */      if (value.getName().equals("Color")) {
/* 88:88 */        setColor((Color)value.getObject());
/* 89:   */      }
/* 90:   */    }
/* 91:   */  }
/* 92:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.font.effects.ColorEffect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
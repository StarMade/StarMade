package org.newdawn.slick.font.effects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.Glyph;

public class ColorEffect
  implements ConfigurableEffect
{
  private Color color = Color.white;
  
  public ColorEffect() {}
  
  public ColorEffect(Color color)
  {
    this.color = color;
  }
  
  public void draw(BufferedImage image, Graphics2D local_g, UnicodeFont unicodeFont, Glyph glyph)
  {
    local_g.setColor(this.color);
    local_g.fill(glyph.getShape());
  }
  
  public Color getColor()
  {
    return this.color;
  }
  
  public void setColor(Color color)
  {
    if (color == null) {
      throw new IllegalArgumentException("color cannot be null.");
    }
    this.color = color;
  }
  
  public String toString()
  {
    return "Color";
  }
  
  public List getValues()
  {
    List values = new ArrayList();
    values.add(EffectUtil.colorValue("Color", this.color));
    return values;
  }
  
  public void setValues(List values)
  {
    Iterator iter = values.iterator();
    while (iter.hasNext())
    {
      ConfigurableEffect.Value value = (ConfigurableEffect.Value)iter.next();
      if (value.getName().equals("Color")) {
        setColor((Color)value.getObject());
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.font.effects.ColorEffect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
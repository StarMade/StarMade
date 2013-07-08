/*   1:    */package org.newdawn.slick.font.effects;
/*   2:    */
/*   3:    */import java.awt.BasicStroke;
/*   4:    */import java.awt.Color;
/*   5:    */import java.awt.Graphics2D;
/*   6:    */import java.awt.Stroke;
/*   7:    */import java.awt.image.BufferedImage;
/*   8:    */import java.util.ArrayList;
/*   9:    */import java.util.Iterator;
/*  10:    */import java.util.List;
/*  11:    */import org.newdawn.slick.UnicodeFont;
/*  12:    */import org.newdawn.slick.font.Glyph;
/*  13:    */
/*  20:    */public class OutlineEffect
/*  21:    */  implements ConfigurableEffect
/*  22:    */{
/*  23: 23 */  private float width = 2.0F;
/*  24:    */  
/*  25: 25 */  private Color color = Color.black;
/*  26:    */  
/*  27: 27 */  private int join = 2;
/*  28:    */  
/*  32:    */  private Stroke stroke;
/*  33:    */  
/*  37:    */  public OutlineEffect() {}
/*  38:    */  
/*  42:    */  public OutlineEffect(int width, Color color)
/*  43:    */  {
/*  44: 44 */    this.width = width;
/*  45: 45 */    this.color = color;
/*  46:    */  }
/*  47:    */  
/*  50:    */  public void draw(BufferedImage image, Graphics2D g, UnicodeFont unicodeFont, Glyph glyph)
/*  51:    */  {
/*  52: 52 */    g = (Graphics2D)g.create();
/*  53: 53 */    if (this.stroke != null) {
/*  54: 54 */      g.setStroke(this.stroke);
/*  55:    */    } else
/*  56: 56 */      g.setStroke(getStroke());
/*  57: 57 */    g.setColor(this.color);
/*  58: 58 */    g.draw(glyph.getShape());
/*  59: 59 */    g.dispose();
/*  60:    */  }
/*  61:    */  
/*  66:    */  public float getWidth()
/*  67:    */  {
/*  68: 68 */    return this.width;
/*  69:    */  }
/*  70:    */  
/*  76:    */  public void setWidth(int width)
/*  77:    */  {
/*  78: 78 */    this.width = width;
/*  79:    */  }
/*  80:    */  
/*  85:    */  public Color getColor()
/*  86:    */  {
/*  87: 87 */    return this.color;
/*  88:    */  }
/*  89:    */  
/*  94:    */  public void setColor(Color color)
/*  95:    */  {
/*  96: 96 */    this.color = color;
/*  97:    */  }
/*  98:    */  
/* 103:    */  public int getJoin()
/* 104:    */  {
/* 105:105 */    return this.join;
/* 106:    */  }
/* 107:    */  
/* 112:    */  public Stroke getStroke()
/* 113:    */  {
/* 114:114 */    if (this.stroke == null) {
/* 115:115 */      return new BasicStroke(this.width, 2, this.join);
/* 116:    */    }
/* 117:    */    
/* 118:118 */    return this.stroke;
/* 119:    */  }
/* 120:    */  
/* 126:    */  public void setStroke(Stroke stroke)
/* 127:    */  {
/* 128:128 */    this.stroke = stroke;
/* 129:    */  }
/* 130:    */  
/* 136:    */  public void setJoin(int join)
/* 137:    */  {
/* 138:138 */    this.join = join;
/* 139:    */  }
/* 140:    */  
/* 143:    */  public String toString()
/* 144:    */  {
/* 145:145 */    return "Outline";
/* 146:    */  }
/* 147:    */  
/* 150:    */  public List getValues()
/* 151:    */  {
/* 152:152 */    List values = new ArrayList();
/* 153:153 */    values.add(EffectUtil.colorValue("Color", this.color));
/* 154:154 */    values.add(EffectUtil.floatValue("Width", this.width, 0.1F, 999.0F, "This setting controls the width of the outline. The glyphs will need padding so the outline doesn't get clipped."));
/* 155:    */    
/* 156:156 */    values.add(EffectUtil.optionValue("Join", String.valueOf(this.join), new String[][] { { "Bevel", "2" }, { "Miter", "0" }, { "Round", "1" } }, "This setting defines how the corners of the outline are drawn. This is usually only noticeable at large outline widths."));
/* 157:    */    
/* 160:160 */    return values;
/* 161:    */  }
/* 162:    */  
/* 165:    */  public void setValues(List values)
/* 166:    */  {
/* 167:167 */    for (Iterator iter = values.iterator(); iter.hasNext();) {
/* 168:168 */      ConfigurableEffect.Value value = (ConfigurableEffect.Value)iter.next();
/* 169:169 */      if (value.getName().equals("Color")) {
/* 170:170 */        this.color = ((Color)value.getObject());
/* 171:171 */      } else if (value.getName().equals("Width")) {
/* 172:172 */        this.width = ((Float)value.getObject()).floatValue();
/* 173:173 */      } else if (value.getName().equals("Join")) {
/* 174:174 */        this.join = Integer.parseInt((String)value.getObject());
/* 175:    */      }
/* 176:    */    }
/* 177:    */  }
/* 178:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.font.effects.OutlineEffect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
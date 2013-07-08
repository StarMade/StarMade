/*   1:    */package org.newdawn.slick.font.effects;
/*   2:    */
/*   3:    */import java.awt.Color;
/*   4:    */import java.awt.GradientPaint;
/*   5:    */import java.awt.Graphics2D;
/*   6:    */import java.awt.image.BufferedImage;
/*   7:    */import java.util.ArrayList;
/*   8:    */import java.util.Iterator;
/*   9:    */import java.util.List;
/*  10:    */import org.newdawn.slick.UnicodeFont;
/*  11:    */import org.newdawn.slick.font.Glyph;
/*  12:    */
/*  19:    */public class GradientEffect
/*  20:    */  implements ConfigurableEffect
/*  21:    */{
/*  22: 22 */  private Color topColor = Color.cyan;
/*  23:    */  
/*  24: 24 */  private Color bottomColor = Color.blue;
/*  25:    */  
/*  26: 26 */  private int offset = 0;
/*  27:    */  
/*  28: 28 */  private float scale = 1.0F;
/*  29:    */  
/*  33:    */  private boolean cyclic;
/*  34:    */  
/*  39:    */  public GradientEffect() {}
/*  40:    */  
/*  44:    */  public GradientEffect(Color topColor, Color bottomColor, float scale)
/*  45:    */  {
/*  46: 46 */    this.topColor = topColor;
/*  47: 47 */    this.bottomColor = bottomColor;
/*  48: 48 */    this.scale = scale;
/*  49:    */  }
/*  50:    */  
/*  53:    */  public void draw(BufferedImage image, Graphics2D g, UnicodeFont unicodeFont, Glyph glyph)
/*  54:    */  {
/*  55: 55 */    int ascent = unicodeFont.getAscent();
/*  56: 56 */    float height = ascent * this.scale;
/*  57: 57 */    float top = -glyph.getYOffset() + unicodeFont.getDescent() + this.offset + ascent / 2 - height / 2.0F;
/*  58: 58 */    g.setPaint(new GradientPaint(0.0F, top, this.topColor, 0.0F, top + height, this.bottomColor, this.cyclic));
/*  59: 59 */    g.fill(glyph.getShape());
/*  60:    */  }
/*  61:    */  
/*  66:    */  public Color getTopColor()
/*  67:    */  {
/*  68: 68 */    return this.topColor;
/*  69:    */  }
/*  70:    */  
/*  75:    */  public void setTopColor(Color topColor)
/*  76:    */  {
/*  77: 77 */    this.topColor = topColor;
/*  78:    */  }
/*  79:    */  
/*  84:    */  public Color getBottomColor()
/*  85:    */  {
/*  86: 86 */    return this.bottomColor;
/*  87:    */  }
/*  88:    */  
/*  93:    */  public void setBottomColor(Color bottomColor)
/*  94:    */  {
/*  95: 95 */    this.bottomColor = bottomColor;
/*  96:    */  }
/*  97:    */  
/* 102:    */  public int getOffset()
/* 103:    */  {
/* 104:104 */    return this.offset;
/* 105:    */  }
/* 106:    */  
/* 112:    */  public void setOffset(int offset)
/* 113:    */  {
/* 114:114 */    this.offset = offset;
/* 115:    */  }
/* 116:    */  
/* 121:    */  public float getScale()
/* 122:    */  {
/* 123:123 */    return this.scale;
/* 124:    */  }
/* 125:    */  
/* 131:    */  public void setScale(float scale)
/* 132:    */  {
/* 133:133 */    this.scale = scale;
/* 134:    */  }
/* 135:    */  
/* 140:    */  public boolean isCyclic()
/* 141:    */  {
/* 142:142 */    return this.cyclic;
/* 143:    */  }
/* 144:    */  
/* 149:    */  public void setCyclic(boolean cyclic)
/* 150:    */  {
/* 151:151 */    this.cyclic = cyclic;
/* 152:    */  }
/* 153:    */  
/* 156:    */  public String toString()
/* 157:    */  {
/* 158:158 */    return "Gradient";
/* 159:    */  }
/* 160:    */  
/* 163:    */  public List getValues()
/* 164:    */  {
/* 165:165 */    List values = new ArrayList();
/* 166:166 */    values.add(EffectUtil.colorValue("Top color", this.topColor));
/* 167:167 */    values.add(EffectUtil.colorValue("Bottom color", this.bottomColor));
/* 168:168 */    values.add(EffectUtil.intValue("Offset", this.offset, "This setting allows you to move the gradient up or down. The gradient is normally centered on the glyph."));
/* 169:    */    
/* 170:170 */    values.add(EffectUtil.floatValue("Scale", this.scale, 0.0F, 1.0F, "This setting allows you to change the height of the gradient by apercentage. The gradient is normally the height of most glyphs in the font."));
/* 171:    */    
/* 172:172 */    values.add(EffectUtil.booleanValue("Cyclic", this.cyclic, "If this setting is checked, the gradient will repeat."));
/* 173:173 */    return values;
/* 174:    */  }
/* 175:    */  
/* 178:    */  public void setValues(List values)
/* 179:    */  {
/* 180:180 */    for (Iterator iter = values.iterator(); iter.hasNext();) {
/* 181:181 */      ConfigurableEffect.Value value = (ConfigurableEffect.Value)iter.next();
/* 182:182 */      if (value.getName().equals("Top color")) {
/* 183:183 */        this.topColor = ((Color)value.getObject());
/* 184:184 */      } else if (value.getName().equals("Bottom color")) {
/* 185:185 */        this.bottomColor = ((Color)value.getObject());
/* 186:186 */      } else if (value.getName().equals("Offset")) {
/* 187:187 */        this.offset = ((Integer)value.getObject()).intValue();
/* 188:188 */      } else if (value.getName().equals("Scale")) {
/* 189:189 */        this.scale = ((Float)value.getObject()).floatValue();
/* 190:190 */      } else if (value.getName().equals("Cyclic")) {
/* 191:191 */        this.cyclic = ((Boolean)value.getObject()).booleanValue();
/* 192:    */      }
/* 193:    */    }
/* 194:    */  }
/* 195:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.font.effects.GradientEffect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
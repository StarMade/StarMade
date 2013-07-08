package org.newdawn.slick;

import java.io.Serializable;
import java.nio.FloatBuffer;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;

public class Color
  implements Serializable
{
  private static final long serialVersionUID = 1393939L;
  protected transient SGL field_1775 = Renderer.get();
  public static final Color transparent = new Color(0.0F, 0.0F, 0.0F, 0.0F);
  public static final Color white = new Color(1.0F, 1.0F, 1.0F, 1.0F);
  public static final Color yellow = new Color(1.0F, 1.0F, 0.0F, 1.0F);
  public static final Color red = new Color(1.0F, 0.0F, 0.0F, 1.0F);
  public static final Color blue = new Color(0.0F, 0.0F, 1.0F, 1.0F);
  public static final Color green = new Color(0.0F, 1.0F, 0.0F, 1.0F);
  public static final Color black = new Color(0.0F, 0.0F, 0.0F, 1.0F);
  public static final Color gray = new Color(0.5F, 0.5F, 0.5F, 1.0F);
  public static final Color cyan = new Color(0.0F, 1.0F, 1.0F, 1.0F);
  public static final Color darkGray = new Color(0.3F, 0.3F, 0.3F, 1.0F);
  public static final Color lightGray = new Color(0.7F, 0.7F, 0.7F, 1.0F);
  public static final Color pink = new Color(255, 175, 175, 255);
  public static final Color orange = new Color(255, 200, 0, 255);
  public static final Color magenta = new Color(255, 0, 255, 255);
  public float field_1776;
  public float field_1777;
  public float field_1778;
  public float field_1779 = 1.0F;
  
  public Color(Color color)
  {
    this.field_1776 = color.field_1776;
    this.field_1777 = color.field_1777;
    this.field_1778 = color.field_1778;
    this.field_1779 = color.field_1779;
  }
  
  public Color(FloatBuffer buffer)
  {
    this.field_1776 = buffer.get();
    this.field_1777 = buffer.get();
    this.field_1778 = buffer.get();
    this.field_1779 = buffer.get();
  }
  
  public Color(float local_r, float local_g, float local_b)
  {
    this.field_1776 = local_r;
    this.field_1777 = local_g;
    this.field_1778 = local_b;
    this.field_1779 = 1.0F;
  }
  
  public Color(float local_r, float local_g, float local_b, float local_a)
  {
    this.field_1776 = Math.min(local_r, 1.0F);
    this.field_1777 = Math.min(local_g, 1.0F);
    this.field_1778 = Math.min(local_b, 1.0F);
    this.field_1779 = Math.min(local_a, 1.0F);
  }
  
  public Color(int local_r, int local_g, int local_b)
  {
    this.field_1776 = (local_r / 255.0F);
    this.field_1777 = (local_g / 255.0F);
    this.field_1778 = (local_b / 255.0F);
    this.field_1779 = 1.0F;
  }
  
  public Color(int local_r, int local_g, int local_b, int local_a)
  {
    this.field_1776 = (local_r / 255.0F);
    this.field_1777 = (local_g / 255.0F);
    this.field_1778 = (local_b / 255.0F);
    this.field_1779 = (local_a / 255.0F);
  }
  
  public Color(int value)
  {
    int local_r = (value & 0xFF0000) >> 16;
    int local_g = (value & 0xFF00) >> 8;
    int local_b = value & 0xFF;
    int local_a = (value & 0xFF000000) >> 24;
    if (local_a < 0) {
      local_a += 256;
    }
    if (local_a == 0) {
      local_a = 255;
    }
    this.field_1776 = (local_r / 255.0F);
    this.field_1777 = (local_g / 255.0F);
    this.field_1778 = (local_b / 255.0F);
    this.field_1779 = (local_a / 255.0F);
  }
  
  public static Color decode(String local_nm)
  {
    return new Color(Integer.decode(local_nm).intValue());
  }
  
  public void bind()
  {
    this.field_1775.glColor4f(this.field_1776, this.field_1777, this.field_1778, this.field_1779);
  }
  
  public int hashCode()
  {
    return (int)(this.field_1776 + this.field_1777 + this.field_1778 + this.field_1779) * 255;
  }
  
  public boolean equals(Object other)
  {
    if ((other instanceof Color))
    {
      Color local_o = (Color)other;
      return (local_o.field_1776 == this.field_1776) && (local_o.field_1777 == this.field_1777) && (local_o.field_1778 == this.field_1778) && (local_o.field_1779 == this.field_1779);
    }
    return false;
  }
  
  public String toString()
  {
    return "Color (" + this.field_1776 + "," + this.field_1777 + "," + this.field_1778 + "," + this.field_1779 + ")";
  }
  
  public Color darker()
  {
    return darker(0.5F);
  }
  
  public Color darker(float scale)
  {
    scale = 1.0F - scale;
    Color temp = new Color(this.field_1776 * scale, this.field_1777 * scale, this.field_1778 * scale, this.field_1779);
    return temp;
  }
  
  public Color brighter()
  {
    return brighter(0.2F);
  }
  
  public int getRed()
  {
    return (int)(this.field_1776 * 255.0F);
  }
  
  public int getGreen()
  {
    return (int)(this.field_1777 * 255.0F);
  }
  
  public int getBlue()
  {
    return (int)(this.field_1778 * 255.0F);
  }
  
  public int getAlpha()
  {
    return (int)(this.field_1779 * 255.0F);
  }
  
  public int getRedByte()
  {
    return (int)(this.field_1776 * 255.0F);
  }
  
  public int getGreenByte()
  {
    return (int)(this.field_1777 * 255.0F);
  }
  
  public int getBlueByte()
  {
    return (int)(this.field_1778 * 255.0F);
  }
  
  public int getAlphaByte()
  {
    return (int)(this.field_1779 * 255.0F);
  }
  
  public Color brighter(float scale)
  {
    scale += 1.0F;
    Color temp = new Color(this.field_1776 * scale, this.field_1777 * scale, this.field_1778 * scale, this.field_1779);
    return temp;
  }
  
  public Color multiply(Color local_c)
  {
    return new Color(this.field_1776 * local_c.field_1776, this.field_1777 * local_c.field_1777, this.field_1778 * local_c.field_1778, this.field_1779 * local_c.field_1779);
  }
  
  public void add(Color local_c)
  {
    this.field_1776 += local_c.field_1776;
    this.field_1777 += local_c.field_1777;
    this.field_1778 += local_c.field_1778;
    this.field_1779 += local_c.field_1779;
  }
  
  public void scale(float value)
  {
    this.field_1776 *= value;
    this.field_1777 *= value;
    this.field_1778 *= value;
    this.field_1779 *= value;
  }
  
  public Color addToCopy(Color local_c)
  {
    Color copy = new Color(this.field_1776, this.field_1777, this.field_1778, this.field_1779);
    copy.field_1776 += local_c.field_1776;
    copy.field_1777 += local_c.field_1777;
    copy.field_1778 += local_c.field_1778;
    copy.field_1779 += local_c.field_1779;
    return copy;
  }
  
  public Color scaleCopy(float value)
  {
    Color copy = new Color(this.field_1776, this.field_1777, this.field_1778, this.field_1779);
    copy.field_1776 *= value;
    copy.field_1777 *= value;
    copy.field_1778 *= value;
    copy.field_1779 *= value;
    return copy;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.Color
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
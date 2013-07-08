package org.lwjgl.util;

import java.io.Serializable;
import java.nio.ByteBuffer;

public final class Color
  implements ReadableColor, Serializable, WritableColor
{
  static final long serialVersionUID = 1L;
  private byte red;
  private byte green;
  private byte blue;
  private byte alpha;
  
  public Color()
  {
    this(0, 0, 0, 255);
  }
  
  public Color(int local_r, int local_g, int local_b)
  {
    this(local_r, local_g, local_b, 255);
  }
  
  public Color(byte local_r, byte local_g, byte local_b)
  {
    this(local_r, local_g, local_b, (byte)-1);
  }
  
  public Color(int local_r, int local_g, int local_b, int local_a)
  {
    set(local_r, local_g, local_b, local_a);
  }
  
  public Color(byte local_r, byte local_g, byte local_b, byte local_a)
  {
    set(local_r, local_g, local_b, local_a);
  }
  
  public Color(ReadableColor local_c)
  {
    setColor(local_c);
  }
  
  public void set(int local_r, int local_g, int local_b, int local_a)
  {
    this.red = ((byte)local_r);
    this.green = ((byte)local_g);
    this.blue = ((byte)local_b);
    this.alpha = ((byte)local_a);
  }
  
  public void set(byte local_r, byte local_g, byte local_b, byte local_a)
  {
    this.red = local_r;
    this.green = local_g;
    this.blue = local_b;
    this.alpha = local_a;
  }
  
  public void set(int local_r, int local_g, int local_b)
  {
    set(local_r, local_g, local_b, 255);
  }
  
  public void set(byte local_r, byte local_g, byte local_b)
  {
    set(local_r, local_g, local_b, (byte)-1);
  }
  
  public int getRed()
  {
    return this.red & 0xFF;
  }
  
  public int getGreen()
  {
    return this.green & 0xFF;
  }
  
  public int getBlue()
  {
    return this.blue & 0xFF;
  }
  
  public int getAlpha()
  {
    return this.alpha & 0xFF;
  }
  
  public void setRed(int red)
  {
    this.red = ((byte)red);
  }
  
  public void setGreen(int green)
  {
    this.green = ((byte)green);
  }
  
  public void setBlue(int blue)
  {
    this.blue = ((byte)blue);
  }
  
  public void setAlpha(int alpha)
  {
    this.alpha = ((byte)alpha);
  }
  
  public void setRed(byte red)
  {
    this.red = red;
  }
  
  public void setGreen(byte green)
  {
    this.green = green;
  }
  
  public void setBlue(byte blue)
  {
    this.blue = blue;
  }
  
  public void setAlpha(byte alpha)
  {
    this.alpha = alpha;
  }
  
  public String toString()
  {
    return "Color [" + getRed() + ", " + getGreen() + ", " + getBlue() + ", " + getAlpha() + "]";
  }
  
  public boolean equals(Object local_o)
  {
    return (local_o != null) && ((local_o instanceof ReadableColor)) && (((ReadableColor)local_o).getRed() == getRed()) && (((ReadableColor)local_o).getGreen() == getGreen()) && (((ReadableColor)local_o).getBlue() == getBlue()) && (((ReadableColor)local_o).getAlpha() == getAlpha());
  }
  
  public int hashCode()
  {
    return this.red << 24 | this.green << 16 | this.blue << 8 | this.alpha;
  }
  
  public byte getAlphaByte()
  {
    return this.alpha;
  }
  
  public byte getBlueByte()
  {
    return this.blue;
  }
  
  public byte getGreenByte()
  {
    return this.green;
  }
  
  public byte getRedByte()
  {
    return this.red;
  }
  
  public void writeRGBA(ByteBuffer dest)
  {
    dest.put(this.red);
    dest.put(this.green);
    dest.put(this.blue);
    dest.put(this.alpha);
  }
  
  public void writeRGB(ByteBuffer dest)
  {
    dest.put(this.red);
    dest.put(this.green);
    dest.put(this.blue);
  }
  
  public void writeABGR(ByteBuffer dest)
  {
    dest.put(this.alpha);
    dest.put(this.blue);
    dest.put(this.green);
    dest.put(this.red);
  }
  
  public void writeARGB(ByteBuffer dest)
  {
    dest.put(this.alpha);
    dest.put(this.red);
    dest.put(this.green);
    dest.put(this.blue);
  }
  
  public void writeBGR(ByteBuffer dest)
  {
    dest.put(this.blue);
    dest.put(this.green);
    dest.put(this.red);
  }
  
  public void writeBGRA(ByteBuffer dest)
  {
    dest.put(this.blue);
    dest.put(this.green);
    dest.put(this.red);
    dest.put(this.alpha);
  }
  
  public void readRGBA(ByteBuffer src)
  {
    this.red = src.get();
    this.green = src.get();
    this.blue = src.get();
    this.alpha = src.get();
  }
  
  public void readRGB(ByteBuffer src)
  {
    this.red = src.get();
    this.green = src.get();
    this.blue = src.get();
  }
  
  public void readARGB(ByteBuffer src)
  {
    this.alpha = src.get();
    this.red = src.get();
    this.green = src.get();
    this.blue = src.get();
  }
  
  public void readBGRA(ByteBuffer src)
  {
    this.blue = src.get();
    this.green = src.get();
    this.red = src.get();
    this.alpha = src.get();
  }
  
  public void readBGR(ByteBuffer src)
  {
    this.blue = src.get();
    this.green = src.get();
    this.red = src.get();
  }
  
  public void readABGR(ByteBuffer src)
  {
    this.alpha = src.get();
    this.blue = src.get();
    this.green = src.get();
    this.red = src.get();
  }
  
  public void setColor(ReadableColor src)
  {
    this.red = src.getRedByte();
    this.green = src.getGreenByte();
    this.blue = src.getBlueByte();
    this.alpha = src.getAlphaByte();
  }
  
  public void fromHSB(float hue, float saturation, float brightness)
  {
    if (saturation == 0.0F)
    {
      this.red = (this.green = this.blue = (byte)(int)(brightness * 255.0F + 0.5F));
    }
    else
    {
      float local_f3 = (hue - (float)Math.floor(hue)) * 6.0F;
      float local_f4 = local_f3 - (float)Math.floor(local_f3);
      float local_f5 = brightness * (1.0F - saturation);
      float local_f6 = brightness * (1.0F - saturation * local_f4);
      float local_f7 = brightness * (1.0F - saturation * (1.0F - local_f4));
      switch ((int)local_f3)
      {
      case 0: 
        this.red = ((byte)(int)(brightness * 255.0F + 0.5F));
        this.green = ((byte)(int)(local_f7 * 255.0F + 0.5F));
        this.blue = ((byte)(int)(local_f5 * 255.0F + 0.5F));
        break;
      case 1: 
        this.red = ((byte)(int)(local_f6 * 255.0F + 0.5F));
        this.green = ((byte)(int)(brightness * 255.0F + 0.5F));
        this.blue = ((byte)(int)(local_f5 * 255.0F + 0.5F));
        break;
      case 2: 
        this.red = ((byte)(int)(local_f5 * 255.0F + 0.5F));
        this.green = ((byte)(int)(brightness * 255.0F + 0.5F));
        this.blue = ((byte)(int)(local_f7 * 255.0F + 0.5F));
        break;
      case 3: 
        this.red = ((byte)(int)(local_f5 * 255.0F + 0.5F));
        this.green = ((byte)(int)(local_f6 * 255.0F + 0.5F));
        this.blue = ((byte)(int)(brightness * 255.0F + 0.5F));
        break;
      case 4: 
        this.red = ((byte)(int)(local_f7 * 255.0F + 0.5F));
        this.green = ((byte)(int)(local_f5 * 255.0F + 0.5F));
        this.blue = ((byte)(int)(brightness * 255.0F + 0.5F));
        break;
      case 5: 
        this.red = ((byte)(int)(brightness * 255.0F + 0.5F));
        this.green = ((byte)(int)(local_f5 * 255.0F + 0.5F));
        this.blue = ((byte)(int)(local_f6 * 255.0F + 0.5F));
      }
    }
  }
  
  public float[] toHSB(float[] dest)
  {
    int local_r = getRed();
    int local_g = getGreen();
    int local_b = getBlue();
    if (dest == null) {
      dest = new float[3];
    }
    int local_l = local_r <= local_g ? local_g : local_r;
    if (local_b > local_l) {
      local_l = local_b;
    }
    int local_i1 = local_r >= local_g ? local_g : local_r;
    if (local_b < local_i1) {
      local_i1 = local_b;
    }
    float brightness = local_l / 255.0F;
    float saturation;
    float saturation;
    if (local_l != 0) {
      saturation = (local_l - local_i1) / local_l;
    } else {
      saturation = 0.0F;
    }
    float hue;
    float hue;
    if (saturation == 0.0F)
    {
      hue = 0.0F;
    }
    else
    {
      float local_f3 = (local_l - local_r) / (local_l - local_i1);
      float local_f4 = (local_l - local_g) / (local_l - local_i1);
      float local_f5 = (local_l - local_b) / (local_l - local_i1);
      float hue;
      if (local_r == local_l)
      {
        hue = local_f5 - local_f4;
      }
      else
      {
        float hue;
        if (local_g == local_l) {
          hue = 2.0F + local_f3 - local_f5;
        } else {
          hue = 4.0F + local_f4 - local_f3;
        }
      }
      hue /= 6.0F;
      if (hue < 0.0F) {
        hue += 1.0F;
      }
    }
    dest[0] = hue;
    dest[1] = saturation;
    dest[2] = brightness;
    return dest;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.Color
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
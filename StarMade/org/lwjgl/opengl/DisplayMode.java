package org.lwjgl.opengl;

public final class DisplayMode
{
  private final int width;
  private final int height;
  private final int bpp;
  private final int freq;
  private final boolean fullscreen;
  
  public DisplayMode(int width, int height)
  {
    this(width, height, 0, 0, false);
  }
  
  DisplayMode(int width, int height, int bpp, int freq)
  {
    this(width, height, bpp, freq, true);
  }
  
  private DisplayMode(int width, int height, int bpp, int freq, boolean fullscreen)
  {
    this.width = width;
    this.height = height;
    this.bpp = bpp;
    this.freq = freq;
    this.fullscreen = fullscreen;
  }
  
  public boolean isFullscreenCapable()
  {
    return this.fullscreen;
  }
  
  public int getWidth()
  {
    return this.width;
  }
  
  public int getHeight()
  {
    return this.height;
  }
  
  public int getBitsPerPixel()
  {
    return this.bpp;
  }
  
  public int getFrequency()
  {
    return this.freq;
  }
  
  public boolean equals(Object obj)
  {
    if ((obj == null) || (!(obj instanceof DisplayMode))) {
      return false;
    }
    DisplayMode local_dm = (DisplayMode)obj;
    return (local_dm.width == this.width) && (local_dm.height == this.height) && (local_dm.bpp == this.bpp) && (local_dm.freq == this.freq);
  }
  
  public int hashCode()
  {
    return this.width ^ this.height ^ this.freq ^ this.bpp;
  }
  
  public String toString()
  {
    StringBuilder local_sb = new StringBuilder(32);
    local_sb.append(this.width);
    local_sb.append(" x ");
    local_sb.append(this.height);
    local_sb.append(" x ");
    local_sb.append(this.bpp);
    local_sb.append(" @");
    local_sb.append(this.freq);
    local_sb.append("Hz");
    return local_sb.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.DisplayMode
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
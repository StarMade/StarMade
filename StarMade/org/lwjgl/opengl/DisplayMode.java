/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*  12:    */public final class DisplayMode
/*  13:    */{
/*  14:    */  private final int width;
/*  15:    */  
/*  24:    */  private final int height;
/*  25:    */  
/*  33:    */  private final int bpp;
/*  34:    */  
/*  42:    */  private final int freq;
/*  43:    */  
/*  51:    */  private final boolean fullscreen;
/*  52:    */  
/*  61:    */  public DisplayMode(int width, int height)
/*  62:    */  {
/*  63: 63 */    this(width, height, 0, 0, false);
/*  64:    */  }
/*  65:    */  
/*  66:    */  DisplayMode(int width, int height, int bpp, int freq) {
/*  67: 67 */    this(width, height, bpp, freq, true);
/*  68:    */  }
/*  69:    */  
/*  70:    */  private DisplayMode(int width, int height, int bpp, int freq, boolean fullscreen) {
/*  71: 71 */    this.width = width;
/*  72: 72 */    this.height = height;
/*  73: 73 */    this.bpp = bpp;
/*  74: 74 */    this.freq = freq;
/*  75: 75 */    this.fullscreen = fullscreen;
/*  76:    */  }
/*  77:    */  
/*  78:    */  public boolean isFullscreenCapable()
/*  79:    */  {
/*  80: 80 */    return this.fullscreen;
/*  81:    */  }
/*  82:    */  
/*  83:    */  public int getWidth() {
/*  84: 84 */    return this.width;
/*  85:    */  }
/*  86:    */  
/*  87:    */  public int getHeight() {
/*  88: 88 */    return this.height;
/*  89:    */  }
/*  90:    */  
/*  91:    */  public int getBitsPerPixel() {
/*  92: 92 */    return this.bpp;
/*  93:    */  }
/*  94:    */  
/*  95:    */  public int getFrequency() {
/*  96: 96 */    return this.freq;
/*  97:    */  }
/*  98:    */  
/* 103:    */  public boolean equals(Object obj)
/* 104:    */  {
/* 105:105 */    if ((obj == null) || (!(obj instanceof DisplayMode))) {
/* 106:106 */      return false;
/* 107:    */    }
/* 108:    */    
/* 109:109 */    DisplayMode dm = (DisplayMode)obj;
/* 110:110 */    return (dm.width == this.width) && (dm.height == this.height) && (dm.bpp == this.bpp) && (dm.freq == this.freq);
/* 111:    */  }
/* 112:    */  
/* 120:    */  public int hashCode()
/* 121:    */  {
/* 122:122 */    return this.width ^ this.height ^ this.freq ^ this.bpp;
/* 123:    */  }
/* 124:    */  
/* 129:    */  public String toString()
/* 130:    */  {
/* 131:131 */    StringBuilder sb = new StringBuilder(32);
/* 132:132 */    sb.append(this.width);
/* 133:133 */    sb.append(" x ");
/* 134:134 */    sb.append(this.height);
/* 135:135 */    sb.append(" x ");
/* 136:136 */    sb.append(this.bpp);
/* 137:137 */    sb.append(" @");
/* 138:138 */    sb.append(this.freq);
/* 139:139 */    sb.append("Hz");
/* 140:140 */    return sb.toString();
/* 141:    */  }
/* 142:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.DisplayMode
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
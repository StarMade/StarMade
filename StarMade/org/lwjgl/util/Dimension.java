/*   1:    */package org.lwjgl.util;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */
/*  49:    */public final class Dimension
/*  50:    */  implements Serializable, ReadableDimension, WritableDimension
/*  51:    */{
/*  52:    */  static final long serialVersionUID = 1L;
/*  53:    */  private int width;
/*  54:    */  private int height;
/*  55:    */  
/*  56:    */  public Dimension() {}
/*  57:    */  
/*  58:    */  public Dimension(int w, int h)
/*  59:    */  {
/*  60: 60 */    this.width = w;
/*  61: 61 */    this.height = h;
/*  62:    */  }
/*  63:    */  
/*  66:    */  public Dimension(ReadableDimension d)
/*  67:    */  {
/*  68: 68 */    setSize(d);
/*  69:    */  }
/*  70:    */  
/*  71:    */  public void setSize(int w, int h) {
/*  72: 72 */    this.width = w;
/*  73: 73 */    this.height = h;
/*  74:    */  }
/*  75:    */  
/*  76:    */  public void setSize(ReadableDimension d) {
/*  77: 77 */    this.width = d.getWidth();
/*  78: 78 */    this.height = d.getHeight();
/*  79:    */  }
/*  80:    */  
/*  83:    */  public void getSize(WritableDimension dest)
/*  84:    */  {
/*  85: 85 */    dest.setSize(this);
/*  86:    */  }
/*  87:    */  
/*  90:    */  public boolean equals(Object obj)
/*  91:    */  {
/*  92: 92 */    if ((obj instanceof ReadableDimension)) {
/*  93: 93 */      ReadableDimension d = (ReadableDimension)obj;
/*  94: 94 */      return (this.width == d.getWidth()) && (this.height == d.getHeight());
/*  95:    */    }
/*  96: 96 */    return false;
/*  97:    */  }
/*  98:    */  
/* 103:    */  public int hashCode()
/* 104:    */  {
/* 105:105 */    int sum = this.width + this.height;
/* 106:106 */    return sum * (sum + 1) / 2 + this.width;
/* 107:    */  }
/* 108:    */  
/* 119:    */  public String toString()
/* 120:    */  {
/* 121:121 */    return getClass().getName() + "[width=" + this.width + ",height=" + this.height + "]";
/* 122:    */  }
/* 123:    */  
/* 127:    */  public int getHeight()
/* 128:    */  {
/* 129:129 */    return this.height;
/* 130:    */  }
/* 131:    */  
/* 135:    */  public void setHeight(int height)
/* 136:    */  {
/* 137:137 */    this.height = height;
/* 138:    */  }
/* 139:    */  
/* 143:    */  public int getWidth()
/* 144:    */  {
/* 145:145 */    return this.width;
/* 146:    */  }
/* 147:    */  
/* 151:    */  public void setWidth(int width)
/* 152:    */  {
/* 153:153 */    this.width = width;
/* 154:    */  }
/* 155:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.Dimension
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
/*   1:    */package org.lwjgl.util;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */
/*  49:    */public final class Point
/*  50:    */  implements ReadablePoint, WritablePoint, Serializable
/*  51:    */{
/*  52:    */  static final long serialVersionUID = 1L;
/*  53:    */  private int x;
/*  54:    */  private int y;
/*  55:    */  
/*  56:    */  public Point() {}
/*  57:    */  
/*  58:    */  public Point(int x, int y)
/*  59:    */  {
/*  60: 60 */    setLocation(x, y);
/*  61:    */  }
/*  62:    */  
/*  65:    */  public Point(ReadablePoint p)
/*  66:    */  {
/*  67: 67 */    setLocation(p);
/*  68:    */  }
/*  69:    */  
/*  70:    */  public void setLocation(int x, int y) {
/*  71: 71 */    this.x = x;
/*  72: 72 */    this.y = y;
/*  73:    */  }
/*  74:    */  
/*  75:    */  public void setLocation(ReadablePoint p) {
/*  76: 76 */    this.x = p.getX();
/*  77: 77 */    this.y = p.getY();
/*  78:    */  }
/*  79:    */  
/*  80:    */  public void setX(int x) {
/*  81: 81 */    this.x = x;
/*  82:    */  }
/*  83:    */  
/*  84:    */  public void setY(int y) {
/*  85: 85 */    this.y = y;
/*  86:    */  }
/*  87:    */  
/*  92:    */  public void translate(int dx, int dy)
/*  93:    */  {
/*  94: 94 */    this.x += dx;
/*  95: 95 */    this.y += dy;
/*  96:    */  }
/*  97:    */  
/* 101:    */  public void translate(ReadablePoint p)
/* 102:    */  {
/* 103:103 */    this.x += p.getX();
/* 104:104 */    this.y += p.getY();
/* 105:    */  }
/* 106:    */  
/* 110:    */  public void untranslate(ReadablePoint p)
/* 111:    */  {
/* 112:112 */    this.x -= p.getX();
/* 113:113 */    this.y -= p.getY();
/* 114:    */  }
/* 115:    */  
/* 126:    */  public boolean equals(Object obj)
/* 127:    */  {
/* 128:128 */    if ((obj instanceof Point)) {
/* 129:129 */      Point pt = (Point)obj;
/* 130:130 */      return (this.x == pt.x) && (this.y == pt.y);
/* 131:    */    }
/* 132:132 */    return super.equals(obj);
/* 133:    */  }
/* 134:    */  
/* 143:    */  public String toString()
/* 144:    */  {
/* 145:145 */    return getClass().getName() + "[x=" + this.x + ",y=" + this.y + "]";
/* 146:    */  }
/* 147:    */  
/* 152:    */  public int hashCode()
/* 153:    */  {
/* 154:154 */    int sum = this.x + this.y;
/* 155:155 */    return sum * (sum + 1) / 2 + this.x;
/* 156:    */  }
/* 157:    */  
/* 158:    */  public int getX() {
/* 159:159 */    return this.x;
/* 160:    */  }
/* 161:    */  
/* 162:    */  public int getY() {
/* 163:163 */    return this.y;
/* 164:    */  }
/* 165:    */  
/* 166:    */  public void getLocation(WritablePoint dest) {
/* 167:167 */    dest.setLocation(this.x, this.y);
/* 168:    */  }
/* 169:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.Point
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
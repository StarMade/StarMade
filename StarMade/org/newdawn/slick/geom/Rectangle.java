/*   1:    */package org.newdawn.slick.geom;
/*   2:    */
/*   7:    */public class Rectangle
/*   8:    */  extends Shape
/*   9:    */{
/*  10:    */  protected float width;
/*  11:    */  
/*  15:    */  protected float height;
/*  16:    */  
/*  21:    */  public Rectangle(float x, float y, float width, float height)
/*  22:    */  {
/*  23: 23 */    this.x = x;
/*  24: 24 */    this.y = y;
/*  25: 25 */    this.width = width;
/*  26: 26 */    this.height = height;
/*  27: 27 */    this.maxX = (x + width);
/*  28: 28 */    this.maxY = (y + height);
/*  29: 29 */    checkPoints();
/*  30:    */  }
/*  31:    */  
/*  38:    */  public boolean contains(float xp, float yp)
/*  39:    */  {
/*  40: 40 */    if (xp <= getX()) {
/*  41: 41 */      return false;
/*  42:    */    }
/*  43: 43 */    if (yp <= getY()) {
/*  44: 44 */      return false;
/*  45:    */    }
/*  46: 46 */    if (xp >= this.maxX) {
/*  47: 47 */      return false;
/*  48:    */    }
/*  49: 49 */    if (yp >= this.maxY) {
/*  50: 50 */      return false;
/*  51:    */    }
/*  52:    */    
/*  53: 53 */    return true;
/*  54:    */  }
/*  55:    */  
/*  60:    */  public void setBounds(Rectangle other)
/*  61:    */  {
/*  62: 62 */    setBounds(other.getX(), other.getY(), other.getWidth(), other.getHeight());
/*  63:    */  }
/*  64:    */  
/*  72:    */  public void setBounds(float x, float y, float width, float height)
/*  73:    */  {
/*  74: 74 */    setX(x);
/*  75: 75 */    setY(y);
/*  76: 76 */    setSize(width, height);
/*  77:    */  }
/*  78:    */  
/*  84:    */  public void setSize(float width, float height)
/*  85:    */  {
/*  86: 86 */    setWidth(width);
/*  87: 87 */    setHeight(height);
/*  88:    */  }
/*  89:    */  
/*  95:    */  public float getWidth()
/*  96:    */  {
/*  97: 97 */    return this.width;
/*  98:    */  }
/*  99:    */  
/* 104:    */  public float getHeight()
/* 105:    */  {
/* 106:106 */    return this.height;
/* 107:    */  }
/* 108:    */  
/* 115:    */  public void grow(float h, float v)
/* 116:    */  {
/* 117:117 */    setX(getX() - h);
/* 118:118 */    setY(getY() - v);
/* 119:119 */    setWidth(getWidth() + h * 2.0F);
/* 120:120 */    setHeight(getHeight() + v * 2.0F);
/* 121:    */  }
/* 122:    */  
/* 128:    */  public void scaleGrow(float h, float v)
/* 129:    */  {
/* 130:130 */    grow(getWidth() * (h - 1.0F), getHeight() * (v - 1.0F));
/* 131:    */  }
/* 132:    */  
/* 137:    */  public void setWidth(float width)
/* 138:    */  {
/* 139:139 */    if (width != this.width) {
/* 140:140 */      this.pointsDirty = true;
/* 141:141 */      this.width = width;
/* 142:142 */      this.maxX = (this.x + width);
/* 143:    */    }
/* 144:    */  }
/* 145:    */  
/* 150:    */  public void setHeight(float height)
/* 151:    */  {
/* 152:152 */    if (height != this.height) {
/* 153:153 */      this.pointsDirty = true;
/* 154:154 */      this.height = height;
/* 155:155 */      this.maxY = (this.y + height);
/* 156:    */    }
/* 157:    */  }
/* 158:    */  
/* 164:    */  public boolean intersects(Shape shape)
/* 165:    */  {
/* 166:166 */    if ((shape instanceof Rectangle)) {
/* 167:167 */      Rectangle other = (Rectangle)shape;
/* 168:168 */      if ((this.x > other.x + other.width) || (this.x + this.width < other.x)) {
/* 169:169 */        return false;
/* 170:    */      }
/* 171:171 */      if ((this.y > other.y + other.height) || (this.y + this.height < other.y)) {
/* 172:172 */        return false;
/* 173:    */      }
/* 174:174 */      return true;
/* 175:    */    }
/* 176:176 */    if ((shape instanceof Circle)) {
/* 177:177 */      return intersects((Circle)shape);
/* 178:    */    }
/* 179:    */    
/* 180:180 */    return super.intersects(shape);
/* 181:    */  }
/* 182:    */  
/* 183:    */  protected void createPoints()
/* 184:    */  {
/* 185:185 */    float useWidth = this.width;
/* 186:186 */    float useHeight = this.height;
/* 187:187 */    this.points = new float[8];
/* 188:    */    
/* 189:189 */    this.points[0] = this.x;
/* 190:190 */    this.points[1] = this.y;
/* 191:    */    
/* 192:192 */    this.points[2] = (this.x + useWidth);
/* 193:193 */    this.points[3] = this.y;
/* 194:    */    
/* 195:195 */    this.points[4] = (this.x + useWidth);
/* 196:196 */    this.points[5] = (this.y + useHeight);
/* 197:    */    
/* 198:198 */    this.points[6] = this.x;
/* 199:199 */    this.points[7] = (this.y + useHeight);
/* 200:    */    
/* 201:201 */    this.maxX = this.points[2];
/* 202:202 */    this.maxY = this.points[5];
/* 203:203 */    this.minX = this.points[0];
/* 204:204 */    this.minY = this.points[1];
/* 205:    */    
/* 206:206 */    findCenter();
/* 207:207 */    calculateRadius();
/* 208:    */  }
/* 209:    */  
/* 215:    */  private boolean intersects(Circle other)
/* 216:    */  {
/* 217:217 */    return other.intersects(this);
/* 218:    */  }
/* 219:    */  
/* 222:    */  public String toString()
/* 223:    */  {
/* 224:224 */    return "[Rectangle " + this.width + "x" + this.height + "]";
/* 225:    */  }
/* 226:    */  
/* 243:    */  public static boolean contains(float xp, float yp, float xr, float yr, float widthr, float heightr)
/* 244:    */  {
/* 245:245 */    return (xp >= xr) && (yp >= yr) && (xp <= xr + widthr) && (yp <= yr + heightr);
/* 246:    */  }
/* 247:    */  
/* 255:    */  public Shape transform(Transform transform)
/* 256:    */  {
/* 257:257 */    checkPoints();
/* 258:    */    
/* 259:259 */    Polygon resultPolygon = new Polygon();
/* 260:    */    
/* 261:261 */    float[] result = new float[this.points.length];
/* 262:262 */    transform.transform(this.points, 0, result, 0, this.points.length / 2);
/* 263:263 */    resultPolygon.points = result;
/* 264:264 */    resultPolygon.findCenter();
/* 265:265 */    resultPolygon.checkPoints();
/* 266:    */    
/* 267:267 */    return resultPolygon;
/* 268:    */  }
/* 269:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.geom.Rectangle
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
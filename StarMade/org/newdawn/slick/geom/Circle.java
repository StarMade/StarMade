/*   1:    */package org.newdawn.slick.geom;
/*   2:    */
/*   8:    */public class Circle
/*   9:    */  extends Ellipse
/*  10:    */{
/*  11:    */  public float radius;
/*  12:    */  
/*  18:    */  public strictfp Circle(float centerPointX, float centerPointY, float radius)
/*  19:    */  {
/*  20: 20 */    this(centerPointX, centerPointY, radius, 50);
/*  21:    */  }
/*  22:    */  
/*  30:    */  public strictfp Circle(float centerPointX, float centerPointY, float radius, int segmentCount)
/*  31:    */  {
/*  32: 32 */    super(centerPointX, centerPointY, radius, radius, segmentCount);
/*  33: 33 */    this.x = (centerPointX - radius);
/*  34: 34 */    this.y = (centerPointY - radius);
/*  35: 35 */    this.radius = radius;
/*  36: 36 */    this.boundingCircleRadius = radius;
/*  37:    */  }
/*  38:    */  
/*  43:    */  public strictfp float getCenterX()
/*  44:    */  {
/*  45: 45 */    return getX() + this.radius;
/*  46:    */  }
/*  47:    */  
/*  52:    */  public strictfp float getCenterY()
/*  53:    */  {
/*  54: 54 */    return getY() + this.radius;
/*  55:    */  }
/*  56:    */  
/*  61:    */  public strictfp void setRadius(float radius)
/*  62:    */  {
/*  63: 63 */    if (radius != this.radius) {
/*  64: 64 */      this.pointsDirty = true;
/*  65: 65 */      this.radius = radius;
/*  66: 66 */      setRadii(radius, radius);
/*  67:    */    }
/*  68:    */  }
/*  69:    */  
/*  74:    */  public strictfp float getRadius()
/*  75:    */  {
/*  76: 76 */    return this.radius;
/*  77:    */  }
/*  78:    */  
/*  84:    */  public strictfp boolean intersects(Shape shape)
/*  85:    */  {
/*  86: 86 */    if ((shape instanceof Circle)) {
/*  87: 87 */      Circle other = (Circle)shape;
/*  88: 88 */      float totalRad2 = getRadius() + other.getRadius();
/*  89:    */      
/*  90: 90 */      if (Math.abs(other.getCenterX() - getCenterX()) > totalRad2) {
/*  91: 91 */        return false;
/*  92:    */      }
/*  93: 93 */      if (Math.abs(other.getCenterY() - getCenterY()) > totalRad2) {
/*  94: 94 */        return false;
/*  95:    */      }
/*  96:    */      
/*  97: 97 */      totalRad2 *= totalRad2;
/*  98:    */      
/*  99: 99 */      float dx = Math.abs(other.getCenterX() - getCenterX());
/* 100:100 */      float dy = Math.abs(other.getCenterY() - getCenterY());
/* 101:    */      
/* 102:102 */      return totalRad2 >= dx * dx + dy * dy;
/* 103:    */    }
/* 104:104 */    if ((shape instanceof Rectangle)) {
/* 105:105 */      return intersects((Rectangle)shape);
/* 106:    */    }
/* 107:    */    
/* 108:108 */    return super.intersects(shape);
/* 109:    */  }
/* 110:    */  
/* 119:    */  public strictfp boolean contains(float x, float y)
/* 120:    */  {
/* 121:121 */    return (x - getX()) * (x - getX()) + (y - getY()) * (y - getY()) < getRadius() * getRadius();
/* 122:    */  }
/* 123:    */  
/* 128:    */  private strictfp boolean contains(Line line)
/* 129:    */  {
/* 130:130 */    return (contains(line.getX1(), line.getY1())) && (contains(line.getX2(), line.getY2()));
/* 131:    */  }
/* 132:    */  
/* 135:    */  protected strictfp void findCenter()
/* 136:    */  {
/* 137:137 */    this.center = new float[2];
/* 138:138 */    this.center[0] = (this.x + this.radius);
/* 139:139 */    this.center[1] = (this.y + this.radius);
/* 140:    */  }
/* 141:    */  
/* 144:    */  protected strictfp void calculateRadius()
/* 145:    */  {
/* 146:146 */    this.boundingCircleRadius = this.radius;
/* 147:    */  }
/* 148:    */  
/* 154:    */  private strictfp boolean intersects(Rectangle other)
/* 155:    */  {
/* 156:156 */    Rectangle box = other;
/* 157:157 */    Circle circle = this;
/* 158:    */    
/* 159:159 */    if (box.contains(this.x + this.radius, this.y + this.radius)) {
/* 160:160 */      return true;
/* 161:    */    }
/* 162:    */    
/* 163:163 */    float x1 = box.getX();
/* 164:164 */    float y1 = box.getY();
/* 165:165 */    float x2 = box.getX() + box.getWidth();
/* 166:166 */    float y2 = box.getY() + box.getHeight();
/* 167:    */    
/* 168:168 */    Line[] lines = new Line[4];
/* 169:169 */    lines[0] = new Line(x1, y1, x2, y1);
/* 170:170 */    lines[1] = new Line(x2, y1, x2, y2);
/* 171:171 */    lines[2] = new Line(x2, y2, x1, y2);
/* 172:172 */    lines[3] = new Line(x1, y2, x1, y1);
/* 173:    */    
/* 174:174 */    float r2 = circle.getRadius() * circle.getRadius();
/* 175:    */    
/* 176:176 */    Vector2f pos = new Vector2f(circle.getCenterX(), circle.getCenterY());
/* 177:    */    
/* 178:178 */    for (int i = 0; i < 4; i++) {
/* 179:179 */      float dis = lines[i].distanceSquared(pos);
/* 180:180 */      if (dis < r2) {
/* 181:181 */        return true;
/* 182:    */      }
/* 183:    */    }
/* 184:    */    
/* 185:185 */    return false;
/* 186:    */  }
/* 187:    */  
/* 193:    */  private strictfp boolean intersects(Line other)
/* 194:    */  {
/* 195:195 */    Vector2f lineSegmentStart = new Vector2f(other.getX1(), other.getY1());
/* 196:196 */    Vector2f lineSegmentEnd = new Vector2f(other.getX2(), other.getY2());
/* 197:197 */    Vector2f circleCenter = new Vector2f(getCenterX(), getCenterY());
/* 198:    */    
/* 202:202 */    Vector2f segv = lineSegmentEnd.copy().sub(lineSegmentStart);
/* 203:203 */    Vector2f ptv = circleCenter.copy().sub(lineSegmentStart);
/* 204:204 */    float segvLength = segv.length();
/* 205:205 */    float projvl = ptv.dot(segv) / segvLength;
/* 206:206 */    Vector2f closest; Vector2f closest; if (projvl < 0.0F)
/* 207:    */    {
/* 208:208 */      closest = lineSegmentStart;
/* 209:    */    } else { Vector2f closest;
/* 210:210 */      if (projvl > segvLength)
/* 211:    */      {
/* 212:212 */        closest = lineSegmentEnd;
/* 213:    */      }
/* 214:    */      else
/* 215:    */      {
/* 216:216 */        Vector2f projv = segv.copy().scale(projvl / segvLength);
/* 217:217 */        closest = lineSegmentStart.copy().add(projv);
/* 218:    */      } }
/* 219:219 */    boolean intersects = circleCenter.copy().sub(closest).lengthSquared() <= getRadius() * getRadius();
/* 220:    */    
/* 221:221 */    return intersects;
/* 222:    */  }
/* 223:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.geom.Circle
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
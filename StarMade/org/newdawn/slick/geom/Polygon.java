/*   1:    */package org.newdawn.slick.geom;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */
/*  10:    */public class Polygon
/*  11:    */  extends Shape
/*  12:    */{
/*  13: 13 */  private boolean allowDups = false;
/*  14:    */  
/*  15: 15 */  private boolean closed = true;
/*  16:    */  
/*  23:    */  public Polygon(float[] points)
/*  24:    */  {
/*  25: 25 */    int length = points.length;
/*  26:    */    
/*  27: 27 */    this.points = new float[length];
/*  28: 28 */    this.maxX = -1.401299E-045F;
/*  29: 29 */    this.maxY = -1.401299E-045F;
/*  30: 30 */    this.minX = 3.4028235E+38F;
/*  31: 31 */    this.minY = 3.4028235E+38F;
/*  32: 32 */    this.x = 3.4028235E+38F;
/*  33: 33 */    this.y = 3.4028235E+38F;
/*  34:    */    
/*  35: 35 */    for (int i = 0; i < length; i++) {
/*  36: 36 */      this.points[i] = points[i];
/*  37: 37 */      if (i % 2 == 0) {
/*  38: 38 */        if (points[i] > this.maxX) {
/*  39: 39 */          this.maxX = points[i];
/*  40:    */        }
/*  41: 41 */        if (points[i] < this.minX) {
/*  42: 42 */          this.minX = points[i];
/*  43:    */        }
/*  44: 44 */        if (points[i] < this.x) {
/*  45: 45 */          this.x = points[i];
/*  46:    */        }
/*  47:    */      }
/*  48:    */      else {
/*  49: 49 */        if (points[i] > this.maxY) {
/*  50: 50 */          this.maxY = points[i];
/*  51:    */        }
/*  52: 52 */        if (points[i] < this.minY) {
/*  53: 53 */          this.minY = points[i];
/*  54:    */        }
/*  55: 55 */        if (points[i] < this.y) {
/*  56: 56 */          this.y = points[i];
/*  57:    */        }
/*  58:    */      }
/*  59:    */    }
/*  60:    */    
/*  61: 61 */    findCenter();
/*  62: 62 */    calculateRadius();
/*  63: 63 */    this.pointsDirty = true;
/*  64:    */  }
/*  65:    */  
/*  68:    */  public Polygon()
/*  69:    */  {
/*  70: 70 */    this.points = new float[0];
/*  71: 71 */    this.maxX = -1.401299E-045F;
/*  72: 72 */    this.maxY = -1.401299E-045F;
/*  73: 73 */    this.minX = 3.4028235E+38F;
/*  74: 74 */    this.minY = 3.4028235E+38F;
/*  75:    */  }
/*  76:    */  
/*  81:    */  public void setAllowDuplicatePoints(boolean allowDups)
/*  82:    */  {
/*  83: 83 */    this.allowDups = allowDups;
/*  84:    */  }
/*  85:    */  
/*  91:    */  public void addPoint(float x, float y)
/*  92:    */  {
/*  93: 93 */    if ((hasVertex(x, y)) && (!this.allowDups)) {
/*  94: 94 */      return;
/*  95:    */    }
/*  96:    */    
/*  97: 97 */    ArrayList tempPoints = new ArrayList();
/*  98: 98 */    for (int i = 0; i < this.points.length; i++) {
/*  99: 99 */      tempPoints.add(new Float(this.points[i]));
/* 100:    */    }
/* 101:101 */    tempPoints.add(new Float(x));
/* 102:102 */    tempPoints.add(new Float(y));
/* 103:103 */    int length = tempPoints.size();
/* 104:104 */    this.points = new float[length];
/* 105:105 */    for (int i = 0; i < length; i++) {
/* 106:106 */      this.points[i] = ((Float)tempPoints.get(i)).floatValue();
/* 107:    */    }
/* 108:108 */    if (x > this.maxX) {
/* 109:109 */      this.maxX = x;
/* 110:    */    }
/* 111:111 */    if (y > this.maxY) {
/* 112:112 */      this.maxY = y;
/* 113:    */    }
/* 114:114 */    if (x < this.minX) {
/* 115:115 */      this.minX = x;
/* 116:    */    }
/* 117:117 */    if (y < this.minY) {
/* 118:118 */      this.minY = y;
/* 119:    */    }
/* 120:120 */    findCenter();
/* 121:121 */    calculateRadius();
/* 122:    */    
/* 123:123 */    this.pointsDirty = true;
/* 124:    */  }
/* 125:    */  
/* 133:    */  public Shape transform(Transform transform)
/* 134:    */  {
/* 135:135 */    checkPoints();
/* 136:    */    
/* 137:137 */    Polygon resultPolygon = new Polygon();
/* 138:    */    
/* 139:139 */    float[] result = new float[this.points.length];
/* 140:140 */    transform.transform(this.points, 0, result, 0, this.points.length / 2);
/* 141:141 */    resultPolygon.points = result;
/* 142:142 */    resultPolygon.findCenter();
/* 143:143 */    resultPolygon.closed = this.closed;
/* 144:    */    
/* 145:145 */    return resultPolygon;
/* 146:    */  }
/* 147:    */  
/* 150:    */  public void setX(float x)
/* 151:    */  {
/* 152:152 */    super.setX(x);
/* 153:    */    
/* 154:154 */    this.pointsDirty = false;
/* 155:    */  }
/* 156:    */  
/* 159:    */  public void setY(float y)
/* 160:    */  {
/* 161:161 */    super.setY(y);
/* 162:    */    
/* 163:163 */    this.pointsDirty = false;
/* 164:    */  }
/* 165:    */  
/* 170:    */  protected void createPoints() {}
/* 171:    */  
/* 175:    */  public boolean closed()
/* 176:    */  {
/* 177:177 */    return this.closed;
/* 178:    */  }
/* 179:    */  
/* 184:    */  public void setClosed(boolean closed)
/* 185:    */  {
/* 186:186 */    this.closed = closed;
/* 187:    */  }
/* 188:    */  
/* 193:    */  public Polygon copy()
/* 194:    */  {
/* 195:195 */    float[] copyPoints = new float[this.points.length];
/* 196:196 */    System.arraycopy(this.points, 0, copyPoints, 0, copyPoints.length);
/* 197:    */    
/* 198:198 */    return new Polygon(copyPoints);
/* 199:    */  }
/* 200:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.geom.Polygon
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
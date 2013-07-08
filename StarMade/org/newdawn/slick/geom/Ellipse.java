/*   1:    */package org.newdawn.slick.geom;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import org.newdawn.slick.util.FastTrig;
/*   5:    */
/*  31:    */public class Ellipse
/*  32:    */  extends Shape
/*  33:    */{
/*  34:    */  protected static final int DEFAULT_SEGMENT_COUNT = 50;
/*  35:    */  private int segmentCount;
/*  36:    */  private float radius1;
/*  37:    */  private float radius2;
/*  38:    */  
/*  39:    */  public Ellipse(float centerPointX, float centerPointY, float radius1, float radius2)
/*  40:    */  {
/*  41: 41 */    this(centerPointX, centerPointY, radius1, radius2, 50);
/*  42:    */  }
/*  43:    */  
/*  52:    */  public Ellipse(float centerPointX, float centerPointY, float radius1, float radius2, int segmentCount)
/*  53:    */  {
/*  54: 54 */    this.x = (centerPointX - radius1);
/*  55: 55 */    this.y = (centerPointY - radius2);
/*  56: 56 */    this.radius1 = radius1;
/*  57: 57 */    this.radius2 = radius2;
/*  58: 58 */    this.segmentCount = segmentCount;
/*  59: 59 */    checkPoints();
/*  60:    */  }
/*  61:    */  
/*  67:    */  public void setRadii(float radius1, float radius2)
/*  68:    */  {
/*  69: 69 */    setRadius1(radius1);
/*  70: 70 */    setRadius2(radius2);
/*  71:    */  }
/*  72:    */  
/*  77:    */  public float getRadius1()
/*  78:    */  {
/*  79: 79 */    return this.radius1;
/*  80:    */  }
/*  81:    */  
/*  86:    */  public void setRadius1(float radius1)
/*  87:    */  {
/*  88: 88 */    if (radius1 != this.radius1) {
/*  89: 89 */      this.radius1 = radius1;
/*  90: 90 */      this.pointsDirty = true;
/*  91:    */    }
/*  92:    */  }
/*  93:    */  
/*  98:    */  public float getRadius2()
/*  99:    */  {
/* 100:100 */    return this.radius2;
/* 101:    */  }
/* 102:    */  
/* 107:    */  public void setRadius2(float radius2)
/* 108:    */  {
/* 109:109 */    if (radius2 != this.radius2) {
/* 110:110 */      this.radius2 = radius2;
/* 111:111 */      this.pointsDirty = true;
/* 112:    */    }
/* 113:    */  }
/* 114:    */  
/* 118:    */  protected void createPoints()
/* 119:    */  {
/* 120:120 */    ArrayList tempPoints = new ArrayList();
/* 121:    */    
/* 122:122 */    this.maxX = -1.401299E-045F;
/* 123:123 */    this.maxY = -1.401299E-045F;
/* 124:124 */    this.minX = 3.4028235E+38F;
/* 125:125 */    this.minY = 3.4028235E+38F;
/* 126:    */    
/* 127:127 */    float start = 0.0F;
/* 128:128 */    float end = 359.0F;
/* 129:    */    
/* 130:130 */    float cx = this.x + this.radius1;
/* 131:131 */    float cy = this.y + this.radius2;
/* 132:    */    
/* 133:133 */    int step = 360 / this.segmentCount;
/* 134:    */    
/* 135:135 */    for (float a = start; a <= end + step; a += step) {
/* 136:136 */      float ang = a;
/* 137:137 */      if (ang > end) {
/* 138:138 */        ang = end;
/* 139:    */      }
/* 140:140 */      float newX = (float)(cx + FastTrig.cos(Math.toRadians(ang)) * this.radius1);
/* 141:141 */      float newY = (float)(cy + FastTrig.sin(Math.toRadians(ang)) * this.radius2);
/* 142:    */      
/* 143:143 */      if (newX > this.maxX) {
/* 144:144 */        this.maxX = newX;
/* 145:    */      }
/* 146:146 */      if (newY > this.maxY) {
/* 147:147 */        this.maxY = newY;
/* 148:    */      }
/* 149:149 */      if (newX < this.minX) {
/* 150:150 */        this.minX = newX;
/* 151:    */      }
/* 152:152 */      if (newY < this.minY) {
/* 153:153 */        this.minY = newY;
/* 154:    */      }
/* 155:    */      
/* 156:156 */      tempPoints.add(new Float(newX));
/* 157:157 */      tempPoints.add(new Float(newY));
/* 158:    */    }
/* 159:159 */    this.points = new float[tempPoints.size()];
/* 160:160 */    for (int i = 0; i < this.points.length; i++) {
/* 161:161 */      this.points[i] = ((Float)tempPoints.get(i)).floatValue();
/* 162:    */    }
/* 163:    */  }
/* 164:    */  
/* 167:    */  public Shape transform(Transform transform)
/* 168:    */  {
/* 169:169 */    checkPoints();
/* 170:    */    
/* 171:171 */    Polygon resultPolygon = new Polygon();
/* 172:    */    
/* 173:173 */    float[] result = new float[this.points.length];
/* 174:174 */    transform.transform(this.points, 0, result, 0, this.points.length / 2);
/* 175:175 */    resultPolygon.points = result;
/* 176:176 */    resultPolygon.checkPoints();
/* 177:    */    
/* 178:178 */    return resultPolygon;
/* 179:    */  }
/* 180:    */  
/* 183:    */  protected void findCenter()
/* 184:    */  {
/* 185:185 */    this.center = new float[2];
/* 186:186 */    this.center[0] = (this.x + this.radius1);
/* 187:187 */    this.center[1] = (this.y + this.radius2);
/* 188:    */  }
/* 189:    */  
/* 192:    */  protected void calculateRadius()
/* 193:    */  {
/* 194:194 */    this.boundingCircleRadius = (this.radius1 > this.radius2 ? this.radius1 : this.radius2);
/* 195:    */  }
/* 196:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.geom.Ellipse
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
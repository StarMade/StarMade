/*   1:    */package org.newdawn.slick.geom;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.List;
/*   5:    */import org.newdawn.slick.util.FastTrig;
/*   6:    */
/*  30:    */public class RoundedRectangle
/*  31:    */  extends Rectangle
/*  32:    */{
/*  33:    */  public static final int TOP_LEFT = 1;
/*  34:    */  public static final int TOP_RIGHT = 2;
/*  35:    */  public static final int BOTTOM_RIGHT = 4;
/*  36:    */  public static final int BOTTOM_LEFT = 8;
/*  37:    */  public static final int ALL = 15;
/*  38:    */  private static final int DEFAULT_SEGMENT_COUNT = 25;
/*  39:    */  private float cornerRadius;
/*  40:    */  private int segmentCount;
/*  41:    */  private int cornerFlags;
/*  42:    */  
/*  43:    */  public RoundedRectangle(float x, float y, float width, float height, float cornerRadius)
/*  44:    */  {
/*  45: 45 */    this(x, y, width, height, cornerRadius, 25);
/*  46:    */  }
/*  47:    */  
/*  57:    */  public RoundedRectangle(float x, float y, float width, float height, float cornerRadius, int segmentCount)
/*  58:    */  {
/*  59: 59 */    this(x, y, width, height, cornerRadius, segmentCount, 15);
/*  60:    */  }
/*  61:    */  
/*  73:    */  public RoundedRectangle(float x, float y, float width, float height, float cornerRadius, int segmentCount, int cornerFlags)
/*  74:    */  {
/*  75: 75 */    super(x, y, width, height);
/*  76:    */    
/*  77: 77 */    if (cornerRadius < 0.0F) {
/*  78: 78 */      throw new IllegalArgumentException("corner radius must be >= 0");
/*  79:    */    }
/*  80: 80 */    this.x = x;
/*  81: 81 */    this.y = y;
/*  82: 82 */    this.width = width;
/*  83: 83 */    this.height = height;
/*  84: 84 */    this.cornerRadius = cornerRadius;
/*  85: 85 */    this.segmentCount = segmentCount;
/*  86: 86 */    this.pointsDirty = true;
/*  87: 87 */    this.cornerFlags = cornerFlags;
/*  88:    */  }
/*  89:    */  
/*  94:    */  public float getCornerRadius()
/*  95:    */  {
/*  96: 96 */    return this.cornerRadius;
/*  97:    */  }
/*  98:    */  
/* 103:    */  public void setCornerRadius(float cornerRadius)
/* 104:    */  {
/* 105:105 */    if ((cornerRadius >= 0.0F) && 
/* 106:106 */      (cornerRadius != this.cornerRadius)) {
/* 107:107 */      this.cornerRadius = cornerRadius;
/* 108:108 */      this.pointsDirty = true;
/* 109:    */    }
/* 110:    */  }
/* 111:    */  
/* 117:    */  public float getHeight()
/* 118:    */  {
/* 119:119 */    return this.height;
/* 120:    */  }
/* 121:    */  
/* 126:    */  public void setHeight(float height)
/* 127:    */  {
/* 128:128 */    if (this.height != height) {
/* 129:129 */      this.height = height;
/* 130:130 */      this.pointsDirty = true;
/* 131:    */    }
/* 132:    */  }
/* 133:    */  
/* 138:    */  public float getWidth()
/* 139:    */  {
/* 140:140 */    return this.width;
/* 141:    */  }
/* 142:    */  
/* 147:    */  public void setWidth(float width)
/* 148:    */  {
/* 149:149 */    if (width != this.width) {
/* 150:150 */      this.width = width;
/* 151:151 */      this.pointsDirty = true;
/* 152:    */    }
/* 153:    */  }
/* 154:    */  
/* 155:    */  protected void createPoints() {
/* 156:156 */    this.maxX = (this.x + this.width);
/* 157:157 */    this.maxY = (this.y + this.height);
/* 158:158 */    this.minX = this.x;
/* 159:159 */    this.minY = this.y;
/* 160:160 */    float useWidth = this.width - 1.0F;
/* 161:161 */    float useHeight = this.height - 1.0F;
/* 162:162 */    if (this.cornerRadius == 0.0F) {
/* 163:163 */      this.points = new float[8];
/* 164:    */      
/* 165:165 */      this.points[0] = this.x;
/* 166:166 */      this.points[1] = this.y;
/* 167:    */      
/* 168:168 */      this.points[2] = (this.x + useWidth);
/* 169:169 */      this.points[3] = this.y;
/* 170:    */      
/* 171:171 */      this.points[4] = (this.x + useWidth);
/* 172:172 */      this.points[5] = (this.y + useHeight);
/* 173:    */      
/* 174:174 */      this.points[6] = this.x;
/* 175:175 */      this.points[7] = (this.y + useHeight);
/* 176:    */    }
/* 177:    */    else {
/* 178:178 */      float doubleRadius = this.cornerRadius * 2.0F;
/* 179:179 */      if (doubleRadius > useWidth) {
/* 180:180 */        doubleRadius = useWidth;
/* 181:181 */        this.cornerRadius = (doubleRadius / 2.0F);
/* 182:    */      }
/* 183:183 */      if (doubleRadius > useHeight) {
/* 184:184 */        doubleRadius = useHeight;
/* 185:185 */        this.cornerRadius = (doubleRadius / 2.0F);
/* 186:    */      }
/* 187:    */      
/* 188:188 */      ArrayList tempPoints = new ArrayList();
/* 189:    */      
/* 193:193 */      if ((this.cornerFlags & 0x1) != 0) {
/* 194:194 */        tempPoints.addAll(createPoints(this.segmentCount, this.cornerRadius, this.x + this.cornerRadius, this.y + this.cornerRadius, 180.0F, 270.0F));
/* 195:    */      } else {
/* 196:196 */        tempPoints.add(new Float(this.x));
/* 197:197 */        tempPoints.add(new Float(this.y));
/* 198:    */      }
/* 199:    */      
/* 201:201 */      if ((this.cornerFlags & 0x2) != 0) {
/* 202:202 */        tempPoints.addAll(createPoints(this.segmentCount, this.cornerRadius, this.x + useWidth - this.cornerRadius, this.y + this.cornerRadius, 270.0F, 360.0F));
/* 203:    */      } else {
/* 204:204 */        tempPoints.add(new Float(this.x + useWidth));
/* 205:205 */        tempPoints.add(new Float(this.y));
/* 206:    */      }
/* 207:    */      
/* 209:209 */      if ((this.cornerFlags & 0x4) != 0) {
/* 210:210 */        tempPoints.addAll(createPoints(this.segmentCount, this.cornerRadius, this.x + useWidth - this.cornerRadius, this.y + useHeight - this.cornerRadius, 0.0F, 90.0F));
/* 211:    */      } else {
/* 212:212 */        tempPoints.add(new Float(this.x + useWidth));
/* 213:213 */        tempPoints.add(new Float(this.y + useHeight));
/* 214:    */      }
/* 215:    */      
/* 217:217 */      if ((this.cornerFlags & 0x8) != 0) {
/* 218:218 */        tempPoints.addAll(createPoints(this.segmentCount, this.cornerRadius, this.x + this.cornerRadius, this.y + useHeight - this.cornerRadius, 90.0F, 180.0F));
/* 219:    */      } else {
/* 220:220 */        tempPoints.add(new Float(this.x));
/* 221:221 */        tempPoints.add(new Float(this.y + useHeight));
/* 222:    */      }
/* 223:    */      
/* 224:224 */      this.points = new float[tempPoints.size()];
/* 225:225 */      for (int i = 0; i < tempPoints.size(); i++) {
/* 226:226 */        this.points[i] = ((Float)tempPoints.get(i)).floatValue();
/* 227:    */      }
/* 228:    */    }
/* 229:    */    
/* 230:230 */    findCenter();
/* 231:231 */    calculateRadius();
/* 232:    */  }
/* 233:    */  
/* 244:    */  private List createPoints(int numberOfSegments, float radius, float cx, float cy, float start, float end)
/* 245:    */  {
/* 246:246 */    ArrayList tempPoints = new ArrayList();
/* 247:    */    
/* 248:248 */    int step = 360 / numberOfSegments;
/* 249:    */    
/* 250:250 */    for (float a = start; a <= end + step; a += step) {
/* 251:251 */      float ang = a;
/* 252:252 */      if (ang > end) {
/* 253:253 */        ang = end;
/* 254:    */      }
/* 255:255 */      float x = (float)(cx + FastTrig.cos(Math.toRadians(ang)) * radius);
/* 256:256 */      float y = (float)(cy + FastTrig.sin(Math.toRadians(ang)) * radius);
/* 257:    */      
/* 258:258 */      tempPoints.add(new Float(x));
/* 259:259 */      tempPoints.add(new Float(y));
/* 260:    */    }
/* 261:    */    
/* 262:262 */    return tempPoints;
/* 263:    */  }
/* 264:    */  
/* 270:    */  public Shape transform(Transform transform)
/* 271:    */  {
/* 272:272 */    checkPoints();
/* 273:    */    
/* 274:274 */    Polygon resultPolygon = new Polygon();
/* 275:    */    
/* 276:276 */    float[] result = new float[this.points.length];
/* 277:277 */    transform.transform(this.points, 0, result, 0, this.points.length / 2);
/* 278:278 */    resultPolygon.points = result;
/* 279:279 */    resultPolygon.findCenter();
/* 280:    */    
/* 281:281 */    return resultPolygon;
/* 282:    */  }
/* 283:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.geom.RoundedRectangle
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
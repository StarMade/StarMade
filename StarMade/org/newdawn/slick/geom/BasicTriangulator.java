/*   1:    */package org.newdawn.slick.geom;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */
/*  11:    */public class BasicTriangulator
/*  12:    */  implements Triangulator
/*  13:    */{
/*  14:    */  private static final float EPSILON = 1.0E-010F;
/*  15: 15 */  private PointList poly = new PointList();
/*  16:    */  
/*  17: 17 */  private PointList tris = new PointList();
/*  18:    */  
/*  24:    */  private boolean tried;
/*  25:    */  
/*  32:    */  public void addPolyPoint(float x, float y)
/*  33:    */  {
/*  34: 34 */    Point p = new Point(x, y);
/*  35: 35 */    if (!this.poly.contains(p)) {
/*  36: 36 */      this.poly.add(p);
/*  37:    */    }
/*  38:    */  }
/*  39:    */  
/*  44:    */  public int getPolyPointCount()
/*  45:    */  {
/*  46: 46 */    return this.poly.size();
/*  47:    */  }
/*  48:    */  
/*  54:    */  public float[] getPolyPoint(int index)
/*  55:    */  {
/*  56: 56 */    return new float[] { this.poly.get(index).x, this.poly.get(index).y };
/*  57:    */  }
/*  58:    */  
/*  63:    */  public boolean triangulate()
/*  64:    */  {
/*  65: 65 */    this.tried = true;
/*  66:    */    
/*  67: 67 */    boolean worked = process(this.poly, this.tris);
/*  68: 68 */    return worked;
/*  69:    */  }
/*  70:    */  
/*  75:    */  public int getTriangleCount()
/*  76:    */  {
/*  77: 77 */    if (!this.tried) {
/*  78: 78 */      throw new RuntimeException("Call triangulate() before accessing triangles");
/*  79:    */    }
/*  80: 80 */    return this.tris.size() / 3;
/*  81:    */  }
/*  82:    */  
/*  90:    */  public float[] getTrianglePoint(int tri, int i)
/*  91:    */  {
/*  92: 92 */    if (!this.tried) {
/*  93: 93 */      throw new RuntimeException("Call triangulate() before accessing triangles");
/*  94:    */    }
/*  95:    */    
/*  96: 96 */    return this.tris.get(tri * 3 + i).toArray();
/*  97:    */  }
/*  98:    */  
/* 106:    */  private float area(PointList contour)
/* 107:    */  {
/* 108:108 */    int n = contour.size();
/* 109:    */    
/* 110:110 */    float A = 0.0F;
/* 111:    */    
/* 112:112 */    int p = n - 1; for (int q = 0; q < n; p = q++) {
/* 113:113 */      Point contourP = contour.get(p);
/* 114:114 */      Point contourQ = contour.get(q);
/* 115:    */      
/* 116:116 */      A += contourP.getX() * contourQ.getY() - contourQ.getX() * contourP.getY();
/* 117:    */    }
/* 118:    */    
/* 119:119 */    return A * 0.5F;
/* 120:    */  }
/* 121:    */  
/* 139:    */  private boolean insideTriangle(float Ax, float Ay, float Bx, float By, float Cx, float Cy, float Px, float Py)
/* 140:    */  {
/* 141:141 */    float ax = Cx - Bx;
/* 142:142 */    float ay = Cy - By;
/* 143:143 */    float bx = Ax - Cx;
/* 144:144 */    float by = Ay - Cy;
/* 145:145 */    float cx = Bx - Ax;
/* 146:146 */    float cy = By - Ay;
/* 147:147 */    float apx = Px - Ax;
/* 148:148 */    float apy = Py - Ay;
/* 149:149 */    float bpx = Px - Bx;
/* 150:150 */    float bpy = Py - By;
/* 151:151 */    float cpx = Px - Cx;
/* 152:152 */    float cpy = Py - Cy;
/* 153:    */    
/* 154:154 */    float aCROSSbp = ax * bpy - ay * bpx;
/* 155:155 */    float cCROSSap = cx * apy - cy * apx;
/* 156:156 */    float bCROSScp = bx * cpy - by * cpx;
/* 157:    */    
/* 158:158 */    return (aCROSSbp >= 0.0F) && (bCROSScp >= 0.0F) && (cCROSSap >= 0.0F);
/* 159:    */  }
/* 160:    */  
/* 176:    */  private boolean snip(PointList contour, int u, int v, int w, int n, int[] V)
/* 177:    */  {
/* 178:178 */    float Ax = contour.get(V[u]).getX();
/* 179:179 */    float Ay = contour.get(V[u]).getY();
/* 180:    */    
/* 181:181 */    float Bx = contour.get(V[v]).getX();
/* 182:182 */    float By = contour.get(V[v]).getY();
/* 183:    */    
/* 184:184 */    float Cx = contour.get(V[w]).getX();
/* 185:185 */    float Cy = contour.get(V[w]).getY();
/* 186:    */    
/* 187:187 */    if (1.0E-010F > (Bx - Ax) * (Cy - Ay) - (By - Ay) * (Cx - Ax)) {
/* 188:188 */      return false;
/* 189:    */    }
/* 190:    */    
/* 191:191 */    for (int p = 0; p < n; p++) {
/* 192:192 */      if ((p != u) && (p != v) && (p != w))
/* 193:    */      {
/* 196:196 */        float Px = contour.get(V[p]).getX();
/* 197:197 */        float Py = contour.get(V[p]).getY();
/* 198:    */        
/* 199:199 */        if (insideTriangle(Ax, Ay, Bx, By, Cx, Cy, Px, Py)) {
/* 200:200 */          return false;
/* 201:    */        }
/* 202:    */      }
/* 203:    */    }
/* 204:204 */    return true;
/* 205:    */  }
/* 206:    */  
/* 214:    */  private boolean process(PointList contour, PointList result)
/* 215:    */  {
/* 216:216 */    result.clear();
/* 217:    */    
/* 220:220 */    int n = contour.size();
/* 221:221 */    if (n < 3) {
/* 222:222 */      return false;
/* 223:    */    }
/* 224:224 */    int[] V = new int[n];
/* 225:    */    
/* 228:228 */    if (0.0F < area(contour)) {
/* 229:229 */      for (int v = 0; v < n; v++)
/* 230:230 */        V[v] = v;
/* 231:    */    } else {
/* 232:232 */      for (int v = 0; v < n; v++) {
/* 233:233 */        V[v] = (n - 1 - v);
/* 234:    */      }
/* 235:    */    }
/* 236:236 */    int nv = n;
/* 237:    */    
/* 239:239 */    int count = 2 * nv;
/* 240:    */    
/* 241:241 */    int m = 0; for (int v = nv - 1; nv > 2;)
/* 242:    */    {
/* 243:243 */      if (0 >= count--)
/* 244:    */      {
/* 245:245 */        return false;
/* 246:    */      }
/* 247:    */      
/* 249:249 */      int u = v;
/* 250:250 */      if (nv <= u)
/* 251:251 */        u = 0;
/* 252:252 */      v = u + 1;
/* 253:253 */      if (nv <= v)
/* 254:254 */        v = 0;
/* 255:255 */      int w = v + 1;
/* 256:256 */      if (nv <= w) {
/* 257:257 */        w = 0;
/* 258:    */      }
/* 259:259 */      if (snip(contour, u, v, w, nv, V))
/* 260:    */      {
/* 263:263 */        int a = V[u];
/* 264:264 */        int b = V[v];
/* 265:265 */        int c = V[w];
/* 266:    */        
/* 268:268 */        result.add(contour.get(a));
/* 269:269 */        result.add(contour.get(b));
/* 270:270 */        result.add(contour.get(c));
/* 271:    */        
/* 272:272 */        m++;
/* 273:    */        
/* 275:275 */        int s = v; for (int t = v + 1; t < nv; t++) {
/* 276:276 */          V[s] = V[t];s++;
/* 277:    */        }
/* 278:    */        
/* 279:278 */        nv--;
/* 280:    */        
/* 282:281 */        count = 2 * nv;
/* 283:    */      }
/* 284:    */    }
/* 285:    */    
/* 286:285 */    return true;
/* 287:    */  }
/* 288:    */  
/* 291:    */  public void startHole() {}
/* 292:    */  
/* 295:    */  private class Point
/* 296:    */  {
/* 297:    */    private float x;
/* 298:    */    
/* 300:    */    private float y;
/* 301:    */    
/* 303:    */    private float[] array;
/* 304:    */    
/* 307:    */    public Point(float x, float y)
/* 308:    */    {
/* 309:308 */      this.x = x;
/* 310:309 */      this.y = y;
/* 311:310 */      this.array = new float[] { x, y };
/* 312:    */    }
/* 313:    */    
/* 318:    */    public float getX()
/* 319:    */    {
/* 320:319 */      return this.x;
/* 321:    */    }
/* 322:    */    
/* 327:    */    public float getY()
/* 328:    */    {
/* 329:328 */      return this.y;
/* 330:    */    }
/* 331:    */    
/* 336:    */    public float[] toArray()
/* 337:    */    {
/* 338:337 */      return this.array;
/* 339:    */    }
/* 340:    */    
/* 343:    */    public int hashCode()
/* 344:    */    {
/* 345:344 */      return (int)(this.x * this.y * 31.0F);
/* 346:    */    }
/* 347:    */    
/* 350:    */    public boolean equals(Object other)
/* 351:    */    {
/* 352:351 */      if ((other instanceof Point)) {
/* 353:352 */        Point p = (Point)other;
/* 354:353 */        return (p.x == this.x) && (p.y == this.y);
/* 355:    */      }
/* 356:    */      
/* 357:356 */      return false;
/* 358:    */    }
/* 359:    */  }
/* 360:    */  
/* 366:    */  private class PointList
/* 367:    */  {
/* 368:367 */    private ArrayList points = new ArrayList();
/* 369:    */    
/* 375:    */    public PointList() {}
/* 376:    */    
/* 381:    */    public boolean contains(BasicTriangulator.Point p)
/* 382:    */    {
/* 383:382 */      return this.points.contains(p);
/* 384:    */    }
/* 385:    */    
/* 390:    */    public void add(BasicTriangulator.Point point)
/* 391:    */    {
/* 392:391 */      this.points.add(point);
/* 393:    */    }
/* 394:    */    
/* 399:    */    public void remove(BasicTriangulator.Point point)
/* 400:    */    {
/* 401:400 */      this.points.remove(point);
/* 402:    */    }
/* 403:    */    
/* 408:    */    public int size()
/* 409:    */    {
/* 410:409 */      return this.points.size();
/* 411:    */    }
/* 412:    */    
/* 418:    */    public BasicTriangulator.Point get(int i)
/* 419:    */    {
/* 420:419 */      return (BasicTriangulator.Point)this.points.get(i);
/* 421:    */    }
/* 422:    */    
/* 425:    */    public void clear()
/* 426:    */    {
/* 427:426 */      this.points.clear();
/* 428:    */    }
/* 429:    */  }
/* 430:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.geom.BasicTriangulator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
/*   1:    */package org.newdawn.slick.geom;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */
/*   5:    */public class GeomUtil {
/*   6:    */  public float EPSILON;
/*   7:    */  public float EDGE_SCALE;
/*   8:    */  public int MAX_POINTS;
/*   9:    */  public GeomUtilListener listener;
/*  10:    */  
/*  11:    */  public GeomUtil() {
/*  12: 12 */    this.EPSILON = 1.0E-004F;
/*  13:    */    
/*  14: 14 */    this.EDGE_SCALE = 1.0F;
/*  15:    */    
/*  16: 16 */    this.MAX_POINTS = 10000;
/*  17:    */  }
/*  18:    */  
/*  27:    */  public Shape[] subtract(Shape target, Shape missing)
/*  28:    */  {
/*  29: 29 */    target = target.transform(new Transform());
/*  30: 30 */    missing = missing.transform(new Transform());
/*  31:    */    
/*  32: 32 */    int count = 0;
/*  33: 33 */    for (int i = 0; i < target.getPointCount(); i++) {
/*  34: 34 */      if (missing.contains(target.getPoint(i)[0], target.getPoint(i)[1])) {
/*  35: 35 */        count++;
/*  36:    */      }
/*  37:    */    }
/*  38:    */    
/*  39: 39 */    if (count == target.getPointCount()) {
/*  40: 40 */      return new Shape[0];
/*  41:    */    }
/*  42:    */    
/*  43: 43 */    if (!target.intersects(missing)) {
/*  44: 44 */      return new Shape[] { target };
/*  45:    */    }
/*  46:    */    
/*  47: 47 */    int found = 0;
/*  48: 48 */    for (int i = 0; i < missing.getPointCount(); i++) {
/*  49: 49 */      if ((target.contains(missing.getPoint(i)[0], missing.getPoint(i)[1])) && 
/*  50: 50 */        (!onPath(target, missing.getPoint(i)[0], missing.getPoint(i)[1]))) {
/*  51: 51 */        found++;
/*  52:    */      }
/*  53:    */    }
/*  54:    */    
/*  55: 55 */    for (int i = 0; i < target.getPointCount(); i++) {
/*  56: 56 */      if ((missing.contains(target.getPoint(i)[0], target.getPoint(i)[1])) && 
/*  57: 57 */        (!onPath(missing, target.getPoint(i)[0], target.getPoint(i)[1])))
/*  58:    */      {
/*  59: 59 */        found++;
/*  60:    */      }
/*  61:    */    }
/*  62:    */    
/*  64: 64 */    if (found < 1) {
/*  65: 65 */      return new Shape[] { target };
/*  66:    */    }
/*  67:    */    
/*  68: 68 */    return combine(target, missing, true);
/*  69:    */  }
/*  70:    */  
/*  78:    */  private boolean onPath(Shape path, float x, float y)
/*  79:    */  {
/*  80: 80 */    for (int i = 0; i < path.getPointCount() + 1; i++) {
/*  81: 81 */      int n = rationalPoint(path, i + 1);
/*  82: 82 */      Line line = getLine(path, rationalPoint(path, i), n);
/*  83: 83 */      if (line.distance(new Vector2f(x, y)) < this.EPSILON * 100.0F) {
/*  84: 84 */        return true;
/*  85:    */      }
/*  86:    */    }
/*  87:    */    
/*  88: 88 */    return false;
/*  89:    */  }
/*  90:    */  
/*  95:    */  public void setListener(GeomUtilListener listener)
/*  96:    */  {
/*  97: 97 */    this.listener = listener;
/*  98:    */  }
/*  99:    */  
/* 107:    */  public Shape[] union(Shape target, Shape other)
/* 108:    */  {
/* 109:109 */    target = target.transform(new Transform());
/* 110:110 */    other = other.transform(new Transform());
/* 111:    */    
/* 112:112 */    if (!target.intersects(other)) {
/* 113:113 */      return new Shape[] { target, other };
/* 114:    */    }
/* 115:    */    
/* 118:118 */    boolean touches = false;
/* 119:119 */    int buttCount = 0;
/* 120:120 */    for (int i = 0; i < target.getPointCount(); i++) {
/* 121:121 */      if ((other.contains(target.getPoint(i)[0], target.getPoint(i)[1])) && 
/* 122:122 */        (!other.hasVertex(target.getPoint(i)[0], target.getPoint(i)[1]))) {
/* 123:123 */        touches = true;
/* 124:124 */        break;
/* 125:    */      }
/* 126:    */      
/* 127:127 */      if (other.hasVertex(target.getPoint(i)[0], target.getPoint(i)[1])) {
/* 128:128 */        buttCount++;
/* 129:    */      }
/* 130:    */    }
/* 131:131 */    for (int i = 0; i < other.getPointCount(); i++) {
/* 132:132 */      if ((target.contains(other.getPoint(i)[0], other.getPoint(i)[1])) && 
/* 133:133 */        (!target.hasVertex(other.getPoint(i)[0], other.getPoint(i)[1]))) {
/* 134:134 */        touches = true;
/* 135:135 */        break;
/* 136:    */      }
/* 137:    */    }
/* 138:    */    
/* 140:140 */    if ((!touches) && (buttCount < 2)) {
/* 141:141 */      return new Shape[] { target, other };
/* 142:    */    }
/* 143:    */    
/* 145:145 */    return combine(target, other, false);
/* 146:    */  }
/* 147:    */  
/* 155:    */  private Shape[] combine(Shape target, Shape other, boolean subtract)
/* 156:    */  {
/* 157:157 */    if (subtract) {
/* 158:158 */      ArrayList shapes = new ArrayList();
/* 159:159 */      ArrayList used = new ArrayList();
/* 160:    */      
/* 163:163 */      for (int i = 0; i < target.getPointCount(); i++) {
/* 164:164 */        float[] point = target.getPoint(i);
/* 165:165 */        if (other.contains(point[0], point[1])) {
/* 166:166 */          used.add(new Vector2f(point[0], point[1]));
/* 167:167 */          if (this.listener != null) {
/* 168:168 */            this.listener.pointExcluded(point[0], point[1]);
/* 169:    */          }
/* 170:    */        }
/* 171:    */      }
/* 172:    */      
/* 173:173 */      for (int i = 0; i < target.getPointCount(); i++) {
/* 174:174 */        float[] point = target.getPoint(i);
/* 175:175 */        Vector2f pt = new Vector2f(point[0], point[1]);
/* 176:    */        
/* 177:177 */        if (!used.contains(pt)) {
/* 178:178 */          Shape result = combineSingle(target, other, true, i);
/* 179:179 */          shapes.add(result);
/* 180:180 */          for (int j = 0; j < result.getPointCount(); j++) {
/* 181:181 */            float[] kpoint = result.getPoint(j);
/* 182:182 */            Vector2f kpt = new Vector2f(kpoint[0], kpoint[1]);
/* 183:183 */            used.add(kpt);
/* 184:    */          }
/* 185:    */        }
/* 186:    */      }
/* 187:    */      
/* 188:188 */      return (Shape[])shapes.toArray(new Shape[0]);
/* 189:    */    }
/* 190:190 */    for (int i = 0; i < target.getPointCount(); i++) {
/* 191:191 */      if ((!other.contains(target.getPoint(i)[0], target.getPoint(i)[1])) && 
/* 192:192 */        (!other.hasVertex(target.getPoint(i)[0], target.getPoint(i)[1]))) {
/* 193:193 */        Shape shape = combineSingle(target, other, false, i);
/* 194:194 */        return new Shape[] { shape };
/* 195:    */      }
/* 196:    */    }
/* 197:    */    
/* 199:199 */    return new Shape[] { other };
/* 200:    */  }
/* 201:    */  
/* 211:    */  private Shape combineSingle(Shape target, Shape missing, boolean subtract, int start)
/* 212:    */  {
/* 213:213 */    Shape current = target;
/* 214:214 */    Shape other = missing;
/* 215:215 */    int point = start;
/* 216:216 */    int dir = 1;
/* 217:    */    
/* 218:218 */    Polygon poly = new Polygon();
/* 219:219 */    boolean first = true;
/* 220:    */    
/* 221:221 */    int loop = 0;
/* 222:    */    
/* 224:224 */    float px = current.getPoint(point)[0];
/* 225:225 */    float py = current.getPoint(point)[1];
/* 226:    */    
/* 227:227 */    while ((!poly.hasVertex(px, py)) || (first) || (current != target)) {
/* 228:228 */      first = false;
/* 229:229 */      loop++;
/* 230:230 */      if (loop > this.MAX_POINTS) {
/* 231:    */        break;
/* 232:    */      }
/* 233:    */      
/* 235:235 */      poly.addPoint(px, py);
/* 236:236 */      if (this.listener != null) {
/* 237:237 */        this.listener.pointUsed(px, py);
/* 238:    */      }
/* 239:    */      
/* 243:243 */      Line line = getLine(current, px, py, rationalPoint(current, point + dir));
/* 244:244 */      HitResult hit = intersect(other, line);
/* 245:    */      
/* 246:246 */      if (hit != null) {
/* 247:247 */        Line hitLine = hit.line;
/* 248:248 */        Vector2f pt = hit.pt;
/* 249:249 */        px = pt.x;
/* 250:250 */        py = pt.y;
/* 251:    */        
/* 252:252 */        if (this.listener != null) {
/* 253:253 */          this.listener.pointIntersected(px, py);
/* 254:    */        }
/* 255:    */        
/* 256:256 */        if (other.hasVertex(px, py)) {
/* 257:257 */          point = other.indexOf(pt.x, pt.y);
/* 258:258 */          dir = 1;
/* 259:259 */          px = pt.x;
/* 260:260 */          py = pt.y;
/* 261:    */          
/* 262:262 */          Shape temp = current;
/* 263:263 */          current = other;
/* 264:264 */          other = temp;
/* 265:    */        }
/* 266:    */        else
/* 267:    */        {
/* 268:268 */          float dx = hitLine.getDX() / hitLine.length();
/* 269:269 */          float dy = hitLine.getDY() / hitLine.length();
/* 270:270 */          dx *= this.EDGE_SCALE;
/* 271:271 */          dy *= this.EDGE_SCALE;
/* 272:    */          
/* 273:273 */          if (current.contains(pt.x + dx, pt.y + dy))
/* 274:    */          {
/* 276:276 */            if (subtract) {
/* 277:277 */              if (current == missing) {
/* 278:278 */                point = hit.p2;
/* 279:279 */                dir = -1;
/* 280:    */              } else {
/* 281:281 */                point = hit.p1;
/* 282:282 */                dir = 1;
/* 283:    */              }
/* 284:    */            }
/* 285:285 */            else if (current == target) {
/* 286:286 */              point = hit.p2;
/* 287:287 */              dir = -1;
/* 288:    */            } else {
/* 289:289 */              point = hit.p2;
/* 290:290 */              dir = -1;
/* 291:    */            }
/* 292:    */            
/* 295:295 */            Shape temp = current;
/* 296:296 */            current = other;
/* 297:297 */            other = temp;
/* 298:298 */          } else if (current.contains(pt.x - dx, pt.y - dy)) {
/* 299:299 */            if (subtract) {
/* 300:300 */              if (current == target) {
/* 301:301 */                point = hit.p2;
/* 302:302 */                dir = -1;
/* 303:    */              } else {
/* 304:304 */                point = hit.p1;
/* 305:305 */                dir = 1;
/* 306:    */              }
/* 307:    */            }
/* 308:308 */            else if (current == missing) {
/* 309:309 */              point = hit.p1;
/* 310:310 */              dir = 1;
/* 311:    */            } else {
/* 312:312 */              point = hit.p1;
/* 313:313 */              dir = 1;
/* 314:    */            }
/* 315:    */            
/* 318:318 */            Shape temp = current;
/* 319:319 */            current = other;
/* 320:320 */            other = temp;
/* 321:    */          }
/* 322:    */          else {
/* 323:323 */            if (subtract) {
/* 324:    */              break;
/* 325:    */            }
/* 326:326 */            point = hit.p1;
/* 327:327 */            dir = 1;
/* 328:328 */            Shape temp = current;
/* 329:329 */            current = other;
/* 330:330 */            other = temp;
/* 331:    */            
/* 332:332 */            point = rationalPoint(current, point + dir);
/* 333:333 */            px = current.getPoint(point)[0];
/* 334:334 */            py = current.getPoint(point)[1];
/* 335:    */          }
/* 336:    */        }
/* 337:    */      }
/* 338:    */      else {
/* 339:339 */        point = rationalPoint(current, point + dir);
/* 340:340 */        px = current.getPoint(point)[0];
/* 341:341 */        py = current.getPoint(point)[1];
/* 342:    */      }
/* 343:    */    }
/* 344:    */    
/* 345:345 */    poly.addPoint(px, py);
/* 346:346 */    if (this.listener != null) {
/* 347:347 */      this.listener.pointUsed(px, py);
/* 348:    */    }
/* 349:    */    
/* 350:350 */    return poly;
/* 351:    */  }
/* 352:    */  
/* 359:    */  public HitResult intersect(Shape shape, Line line)
/* 360:    */  {
/* 361:361 */    float distance = 3.4028235E+38F;
/* 362:362 */    HitResult hit = null;
/* 363:    */    
/* 364:364 */    for (int i = 0; i < shape.getPointCount(); i++) {
/* 365:365 */      int next = rationalPoint(shape, i + 1);
/* 366:366 */      Line local = getLine(shape, i, next);
/* 367:    */      
/* 368:368 */      Vector2f pt = line.intersect(local, true);
/* 369:369 */      if (pt != null) {
/* 370:370 */        float newDis = pt.distance(line.getStart());
/* 371:371 */        if ((newDis < distance) && (newDis > this.EPSILON)) {
/* 372:372 */          hit = new HitResult();
/* 373:373 */          hit.pt = pt;
/* 374:374 */          hit.line = local;
/* 375:375 */          hit.p1 = i;
/* 376:376 */          hit.p2 = next;
/* 377:377 */          distance = newDis;
/* 378:    */        }
/* 379:    */      }
/* 380:    */    }
/* 381:    */    
/* 382:382 */    return hit;
/* 383:    */  }
/* 384:    */  
/* 391:    */  public static int rationalPoint(Shape shape, int p)
/* 392:    */  {
/* 393:393 */    while (p < 0) {
/* 394:394 */      p += shape.getPointCount();
/* 395:    */    }
/* 396:396 */    while (p >= shape.getPointCount()) {
/* 397:397 */      p -= shape.getPointCount();
/* 398:    */    }
/* 399:    */    
/* 400:400 */    return p;
/* 401:    */  }
/* 402:    */  
/* 410:    */  public Line getLine(Shape shape, int s, int e)
/* 411:    */  {
/* 412:412 */    float[] start = shape.getPoint(s);
/* 413:413 */    float[] end = shape.getPoint(e);
/* 414:    */    
/* 415:415 */    Line line = new Line(start[0], start[1], end[0], end[1]);
/* 416:416 */    return line;
/* 417:    */  }
/* 418:    */  
/* 427:    */  public Line getLine(Shape shape, float sx, float sy, int e)
/* 428:    */  {
/* 429:429 */    float[] end = shape.getPoint(e);
/* 430:    */    
/* 431:431 */    Line line = new Line(sx, sy, end[0], end[1]);
/* 432:432 */    return line;
/* 433:    */  }
/* 434:    */  
/* 435:    */  public class HitResult
/* 436:    */  {
/* 437:    */    public Line line;
/* 438:    */    public int p1;
/* 439:    */    public int p2;
/* 440:    */    public Vector2f pt;
/* 441:    */    
/* 442:    */    public HitResult() {}
/* 443:    */  }
/* 444:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.geom.GeomUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
/*   1:    */package org.newdawn.slick.geom;
/*   2:    */
/*   6:    */public class Line
/*   7:    */  extends Shape
/*   8:    */{
/*   9:    */  private Vector2f start;
/*  10:    */  
/*  12:    */  private Vector2f end;
/*  13:    */  
/*  15:    */  private Vector2f vec;
/*  16:    */  
/*  18:    */  private float lenSquared;
/*  19:    */  
/*  21: 21 */  private Vector2f loc = new Vector2f(0.0F, 0.0F);
/*  22:    */  
/*  23: 23 */  private Vector2f v = new Vector2f(0.0F, 0.0F);
/*  24:    */  
/*  25: 25 */  private Vector2f v2 = new Vector2f(0.0F, 0.0F);
/*  26:    */  
/*  27: 27 */  private Vector2f proj = new Vector2f(0.0F, 0.0F);
/*  28:    */  
/*  30: 30 */  private Vector2f closest = new Vector2f(0.0F, 0.0F);
/*  31:    */  
/*  32: 32 */  private Vector2f other = new Vector2f(0.0F, 0.0F);
/*  33:    */  
/*  35: 35 */  private boolean outerEdge = true;
/*  36:    */  
/*  37: 37 */  private boolean innerEdge = true;
/*  38:    */  
/*  50:    */  public Line(float x, float y, boolean inner, boolean outer)
/*  51:    */  {
/*  52: 52 */    this(0.0F, 0.0F, x, y);
/*  53:    */  }
/*  54:    */  
/*  62:    */  public Line(float x, float y)
/*  63:    */  {
/*  64: 64 */    this(x, y, true, true);
/*  65:    */  }
/*  66:    */  
/*  78:    */  public Line(float x1, float y1, float x2, float y2)
/*  79:    */  {
/*  80: 80 */    this(new Vector2f(x1, y1), new Vector2f(x2, y2));
/*  81:    */  }
/*  82:    */  
/*  96:    */  public Line(float x1, float y1, float dx, float dy, boolean dummy)
/*  97:    */  {
/*  98: 98 */    this(new Vector2f(x1, y1), new Vector2f(x1 + dx, y1 + dy));
/*  99:    */  }
/* 100:    */  
/* 110:    */  public Line(float[] start, float[] end)
/* 111:    */  {
/* 112:112 */    set(start, end);
/* 113:    */  }
/* 114:    */  
/* 124:    */  public Line(Vector2f start, Vector2f end)
/* 125:    */  {
/* 126:126 */    set(start, end);
/* 127:    */  }
/* 128:    */  
/* 136:    */  public void set(float[] start, float[] end)
/* 137:    */  {
/* 138:138 */    set(start[0], start[1], end[0], end[1]);
/* 139:    */  }
/* 140:    */  
/* 145:    */  public Vector2f getStart()
/* 146:    */  {
/* 147:147 */    return this.start;
/* 148:    */  }
/* 149:    */  
/* 154:    */  public Vector2f getEnd()
/* 155:    */  {
/* 156:156 */    return this.end;
/* 157:    */  }
/* 158:    */  
/* 163:    */  public float length()
/* 164:    */  {
/* 165:165 */    return this.vec.length();
/* 166:    */  }
/* 167:    */  
/* 172:    */  public float lengthSquared()
/* 173:    */  {
/* 174:174 */    return this.vec.lengthSquared();
/* 175:    */  }
/* 176:    */  
/* 184:    */  public void set(Vector2f start, Vector2f end)
/* 185:    */  {
/* 186:186 */    this.pointsDirty = true;
/* 187:187 */    if (this.start == null) {
/* 188:188 */      this.start = new Vector2f();
/* 189:    */    }
/* 190:190 */    this.start.set(start);
/* 191:    */    
/* 192:192 */    if (this.end == null) {
/* 193:193 */      this.end = new Vector2f();
/* 194:    */    }
/* 195:195 */    this.end.set(end);
/* 196:    */    
/* 197:197 */    this.vec = new Vector2f(end);
/* 198:198 */    this.vec.sub(start);
/* 199:    */    
/* 200:200 */    this.lenSquared = this.vec.lengthSquared();
/* 201:    */  }
/* 202:    */  
/* 214:    */  public void set(float sx, float sy, float ex, float ey)
/* 215:    */  {
/* 216:216 */    this.pointsDirty = true;
/* 217:217 */    this.start.set(sx, sy);
/* 218:218 */    this.end.set(ex, ey);
/* 219:219 */    float dx = ex - sx;
/* 220:220 */    float dy = ey - sy;
/* 221:221 */    this.vec.set(dx, dy);
/* 222:    */    
/* 223:223 */    this.lenSquared = (dx * dx + dy * dy);
/* 224:    */  }
/* 225:    */  
/* 230:    */  public float getDX()
/* 231:    */  {
/* 232:232 */    return this.end.getX() - this.start.getX();
/* 233:    */  }
/* 234:    */  
/* 239:    */  public float getDY()
/* 240:    */  {
/* 241:241 */    return this.end.getY() - this.start.getY();
/* 242:    */  }
/* 243:    */  
/* 246:    */  public float getX()
/* 247:    */  {
/* 248:248 */    return getX1();
/* 249:    */  }
/* 250:    */  
/* 253:    */  public float getY()
/* 254:    */  {
/* 255:255 */    return getY1();
/* 256:    */  }
/* 257:    */  
/* 262:    */  public float getX1()
/* 263:    */  {
/* 264:264 */    return this.start.getX();
/* 265:    */  }
/* 266:    */  
/* 271:    */  public float getY1()
/* 272:    */  {
/* 273:273 */    return this.start.getY();
/* 274:    */  }
/* 275:    */  
/* 280:    */  public float getX2()
/* 281:    */  {
/* 282:282 */    return this.end.getX();
/* 283:    */  }
/* 284:    */  
/* 289:    */  public float getY2()
/* 290:    */  {
/* 291:291 */    return this.end.getY();
/* 292:    */  }
/* 293:    */  
/* 300:    */  public float distance(Vector2f point)
/* 301:    */  {
/* 302:302 */    return (float)Math.sqrt(distanceSquared(point));
/* 303:    */  }
/* 304:    */  
/* 311:    */  public boolean on(Vector2f point)
/* 312:    */  {
/* 313:313 */    getClosestPoint(point, this.closest);
/* 314:    */    
/* 315:315 */    return point.equals(this.closest);
/* 316:    */  }
/* 317:    */  
/* 324:    */  public float distanceSquared(Vector2f point)
/* 325:    */  {
/* 326:326 */    getClosestPoint(point, this.closest);
/* 327:327 */    this.closest.sub(point);
/* 328:    */    
/* 329:329 */    float result = this.closest.lengthSquared();
/* 330:    */    
/* 331:331 */    return result;
/* 332:    */  }
/* 333:    */  
/* 341:    */  public void getClosestPoint(Vector2f point, Vector2f result)
/* 342:    */  {
/* 343:343 */    this.loc.set(point);
/* 344:344 */    this.loc.sub(this.start);
/* 345:    */    
/* 346:346 */    float projDistance = this.vec.dot(this.loc);
/* 347:    */    
/* 348:348 */    projDistance /= this.vec.lengthSquared();
/* 349:    */    
/* 350:350 */    if (projDistance < 0.0F) {
/* 351:351 */      result.set(this.start);
/* 352:352 */      return;
/* 353:    */    }
/* 354:354 */    if (projDistance > 1.0F) {
/* 355:355 */      result.set(this.end);
/* 356:356 */      return;
/* 357:    */    }
/* 358:    */    
/* 359:359 */    result.x = (this.start.getX() + projDistance * this.vec.getX());
/* 360:360 */    result.y = (this.start.getY() + projDistance * this.vec.getY());
/* 361:    */  }
/* 362:    */  
/* 365:    */  public String toString()
/* 366:    */  {
/* 367:367 */    return "[Line " + this.start + "," + this.end + "]";
/* 368:    */  }
/* 369:    */  
/* 376:    */  public Vector2f intersect(Line other)
/* 377:    */  {
/* 378:378 */    return intersect(other, false);
/* 379:    */  }
/* 380:    */  
/* 389:    */  public Vector2f intersect(Line other, boolean limit)
/* 390:    */  {
/* 391:391 */    Vector2f temp = new Vector2f();
/* 392:    */    
/* 393:393 */    if (!intersect(other, limit, temp)) {
/* 394:394 */      return null;
/* 395:    */    }
/* 396:    */    
/* 397:397 */    return temp;
/* 398:    */  }
/* 399:    */  
/* 410:    */  public boolean intersect(Line other, boolean limit, Vector2f result)
/* 411:    */  {
/* 412:412 */    float dx1 = this.end.getX() - this.start.getX();
/* 413:413 */    float dx2 = other.end.getX() - other.start.getX();
/* 414:414 */    float dy1 = this.end.getY() - this.start.getY();
/* 415:415 */    float dy2 = other.end.getY() - other.start.getY();
/* 416:416 */    float denom = dy2 * dx1 - dx2 * dy1;
/* 417:    */    
/* 418:418 */    if (denom == 0.0F) {
/* 419:419 */      return false;
/* 420:    */    }
/* 421:    */    
/* 422:422 */    float ua = dx2 * (this.start.getY() - other.start.getY()) - dy2 * (this.start.getX() - other.start.getX());
/* 423:    */    
/* 424:424 */    ua /= denom;
/* 425:425 */    float ub = dx1 * (this.start.getY() - other.start.getY()) - dy1 * (this.start.getX() - other.start.getX());
/* 426:    */    
/* 427:427 */    ub /= denom;
/* 428:    */    
/* 429:429 */    if ((limit) && ((ua < 0.0F) || (ua > 1.0F) || (ub < 0.0F) || (ub > 1.0F))) {
/* 430:430 */      return false;
/* 431:    */    }
/* 432:    */    
/* 433:433 */    float u = ua;
/* 434:    */    
/* 435:435 */    float ix = this.start.getX() + u * (this.end.getX() - this.start.getX());
/* 436:436 */    float iy = this.start.getY() + u * (this.end.getY() - this.start.getY());
/* 437:    */    
/* 438:438 */    result.set(ix, iy);
/* 439:439 */    return true;
/* 440:    */  }
/* 441:    */  
/* 444:    */  protected void createPoints()
/* 445:    */  {
/* 446:446 */    this.points = new float[4];
/* 447:447 */    this.points[0] = getX1();
/* 448:448 */    this.points[1] = getY1();
/* 449:449 */    this.points[2] = getX2();
/* 450:450 */    this.points[3] = getY2();
/* 451:    */  }
/* 452:    */  
/* 455:    */  public Shape transform(Transform transform)
/* 456:    */  {
/* 457:457 */    float[] temp = new float[4];
/* 458:458 */    createPoints();
/* 459:459 */    transform.transform(this.points, 0, temp, 0, 2);
/* 460:    */    
/* 461:461 */    return new Line(temp[0], temp[1], temp[2], temp[3]);
/* 462:    */  }
/* 463:    */  
/* 466:    */  public boolean closed()
/* 467:    */  {
/* 468:468 */    return false;
/* 469:    */  }
/* 470:    */  
/* 474:    */  public boolean intersects(Shape shape)
/* 475:    */  {
/* 476:476 */    if ((shape instanceof Circle))
/* 477:    */    {
/* 478:478 */      return shape.intersects(this);
/* 479:    */    }
/* 480:480 */    return super.intersects(shape);
/* 481:    */  }
/* 482:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.geom.Line
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
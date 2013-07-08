/*   1:    */package org.newdawn.slick.geom;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.lang.reflect.Array;
/*   5:    */import java.util.ArrayList;
/*   6:    */import java.util.List;
/*   7:    */
/*  44:    */public class MannTriangulator
/*  45:    */  implements Triangulator
/*  46:    */{
/*  47:    */  private static final double EPSILON = 1.E-005D;
/*  48:    */  protected PointBag contour;
/*  49:    */  protected PointBag holes;
/*  50:    */  private PointBag nextFreePointBag;
/*  51:    */  private Point nextFreePoint;
/*  52: 52 */  private List triangles = new ArrayList();
/*  53:    */  
/*  54:    */  public MannTriangulator()
/*  55:    */  {
/*  56: 56 */    this.contour = getPointBag();
/*  57:    */  }
/*  58:    */  
/*  61:    */  public void addPolyPoint(float x, float y)
/*  62:    */  {
/*  63: 63 */    addPoint(new Vector2f(x, y));
/*  64:    */  }
/*  65:    */  
/*  68:    */  public void reset()
/*  69:    */  {
/*  70: 70 */    while (this.holes != null) {
/*  71: 71 */      this.holes = freePointBag(this.holes);
/*  72:    */    }
/*  73:    */    
/*  74: 74 */    this.contour.clear();
/*  75: 75 */    this.holes = null;
/*  76:    */  }
/*  77:    */  
/*  80:    */  public void startHole()
/*  81:    */  {
/*  82: 82 */    PointBag newHole = getPointBag();
/*  83: 83 */    newHole.next = this.holes;
/*  84: 84 */    this.holes = newHole;
/*  85:    */  }
/*  86:    */  
/*  91:    */  private void addPoint(Vector2f pt)
/*  92:    */  {
/*  93: 93 */    if (this.holes == null) {
/*  94: 94 */      Point p = getPoint(pt);
/*  95: 95 */      this.contour.add(p);
/*  96:    */    } else {
/*  97: 97 */      Point p = getPoint(pt);
/*  98: 98 */      this.holes.add(p);
/*  99:    */    }
/* 100:    */  }
/* 101:    */  
/* 108:    */  private Vector2f[] triangulate(Vector2f[] result)
/* 109:    */  {
/* 110:110 */    this.contour.computeAngles();
/* 111:111 */    for (PointBag hole = this.holes; hole != null; hole = hole.next) {
/* 112:112 */      hole.computeAngles();
/* 113:    */    }
/* 114:    */    
/* 116:116 */    while (this.holes != null) {
/* 117:117 */      Point pHole = this.holes.first;
/* 118:    */      do {
/* 119:119 */        if (pHole.angle <= 0.0D) {
/* 120:120 */          Point pContour = this.contour.first;
/* 121:    */          do {
/* 122:122 */            if ((pHole.isInfront(pContour)) && (pContour.isInfront(pHole)))
/* 123:    */            {
/* 124:124 */              if (!this.contour.doesIntersectSegment(pHole.pt, pContour.pt))
/* 125:    */              {
/* 126:126 */                PointBag hole = this.holes;
/* 127:    */                
/* 128:128 */                while (!hole.doesIntersectSegment(pHole.pt, pContour.pt))
/* 129:    */                {
/* 132:132 */                  if ((hole = hole.next) == null)
/* 133:    */                  {
/* 134:134 */                    Point newPtContour = getPoint(pContour.pt);
/* 135:135 */                    pContour.insertAfter(newPtContour);
/* 136:    */                    
/* 137:137 */                    Point newPtHole = getPoint(pHole.pt);
/* 138:138 */                    pHole.insertBefore(newPtHole);
/* 139:    */                    
/* 140:140 */                    pContour.next = pHole;
/* 141:141 */                    pHole.prev = pContour;
/* 142:    */                    
/* 143:143 */                    newPtHole.next = newPtContour;
/* 144:144 */                    newPtContour.prev = newPtHole;
/* 145:    */                    
/* 146:146 */                    pContour.computeAngle();
/* 147:147 */                    pHole.computeAngle();
/* 148:148 */                    newPtContour.computeAngle();
/* 149:149 */                    newPtHole.computeAngle();
/* 150:    */                    
/* 152:152 */                    this.holes.first = null;
/* 153:    */                    break label247;
/* 154:    */                  } }
/* 155:    */              } }
/* 156:156 */          } while ((pContour = pContour.next) != this.contour.first);
/* 157:    */        }
/* 158:158 */      } while ((pHole = pHole.next) != this.holes.first);
/* 159:    */      
/* 160:    */      label247:
/* 161:161 */      this.holes = freePointBag(this.holes);
/* 162:    */    }
/* 163:    */    
/* 165:165 */    int numTriangles = this.contour.countPoints() - 2;
/* 166:166 */    int neededSpace = numTriangles * 3 + 1;
/* 167:167 */    if (result.length < neededSpace) {
/* 168:168 */      result = (Vector2f[])Array.newInstance(result.getClass().getComponentType(), neededSpace);
/* 169:    */    }
/* 170:    */    
/* 173:173 */    int idx = 0;
/* 174:    */    for (;;) {
/* 175:175 */      Point pContour = this.contour.first;
/* 176:    */      
/* 177:177 */      if (pContour == null) {
/* 178:    */        break;
/* 179:    */      }
/* 180:    */      
/* 181:181 */      if (pContour.next == pContour.prev) {
/* 182:    */        break;
/* 183:    */      }
/* 184:    */      do
/* 185:    */      {
/* 186:186 */        if (pContour.angle > 0.0D) {
/* 187:187 */          Point prev = pContour.prev;
/* 188:188 */          Point next = pContour.next;
/* 189:    */          
/* 190:190 */          if (((next.next == prev) || ((prev.isInfront(next)) && (next.isInfront(prev)))) && 
/* 191:191 */            (!this.contour.doesIntersectSegment(prev.pt, next.pt))) {
/* 192:192 */            result[(idx++)] = pContour.pt;
/* 193:193 */            result[(idx++)] = next.pt;
/* 194:194 */            result[(idx++)] = prev.pt;
/* 195:195 */            break;
/* 196:    */          }
/* 197:    */          
/* 198:    */        }
/* 199:199 */      } while ((pContour = pContour.next) != this.contour.first);
/* 200:    */      
/* 202:202 */      Point prev = pContour.prev;
/* 203:203 */      Point next = pContour.next;
/* 204:    */      
/* 205:205 */      this.contour.first = prev;
/* 206:206 */      pContour.unlink();
/* 207:207 */      freePoint(pContour);
/* 208:    */      
/* 209:209 */      next.computeAngle();
/* 210:210 */      prev.computeAngle();
/* 211:    */    }
/* 212:    */    
/* 214:214 */    result[idx] = null;
/* 215:    */    
/* 217:217 */    this.contour.clear();
/* 218:    */    
/* 220:220 */    return result;
/* 221:    */  }
/* 222:    */  
/* 227:    */  private PointBag getPointBag()
/* 228:    */  {
/* 229:229 */    PointBag pb = this.nextFreePointBag;
/* 230:230 */    if (pb != null) {
/* 231:231 */      this.nextFreePointBag = pb.next;
/* 232:232 */      pb.next = null;
/* 233:233 */      return pb;
/* 234:    */    }
/* 235:235 */    return new PointBag();
/* 236:    */  }
/* 237:    */  
/* 243:    */  private PointBag freePointBag(PointBag pb)
/* 244:    */  {
/* 245:245 */    PointBag next = pb.next;
/* 246:246 */    pb.clear();
/* 247:247 */    pb.next = this.nextFreePointBag;
/* 248:248 */    this.nextFreePointBag = pb;
/* 249:249 */    return next;
/* 250:    */  }
/* 251:    */  
/* 257:    */  private Point getPoint(Vector2f pt)
/* 258:    */  {
/* 259:259 */    Point p = this.nextFreePoint;
/* 260:260 */    if (p != null) {
/* 261:261 */      this.nextFreePoint = p.next;
/* 262:    */      
/* 263:263 */      p.next = null;
/* 264:264 */      p.prev = null;
/* 265:265 */      p.pt = pt;
/* 266:266 */      return p;
/* 267:    */    }
/* 268:268 */    return new Point(pt);
/* 269:    */  }
/* 270:    */  
/* 275:    */  private void freePoint(Point p)
/* 276:    */  {
/* 277:277 */    p.next = this.nextFreePoint;
/* 278:278 */    this.nextFreePoint = p;
/* 279:    */  }
/* 280:    */  
/* 285:    */  private void freePoints(Point head)
/* 286:    */  {
/* 287:287 */    head.prev.next = this.nextFreePoint;
/* 288:288 */    head.prev = null;
/* 289:289 */    this.nextFreePoint = head;
/* 290:    */  }
/* 291:    */  
/* 294:    */  private static class Point
/* 295:    */    implements Serializable
/* 296:    */  {
/* 297:    */    protected Vector2f pt;
/* 298:    */    
/* 300:    */    protected Point prev;
/* 301:    */    
/* 303:    */    protected Point next;
/* 304:    */    
/* 306:    */    protected double nx;
/* 307:    */    
/* 309:    */    protected double ny;
/* 310:    */    
/* 312:    */    protected double angle;
/* 313:    */    
/* 314:    */    protected double dist;
/* 315:    */    
/* 317:    */    public Point(Vector2f pt)
/* 318:    */    {
/* 319:319 */      this.pt = pt;
/* 320:    */    }
/* 321:    */    
/* 324:    */    public void unlink()
/* 325:    */    {
/* 326:326 */      this.prev.next = this.next;
/* 327:327 */      this.next.prev = this.prev;
/* 328:328 */      this.next = null;
/* 329:329 */      this.prev = null;
/* 330:    */    }
/* 331:    */    
/* 336:    */    public void insertBefore(Point p)
/* 337:    */    {
/* 338:338 */      this.prev.next = p;
/* 339:339 */      p.prev = this.prev;
/* 340:340 */      p.next = this;
/* 341:341 */      this.prev = p;
/* 342:    */    }
/* 343:    */    
/* 348:    */    public void insertAfter(Point p)
/* 349:    */    {
/* 350:350 */      this.next.prev = p;
/* 351:351 */      p.prev = this;
/* 352:352 */      p.next = this.next;
/* 353:353 */      this.next = p;
/* 354:    */    }
/* 355:    */    
/* 362:    */    private double hypot(double x, double y)
/* 363:    */    {
/* 364:364 */      return Math.sqrt(x * x + y * y);
/* 365:    */    }
/* 366:    */    
/* 369:    */    public void computeAngle()
/* 370:    */    {
/* 371:371 */      if (this.prev.pt.equals(this.pt)) {
/* 372:372 */        this.pt.x += 0.01F;
/* 373:    */      }
/* 374:374 */      double dx1 = this.pt.x - this.prev.pt.x;
/* 375:375 */      double dy1 = this.pt.y - this.prev.pt.y;
/* 376:376 */      double len1 = hypot(dx1, dy1);
/* 377:377 */      dx1 /= len1;
/* 378:378 */      dy1 /= len1;
/* 379:    */      
/* 380:380 */      if (this.next.pt.equals(this.pt)) {
/* 381:381 */        this.pt.y += 0.01F;
/* 382:    */      }
/* 383:383 */      double dx2 = this.next.pt.x - this.pt.x;
/* 384:384 */      double dy2 = this.next.pt.y - this.pt.y;
/* 385:385 */      double len2 = hypot(dx2, dy2);
/* 386:386 */      dx2 /= len2;
/* 387:387 */      dy2 /= len2;
/* 388:    */      
/* 389:389 */      double nx1 = -dy1;
/* 390:390 */      double ny1 = dx1;
/* 391:    */      
/* 392:392 */      this.nx = ((nx1 - dy2) * 0.5D);
/* 393:393 */      this.ny = ((ny1 + dx2) * 0.5D);
/* 394:    */      
/* 395:395 */      if (this.nx * this.nx + this.ny * this.ny < 1.E-005D) {
/* 396:396 */        this.nx = dx1;
/* 397:397 */        this.ny = dy2;
/* 398:398 */        this.angle = 1.0D;
/* 399:399 */        if (dx1 * dx2 + dy1 * dy2 > 0.0D) {
/* 400:400 */          this.nx = (-dx1);
/* 401:401 */          this.ny = (-dy1);
/* 402:    */        }
/* 403:    */      } else {
/* 404:404 */        this.angle = (this.nx * dx2 + this.ny * dy2);
/* 405:    */      }
/* 406:    */    }
/* 407:    */    
/* 413:    */    public double getAngle(Point p)
/* 414:    */    {
/* 415:415 */      double dx = p.pt.x - this.pt.x;
/* 416:416 */      double dy = p.pt.y - this.pt.y;
/* 417:417 */      double dlen = hypot(dx, dy);
/* 418:    */      
/* 419:419 */      return (this.nx * dx + this.ny * dy) / dlen;
/* 420:    */    }
/* 421:    */    
/* 426:    */    public boolean isConcave()
/* 427:    */    {
/* 428:428 */      return this.angle < 0.0D;
/* 429:    */    }
/* 430:    */    
/* 439:    */    public boolean isInfront(double dx, double dy)
/* 440:    */    {
/* 441:441 */      boolean sidePrev = (this.prev.pt.y - this.pt.y) * dx + (this.pt.x - this.prev.pt.x) * dy >= 0.0D;
/* 442:    */      
/* 443:443 */      boolean sideNext = (this.pt.y - this.next.pt.y) * dx + (this.next.pt.x - this.pt.x) * dy >= 0.0D;
/* 444:    */      
/* 446:446 */      return this.angle < 0.0D ? sidePrev | sideNext : sidePrev & sideNext;
/* 447:    */    }
/* 448:    */    
/* 454:    */    public boolean isInfront(Point p)
/* 455:    */    {
/* 456:456 */      return isInfront(p.pt.x - this.pt.x, p.pt.y - this.pt.y);
/* 457:    */    }
/* 458:    */  }
/* 459:    */  
/* 462:    */  protected class PointBag
/* 463:    */    implements Serializable
/* 464:    */  {
/* 465:    */    protected MannTriangulator.Point first;
/* 466:    */    
/* 467:    */    protected PointBag next;
/* 468:    */    
/* 470:    */    protected PointBag() {}
/* 471:    */    
/* 473:    */    public void clear()
/* 474:    */    {
/* 475:475 */      if (this.first != null) {
/* 476:476 */        MannTriangulator.this.freePoints(this.first);
/* 477:477 */        this.first = null;
/* 478:    */      }
/* 479:    */    }
/* 480:    */    
/* 485:    */    public void add(MannTriangulator.Point p)
/* 486:    */    {
/* 487:487 */      if (this.first != null) {
/* 488:488 */        this.first.insertBefore(p);
/* 489:    */      } else {
/* 490:490 */        this.first = p;
/* 491:491 */        p.next = p;
/* 492:492 */        p.prev = p;
/* 493:    */      }
/* 494:    */    }
/* 495:    */    
/* 498:    */    public void computeAngles()
/* 499:    */    {
/* 500:500 */      if (this.first == null) {
/* 501:501 */        return;
/* 502:    */      }
/* 503:    */      
/* 504:504 */      MannTriangulator.Point p = this.first;
/* 505:    */      do {
/* 506:506 */        p.computeAngle();
/* 507:507 */      } while ((p = p.next) != this.first);
/* 508:    */    }
/* 509:    */    
/* 517:    */    public boolean doesIntersectSegment(Vector2f v1, Vector2f v2)
/* 518:    */    {
/* 519:519 */      double dxA = v2.x - v1.x;
/* 520:520 */      double dyA = v2.y - v1.y;
/* 521:    */      
/* 522:522 */      MannTriangulator.Point p = this.first;
/* 523:523 */      for (;;) { MannTriangulator.Point n = p.next;
/* 524:524 */        if ((p.pt != v1) && (n.pt != v1) && (p.pt != v2) && (n.pt != v2)) {
/* 525:525 */          double dxB = n.pt.x - p.pt.x;
/* 526:526 */          double dyB = n.pt.y - p.pt.y;
/* 527:527 */          double d = dxA * dyB - dyA * dxB;
/* 528:    */          
/* 529:529 */          if (Math.abs(d) > 1.E-005D) {
/* 530:530 */            double tmp1 = p.pt.x - v1.x;
/* 531:531 */            double tmp2 = p.pt.y - v1.y;
/* 532:532 */            double tA = (dyB * tmp1 - dxB * tmp2) / d;
/* 533:533 */            double tB = (dyA * tmp1 - dxA * tmp2) / d;
/* 534:    */            
/* 535:535 */            if ((tA >= 0.0D) && (tA <= 1.0D) && (tB >= 0.0D) && (tB <= 1.0D)) {
/* 536:536 */              return true;
/* 537:    */            }
/* 538:    */          }
/* 539:    */        }
/* 540:    */        
/* 541:541 */        if (n == this.first) {
/* 542:542 */          return false;
/* 543:    */        }
/* 544:544 */        p = n;
/* 545:    */      }
/* 546:    */    }
/* 547:    */    
/* 552:    */    public int countPoints()
/* 553:    */    {
/* 554:554 */      if (this.first == null) {
/* 555:555 */        return 0;
/* 556:    */      }
/* 557:    */      
/* 558:558 */      int count = 0;
/* 559:559 */      MannTriangulator.Point p = this.first;
/* 560:    */      do {
/* 561:561 */        count++;
/* 562:562 */      } while ((p = p.next) != this.first);
/* 563:563 */      return count;
/* 564:    */    }
/* 565:    */    
/* 571:    */    public boolean contains(Vector2f point)
/* 572:    */    {
/* 573:573 */      if (this.first == null) {
/* 574:574 */        return false;
/* 575:    */      }
/* 576:    */      
/* 577:577 */      if (this.first.prev.pt.equals(point)) {
/* 578:578 */        return true;
/* 579:    */      }
/* 580:580 */      if (this.first.pt.equals(point)) {
/* 581:581 */        return true;
/* 582:    */      }
/* 583:583 */      return false;
/* 584:    */    }
/* 585:    */  }
/* 586:    */  
/* 587:    */  public boolean triangulate() {
/* 588:588 */    Vector2f[] temp = triangulate(new Vector2f[0]);
/* 589:    */    
/* 590:590 */    for (int i = 0; i < temp.length; i++) {
/* 591:591 */      if (temp[i] == null) {
/* 592:    */        break;
/* 593:    */      }
/* 594:594 */      this.triangles.add(temp[i]);
/* 595:    */    }
/* 596:    */    
/* 598:598 */    return true;
/* 599:    */  }
/* 600:    */  
/* 603:    */  public int getTriangleCount()
/* 604:    */  {
/* 605:605 */    return this.triangles.size() / 3;
/* 606:    */  }
/* 607:    */  
/* 610:    */  public float[] getTrianglePoint(int tri, int i)
/* 611:    */  {
/* 612:612 */    Vector2f pt = (Vector2f)this.triangles.get(tri * 3 + i);
/* 613:    */    
/* 614:614 */    return new float[] { pt.x, pt.y };
/* 615:    */  }
/* 616:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.geom.MannTriangulator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
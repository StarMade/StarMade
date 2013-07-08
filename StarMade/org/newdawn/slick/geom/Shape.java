/*   1:    */package org.newdawn.slick.geom;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */
/*  24:    */public abstract class Shape
/*  25:    */  implements Serializable
/*  26:    */{
/*  27:    */  protected float[] points;
/*  28:    */  protected float[] center;
/*  29:    */  protected float x;
/*  30:    */  protected float y;
/*  31:    */  protected float maxX;
/*  32:    */  protected float maxY;
/*  33:    */  protected float minX;
/*  34:    */  protected float minY;
/*  35:    */  protected float boundingCircleRadius;
/*  36:    */  protected boolean pointsDirty;
/*  37:    */  protected transient Triangulator tris;
/*  38:    */  protected boolean trianglesDirty;
/*  39:    */  
/*  40:    */  public Shape()
/*  41:    */  {
/*  42: 42 */    this.pointsDirty = true;
/*  43:    */  }
/*  44:    */  
/*  50:    */  public void setLocation(float x, float y)
/*  51:    */  {
/*  52: 52 */    setX(x);
/*  53: 53 */    setY(y);
/*  54:    */  }
/*  55:    */  
/*  61:    */  public abstract Shape transform(Transform paramTransform);
/*  62:    */  
/*  68:    */  protected abstract void createPoints();
/*  69:    */  
/*  75:    */  public float getX()
/*  76:    */  {
/*  77: 77 */    return this.x;
/*  78:    */  }
/*  79:    */  
/*  84:    */  public void setX(float x)
/*  85:    */  {
/*  86: 86 */    if (x != this.x) {
/*  87: 87 */      float dx = x - this.x;
/*  88: 88 */      this.x = x;
/*  89:    */      
/*  90: 90 */      if ((this.points == null) || (this.center == null)) {
/*  91: 91 */        checkPoints();
/*  92:    */      }
/*  93:    */      
/*  94: 94 */      for (int i = 0; i < this.points.length / 2; i++) {
/*  95: 95 */        this.points[(i * 2)] += dx;
/*  96:    */      }
/*  97: 97 */      this.center[0] += dx;
/*  98: 98 */      x += dx;
/*  99: 99 */      this.maxX += dx;
/* 100:100 */      this.minX += dx;
/* 101:101 */      this.trianglesDirty = true;
/* 102:    */    }
/* 103:    */  }
/* 104:    */  
/* 109:    */  public void setY(float y)
/* 110:    */  {
/* 111:111 */    if (y != this.y) {
/* 112:112 */      float dy = y - this.y;
/* 113:113 */      this.y = y;
/* 114:    */      
/* 115:115 */      if ((this.points == null) || (this.center == null)) {
/* 116:116 */        checkPoints();
/* 117:    */      }
/* 118:    */      
/* 119:119 */      for (int i = 0; i < this.points.length / 2; i++) {
/* 120:120 */        this.points[(i * 2 + 1)] += dy;
/* 121:    */      }
/* 122:122 */      this.center[1] += dy;
/* 123:123 */      y += dy;
/* 124:124 */      this.maxY += dy;
/* 125:125 */      this.minY += dy;
/* 126:126 */      this.trianglesDirty = true;
/* 127:    */    }
/* 128:    */  }
/* 129:    */  
/* 134:    */  public float getY()
/* 135:    */  {
/* 136:136 */    return this.y;
/* 137:    */  }
/* 138:    */  
/* 143:    */  public void setLocation(Vector2f loc)
/* 144:    */  {
/* 145:145 */    setX(loc.x);
/* 146:146 */    setY(loc.y);
/* 147:    */  }
/* 148:    */  
/* 153:    */  public float getCenterX()
/* 154:    */  {
/* 155:155 */    checkPoints();
/* 156:    */    
/* 157:157 */    return this.center[0];
/* 158:    */  }
/* 159:    */  
/* 164:    */  public void setCenterX(float centerX)
/* 165:    */  {
/* 166:166 */    if ((this.points == null) || (this.center == null)) {
/* 167:167 */      checkPoints();
/* 168:    */    }
/* 169:    */    
/* 170:170 */    float xDiff = centerX - getCenterX();
/* 171:171 */    setX(this.x + xDiff);
/* 172:    */  }
/* 173:    */  
/* 178:    */  public float getCenterY()
/* 179:    */  {
/* 180:180 */    checkPoints();
/* 181:    */    
/* 182:182 */    return this.center[1];
/* 183:    */  }
/* 184:    */  
/* 189:    */  public void setCenterY(float centerY)
/* 190:    */  {
/* 191:191 */    if ((this.points == null) || (this.center == null)) {
/* 192:192 */      checkPoints();
/* 193:    */    }
/* 194:    */    
/* 195:195 */    float yDiff = centerY - getCenterY();
/* 196:196 */    setY(this.y + yDiff);
/* 197:    */  }
/* 198:    */  
/* 203:    */  public float getMaxX()
/* 204:    */  {
/* 205:205 */    checkPoints();
/* 206:206 */    return this.maxX;
/* 207:    */  }
/* 208:    */  
/* 212:    */  public float getMaxY()
/* 213:    */  {
/* 214:214 */    checkPoints();
/* 215:215 */    return this.maxY;
/* 216:    */  }
/* 217:    */  
/* 222:    */  public float getMinX()
/* 223:    */  {
/* 224:224 */    checkPoints();
/* 225:225 */    return this.minX;
/* 226:    */  }
/* 227:    */  
/* 232:    */  public float getMinY()
/* 233:    */  {
/* 234:234 */    checkPoints();
/* 235:235 */    return this.minY;
/* 236:    */  }
/* 237:    */  
/* 242:    */  public float getBoundingCircleRadius()
/* 243:    */  {
/* 244:244 */    checkPoints();
/* 245:245 */    return this.boundingCircleRadius;
/* 246:    */  }
/* 247:    */  
/* 252:    */  public float[] getCenter()
/* 253:    */  {
/* 254:254 */    checkPoints();
/* 255:255 */    return this.center;
/* 256:    */  }
/* 257:    */  
/* 262:    */  public float[] getPoints()
/* 263:    */  {
/* 264:264 */    checkPoints();
/* 265:265 */    return this.points;
/* 266:    */  }
/* 267:    */  
/* 272:    */  public int getPointCount()
/* 273:    */  {
/* 274:274 */    checkPoints();
/* 275:275 */    return this.points.length / 2;
/* 276:    */  }
/* 277:    */  
/* 283:    */  public float[] getPoint(int index)
/* 284:    */  {
/* 285:285 */    checkPoints();
/* 286:    */    
/* 287:287 */    float[] result = new float[2];
/* 288:    */    
/* 289:289 */    result[0] = this.points[(index * 2)];
/* 290:290 */    result[1] = this.points[(index * 2 + 1)];
/* 291:    */    
/* 292:292 */    return result;
/* 293:    */  }
/* 294:    */  
/* 300:    */  public float[] getNormal(int index)
/* 301:    */  {
/* 302:302 */    float[] current = getPoint(index);
/* 303:303 */    float[] prev = getPoint(index - 1 < 0 ? getPointCount() - 1 : index - 1);
/* 304:304 */    float[] next = getPoint(index + 1 >= getPointCount() ? 0 : index + 1);
/* 305:    */    
/* 306:306 */    float[] t1 = getNormal(prev, current);
/* 307:307 */    float[] t2 = getNormal(current, next);
/* 308:    */    
/* 309:309 */    if ((index == 0) && (!closed())) {
/* 310:310 */      return t2;
/* 311:    */    }
/* 312:312 */    if ((index == getPointCount() - 1) && (!closed())) {
/* 313:313 */      return t1;
/* 314:    */    }
/* 315:    */    
/* 316:316 */    float tx = (t1[0] + t2[0]) / 2.0F;
/* 317:317 */    float ty = (t1[1] + t2[1]) / 2.0F;
/* 318:318 */    float len = (float)Math.sqrt(tx * tx + ty * ty);
/* 319:319 */    return new float[] { tx / len, ty / len };
/* 320:    */  }
/* 321:    */  
/* 329:    */  public boolean contains(Shape other)
/* 330:    */  {
/* 331:331 */    if (other.intersects(this)) {
/* 332:332 */      return false;
/* 333:    */    }
/* 334:    */    
/* 335:335 */    for (int i = 0; i < other.getPointCount(); i++) {
/* 336:336 */      float[] pt = other.getPoint(i);
/* 337:337 */      if (!contains(pt[0], pt[1])) {
/* 338:338 */        return false;
/* 339:    */      }
/* 340:    */    }
/* 341:    */    
/* 342:342 */    return true;
/* 343:    */  }
/* 344:    */  
/* 350:    */  private float[] getNormal(float[] start, float[] end)
/* 351:    */  {
/* 352:352 */    float dx = start[0] - end[0];
/* 353:353 */    float dy = start[1] - end[1];
/* 354:354 */    float len = (float)Math.sqrt(dx * dx + dy * dy);
/* 355:355 */    dx /= len;
/* 356:356 */    dy /= len;
/* 357:357 */    return new float[] { -dy, dx };
/* 358:    */  }
/* 359:    */  
/* 367:    */  public boolean includes(float x, float y)
/* 368:    */  {
/* 369:369 */    if (this.points.length == 0) {
/* 370:370 */      return false;
/* 371:    */    }
/* 372:    */    
/* 373:373 */    checkPoints();
/* 374:    */    
/* 375:375 */    Line testLine = new Line(0.0F, 0.0F, 0.0F, 0.0F);
/* 376:376 */    Vector2f pt = new Vector2f(x, y);
/* 377:    */    
/* 378:378 */    for (int i = 0; i < this.points.length; i += 2) {
/* 379:379 */      int n = i + 2;
/* 380:380 */      if (n >= this.points.length) {
/* 381:381 */        n = 0;
/* 382:    */      }
/* 383:383 */      testLine.set(this.points[i], this.points[(i + 1)], this.points[n], this.points[(n + 1)]);
/* 384:    */      
/* 385:385 */      if (testLine.on(pt)) {
/* 386:386 */        return true;
/* 387:    */      }
/* 388:    */    }
/* 389:    */    
/* 390:390 */    return false;
/* 391:    */  }
/* 392:    */  
/* 399:    */  public int indexOf(float x, float y)
/* 400:    */  {
/* 401:401 */    for (int i = 0; i < this.points.length; i += 2) {
/* 402:402 */      if ((this.points[i] == x) && (this.points[(i + 1)] == y)) {
/* 403:403 */        return i / 2;
/* 404:    */      }
/* 405:    */    }
/* 406:    */    
/* 407:407 */    return -1;
/* 408:    */  }
/* 409:    */  
/* 416:    */  public boolean contains(float x, float y)
/* 417:    */  {
/* 418:418 */    checkPoints();
/* 419:419 */    if (this.points.length == 0) {
/* 420:420 */      return false;
/* 421:    */    }
/* 422:    */    
/* 423:423 */    boolean result = false;
/* 424:    */    
/* 428:428 */    int npoints = this.points.length;
/* 429:    */    
/* 430:430 */    float xold = this.points[(npoints - 2)];
/* 431:431 */    float yold = this.points[(npoints - 1)];
/* 432:432 */    for (int i = 0; i < npoints; i += 2) {
/* 433:433 */      float xnew = this.points[i];
/* 434:434 */      float ynew = this.points[(i + 1)];
/* 435:435 */      float y2; float x1; float x2; float y1; float y2; if (xnew > xold) {
/* 436:436 */        float x1 = xold;
/* 437:437 */        float x2 = xnew;
/* 438:438 */        float y1 = yold;
/* 439:439 */        y2 = ynew;
/* 440:    */      }
/* 441:    */      else {
/* 442:442 */        x1 = xnew;
/* 443:443 */        x2 = xold;
/* 444:444 */        y1 = ynew;
/* 445:445 */        y2 = yold;
/* 446:    */      }
/* 447:447 */      if ((xnew < x ? 1 : 0) == (x <= xold ? 1 : 0)) if ((y - y1) * (x2 - x1) < (y2 - y1) * (x - x1))
/* 448:    */        {
/* 450:450 */          result = !result;
/* 451:    */        }
/* 452:452 */      xold = xnew;
/* 453:453 */      yold = ynew;
/* 454:    */    }
/* 455:    */    
/* 456:456 */    return result;
/* 457:    */  }
/* 458:    */  
/* 478:    */  public boolean intersects(Shape shape)
/* 479:    */  {
/* 480:480 */    checkPoints();
/* 481:    */    
/* 482:482 */    boolean result = false;
/* 483:483 */    float[] points = getPoints();
/* 484:484 */    float[] thatPoints = shape.getPoints();
/* 485:485 */    int length = points.length;
/* 486:486 */    int thatLength = thatPoints.length;
/* 487:    */    
/* 490:490 */    if (!closed()) {
/* 491:491 */      length -= 2;
/* 492:    */    }
/* 493:493 */    if (!shape.closed()) {
/* 494:494 */      thatLength -= 2;
/* 495:    */    }
/* 496:    */    
/* 505:505 */    for (int i = 0; i < length; i += 2) {
/* 506:506 */      int iNext = i + 2;
/* 507:507 */      if (iNext >= points.length) {
/* 508:508 */        iNext = 0;
/* 509:    */      }
/* 510:    */      
/* 511:511 */      for (int j = 0; j < thatLength; j += 2) {
/* 512:512 */        int jNext = j + 2;
/* 513:513 */        if (jNext >= thatPoints.length) {
/* 514:514 */          jNext = 0;
/* 515:    */        }
/* 516:    */        
/* 517:517 */        double unknownA = ((points[iNext] - points[i]) * (thatPoints[(j + 1)] - points[(i + 1)]) - (points[(iNext + 1)] - points[(i + 1)]) * (thatPoints[j] - points[i])) / ((points[(iNext + 1)] - points[(i + 1)]) * (thatPoints[jNext] - thatPoints[j]) - (points[iNext] - points[i]) * (thatPoints[(jNext + 1)] - thatPoints[(j + 1)]));
/* 518:    */        
/* 521:521 */        double unknownB = ((thatPoints[jNext] - thatPoints[j]) * (thatPoints[(j + 1)] - points[(i + 1)]) - (thatPoints[(jNext + 1)] - thatPoints[(j + 1)]) * (thatPoints[j] - points[i])) / ((points[(iNext + 1)] - points[(i + 1)]) * (thatPoints[jNext] - thatPoints[j]) - (points[iNext] - points[i]) * (thatPoints[(jNext + 1)] - thatPoints[(j + 1)]));
/* 522:    */        
/* 526:526 */        if ((unknownA >= 0.0D) && (unknownA <= 1.0D) && (unknownB >= 0.0D) && (unknownB <= 1.0D)) {
/* 527:527 */          result = true;
/* 528:528 */          break;
/* 529:    */        }
/* 530:    */      }
/* 531:531 */      if (result) {
/* 532:    */        break;
/* 533:    */      }
/* 534:    */    }
/* 535:    */    
/* 536:536 */    return result;
/* 537:    */  }
/* 538:    */  
/* 545:    */  public boolean hasVertex(float x, float y)
/* 546:    */  {
/* 547:547 */    if (this.points.length == 0) {
/* 548:548 */      return false;
/* 549:    */    }
/* 550:    */    
/* 551:551 */    checkPoints();
/* 552:    */    
/* 553:553 */    for (int i = 0; i < this.points.length; i += 2) {
/* 554:554 */      if ((this.points[i] == x) && (this.points[(i + 1)] == y)) {
/* 555:555 */        return true;
/* 556:    */      }
/* 557:    */    }
/* 558:    */    
/* 559:559 */    return false;
/* 560:    */  }
/* 561:    */  
/* 565:    */  protected void findCenter()
/* 566:    */  {
/* 567:567 */    this.center = new float[] { 0.0F, 0.0F };
/* 568:568 */    int length = this.points.length;
/* 569:569 */    for (int i = 0; i < length; i += 2) {
/* 570:570 */      this.center[0] += this.points[i];
/* 571:571 */      this.center[1] += this.points[(i + 1)];
/* 572:    */    }
/* 573:573 */    this.center[0] /= length / 2;
/* 574:574 */    this.center[1] /= length / 2;
/* 575:    */  }
/* 576:    */  
/* 580:    */  protected void calculateRadius()
/* 581:    */  {
/* 582:582 */    this.boundingCircleRadius = 0.0F;
/* 583:    */    
/* 584:584 */    for (int i = 0; i < this.points.length; i += 2) {
/* 585:585 */      float temp = (this.points[i] - this.center[0]) * (this.points[i] - this.center[0]) + (this.points[(i + 1)] - this.center[1]) * (this.points[(i + 1)] - this.center[1]);
/* 586:    */      
/* 587:587 */      this.boundingCircleRadius = (this.boundingCircleRadius > temp ? this.boundingCircleRadius : temp);
/* 588:    */    }
/* 589:589 */    this.boundingCircleRadius = ((float)Math.sqrt(this.boundingCircleRadius));
/* 590:    */  }
/* 591:    */  
/* 594:    */  protected void calculateTriangles()
/* 595:    */  {
/* 596:596 */    if ((!this.trianglesDirty) && (this.tris != null)) {
/* 597:597 */      return;
/* 598:    */    }
/* 599:599 */    if (this.points.length >= 6) {
/* 600:600 */      boolean clockwise = true;
/* 601:601 */      float area = 0.0F;
/* 602:602 */      for (int i = 0; i < this.points.length / 2 - 1; i++) {
/* 603:603 */        float x1 = this.points[(i * 2)];
/* 604:604 */        float y1 = this.points[(i * 2 + 1)];
/* 605:605 */        float x2 = this.points[(i * 2 + 2)];
/* 606:606 */        float y2 = this.points[(i * 2 + 3)];
/* 607:    */        
/* 608:608 */        area += x1 * y2 - y1 * x2;
/* 609:    */      }
/* 610:610 */      area /= 2.0F;
/* 611:611 */      clockwise = area > 0.0F;
/* 612:    */      
/* 613:613 */      this.tris = new NeatTriangulator();
/* 614:614 */      for (int i = 0; i < this.points.length; i += 2) {
/* 615:615 */        this.tris.addPolyPoint(this.points[i], this.points[(i + 1)]);
/* 616:    */      }
/* 617:617 */      this.tris.triangulate();
/* 618:    */    }
/* 619:    */    
/* 620:620 */    this.trianglesDirty = false;
/* 621:    */  }
/* 622:    */  
/* 625:    */  public void increaseTriangulation()
/* 626:    */  {
/* 627:627 */    checkPoints();
/* 628:628 */    calculateTriangles();
/* 629:    */    
/* 630:630 */    this.tris = new OverTriangulator(this.tris);
/* 631:    */  }
/* 632:    */  
/* 637:    */  public Triangulator getTriangles()
/* 638:    */  {
/* 639:639 */    checkPoints();
/* 640:640 */    calculateTriangles();
/* 641:641 */    return this.tris;
/* 642:    */  }
/* 643:    */  
/* 646:    */  protected final void checkPoints()
/* 647:    */  {
/* 648:648 */    if (this.pointsDirty) {
/* 649:649 */      createPoints();
/* 650:650 */      findCenter();
/* 651:651 */      calculateRadius();
/* 652:    */      
/* 653:653 */      if (this.points.length > 0) {
/* 654:654 */        this.maxX = this.points[0];
/* 655:655 */        this.maxY = this.points[1];
/* 656:656 */        this.minX = this.points[0];
/* 657:657 */        this.minY = this.points[1];
/* 658:658 */        for (int i = 0; i < this.points.length / 2; i++) {
/* 659:659 */          this.maxX = Math.max(this.points[(i * 2)], this.maxX);
/* 660:660 */          this.maxY = Math.max(this.points[(i * 2 + 1)], this.maxY);
/* 661:661 */          this.minX = Math.min(this.points[(i * 2)], this.minX);
/* 662:662 */          this.minY = Math.min(this.points[(i * 2 + 1)], this.minY);
/* 663:    */        }
/* 664:    */      }
/* 665:665 */      this.pointsDirty = false;
/* 666:666 */      this.trianglesDirty = true;
/* 667:    */    }
/* 668:    */  }
/* 669:    */  
/* 672:    */  public void preCache()
/* 673:    */  {
/* 674:674 */    checkPoints();
/* 675:675 */    getTriangles();
/* 676:    */  }
/* 677:    */  
/* 682:    */  public boolean closed()
/* 683:    */  {
/* 684:684 */    return true;
/* 685:    */  }
/* 686:    */  
/* 691:    */  public Shape prune()
/* 692:    */  {
/* 693:693 */    Polygon result = new Polygon();
/* 694:    */    
/* 695:695 */    for (int i = 0; i < getPointCount(); i++) {
/* 696:696 */      int next = i + 1 >= getPointCount() ? 0 : i + 1;
/* 697:697 */      int prev = i - 1 < 0 ? getPointCount() - 1 : i - 1;
/* 698:    */      
/* 699:699 */      float dx1 = getPoint(i)[0] - getPoint(prev)[0];
/* 700:700 */      float dy1 = getPoint(i)[1] - getPoint(prev)[1];
/* 701:701 */      float dx2 = getPoint(next)[0] - getPoint(i)[0];
/* 702:702 */      float dy2 = getPoint(next)[1] - getPoint(i)[1];
/* 703:    */      
/* 704:704 */      float len1 = (float)Math.sqrt(dx1 * dx1 + dy1 * dy1);
/* 705:705 */      float len2 = (float)Math.sqrt(dx2 * dx2 + dy2 * dy2);
/* 706:706 */      dx1 /= len1;
/* 707:707 */      dy1 /= len1;
/* 708:708 */      dx2 /= len2;
/* 709:709 */      dy2 /= len2;
/* 710:    */      
/* 711:711 */      if ((dx1 != dx2) || (dy1 != dy2)) {
/* 712:712 */        result.addPoint(getPoint(i)[0], getPoint(i)[1]);
/* 713:    */      }
/* 714:    */    }
/* 715:    */    
/* 716:716 */    return result;
/* 717:    */  }
/* 718:    */  
/* 725:    */  public Shape[] subtract(Shape other)
/* 726:    */  {
/* 727:727 */    return new GeomUtil().subtract(this, other);
/* 728:    */  }
/* 729:    */  
/* 735:    */  public Shape[] union(Shape other)
/* 736:    */  {
/* 737:737 */    return new GeomUtil().union(this, other);
/* 738:    */  }
/* 739:    */  
/* 744:    */  public float getWidth()
/* 745:    */  {
/* 746:746 */    return this.maxX - this.minX;
/* 747:    */  }
/* 748:    */  
/* 754:    */  public float getHeight()
/* 755:    */  {
/* 756:756 */    return this.maxY - this.minY;
/* 757:    */  }
/* 758:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.geom.Shape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
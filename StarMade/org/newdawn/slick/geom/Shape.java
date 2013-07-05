/*     */ package org.newdawn.slick.geom;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class Shape
/*     */   implements Serializable
/*     */ {
/*     */   protected float[] points;
/*     */   protected float[] center;
/*     */   protected float x;
/*     */   protected float y;
/*     */   protected float maxX;
/*     */   protected float maxY;
/*     */   protected float minX;
/*     */   protected float minY;
/*     */   protected float boundingCircleRadius;
/*     */   protected boolean pointsDirty;
/*     */   protected transient Triangulator tris;
/*     */   protected boolean trianglesDirty;
/*     */ 
/*     */   public Shape()
/*     */   {
/*  42 */     this.pointsDirty = true;
/*     */   }
/*     */ 
/*     */   public void setLocation(float x, float y)
/*     */   {
/*  52 */     setX(x);
/*  53 */     setY(y);
/*     */   }
/*     */ 
/*     */   public abstract Shape transform(Transform paramTransform);
/*     */ 
/*     */   protected abstract void createPoints();
/*     */ 
/*     */   public float getX()
/*     */   {
/*  77 */     return this.x;
/*     */   }
/*     */ 
/*     */   public void setX(float x)
/*     */   {
/*  86 */     if (x != this.x) {
/*  87 */       float dx = x - this.x;
/*  88 */       this.x = x;
/*     */ 
/*  90 */       if ((this.points == null) || (this.center == null)) {
/*  91 */         checkPoints();
/*     */       }
/*     */ 
/*  94 */       for (int i = 0; i < this.points.length / 2; i++) {
/*  95 */         this.points[(i * 2)] += dx;
/*     */       }
/*  97 */       this.center[0] += dx;
/*  98 */       x += dx;
/*  99 */       this.maxX += dx;
/* 100 */       this.minX += dx;
/* 101 */       this.trianglesDirty = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setY(float y)
/*     */   {
/* 111 */     if (y != this.y) {
/* 112 */       float dy = y - this.y;
/* 113 */       this.y = y;
/*     */ 
/* 115 */       if ((this.points == null) || (this.center == null)) {
/* 116 */         checkPoints();
/*     */       }
/*     */ 
/* 119 */       for (int i = 0; i < this.points.length / 2; i++) {
/* 120 */         this.points[(i * 2 + 1)] += dy;
/*     */       }
/* 122 */       this.center[1] += dy;
/* 123 */       y += dy;
/* 124 */       this.maxY += dy;
/* 125 */       this.minY += dy;
/* 126 */       this.trianglesDirty = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public float getY()
/*     */   {
/* 136 */     return this.y;
/*     */   }
/*     */ 
/*     */   public void setLocation(Vector2f loc)
/*     */   {
/* 145 */     setX(loc.x);
/* 146 */     setY(loc.y);
/*     */   }
/*     */ 
/*     */   public float getCenterX()
/*     */   {
/* 155 */     checkPoints();
/*     */ 
/* 157 */     return this.center[0];
/*     */   }
/*     */ 
/*     */   public void setCenterX(float centerX)
/*     */   {
/* 166 */     if ((this.points == null) || (this.center == null)) {
/* 167 */       checkPoints();
/*     */     }
/*     */ 
/* 170 */     float xDiff = centerX - getCenterX();
/* 171 */     setX(this.x + xDiff);
/*     */   }
/*     */ 
/*     */   public float getCenterY()
/*     */   {
/* 180 */     checkPoints();
/*     */ 
/* 182 */     return this.center[1];
/*     */   }
/*     */ 
/*     */   public void setCenterY(float centerY)
/*     */   {
/* 191 */     if ((this.points == null) || (this.center == null)) {
/* 192 */       checkPoints();
/*     */     }
/*     */ 
/* 195 */     float yDiff = centerY - getCenterY();
/* 196 */     setY(this.y + yDiff);
/*     */   }
/*     */ 
/*     */   public float getMaxX()
/*     */   {
/* 205 */     checkPoints();
/* 206 */     return this.maxX;
/*     */   }
/*     */ 
/*     */   public float getMaxY()
/*     */   {
/* 214 */     checkPoints();
/* 215 */     return this.maxY;
/*     */   }
/*     */ 
/*     */   public float getMinX()
/*     */   {
/* 224 */     checkPoints();
/* 225 */     return this.minX;
/*     */   }
/*     */ 
/*     */   public float getMinY()
/*     */   {
/* 234 */     checkPoints();
/* 235 */     return this.minY;
/*     */   }
/*     */ 
/*     */   public float getBoundingCircleRadius()
/*     */   {
/* 244 */     checkPoints();
/* 245 */     return this.boundingCircleRadius;
/*     */   }
/*     */ 
/*     */   public float[] getCenter()
/*     */   {
/* 254 */     checkPoints();
/* 255 */     return this.center;
/*     */   }
/*     */ 
/*     */   public float[] getPoints()
/*     */   {
/* 264 */     checkPoints();
/* 265 */     return this.points;
/*     */   }
/*     */ 
/*     */   public int getPointCount()
/*     */   {
/* 274 */     checkPoints();
/* 275 */     return this.points.length / 2;
/*     */   }
/*     */ 
/*     */   public float[] getPoint(int index)
/*     */   {
/* 285 */     checkPoints();
/*     */ 
/* 287 */     float[] result = new float[2];
/*     */ 
/* 289 */     result[0] = this.points[(index * 2)];
/* 290 */     result[1] = this.points[(index * 2 + 1)];
/*     */ 
/* 292 */     return result;
/*     */   }
/*     */ 
/*     */   public float[] getNormal(int index)
/*     */   {
/* 302 */     float[] current = getPoint(index);
/* 303 */     float[] prev = getPoint(index - 1 < 0 ? getPointCount() - 1 : index - 1);
/* 304 */     float[] next = getPoint(index + 1 >= getPointCount() ? 0 : index + 1);
/*     */ 
/* 306 */     float[] t1 = getNormal(prev, current);
/* 307 */     float[] t2 = getNormal(current, next);
/*     */ 
/* 309 */     if ((index == 0) && (!closed())) {
/* 310 */       return t2;
/*     */     }
/* 312 */     if ((index == getPointCount() - 1) && (!closed())) {
/* 313 */       return t1;
/*     */     }
/*     */ 
/* 316 */     float tx = (t1[0] + t2[0]) / 2.0F;
/* 317 */     float ty = (t1[1] + t2[1]) / 2.0F;
/* 318 */     float len = (float)Math.sqrt(tx * tx + ty * ty);
/* 319 */     return new float[] { tx / len, ty / len };
/*     */   }
/*     */ 
/*     */   public boolean contains(Shape other)
/*     */   {
/* 331 */     if (other.intersects(this)) {
/* 332 */       return false;
/*     */     }
/*     */ 
/* 335 */     for (int i = 0; i < other.getPointCount(); i++) {
/* 336 */       float[] pt = other.getPoint(i);
/* 337 */       if (!contains(pt[0], pt[1])) {
/* 338 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 342 */     return true;
/*     */   }
/*     */ 
/*     */   private float[] getNormal(float[] start, float[] end)
/*     */   {
/* 352 */     float dx = start[0] - end[0];
/* 353 */     float dy = start[1] - end[1];
/* 354 */     float len = (float)Math.sqrt(dx * dx + dy * dy);
/* 355 */     dx /= len;
/* 356 */     dy /= len;
/* 357 */     return new float[] { -dy, dx };
/*     */   }
/*     */ 
/*     */   public boolean includes(float x, float y)
/*     */   {
/* 369 */     if (this.points.length == 0) {
/* 370 */       return false;
/*     */     }
/*     */ 
/* 373 */     checkPoints();
/*     */ 
/* 375 */     Line testLine = new Line(0.0F, 0.0F, 0.0F, 0.0F);
/* 376 */     Vector2f pt = new Vector2f(x, y);
/*     */ 
/* 378 */     for (int i = 0; i < this.points.length; i += 2) {
/* 379 */       int n = i + 2;
/* 380 */       if (n >= this.points.length) {
/* 381 */         n = 0;
/*     */       }
/* 383 */       testLine.set(this.points[i], this.points[(i + 1)], this.points[n], this.points[(n + 1)]);
/*     */ 
/* 385 */       if (testLine.on(pt)) {
/* 386 */         return true;
/*     */       }
/*     */     }
/*     */ 
/* 390 */     return false;
/*     */   }
/*     */ 
/*     */   public int indexOf(float x, float y)
/*     */   {
/* 401 */     for (int i = 0; i < this.points.length; i += 2) {
/* 402 */       if ((this.points[i] == x) && (this.points[(i + 1)] == y)) {
/* 403 */         return i / 2;
/*     */       }
/*     */     }
/*     */ 
/* 407 */     return -1;
/*     */   }
/*     */ 
/*     */   public boolean contains(float x, float y)
/*     */   {
/* 418 */     checkPoints();
/* 419 */     if (this.points.length == 0) {
/* 420 */       return false;
/*     */     }
/*     */ 
/* 423 */     boolean result = false;
/*     */ 
/* 428 */     int npoints = this.points.length;
/*     */ 
/* 430 */     float xold = this.points[(npoints - 2)];
/* 431 */     float yold = this.points[(npoints - 1)];
/* 432 */     for (int i = 0; i < npoints; i += 2) {
/* 433 */       float xnew = this.points[i];
/* 434 */       float ynew = this.points[(i + 1)];
/*     */       float y2;
/*     */       float x1;
/*     */       float x2;
/*     */       float y1;
/*     */       float y2;
/* 435 */       if (xnew > xold) {
/* 436 */         float x1 = xold;
/* 437 */         float x2 = xnew;
/* 438 */         float y1 = yold;
/* 439 */         y2 = ynew;
/*     */       }
/*     */       else {
/* 442 */         x1 = xnew;
/* 443 */         x2 = xold;
/* 444 */         y1 = ynew;
/* 445 */         y2 = yold;
/*     */       }
/* 447 */       if ((xnew < x ? 1 : 0) == (x <= xold ? 1 : 0)) if ((y - y1) * (x2 - x1) < (y2 - y1) * (x - x1))
/*     */         {
/* 450 */           result = !result;
/*     */         }
/* 452 */       xold = xnew;
/* 453 */       yold = ynew;
/*     */     }
/*     */ 
/* 456 */     return result;
/*     */   }
/*     */ 
/*     */   public boolean intersects(Shape shape)
/*     */   {
/* 480 */     checkPoints();
/*     */ 
/* 482 */     boolean result = false;
/* 483 */     float[] points = getPoints();
/* 484 */     float[] thatPoints = shape.getPoints();
/* 485 */     int length = points.length;
/* 486 */     int thatLength = thatPoints.length;
/*     */ 
/* 490 */     if (!closed()) {
/* 491 */       length -= 2;
/*     */     }
/* 493 */     if (!shape.closed()) {
/* 494 */       thatLength -= 2;
/*     */     }
/*     */ 
/* 505 */     for (int i = 0; i < length; i += 2) {
/* 506 */       int iNext = i + 2;
/* 507 */       if (iNext >= points.length) {
/* 508 */         iNext = 0;
/*     */       }
/*     */ 
/* 511 */       for (int j = 0; j < thatLength; j += 2) {
/* 512 */         int jNext = j + 2;
/* 513 */         if (jNext >= thatPoints.length) {
/* 514 */           jNext = 0;
/*     */         }
/*     */ 
/* 517 */         double unknownA = ((points[iNext] - points[i]) * (thatPoints[(j + 1)] - points[(i + 1)]) - (points[(iNext + 1)] - points[(i + 1)]) * (thatPoints[j] - points[i])) / ((points[(iNext + 1)] - points[(i + 1)]) * (thatPoints[jNext] - thatPoints[j]) - (points[iNext] - points[i]) * (thatPoints[(jNext + 1)] - thatPoints[(j + 1)]));
/*     */ 
/* 521 */         double unknownB = ((thatPoints[jNext] - thatPoints[j]) * (thatPoints[(j + 1)] - points[(i + 1)]) - (thatPoints[(jNext + 1)] - thatPoints[(j + 1)]) * (thatPoints[j] - points[i])) / ((points[(iNext + 1)] - points[(i + 1)]) * (thatPoints[jNext] - thatPoints[j]) - (points[iNext] - points[i]) * (thatPoints[(jNext + 1)] - thatPoints[(j + 1)]));
/*     */ 
/* 526 */         if ((unknownA >= 0.0D) && (unknownA <= 1.0D) && (unknownB >= 0.0D) && (unknownB <= 1.0D)) {
/* 527 */           result = true;
/* 528 */           break;
/*     */         }
/*     */       }
/* 531 */       if (result)
/*     */       {
/*     */         break;
/*     */       }
/*     */     }
/* 536 */     return result;
/*     */   }
/*     */ 
/*     */   public boolean hasVertex(float x, float y)
/*     */   {
/* 547 */     if (this.points.length == 0) {
/* 548 */       return false;
/*     */     }
/*     */ 
/* 551 */     checkPoints();
/*     */ 
/* 553 */     for (int i = 0; i < this.points.length; i += 2) {
/* 554 */       if ((this.points[i] == x) && (this.points[(i + 1)] == y)) {
/* 555 */         return true;
/*     */       }
/*     */     }
/*     */ 
/* 559 */     return false;
/*     */   }
/*     */ 
/*     */   protected void findCenter()
/*     */   {
/* 567 */     this.center = new float[] { 0.0F, 0.0F };
/* 568 */     int length = this.points.length;
/* 569 */     for (int i = 0; i < length; i += 2) {
/* 570 */       this.center[0] += this.points[i];
/* 571 */       this.center[1] += this.points[(i + 1)];
/*     */     }
/* 573 */     this.center[0] /= length / 2;
/* 574 */     this.center[1] /= length / 2;
/*     */   }
/*     */ 
/*     */   protected void calculateRadius()
/*     */   {
/* 582 */     this.boundingCircleRadius = 0.0F;
/*     */ 
/* 584 */     for (int i = 0; i < this.points.length; i += 2) {
/* 585 */       float temp = (this.points[i] - this.center[0]) * (this.points[i] - this.center[0]) + (this.points[(i + 1)] - this.center[1]) * (this.points[(i + 1)] - this.center[1]);
/*     */ 
/* 587 */       this.boundingCircleRadius = (this.boundingCircleRadius > temp ? this.boundingCircleRadius : temp);
/*     */     }
/* 589 */     this.boundingCircleRadius = ((float)Math.sqrt(this.boundingCircleRadius));
/*     */   }
/*     */ 
/*     */   protected void calculateTriangles()
/*     */   {
/* 596 */     if ((!this.trianglesDirty) && (this.tris != null)) {
/* 597 */       return;
/*     */     }
/* 599 */     if (this.points.length >= 6) {
/* 600 */       boolean clockwise = true;
/* 601 */       float area = 0.0F;
/* 602 */       for (int i = 0; i < this.points.length / 2 - 1; i++) {
/* 603 */         float x1 = this.points[(i * 2)];
/* 604 */         float y1 = this.points[(i * 2 + 1)];
/* 605 */         float x2 = this.points[(i * 2 + 2)];
/* 606 */         float y2 = this.points[(i * 2 + 3)];
/*     */ 
/* 608 */         area += x1 * y2 - y1 * x2;
/*     */       }
/* 610 */       area /= 2.0F;
/* 611 */       clockwise = area > 0.0F;
/*     */ 
/* 613 */       this.tris = new NeatTriangulator();
/* 614 */       for (int i = 0; i < this.points.length; i += 2) {
/* 615 */         this.tris.addPolyPoint(this.points[i], this.points[(i + 1)]);
/*     */       }
/* 617 */       this.tris.triangulate();
/*     */     }
/*     */ 
/* 620 */     this.trianglesDirty = false;
/*     */   }
/*     */ 
/*     */   public void increaseTriangulation()
/*     */   {
/* 627 */     checkPoints();
/* 628 */     calculateTriangles();
/*     */ 
/* 630 */     this.tris = new OverTriangulator(this.tris);
/*     */   }
/*     */ 
/*     */   public Triangulator getTriangles()
/*     */   {
/* 639 */     checkPoints();
/* 640 */     calculateTriangles();
/* 641 */     return this.tris;
/*     */   }
/*     */ 
/*     */   protected final void checkPoints()
/*     */   {
/* 648 */     if (this.pointsDirty) {
/* 649 */       createPoints();
/* 650 */       findCenter();
/* 651 */       calculateRadius();
/*     */ 
/* 653 */       if (this.points.length > 0) {
/* 654 */         this.maxX = this.points[0];
/* 655 */         this.maxY = this.points[1];
/* 656 */         this.minX = this.points[0];
/* 657 */         this.minY = this.points[1];
/* 658 */         for (int i = 0; i < this.points.length / 2; i++) {
/* 659 */           this.maxX = Math.max(this.points[(i * 2)], this.maxX);
/* 660 */           this.maxY = Math.max(this.points[(i * 2 + 1)], this.maxY);
/* 661 */           this.minX = Math.min(this.points[(i * 2)], this.minX);
/* 662 */           this.minY = Math.min(this.points[(i * 2 + 1)], this.minY);
/*     */         }
/*     */       }
/* 665 */       this.pointsDirty = false;
/* 666 */       this.trianglesDirty = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void preCache()
/*     */   {
/* 674 */     checkPoints();
/* 675 */     getTriangles();
/*     */   }
/*     */ 
/*     */   public boolean closed()
/*     */   {
/* 684 */     return true;
/*     */   }
/*     */ 
/*     */   public Shape prune()
/*     */   {
/* 693 */     Polygon result = new Polygon();
/*     */ 
/* 695 */     for (int i = 0; i < getPointCount(); i++) {
/* 696 */       int next = i + 1 >= getPointCount() ? 0 : i + 1;
/* 697 */       int prev = i - 1 < 0 ? getPointCount() - 1 : i - 1;
/*     */ 
/* 699 */       float dx1 = getPoint(i)[0] - getPoint(prev)[0];
/* 700 */       float dy1 = getPoint(i)[1] - getPoint(prev)[1];
/* 701 */       float dx2 = getPoint(next)[0] - getPoint(i)[0];
/* 702 */       float dy2 = getPoint(next)[1] - getPoint(i)[1];
/*     */ 
/* 704 */       float len1 = (float)Math.sqrt(dx1 * dx1 + dy1 * dy1);
/* 705 */       float len2 = (float)Math.sqrt(dx2 * dx2 + dy2 * dy2);
/* 706 */       dx1 /= len1;
/* 707 */       dy1 /= len1;
/* 708 */       dx2 /= len2;
/* 709 */       dy2 /= len2;
/*     */ 
/* 711 */       if ((dx1 != dx2) || (dy1 != dy2)) {
/* 712 */         result.addPoint(getPoint(i)[0], getPoint(i)[1]);
/*     */       }
/*     */     }
/*     */ 
/* 716 */     return result;
/*     */   }
/*     */ 
/*     */   public Shape[] subtract(Shape other)
/*     */   {
/* 727 */     return new GeomUtil().subtract(this, other);
/*     */   }
/*     */ 
/*     */   public Shape[] union(Shape other)
/*     */   {
/* 737 */     return new GeomUtil().union(this, other);
/*     */   }
/*     */ 
/*     */   public float getWidth()
/*     */   {
/* 746 */     return this.maxX - this.minX;
/*     */   }
/*     */ 
/*     */   public float getHeight()
/*     */   {
/* 756 */     return this.maxY - this.minY;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.geom.Shape
 * JD-Core Version:    0.6.2
 */
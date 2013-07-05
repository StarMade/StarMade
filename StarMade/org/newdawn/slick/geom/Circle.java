/*     */ package org.newdawn.slick.geom;
/*     */ 
/*     */ public class Circle extends Ellipse
/*     */ {
/*     */   public float radius;
/*     */ 
/*     */   public strictfp Circle(float centerPointX, float centerPointY, float radius)
/*     */   {
/*  20 */     this(centerPointX, centerPointY, radius, 50);
/*     */   }
/*     */ 
/*     */   public strictfp Circle(float centerPointX, float centerPointY, float radius, int segmentCount)
/*     */   {
/*  32 */     super(centerPointX, centerPointY, radius, radius, segmentCount);
/*  33 */     this.x = (centerPointX - radius);
/*  34 */     this.y = (centerPointY - radius);
/*  35 */     this.radius = radius;
/*  36 */     this.boundingCircleRadius = radius;
/*     */   }
/*     */ 
/*     */   public strictfp float getCenterX()
/*     */   {
/*  45 */     return getX() + this.radius;
/*     */   }
/*     */ 
/*     */   public strictfp float getCenterY()
/*     */   {
/*  54 */     return getY() + this.radius;
/*     */   }
/*     */ 
/*     */   public strictfp void setRadius(float radius)
/*     */   {
/*  63 */     if (radius != this.radius) {
/*  64 */       this.pointsDirty = true;
/*  65 */       this.radius = radius;
/*  66 */       setRadii(radius, radius);
/*     */     }
/*     */   }
/*     */ 
/*     */   public strictfp float getRadius()
/*     */   {
/*  76 */     return this.radius;
/*     */   }
/*     */ 
/*     */   public strictfp boolean intersects(Shape shape)
/*     */   {
/*  86 */     if ((shape instanceof Circle)) {
/*  87 */       Circle other = (Circle)shape;
/*  88 */       float totalRad2 = getRadius() + other.getRadius();
/*     */ 
/*  90 */       if (Math.abs(other.getCenterX() - getCenterX()) > totalRad2) {
/*  91 */         return false;
/*     */       }
/*  93 */       if (Math.abs(other.getCenterY() - getCenterY()) > totalRad2) {
/*  94 */         return false;
/*     */       }
/*     */ 
/*  97 */       totalRad2 *= totalRad2;
/*     */ 
/*  99 */       float dx = Math.abs(other.getCenterX() - getCenterX());
/* 100 */       float dy = Math.abs(other.getCenterY() - getCenterY());
/*     */ 
/* 102 */       return totalRad2 >= dx * dx + dy * dy;
/*     */     }
/* 104 */     if ((shape instanceof Rectangle)) {
/* 105 */       return intersects((Rectangle)shape);
/*     */     }
/*     */ 
/* 108 */     return super.intersects(shape);
/*     */   }
/*     */ 
/*     */   public strictfp boolean contains(float x, float y)
/*     */   {
/* 121 */     return (x - getX()) * (x - getX()) + (y - getY()) * (y - getY()) < getRadius() * getRadius();
/*     */   }
/*     */ 
/*     */   private strictfp boolean contains(Line line)
/*     */   {
/* 130 */     return (contains(line.getX1(), line.getY1())) && (contains(line.getX2(), line.getY2()));
/*     */   }
/*     */ 
/*     */   protected strictfp void findCenter()
/*     */   {
/* 137 */     this.center = new float[2];
/* 138 */     this.center[0] = (this.x + this.radius);
/* 139 */     this.center[1] = (this.y + this.radius);
/*     */   }
/*     */ 
/*     */   protected strictfp void calculateRadius()
/*     */   {
/* 146 */     this.boundingCircleRadius = this.radius;
/*     */   }
/*     */ 
/*     */   private strictfp boolean intersects(Rectangle other)
/*     */   {
/* 156 */     Rectangle box = other;
/* 157 */     Circle circle = this;
/*     */ 
/* 159 */     if (box.contains(this.x + this.radius, this.y + this.radius)) {
/* 160 */       return true;
/*     */     }
/*     */ 
/* 163 */     float x1 = box.getX();
/* 164 */     float y1 = box.getY();
/* 165 */     float x2 = box.getX() + box.getWidth();
/* 166 */     float y2 = box.getY() + box.getHeight();
/*     */ 
/* 168 */     Line[] lines = new Line[4];
/* 169 */     lines[0] = new Line(x1, y1, x2, y1);
/* 170 */     lines[1] = new Line(x2, y1, x2, y2);
/* 171 */     lines[2] = new Line(x2, y2, x1, y2);
/* 172 */     lines[3] = new Line(x1, y2, x1, y1);
/*     */ 
/* 174 */     float r2 = circle.getRadius() * circle.getRadius();
/*     */ 
/* 176 */     Vector2f pos = new Vector2f(circle.getCenterX(), circle.getCenterY());
/*     */ 
/* 178 */     for (int i = 0; i < 4; i++) {
/* 179 */       float dis = lines[i].distanceSquared(pos);
/* 180 */       if (dis < r2) {
/* 181 */         return true;
/*     */       }
/*     */     }
/*     */ 
/* 185 */     return false;
/*     */   }
/*     */ 
/*     */   private strictfp boolean intersects(Line other)
/*     */   {
/* 195 */     Vector2f lineSegmentStart = new Vector2f(other.getX1(), other.getY1());
/* 196 */     Vector2f lineSegmentEnd = new Vector2f(other.getX2(), other.getY2());
/* 197 */     Vector2f circleCenter = new Vector2f(getCenterX(), getCenterY());
/*     */ 
/* 202 */     Vector2f segv = lineSegmentEnd.copy().sub(lineSegmentStart);
/* 203 */     Vector2f ptv = circleCenter.copy().sub(lineSegmentStart);
/* 204 */     float segvLength = segv.length();
/* 205 */     float projvl = ptv.dot(segv) / segvLength;
/*     */     Vector2f closest;
/*     */     Vector2f closest;
/* 206 */     if (projvl < 0.0F)
/*     */     {
/* 208 */       closest = lineSegmentStart;
/*     */     }
/*     */     else
/*     */     {
/*     */       Vector2f closest;
/* 210 */       if (projvl > segvLength)
/*     */       {
/* 212 */         closest = lineSegmentEnd;
/*     */       }
/*     */       else
/*     */       {
/* 216 */         Vector2f projv = segv.copy().scale(projvl / segvLength);
/* 217 */         closest = lineSegmentStart.copy().add(projv);
/*     */       }
/*     */     }
/* 219 */     boolean intersects = circleCenter.copy().sub(closest).lengthSquared() <= getRadius() * getRadius();
/*     */ 
/* 221 */     return intersects;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.geom.Circle
 * JD-Core Version:    0.6.2
 */
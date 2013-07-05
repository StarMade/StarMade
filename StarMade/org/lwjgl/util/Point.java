/*     */ package org.lwjgl.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class Point
/*     */   implements ReadablePoint, WritablePoint, Serializable
/*     */ {
/*     */   static final long serialVersionUID = 1L;
/*     */   private int x;
/*     */   private int y;
/*     */ 
/*     */   public Point()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Point(int x, int y)
/*     */   {
/*  60 */     setLocation(x, y);
/*     */   }
/*     */ 
/*     */   public Point(ReadablePoint p)
/*     */   {
/*  67 */     setLocation(p);
/*     */   }
/*     */ 
/*     */   public void setLocation(int x, int y) {
/*  71 */     this.x = x;
/*  72 */     this.y = y;
/*     */   }
/*     */ 
/*     */   public void setLocation(ReadablePoint p) {
/*  76 */     this.x = p.getX();
/*  77 */     this.y = p.getY();
/*     */   }
/*     */ 
/*     */   public void setX(int x) {
/*  81 */     this.x = x;
/*     */   }
/*     */ 
/*     */   public void setY(int y) {
/*  85 */     this.y = y;
/*     */   }
/*     */ 
/*     */   public void translate(int dx, int dy)
/*     */   {
/*  94 */     this.x += dx;
/*  95 */     this.y += dy;
/*     */   }
/*     */ 
/*     */   public void translate(ReadablePoint p)
/*     */   {
/* 103 */     this.x += p.getX();
/* 104 */     this.y += p.getY();
/*     */   }
/*     */ 
/*     */   public void untranslate(ReadablePoint p)
/*     */   {
/* 112 */     this.x -= p.getX();
/* 113 */     this.y -= p.getY();
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 128 */     if ((obj instanceof Point)) {
/* 129 */       Point pt = (Point)obj;
/* 130 */       return (this.x == pt.x) && (this.y == pt.y);
/*     */     }
/* 132 */     return super.equals(obj);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 145 */     return getClass().getName() + "[x=" + this.x + ",y=" + this.y + "]";
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 154 */     int sum = this.x + this.y;
/* 155 */     return sum * (sum + 1) / 2 + this.x;
/*     */   }
/*     */ 
/*     */   public int getX() {
/* 159 */     return this.x;
/*     */   }
/*     */ 
/*     */   public int getY() {
/* 163 */     return this.y;
/*     */   }
/*     */ 
/*     */   public void getLocation(WritablePoint dest) {
/* 167 */     dest.setLocation(this.x, this.y);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.Point
 * JD-Core Version:    0.6.2
 */
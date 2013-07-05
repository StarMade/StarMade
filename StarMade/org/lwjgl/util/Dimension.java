/*     */ package org.lwjgl.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class Dimension
/*     */   implements Serializable, ReadableDimension, WritableDimension
/*     */ {
/*     */   static final long serialVersionUID = 1L;
/*     */   private int width;
/*     */   private int height;
/*     */ 
/*     */   public Dimension()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Dimension(int w, int h)
/*     */   {
/*  60 */     this.width = w;
/*  61 */     this.height = h;
/*     */   }
/*     */ 
/*     */   public Dimension(ReadableDimension d)
/*     */   {
/*  68 */     setSize(d);
/*     */   }
/*     */ 
/*     */   public void setSize(int w, int h) {
/*  72 */     this.width = w;
/*  73 */     this.height = h;
/*     */   }
/*     */ 
/*     */   public void setSize(ReadableDimension d) {
/*  77 */     this.width = d.getWidth();
/*  78 */     this.height = d.getHeight();
/*     */   }
/*     */ 
/*     */   public void getSize(WritableDimension dest)
/*     */   {
/*  85 */     dest.setSize(this);
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/*  92 */     if ((obj instanceof ReadableDimension)) {
/*  93 */       ReadableDimension d = (ReadableDimension)obj;
/*  94 */       return (this.width == d.getWidth()) && (this.height == d.getHeight());
/*     */     }
/*  96 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 105 */     int sum = this.width + this.height;
/* 106 */     return sum * (sum + 1) / 2 + this.width;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 121 */     return getClass().getName() + "[width=" + this.width + ",height=" + this.height + "]";
/*     */   }
/*     */ 
/*     */   public int getHeight()
/*     */   {
/* 129 */     return this.height;
/*     */   }
/*     */ 
/*     */   public void setHeight(int height)
/*     */   {
/* 137 */     this.height = height;
/*     */   }
/*     */ 
/*     */   public int getWidth()
/*     */   {
/* 145 */     return this.width;
/*     */   }
/*     */ 
/*     */   public void setWidth(int width)
/*     */   {
/* 153 */     this.width = width;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.Dimension
 * JD-Core Version:    0.6.2
 */
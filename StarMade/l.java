/*     */ import java.io.Externalizable;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ 
/*     */ public final class l
/*     */   implements Externalizable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private float a;
/*     */   private float b;
/*     */   private float c;
/*     */   private float d;
/*     */ 
/*     */   public l()
/*     */   {
/* 157 */     this.a = (this.b = this.c = this.d = 1.0F);
/*     */   }
/*     */ 
/*     */   private l(float paramFloat1, float paramFloat2, float paramFloat3)
/*     */   {
/* 183 */     this.a = paramFloat1;
/* 184 */     this.b = paramFloat2;
/* 185 */     this.c = paramFloat3;
/* 186 */     this.d = 1.0F;
/*     */   }
/*     */ 
/*     */   private l a()
/*     */   {
/*     */     try
/*     */     {
/* 262 */       return (l)super.clone(); } catch (CloneNotSupportedException localCloneNotSupportedException) {
/*     */     }
/* 264 */     throw new AssertionError();
/*     */   }
/*     */ 
/*     */   public final boolean equals(Object paramObject)
/*     */   {
/* 277 */     if (!(paramObject instanceof l)) {
/* 278 */       return false;
/*     */     }
/*     */ 
/* 281 */     if (this == paramObject) {
/* 282 */       return true;
/*     */     }
/*     */ 
/* 285 */     paramObject = (l)paramObject;
/* 286 */     if (Float.compare(this.a, paramObject.a) != 0) {
/* 287 */       return false;
/*     */     }
/* 289 */     if (Float.compare(this.b, paramObject.b) != 0) {
/* 290 */       return false;
/*     */     }
/* 292 */     if (Float.compare(this.c, paramObject.c) != 0) {
/* 293 */       return false;
/*     */     }
/* 295 */     if (Float.compare(this.d, paramObject.d) != 0) {
/* 296 */       return false;
/*     */     }
/* 298 */     return true;
/*     */   }
/*     */ 
/*     */   public final int hashCode()
/*     */   {
/*     */     int tmp14_13 = (37 + (1369 + Float.floatToIntBits(this.a)));
/*     */     int tmp27_26 = (tmp14_13 + (tmp14_13 * 
/* 353 */       37 + Float.floatToIntBits(this.b)));
/*     */     int tmp40_39 = (tmp27_26 + (tmp27_26 * 
/* 354 */       37 + Float.floatToIntBits(this.c)));
/*     */ 
/* 356 */     return tmp40_39 + (tmp40_39 * 
/* 355 */       37 + Float.floatToIntBits(this.d));
/*     */   }
/*     */ 
/*     */   public final void readExternal(ObjectInput paramObjectInput)
/*     */   {
/* 421 */     this.a = paramObjectInput.readFloat();
/* 422 */     this.b = paramObjectInput.readFloat();
/* 423 */     this.c = paramObjectInput.readFloat();
/* 424 */     this.d = paramObjectInput.readFloat();
/*     */   }
/*     */ 
/*     */   public final String toString()
/*     */   {
/* 510 */     return "com.jme.renderer.ColorRGBA: [R=" + this.a + ", G=" + this.b + ", B=" + this.c + ", A=" + this.d + "]";
/*     */   }
/*     */ 
/*     */   public final void writeExternal(ObjectOutput paramObjectOutput)
/*     */   {
/* 521 */     paramObjectOutput.writeFloat(this.a);
/* 522 */     paramObjectOutput.writeFloat(this.b);
/* 523 */     paramObjectOutput.writeFloat(this.c);
/* 524 */     paramObjectOutput.writeFloat(this.d);
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  63 */     new l(0.0F, 0.0F, 0.0F);
/*     */ 
/*  67 */     new l(1.0F, 1.0F, 1.0F);
/*     */ 
/*  71 */     new l(0.2F, 0.2F, 0.2F);
/*     */ 
/*  75 */     new l(0.5F, 0.5F, 0.5F);
/*     */ 
/*  79 */     new l(0.8F, 0.8F, 0.8F);
/*     */ 
/*  83 */     new l(1.0F, 0.0F, 0.0F);
/*     */ 
/*  87 */     new l(0.0F, 1.0F, 0.0F);
/*     */ 
/*  91 */     new l(0.0F, 0.0F, 1.0F);
/*     */ 
/*  95 */     new l(1.0F, 1.0F, 0.0F);
/*     */ 
/*  99 */     new l(1.0F, 0.0F, 1.0F);
/*     */ 
/* 103 */     new l(0.0F, 1.0F, 1.0F);
/*     */ 
/* 107 */     new l(0.9843137F, 0.509804F, 0.0F);
/*     */ 
/* 111 */     new l(0.254902F, 0.1568628F, 0.09803922F);
/*     */ 
/* 115 */     new l(1.0F, 0.68F, 0.68F);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     l
 * JD-Core Version:    0.6.2
 */
/*     */ public final class n
/*     */ {
/*     */   public float a;
/*     */   public float b;
/*     */   public float c;
/*     */ 
/*     */   public n()
/*     */   {
/*     */   }
/*     */ 
/*     */   public n(float paramFloat1, float paramFloat2, float paramFloat3)
/*     */   {
/* 376 */     this.a = paramFloat1;
/* 377 */     this.b = paramFloat2;
/* 378 */     this.c = paramFloat3;
/*     */   }
/*     */ 
/*     */   public final boolean equals(Object paramObject)
/*     */   {
/* 455 */     if ((paramObject instanceof n)) {
/* 456 */       paramObject = (n)paramObject;
/* 457 */       return (this.a == paramObject.a) && (this.b == paramObject.b) && (this.c == paramObject.c);
/*     */     }
/* 459 */     return false;
/*     */   }
/*     */ 
/*     */   public final String toString()
/*     */   {
/* 728 */     return "[" + this.a + ", " + this.b + ", " + this.c + "]";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     n
 * JD-Core Version:    0.6.2
 */
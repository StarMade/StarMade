/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class s
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = 8749319902347760659L;
/*     */   public int a;
/*     */   public int b;
/*     */   public int c;
/*     */   public int d;
/*     */ 
/*     */   public s()
/*     */   {
/*     */   }
/*     */ 
/*     */   public s(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/*  40 */     this.a = paramInt1;
/*  41 */     this.b = paramInt2;
/*  42 */     this.c = paramInt3;
/*  43 */     this.d = paramInt4;
/*     */   }
/*     */ 
/*     */   public s(q paramq)
/*     */   {
/*  67 */     this(paramq.a, paramq.b, paramq.c, 0);
/*     */   }
/*     */ 
/*     */   public s(q paramq, int paramInt)
/*     */   {
/*  91 */     this(paramq.a, paramq.b, paramq.c, paramInt);
/*     */   }
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/*     */     try
/*     */     {
/* 116 */       paramObject = (s)paramObject;
/* 117 */       return (this.a == paramObject.a) && (this.b == paramObject.b) && (this.c == paramObject.c) && (this.d == paramObject.d);
/*     */     }
/*     */     catch (NullPointerException localNullPointerException) {
/* 120 */       return false; } catch (ClassCastException localClassCastException) {
/* 121 */     }return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 135 */     long l = 31L + this.a;
/*     */ 
/* 137 */     l = 31L * l + this.b;
/* 138 */     l = 31L * l + this.c;
/*     */     long tmp45_44 = (31L * l + this.d);
/* 140 */     return (int)(tmp45_44 ^ tmp45_44 >> 
/* 140 */       32);
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 164 */     this.a = paramInt1;
/* 165 */     this.b = paramInt2;
/* 166 */     this.c = paramInt3;
/* 167 */     this.d = paramInt4;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 207 */     return "(" + this.a + ", " + this.b + ", " + this.c + ", " + this.d + ")";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     s
 * JD-Core Version:    0.6.2
 */
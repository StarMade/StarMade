/*     */ import java.text.DecimalFormat;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4f;
/*     */ import org.schema.common.FastMath;
/*     */ 
/*     */ public final class p
/*     */ {
/*     */   public static Vector3f a(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, Vector3f paramVector3f3)
/*     */   {
/*     */     Vector3f localVector3f;
/*  71 */     (
/*  72 */       localVector3f = new Vector3f())
/*  72 */       .sub(paramVector3f1, paramVector3f3);
/*  73 */     if (paramVector3f2.lengthSquared() == 0.0F)
/*     */     {
/*  75 */       return localVector3f;
/*     */     }
/*     */ 
/*  80 */     paramVector3f2.dot(paramVector3f2);
/*     */ 
/*  82 */     double d1 = paramVector3f2.dot(paramVector3f2) - paramFloat * paramFloat;
/*  83 */     double d2 = 2.0F * localVector3f.dot(paramVector3f2);
/*  84 */     double d3 = localVector3f.dot(localVector3f);
/*     */ 
/*  87 */     double d4 = (float)Math.sqrt(Math.abs(d2 * d2 - d1 * 4.0D * d3));
/*     */ 
/*  91 */     double d5 = (-d2 - d4) / (d1 * 2.0D);
/*  92 */     double d6 = (-d2 + d4) / (d1 * 2.0D);
/*     */     double d7;
/*  95 */     if ((d5 > d6) && (d6 > 0.0D))
/*     */     {
/*  97 */       d7 = d6;
/*     */     }
/*     */     else
/*     */     {
/* 101 */       d7 = d5;
/*     */     }
/*     */ 
/* 104 */     paramFloat = new Vector3f();
/* 105 */     (
/* 106 */       paramVector3f2 = new Vector3f(paramVector3f2))
/* 106 */       .scale((float)d7);
/* 107 */     paramFloat.add(paramVector3f1, paramVector3f2);
/*     */ 
/* 109 */     (
/* 110 */       paramVector3f1 = new Vector3f())
/* 110 */       .sub(paramFloat, paramVector3f3);
/*     */ 
/* 113 */     return paramVector3f1;
/*     */   }
/*     */ 
/*     */   public static void a(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3)
/*     */   {
/* 160 */     paramVector3f1.x = FastMath.a(paramVector3f1.x, paramVector3f2.x, paramVector3f3.x);
/* 161 */     paramVector3f1.y = FastMath.a(paramVector3f1.y, paramVector3f2.y, paramVector3f3.y);
/* 162 */     paramVector3f1.z = FastMath.a(paramVector3f1.z, paramVector3f2.z, paramVector3f3.z);
/*     */   }
/*     */ 
/*     */   public static float a(Vector3f paramVector3f1, Vector3f paramVector3f2)
/*     */   {
/* 239 */     float f = paramVector3f1.x * paramVector3f2.x + paramVector3f1.y * paramVector3f2.y;
/*     */ 
/* 242 */     return FastMath.a(paramVector3f1.x * paramVector3f2.y - paramVector3f1.y * paramVector3f2.x, 
/* 241 */       f);
/*     */   }
/*     */ 
/*     */   public static boolean a(Vector3f paramVector3f)
/*     */   {
/* 431 */     return (Float.isNaN(paramVector3f.x)) || (Float.isNaN(paramVector3f.y)) || (Float.isNaN(paramVector3f.z));
/*     */   }
/*     */ 
/*     */   public static void a(Vector3f paramVector3f1, Vector3f paramVector3f2)
/*     */   {
/* 442 */     if (paramVector3f2.x > paramVector3f1.x) paramVector3f1.x = paramVector3f2.x;
/* 443 */     if (paramVector3f2.y > paramVector3f1.y) paramVector3f1.y = paramVector3f2.y;
/* 444 */     if (paramVector3f2.z > paramVector3f1.z) paramVector3f1.z = paramVector3f2.z;
/*     */   }
/*     */ 
/*     */   public static void b(Vector3f paramVector3f1, Vector3f paramVector3f2)
/*     */   {
/* 456 */     if (paramVector3f2.x < paramVector3f1.x) paramVector3f1.x = paramVector3f2.x;
/* 457 */     if (paramVector3f2.y < paramVector3f1.y) paramVector3f1.y = paramVector3f2.y;
/* 458 */     if (paramVector3f2.z < paramVector3f1.z) paramVector3f1.z = paramVector3f2.z;
/*     */   }
/*     */ 
/*     */   public static void a(Vector4f paramVector4f)
/*     */   {
/* 499 */     if ((paramVector4f.x == 0.0F) && (paramVector4f.y == 0.0F) && (paramVector4f.z == 0.0F)) {
/* 500 */       paramVector4f.w = 0.0F;
/* 501 */       return;
/*     */     }
/*     */ 
/* 504 */     float f = FastMath.m(paramVector4f.x * paramVector4f.x + paramVector4f.y * paramVector4f.y + paramVector4f.z * paramVector4f.z);
/*     */ 
/* 507 */     paramVector4f.x /= f;
/* 508 */     paramVector4f.y /= f;
/* 509 */     paramVector4f.z /= f;
/*     */ 
/* 511 */     paramVector4f.w = ((float)(2.0D * FastMath.a(paramVector4f.w)));
/*     */   }
/*     */ 
/*     */   public static float a(float paramFloat1, float paramFloat2, float paramFloat3)
/*     */   {
/* 522 */     return FastMath.l(paramFloat1 * paramFloat1 + paramFloat2 * paramFloat2 + paramFloat3 * paramFloat3);
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  61 */     new DecimalFormat("#,###,###,##0.00");
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     p
 * JD-Core Version:    0.6.2
 */
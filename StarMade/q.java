/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.common.FastMath;
/*     */ 
/*     */ public class q
/*     */   implements Comparable
/*     */ {
/*     */   public int a;
/*     */   public int b;
/*     */   public int c;
/*     */ 
/*     */   public q()
/*     */   {
/*     */   }
/*     */ 
/*     */   public q(float paramFloat1, float paramFloat2, float paramFloat3)
/*     */   {
/*  13 */     this.jdField_a_of_type_Int = ((int)paramFloat1); this.b = ((int)paramFloat2); this.c = ((int)paramFloat3);
/*     */   }
/*     */   public q(int paramInt1, int paramInt2, int paramInt3) {
/*  16 */     this.jdField_a_of_type_Int = paramInt1; this.b = paramInt2; this.c = paramInt3;
/*     */   }
/*     */   public q(Vector3f paramVector3f) {
/*  19 */     this.jdField_a_of_type_Int = ((int)paramVector3f.x); this.b = ((int)paramVector3f.y); this.c = ((int)paramVector3f.z);
/*     */   }
/*     */   public q(q paramq) {
/*  22 */     this.jdField_a_of_type_Int = paramq.jdField_a_of_type_Int;
/*  23 */     this.b = paramq.b;
/*  24 */     this.c = paramq.c;
/*     */   }
/*     */   public final void a(int paramInt1, int paramInt2, int paramInt3) {
/*  27 */     this.jdField_a_of_type_Int += paramInt1;
/*  28 */     this.b += paramInt2;
/*  29 */     this.c += paramInt3;
/*     */   }
/*     */ 
/*     */   public final void a(q paramq) {
/*  33 */     this.jdField_a_of_type_Int += paramq.jdField_a_of_type_Int;
/*  34 */     this.b += paramq.b;
/*  35 */     this.c += paramq.c;
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*  44 */     this.jdField_a_of_type_Int /= 2;
/*  45 */     this.b /= 2;
/*  46 */     this.c /= 2;
/*     */   }
/*     */ 
/*     */   public final boolean a(int paramInt1, int paramInt2, int paramInt3) {
/*  50 */     return (this.jdField_a_of_type_Int == paramInt1) && (this.b == paramInt2) && (this.c == paramInt3);
/*     */   }
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/*     */     try
/*     */     {
/*  63 */       paramObject = (q)paramObject;
/*  64 */       return (this.jdField_a_of_type_Int == paramObject.jdField_a_of_type_Int) && (this.b == paramObject.b) && (this.c == paramObject.c);
/*     */     } catch (NullPointerException localNullPointerException) {
/*  66 */       return false; } catch (ClassCastException localClassCastException) {
/*  67 */     }return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  83 */     return ((this.jdField_a_of_type_Int ^ this.jdField_a_of_type_Int >>> 16) * 
/*  81 */       15 + (this.b ^ this.b >>> 16)) * 
/*  82 */       15 + (this.c ^ this.c >>> 16);
/*     */   }
/*     */ 
/*     */   public final float a() {
/*  86 */     return FastMath.l(this.jdField_a_of_type_Int * this.jdField_a_of_type_Int + this.b * this.b + this.c * this.c);
/*     */   }
/*     */   public final void a(int paramInt) {
/*  89 */     this.jdField_a_of_type_Int *= paramInt;
/*  90 */     this.b *= paramInt;
/*  91 */     this.c *= paramInt;
/*     */   }
/*     */ 
/*     */   public final void b(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 104 */     this.jdField_a_of_type_Int = paramInt1; this.b = paramInt2; this.c = paramInt3;
/*     */   }
/*     */   public final void b(q paramq) {
/* 107 */     b(paramq.jdField_a_of_type_Int, paramq.b, paramq.c);
/*     */   }
/*     */   public final void c(int paramInt1, int paramInt2, int paramInt3) {
/* 110 */     this.jdField_a_of_type_Int -= paramInt1;
/* 111 */     this.b -= paramInt2;
/* 112 */     this.c -= paramInt3;
/*     */   }
/*     */ 
/*     */   public final void c(q paramq) {
/* 116 */     this.jdField_a_of_type_Int -= paramq.jdField_a_of_type_Int;
/* 117 */     this.b -= paramq.b;
/* 118 */     this.c -= paramq.c;
/*     */   }
/*     */ 
/*     */   public final void a(q paramq1, q paramq2) {
/* 122 */     paramq1.jdField_a_of_type_Int -= paramq2.jdField_a_of_type_Int;
/* 123 */     paramq1.b -= paramq2.b;
/* 124 */     paramq1.c -= paramq2.c;
/*     */   }
/*     */   public final void b(q paramq1, q paramq2) {
/* 127 */     paramq1.jdField_a_of_type_Int += paramq2.jdField_a_of_type_Int;
/* 128 */     paramq1.b += paramq2.b;
/* 129 */     paramq1.c += paramq2.c;
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 133 */     return "(" + this.jdField_a_of_type_Int + ", " + this.b + ", " + this.c + ")";
/*     */   }
/*     */ 
/*     */   public final int a(int paramInt)
/*     */   {
/* 139 */     switch (paramInt) { case 0:
/* 140 */       return this.jdField_a_of_type_Int;
/*     */     case 1:
/* 141 */       return this.b;
/*     */     case 2:
/* 142 */       return this.c; }
/* 143 */     if (!jdField_a_of_type_Boolean) throw new AssertionError(paramInt);
/*     */ 
/* 145 */     throw new NullPointerException(paramInt + " coord");
/*     */   }
/*     */ 
/*     */   public static q a(String paramString)
/*     */   {
/* 158 */     if ((
/* 158 */       paramString = paramString.split(",")).length != 
/* 158 */       3) {
/* 159 */       throw new NumberFormatException("Wrong number of arguments");
/*     */     }
/* 161 */     return new q(Integer.parseInt(paramString[0].trim()), Integer.parseInt(paramString[1].trim()), Integer.parseInt(paramString[2].trim()));
/*     */   }
/*     */   public final void b() {
/* 164 */     this.jdField_a_of_type_Int = (-this.jdField_a_of_type_Int);
/* 165 */     this.b = (-this.b);
/* 166 */     this.c = (-this.c);
/*     */   }
/*     */   public final void c() {
/* 169 */     this.jdField_a_of_type_Int = Math.abs(this.jdField_a_of_type_Int);
/* 170 */     this.b = Math.abs(this.b);
/* 171 */     this.c = Math.abs(this.c);
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*   7 */     jdField_a_of_type_Boolean = !q.class.desiredAssertionStatus();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     q
 * JD-Core Version:    0.6.2
 */
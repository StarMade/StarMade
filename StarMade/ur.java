/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public final class ur extends uu
/*    */ {
/* 11 */   private q c = new q();
/* 12 */   private q d = new q();
/* 13 */   private q e = new q();
/* 14 */   private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*    */   private Vector3f b;
/* 16 */   private static float jdField_a_of_type_Float = 5.0F;
/*    */ 
/*    */   public ur(uu[] paramArrayOfuu, q paramq1, q paramq2, int paramInt)
/*    */   {
/* 20 */     super(paramArrayOfuu, paramq1, paramq2, 5, paramInt);
/*    */ 
/* 15 */     this.jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*    */   }
/*    */ 
/*    */   protected final short a(q paramq)
/*    */   {
/* 24 */     a(paramq, this.c);
/* 25 */     a(this.jdField_b_of_type_Q, this.d);
/* 26 */     a(this.jdField_a_of_type_Q, this.e);
/*    */ 
/* 28 */     a(this.e, this.d);
/*    */ 
/* 33 */     if (this.c.b < this.d.b / 2 + jdField_a_of_type_Float) {
/* 34 */       paramq = this; short s = 5; if (paramq.c.b == paramq.d.b / 2) s = 75; else if ((paramq.c.b == paramq.d.b / 2 + 1) || (paramq.c.b == paramq.d.b / 2 - 1)) s = 76; else if (paramq.c.c % 8 == 0) s = 78; if ((paramq.c.b == 0) || (paramq.c.b == paramq.d.b - 1)) { if ((paramq.c.b == 0) && (paramq.c.a > 0) && (paramq.c.a % 10 == 0) && (paramq.c.c % 5 == 0)) return 55; return 5; } if ((paramq.c.a == paramq.d.a - 1) || (paramq.c.a == 0) || (paramq.c.c == paramq.d.c - 1)) return s; return 0;
/*    */     }
/* 36 */     paramq = this; this.jdField_a_of_type_JavaxVecmathVector3f.set(paramq.c.a + 0.5F, paramq.c.b * 1.3F, 0.0F); paramq.jdField_b_of_type_JavaxVecmathVector3f.set(paramq.d.a / 2.0F, paramq.d.b / 2.0F + jdField_a_of_type_Float, 0.0F); paramq.jdField_a_of_type_JavaxVecmathVector3f.sub(paramq.jdField_b_of_type_JavaxVecmathVector3f);
/*    */     float f;
/* 36 */     if (((f = paramq.jdField_a_of_type_JavaxVecmathVector3f.length()) < paramq.d.a / 2.0F + 0.5F) && ((f > paramq.d.a / 2.0F - 1.5F) || (paramq.c.b == paramq.d.b - 1) || (paramq.c.b == paramq.d.b - 2) || (paramq.c.c == paramq.d.c - 1))) return 5; return 0;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ur
 * JD-Core Version:    0.6.2
 */
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public final class us extends uu
/*    */ {
/* 11 */   private q c = new q();
/* 12 */   private q d = new q();
/* 13 */   private q e = new q();
/*    */   private Vector3f a;
/*    */   private Vector3f b;
/*    */ 
/*    */   public us(uu[] paramArrayOfuu, q paramq1, q paramq2)
/*    */   {
/* 18 */     super(paramArrayOfuu, paramq1, paramq2, 5, 0);
/*    */ 
/* 14 */     this.jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/* 15 */     this.jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*    */   }
/*    */ 
/*    */   protected final short a(q paramq)
/*    */   {
/* 23 */     a(paramq, this.c);
/* 24 */     a(this.jdField_b_of_type_Q, this.d);
/* 25 */     a(this.jdField_a_of_type_Q, this.e);
/*    */ 
/* 27 */     a(this.e, this.d);
/*    */     float f;
/* 32 */     if (this.c.b < this.d.b / 2) {
/* 33 */       paramq = this; this.jdField_a_of_type_JavaxVecmathVector3f.set(paramq.c.a + 0.5F, 0.0F, paramq.c.c + 0.5F); paramq.jdField_b_of_type_JavaxVecmathVector3f.set(paramq.d.a / 2.0F, 0.0F, paramq.d.c / 2.0F); paramq.jdField_a_of_type_JavaxVecmathVector3f.sub(paramq.jdField_b_of_type_JavaxVecmathVector3f); if (((f = paramq.jdField_a_of_type_JavaxVecmathVector3f.length()) < 1.0F) && (paramq.c.b < paramq.d.b - 15)) return 2; if ((f < paramq.d.a / 2.0F + 0.5F) && ((f > paramq.d.a / 2.0F - 1.5F) || (paramq.c.b <= 1))) { if ((paramq.c.b > 0) && (paramq.c.b % 20 == 0)) return 77; return 75; } return 0;
/*    */     }
/* 35 */     paramq = this; this.jdField_a_of_type_JavaxVecmathVector3f.set(paramq.c.a + 0.5F, 0.0F, paramq.c.c + 0.5F); paramq.jdField_b_of_type_JavaxVecmathVector3f.set(paramq.d.a / 2.0F, 0.0F, paramq.d.c / 2.0F); paramq.jdField_a_of_type_JavaxVecmathVector3f.sub(paramq.jdField_b_of_type_JavaxVecmathVector3f); if (((f = paramq.jdField_a_of_type_JavaxVecmathVector3f.length()) < 1.0F) && (paramq.c.b < paramq.d.b - 15)) return 2; if ((f < paramq.d.a / 2.0F + 0.5F) && ((f > paramq.d.a / 2.0F - 1.5F) || (paramq.c.b >= paramq.d.b - 1.0F))) { if ((paramq.c.b > 0) && (paramq.c.b % 20 == 0)) return 77; return 75; } return 0;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     us
 * JD-Core Version:    0.6.2
 */
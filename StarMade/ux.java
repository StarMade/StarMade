/*    */ import javax.vecmath.Vector2f;
/*    */ 
/*    */ public final class ux extends uu
/*    */ {
/* 11 */   private q c = new q();
/* 12 */   private q d = new q();
/* 13 */   private q e = new q();
/*    */   private Vector2f a;
/*    */   private Vector2f b;
/*    */ 
/*    */   public ux(uu[] paramArrayOfuu, q paramq1, q paramq2)
/*    */   {
/* 19 */     super(paramArrayOfuu, paramq1, paramq2, 5, 0);
/*    */ 
/* 14 */     this.jdField_a_of_type_JavaxVecmathVector2f = new Vector2f();
/* 15 */     this.jdField_b_of_type_JavaxVecmathVector2f = new Vector2f();
/*    */   }
/*    */ 
/*    */   protected final short a(q paramq)
/*    */   {
/* 23 */     a(paramq, this.c);
/* 24 */     a(this.jdField_b_of_type_Q, this.d);
/* 25 */     a(this.jdField_a_of_type_Q, this.e);
/*    */ 
/* 27 */     a(this.e, this.d);
/*    */ 
/* 32 */     this.jdField_a_of_type_JavaxVecmathVector2f.set(this.c.a + 0.5F, this.c.c + 0.5F);
/* 33 */     this.jdField_b_of_type_JavaxVecmathVector2f.set(this.d.a / 2, this.d.c / 2);
/* 34 */     this.jdField_b_of_type_JavaxVecmathVector2f.sub(this.jdField_a_of_type_JavaxVecmathVector2f);
/* 35 */     if (this.jdField_b_of_type_JavaxVecmathVector2f.length() <= this.d.a / 2) {
/* 36 */       if (this.c.b % 9 < 3)
/* 37 */         return 63;
/* 38 */       if (this.c.b % 9 == 6)
/* 39 */         return 5;
/* 40 */       if (this.c.b % 9 == 7)
/* 41 */         return 55;
/* 42 */       if (this.c.b % 9 == 8) {
/* 43 */         return 5;
/*    */       }
/* 45 */       return 75;
/*    */     }
/*    */ 
/* 50 */     return 0;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ux
 * JD-Core Version:    0.6.2
 */
/*    */ public final class uy extends uu
/*    */ {
/* 16 */   private q c = new q();
/* 17 */   private q d = new q();
/* 18 */   private q e = new q();
/*    */   private int b;
/* 20 */   private q f = new q();
/*    */   private q g;
/*    */ 
/*    */   public uy(uu[] paramArrayOfuu, q paramq1, q paramq2, q paramq3)
/*    */   {
/* 24 */     super(paramArrayOfuu, paramq1, paramq2, 5, 0);
/* 25 */     this.jdField_b_of_type_Int = 3;
/* 26 */     this.g = paramq3;
/*    */   }
/*    */ 
/*    */   protected final short a(q paramq)
/*    */   {
/* 31 */     a(paramq, this.c);
/* 32 */     a(this.jdField_b_of_type_Q, this.d);
/* 33 */     a(this.a, this.e);
/*    */ 
/* 35 */     a(this.e, this.d);
/*    */ 
/* 38 */     this.f.b(this.g);
/* 39 */     q localq = org.schema.game.common.data.element.Element.DIRECTIONSi[this.jdField_b_of_type_Int];
/* 40 */     this.f.a(localq);
/*    */ 
/* 42 */     if (((localq.a > 0) && (paramq.a > this.g.a)) || ((localq.a < 0) && (paramq.a < this.g.a)) || ((localq.jdField_b_of_type_Int > 0) && (paramq.jdField_b_of_type_Int > this.g.jdField_b_of_type_Int)) || ((localq.jdField_b_of_type_Int < 0) && (paramq.jdField_b_of_type_Int < this.g.jdField_b_of_type_Int)) || ((localq.c > 0) && (paramq.c > this.g.c)) || ((localq.c < 0) && (paramq.c < this.g.c)))
/*    */     {
/* 47 */       return 32767;
/*    */     }
/* 49 */     this.f.b(this.g);
/* 50 */     this.f.c(localq);
/*    */ 
/* 52 */     if ((this.c.a == (this.d.a - this.e.a) / 2) || (this.c.jdField_b_of_type_Int == (this.d.jdField_b_of_type_Int - this.e.jdField_b_of_type_Int) / 2))
/*    */     {
/* 55 */       if ((this.c.c > this.d.c - 3) && (this.c.c < this.d.c - 1)) {
/* 56 */         return 282;
/*    */       }
/* 58 */       if (this.c.c < this.d.c - 1) {
/* 59 */         return 286;
/*    */       }
/*    */     }
/*    */ 
/* 63 */     if ((this.c.c % 2 == 0) && (this.c.c < this.d.c - 4)) {
/* 64 */       return 75;
/*    */     }
/*    */ 
/* 70 */     return 32767;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     uy
 * JD-Core Version:    0.6.2
 */
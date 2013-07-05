/*    */ import java.util.Random;
/*    */ 
/*    */ public final class ub extends tX
/*    */ {
/*    */   public ub(long paramLong)
/*    */   {
/* 11 */     super(paramLong);
/*    */   }
/*    */ 
/*    */   protected final short a(float paramFloat)
/*    */   {
/* 16 */     short s = 73;
/*    */ 
/* 18 */     if ((paramFloat < 3.4F) && (this.jdField_a_of_type_JavaUtilRandom.nextFloat() > 0.7F))
/* 19 */       s = 74;
/* 20 */     else if ((paramFloat > 9.1D) && (this.jdField_a_of_type_JavaUtilRandom.nextFloat() > 0.68F)) {
/* 21 */       s = 80;
/*    */     }
/* 23 */     return s;
/*    */   }
/*    */ 
/*    */   public final void a() {
/* 27 */     this.jdField_a_of_type_ArrayOfUX = new uX[5];
/* 28 */     this.jdField_a_of_type_ArrayOfUX[0] = new va(72, 6, 73);
/* 29 */     this.jdField_a_of_type_ArrayOfUX[1] = new va(130, 6, 73);
/* 30 */     this.jdField_a_of_type_ArrayOfUX[2] = new va(133, 6, 74);
/* 31 */     this.jdField_a_of_type_ArrayOfUX[3] = new va(137, 9, 73);
/* 32 */     this.jdField_a_of_type_ArrayOfUX[4] = new va(128, 6, 73);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ub
 * JD-Core Version:    0.6.2
 */
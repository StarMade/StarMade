/*    */ import java.util.Random;
/*    */ 
/*    */ public final class uc extends tX
/*    */ {
/*    */   public uc(long paramLong)
/*    */   {
/* 11 */     super(paramLong);
/*    */   }
/*    */ 
/*    */   protected final short a(float paramFloat)
/*    */   {
/* 16 */     short s = 73;
/*    */ 
/* 18 */     if ((paramFloat < 3.4F) && (this.jdField_a_of_type_JavaUtilRandom.nextFloat() > 0.3F))
/* 19 */       s = 87;
/* 20 */     else if ((paramFloat > 9.1D) && (this.jdField_a_of_type_JavaUtilRandom.nextFloat() > 0.68F)) {
/* 21 */       s = 64;
/*    */     }
/* 23 */     return s;
/*    */   }
/*    */ 
/*    */   public final void a()
/*    */   {
/* 28 */     this.jdField_a_of_type_ArrayOfUX = new uX[3];
/* 29 */     this.jdField_a_of_type_ArrayOfUX[0] = new va(72, 6, 73);
/* 30 */     this.jdField_a_of_type_ArrayOfUX[1] = new va(210, 6, 73);
/* 31 */     this.jdField_a_of_type_ArrayOfUX[2] = new va(209, 8, 64);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     uc
 * JD-Core Version:    0.6.2
 */
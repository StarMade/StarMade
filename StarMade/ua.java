/*    */ import java.util.Random;
/*    */ 
/*    */ public final class ua extends tX
/*    */ {
/*    */   public ua(long paramLong)
/*    */   {
/* 11 */     super(paramLong);
/*    */   }
/*    */ 
/*    */   protected final short a(float paramFloat)
/*    */   {
/* 16 */     short s = 80;
/*    */ 
/* 18 */     if ((paramFloat < 5.1D) && (this.jdField_a_of_type_JavaUtilRandom.nextFloat() > 0.1F)) {
/* 19 */       s = 73;
/*    */     }
/* 21 */     return s;
/*    */   }
/*    */ 
/*    */   public final void a() {
/* 25 */     this.jdField_a_of_type_ArrayOfUX = new uX[3];
/* 26 */     this.jdField_a_of_type_ArrayOfUX[0] = new va(133, 6, 73);
/* 27 */     this.jdField_a_of_type_ArrayOfUX[1] = new va(136, 6, 73);
/* 28 */     this.jdField_a_of_type_ArrayOfUX[2] = new va(129, 8, 73);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ua
 * JD-Core Version:    0.6.2
 */
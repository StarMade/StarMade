/*    */ import java.util.Random;
/*    */ 
/*    */ public final class ue extends tX
/*    */ {
/*    */   public ue(long paramLong)
/*    */   {
/* 11 */     super(paramLong);
/*    */   }
/*    */ 
/*    */   protected final short a(float paramFloat)
/*    */   {
/* 16 */     short s = 92;
/*    */ 
/* 18 */     if ((paramFloat < 3.4F) && (this.jdField_a_of_type_JavaUtilRandom.nextFloat() > 0.3F))
/* 19 */       s = 91;
/* 20 */     else if ((paramFloat > 9.1D) && (this.jdField_a_of_type_JavaUtilRandom.nextFloat() > 0.68F)) {
/* 21 */       s = 80;
/*    */     }
/* 23 */     return s;
/*    */   }
/*    */ 
/*    */   public final void a()
/*    */   {
/* 28 */     this.jdField_a_of_type_ArrayOfUX = new uX[3];
/* 29 */     this.jdField_a_of_type_ArrayOfUX[0] = new va(208, 6, 92);
/* 30 */     this.jdField_a_of_type_ArrayOfUX[1] = new va(207, 6, 92);
/* 31 */     this.jdField_a_of_type_ArrayOfUX[2] = new va(210, 9, 92);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ue
 * JD-Core Version:    0.6.2
 */
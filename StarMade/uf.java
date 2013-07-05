/*    */ import java.util.Random;
/*    */ 
/*    */ public final class uf extends tX
/*    */ {
/*    */   public uf(long paramLong)
/*    */   {
/* 11 */     super(paramLong);
/*    */   }
/*    */ 
/*    */   protected final short a(float paramFloat)
/*    */   {
/* 16 */     short s = 139;
/*    */ 
/* 18 */     if ((paramFloat < 3.4F) && (this.jdField_a_of_type_JavaUtilRandom.nextFloat() > 0.3F))
/* 19 */       s = 140;
/* 20 */     else if ((paramFloat > 9.1D) && (this.jdField_a_of_type_JavaUtilRandom.nextFloat() > 0.68F)) {
/* 21 */       s = 80;
/*    */     }
/* 23 */     return s;
/*    */   }
/*    */ 
/*    */   public final void a()
/*    */   {
/* 28 */     this.jdField_a_of_type_ArrayOfUX = new uX[3];
/* 29 */     this.jdField_a_of_type_ArrayOfUX[0] = new va(137, 6, 139);
/* 30 */     this.jdField_a_of_type_ArrayOfUX[1] = new va(132, 6, 140);
/* 31 */     this.jdField_a_of_type_ArrayOfUX[2] = new va(130, 8, 139);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     uf
 * JD-Core Version:    0.6.2
 */
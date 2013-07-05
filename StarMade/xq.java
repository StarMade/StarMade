/*    */ public final class xq
/*    */ {
/*  6 */   private long jdField_a_of_type_Long = 0L;
/*    */   private long jdField_b_of_type_Long;
/*    */   private long c;
/*    */   private int jdField_a_of_type_Int;
/* 10 */   private long d = 0L;
/*    */   private double jdField_a_of_type_Double;
/* 15 */   private static double jdField_b_of_type_Double = 1000000000.0D;
/*    */ 
/*    */   public final float a()
/*    */   {
/* 44 */     return (float)this.jdField_a_of_type_Double;
/*    */   }
/*    */ 
/*    */   public final long a()
/*    */   {
/* 51 */     return this.jdField_b_of_type_Long;
/*    */   }
/*    */   public final void a() {
/* 54 */     this.c = System.currentTimeMillis();
/*    */ 
/* 56 */     this.jdField_a_of_type_Long = System.nanoTime();
/*    */   }
/*    */ 
/*    */   public final void b()
/*    */   {
/* 61 */     if (System.currentTimeMillis() - 
/* 61 */       this.c > 1000L) {
/* 62 */       this.jdField_b_of_type_Long = this.jdField_a_of_type_Int;
/* 63 */       this.jdField_a_of_type_Int = 0;
/* 64 */       this.c += 1000L;
/*    */     }
/* 66 */     this.jdField_a_of_type_Int += 1;
/* 67 */     this.jdField_a_of_type_Long = this.d;
/* 68 */     this.d = System.nanoTime();
/*    */ 
/* 70 */     long l = this.d - this.jdField_a_of_type_Long;
/* 71 */     this.jdField_a_of_type_Double = (l / jdField_b_of_type_Double);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     xq
 * JD-Core Version:    0.6.2
 */
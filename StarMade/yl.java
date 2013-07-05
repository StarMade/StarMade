/*    */ import javax.vecmath.Vector4f;
/*    */ 
/*    */ public class yl
/*    */ {
/*  7 */   private float b = 1000.0F;
/*    */   protected Vector4f a;
/*    */   private long a;
/*    */   public float a;
/*    */ 
/*    */   public yl()
/*    */   {
/* 10 */     this.jdField_a_of_type_Float = 10.0F;
/*    */ 
/* 13 */     this.jdField_a_of_type_Long = System.currentTimeMillis();
/*    */   }
/*    */ 
/*    */   public yl(byte paramByte)
/*    */   {
/* 10 */     this.jdField_a_of_type_Float = 10.0F;
/*    */ 
/* 16 */     this.jdField_a_of_type_Long = System.currentTimeMillis();
/* 17 */     this.jdField_a_of_type_Float = 10.0F;
/*    */   }
/*    */ 
/*    */   public final float a() {
/* 21 */     return Math.max(0.0F, 1.0F - b() / this.b);
/*    */   }
/*    */ 
/*    */   private float b()
/*    */   {
/* 27 */     return (float)(System.currentTimeMillis() - this.jdField_a_of_type_Long);
/*    */   }
/*    */   public final boolean a() {
/* 30 */     return b() < this.b;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     yl
 * JD-Core Version:    0.6.2
 */
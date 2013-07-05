/*    */ import java.util.Random;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.data.world.Segment;
/*    */ 
/*    */ public final class uk extends uj
/*    */ {
/*    */   private uX[] a;
/*    */ 
/*    */   public final void a(SegmentController paramSegmentController, Segment paramSegment)
/*    */   {
/* 21 */     if (!this.jdField_a_of_type_Boolean) {
/* 22 */       this.jdField_a_of_type_UV = new uN(((jA)paramSegmentController).getSeed());
/* 23 */       this.jdField_a_of_type_UV.a(this);
/* 24 */       a();
/* 25 */       this.jdField_a_of_type_Boolean = true;
/*    */     }
/*    */ 
/* 28 */     a(paramSegment);
/*    */   }
/*    */ 
/*    */   public uk(long paramLong) {
/* 32 */     super(paramLong);
/* 33 */     this.jdField_a_of_type_ArrayOfUX = new uX[8];
/* 34 */     this.jdField_a_of_type_ArrayOfUX[0] = new uZ(137, 4, 275);
/* 35 */     this.jdField_a_of_type_ArrayOfUX[1] = new uZ(132, 4, 275);
/* 36 */     this.jdField_a_of_type_ArrayOfUX[2] = new uZ(130, 4, 275);
/* 37 */     this.jdField_a_of_type_ArrayOfUX[3] = new uZ(129, 4, 275);
/*    */ 
/* 39 */     this.jdField_a_of_type_ArrayOfUX[4] = new uW(Integer.valueOf(1), 280, new short[] { 274, 64 });
/* 40 */     this.jdField_a_of_type_ArrayOfUX[5] = new uW(Integer.valueOf(1), 279, new short[] { 274, 64 });
/* 41 */     this.jdField_a_of_type_ArrayOfUX[6] = new uW(Integer.valueOf(1), 281, new short[] { 274, 64 });
/* 42 */     this.jdField_a_of_type_ArrayOfUX[7] = new uW(Integer.valueOf(1), 278, new short[] { 274, 64 });
/*    */   }
/*    */ 
/*    */   public final short a()
/*    */   {
/* 50 */     return 64;
/*    */   }
/*    */ 
/*    */   public final short d()
/*    */   {
/* 55 */     return 286;
/*    */   }
/*    */ 
/*    */   public final uX[] a() {
/* 59 */     return this.jdField_a_of_type_ArrayOfUX;
/*    */   }
/*    */ 
/*    */   public final short b()
/*    */   {
/* 66 */     return 275;
/*    */   }
/*    */ 
/*    */   public final short c()
/*    */   {
/* 73 */     return 274;
/*    */   }
/*    */ 
/*    */   public final void a(Random paramRandom)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     uk
 * JD-Core Version:    0.6.2
 */
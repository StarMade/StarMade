/*    */ import java.util.Random;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.data.world.Segment;
/*    */ 
/*    */ public final class ug extends uj
/*    */ {
/*    */   private uX[] a;
/*    */ 
/*    */   public ug(long paramLong)
/*    */   {
/* 21 */     super(paramLong);
/* 22 */     this.jdField_a_of_type_ArrayOfUX = new uX[9];
/* 23 */     this.jdField_a_of_type_ArrayOfUX[0] = new va(128, 3, 91);
/* 24 */     this.jdField_a_of_type_ArrayOfUX[1] = new uZ(87, 14, 91);
/*    */ 
/* 26 */     this.jdField_a_of_type_ArrayOfUX[2] = new va(133, 6, 91);
/* 27 */     this.jdField_a_of_type_ArrayOfUX[3] = new va(72, 4, 91);
/* 28 */     this.jdField_a_of_type_ArrayOfUX[4] = new va(129, 4, 91);
/*    */ 
/* 30 */     this.jdField_a_of_type_ArrayOfUX[5] = new uW(97, new short[] { 90, 92 });
/* 31 */     this.jdField_a_of_type_ArrayOfUX[6] = new uW(101, new short[] { 90, 92 });
/* 32 */     this.jdField_a_of_type_ArrayOfUX[7] = new uW(105, new short[] { 90, 92 });
/* 33 */     this.jdField_a_of_type_ArrayOfUX[8] = new uW(109, new short[] { 90, 92 });
/*    */   }
/*    */ 
/*    */   public final void a(SegmentController paramSegmentController, Segment paramSegment)
/*    */   {
/* 40 */     if (!this.jdField_a_of_type_Boolean) {
/* 41 */       this.jdField_a_of_type_UV = new uL(((jA)paramSegmentController).getSeed());
/* 42 */       this.jdField_a_of_type_UV.a(this);
/* 43 */       a();
/* 44 */       this.jdField_a_of_type_Boolean = true;
/*    */     }
/*    */ 
/* 47 */     a(paramSegment);
/*    */   }
/*    */ 
/*    */   public final short a()
/*    */   {
/* 54 */     return 92;
/*    */   }
/*    */ 
/*    */   public final uX[] a()
/*    */   {
/* 61 */     return this.jdField_a_of_type_ArrayOfUX;
/*    */   }
/*    */ 
/*    */   public final short b()
/*    */   {
/* 68 */     return 91;
/*    */   }
/*    */ 
/*    */   public final short c()
/*    */   {
/* 73 */     return 90;
/*    */   }
/*    */ 
/*    */   public final void a(Random paramRandom)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ug
 * JD-Core Version:    0.6.2
 */
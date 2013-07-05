/*    */ import java.util.Random;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.data.world.Segment;
/*    */ 
/*    */ public abstract class uj extends tW
/*    */ {
/*    */   private long a;
/*    */   protected boolean a;
/*    */   protected uV a;
/*    */ 
/*    */   public uj(long paramLong)
/*    */   {
/* 20 */     this.jdField_a_of_type_Boolean = false;
/*    */ 
/* 31 */     this.jdField_a_of_type_Long = paramLong;
/*    */   }
/*    */ 
/*    */   public abstract void a(Random paramRandom);
/*    */ 
/*    */   public void a(SegmentController paramSegmentController, Segment paramSegment) {
/* 37 */     if (!this.jdField_a_of_type_Boolean)
/*    */     {
/* 42 */       this.jdField_a_of_type_UV = new uV(((jA)paramSegmentController).getSeed());
/* 43 */       this.jdField_a_of_type_UV.a(this);
/* 44 */       a();
/*    */ 
/* 46 */       this.jdField_a_of_type_Boolean = true;
/*    */     }
/*    */ 
/* 49 */     a(paramSegment);
/*    */   }
/*    */ 
/*    */   protected final void a()
/*    */   {
/*    */     Random localRandom;
/* 55 */     if ((
/* 55 */       localRandom = new Random(this.jdField_a_of_type_Long))
/* 55 */       .nextInt(15) == 0)
/* 56 */       a(localRandom);
/*    */   }
/*    */ 
/*    */   protected final void a(Segment paramSegment)
/*    */   {
/* 61 */     this.jdField_a_of_type_UV.a(paramSegment.a(), 64 + paramSegment.a.a / 16, Math.abs(paramSegment.a.b) / 16, 64 + paramSegment.a.c / 16, paramSegment.a.b < 0);
/*    */ 
/* 63 */     this.jdField_a_of_type_UV.a(paramSegment);
/*    */   }
/*    */ 
/*    */   public abstract short a();
/*    */ 
/*    */   public abstract uX[] a();
/*    */ 
/*    */   public abstract short b();
/*    */ 
/*    */   public abstract short c();
/*    */ 
/*    */   public short d()
/*    */   {
/* 92 */     return 80;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     uj
 * JD-Core Version:    0.6.2
 */
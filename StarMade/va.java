/*    */ import java.util.Random;
/*    */ import org.schema.game.common.data.element.ElementInformation;
/*    */ import org.schema.game.common.data.element.ElementKeyMap;
/*    */ import org.schema.game.common.data.world.SegmentData;
/*    */ 
/*    */ public class va extends uZ
/*    */ {
/*    */   public va(short paramShort1, int paramInt, short paramShort2)
/*    */   {
/* 13 */     super(paramShort1, paramInt, paramShort2);
/* 14 */     if ((!jdField_a_of_type_Boolean) && (!ElementKeyMap.getInfo(paramShort1).isLeveled())) throw new AssertionError();
/*    */   }
/*    */ 
/*    */   public final void a(SegmentData paramSegmentData, int paramInt1, int paramInt2, int paramInt3, Random paramRandom)
/*    */   {
/* 19 */     int i = 1;
/* 20 */     float f = 0.1F;
/* 21 */     while ((i < 5) && (paramRandom.nextFloat() < f)) {
/* 22 */       i++;
/* 23 */       f *= 0.9F;
/*    */     }
/*    */ 
/* 26 */     paramRandom = ElementKeyMap.getLevel(this.jdField_a_of_type_Short, i);
/*    */ 
/* 28 */     paramSegmentData.setInfoElementUnsynched((byte)Math.abs(paramInt1 % 16), (byte)Math.abs(paramInt2 % 16), (byte)Math.abs(paramInt3 % 16), paramRandom, false);
/*    */   }
/*    */ 
/*    */   static
/*    */   {
/*  8 */     jdField_a_of_type_Boolean = !va.class.desiredAssertionStatus();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     va
 * JD-Core Version:    0.6.2
 */
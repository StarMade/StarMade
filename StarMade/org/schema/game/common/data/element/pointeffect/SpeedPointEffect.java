/*    */ package org.schema.game.common.data.element.pointeffect;
/*    */ 
/*    */ import org.schema.game.common.data.element.PointDistributionUnit;
/*    */ 
/*    */ public class SpeedPointEffect extends PointEffect
/*    */ {
/*  7 */   private static int speedUnit = 8;
/*    */ 
/*    */   public SpeedPointEffect(PointDistributionUnit paramPointDistributionUnit) {
/* 10 */     super(paramPointDistributionUnit);
/*    */   }
/*    */ 
/*    */   public int getEffectId()
/*    */   {
/* 15 */     return 3;
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 20 */     return "Speed";
/*    */   }
/*    */ 
/*    */   protected void recalculate()
/*    */   {
/* 25 */     this.value = ((float)Math.max(1.0D, 15.0D + Math.pow(getPointsSpend() * 0.1D, 0.5D) * speedUnit));
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.pointeffect.SpeedPointEffect
 * JD-Core Version:    0.6.2
 */
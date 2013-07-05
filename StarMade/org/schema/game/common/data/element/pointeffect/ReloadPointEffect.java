/*    */ package org.schema.game.common.data.element.pointeffect;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import org.schema.game.common.data.element.PointDistributionUnit;
/*    */ 
/*    */ public class ReloadPointEffect extends PointEffect
/*    */ {
/*    */   public ReloadPointEffect(PointDistributionUnit paramPointDistributionUnit)
/*    */   {
/*  8 */     super(paramPointDistributionUnit);
/*    */   }
/*    */ 
/*    */   public int getEffectId()
/*    */   {
/* 13 */     return 2;
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 18 */     return "Reload";
/*    */   }
/*    */ 
/*    */   protected void recalculate()
/*    */   {
/* 23 */     double d = Math.pow(getPointsSpend() * 0.5D + 10.0D, -0.9D) * 5000.0D + 50.0D;
/* 24 */     this.value = ((float)Math.max(50.0D, d));
/*    */   }
/*    */   public static void main(String[] paramArrayOfString) {
/* 27 */     for (paramArrayOfString = 0; paramArrayOfString < 1000; paramArrayOfString++) {
/* 28 */       double d = Math.pow(paramArrayOfString * 0.17D + 10.0D, -0.9D) * 6000.0D + 50.0D;
/* 29 */       System.err.println(paramArrayOfString + ": " + d);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.pointeffect.ReloadPointEffect
 * JD-Core Version:    0.6.2
 */
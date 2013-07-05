/*    */ package org.schema.game.common.controller.elements.harvest;
/*    */ 
/*    */ import org.schema.common.FastMath;
/*    */ import org.schema.game.common.data.element.CustomOutputUnit;
/*    */ import q;
/*    */ 
/*    */ public class SalvageUnit extends CustomOutputUnit
/*    */ {
/*    */   private float salvageSpeedFactor;
/* 28 */   private q significator = new q(-2147483648, -2147483648, -2147483648);
/*    */ 
/*    */   public float getSalvageSpeedFactor()
/*    */   {
/* 16 */     return this.salvageSpeedFactor;
/*    */   }
/*    */ 
/*    */   public q getSignificator()
/*    */   {
/* 21 */     return this.significator;
/*    */   }
/*    */ 
/*    */   public void refreshSalvageCapabilities() {
/* 25 */     this.salvageSpeedFactor = (7.0F / FastMath.b(Math.max(0.0F, size()), 1.2F));
/*    */   }
/*    */ 
/*    */   protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
/*    */   {
/* 39 */     if (paramInt3 > this.significator.c)
/* 40 */       this.significator.b(paramInt1, paramInt2, paramInt3);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.harvest.SalvageUnit
 * JD-Core Version:    0.6.2
 */
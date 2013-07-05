/*    */ package org.schema.game.common.controller.elements.powercap;
/*    */ 
/*    */ import org.schema.game.common.data.element.ElementCollection;
/*    */ import q;
/*    */ 
/*    */ public class PowerCapacityUnit extends ElementCollection
/*    */ {
/* 11 */   private q significator = new q();
/*    */ 
/*    */   public q getSignificator()
/*    */   {
/* 22 */     return this.significator;
/*    */   }
/*    */ 
/*    */   protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
/*    */   {
/* 44 */     this.significator.a = (getMax().a - (getMax().a - getMin().a) / 2);
/* 45 */     this.significator.b = (getMax().b - (getMax().b - getMin().b) / 2);
/* 46 */     this.significator.c = (getMax().c - (getMax().c - getMin().c) / 2);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.powercap.PowerCapacityUnit
 * JD-Core Version:    0.6.2
 */
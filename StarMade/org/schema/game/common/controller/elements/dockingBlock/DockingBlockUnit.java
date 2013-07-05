/*    */ package org.schema.game.common.controller.elements.dockingBlock;
/*    */ 
/*    */ import org.schema.game.common.data.element.ElementCollection;
/*    */ import q;
/*    */ 
/*    */ public class DockingBlockUnit extends ElementCollection
/*    */ {
/*  9 */   private q significator = new q();
/*    */ 
/*    */   public q getSignificator()
/*    */   {
/* 13 */     return this.significator;
/*    */   }
/*    */ 
/*    */   protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
/*    */   {
/* 23 */     this.significator.a = getMax().a;
/* 24 */     this.significator.b = (getMax().b - (getMax().b - getMin().b) / 2);
/* 25 */     this.significator.c = (getMax().c - (getMax().c - getMin().c) / 2);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.dockingBlock.DockingBlockUnit
 * JD-Core Version:    0.6.2
 */
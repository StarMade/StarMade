/*    */ package org.schema.game.common.controller.elements.factory;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import org.schema.game.common.data.element.ElementCollection;
/*    */ import q;
/*    */ 
/*    */ public class FactoryUnit extends ElementCollection
/*    */ {
/*  8 */   private q significator = new q();
/*    */   private int capability;
/*    */ 
/*    */   protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
/*    */   {
/* 20 */     this.significator.a = (getMax().a - (getMax().a - getMin().a) / 2);
/* 21 */     this.significator.b = (getMax().b - (getMax().b - getMin().b) / 2);
/* 22 */     this.significator.c = getMax().c;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 31 */     return "FactoryUnit";
/*    */   }
/*    */ 
/*    */   public q getSignificator()
/*    */   {
/* 39 */     return this.significator;
/*    */   }
/*    */ 
/*    */   public void refreshFactoryCapabilities(FactoryCollectionManager paramFactoryCollectionManager)
/*    */   {
/* 44 */     this.capability = getNeighboringCollection().size();
/* 45 */     paramFactoryCollectionManager.addCapability(this.capability);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.factory.FactoryUnit
 * JD-Core Version:    0.6.2
 */
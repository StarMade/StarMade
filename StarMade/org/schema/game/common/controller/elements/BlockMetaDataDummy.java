/*    */ package org.schema.game.common.controller.elements;
/*    */ 
/*    */ import Ad;
/*    */ import q;
/*    */ 
/*    */ public abstract class BlockMetaDataDummy
/*    */ {
/*    */   public q pos;
/*    */ 
/*    */   public void fromTagStructure(Ad paramAd)
/*    */   {
/* 11 */     paramAd = (Ad[])paramAd.a();
/* 12 */     this.pos = ((q)paramAd[0].a());
/* 13 */     fromTagStructrePriv(paramAd[1]);
/*    */   }
/*    */ 
/*    */   protected abstract void fromTagStructrePriv(Ad paramAd);
/*    */ 
/*    */   public q getControllerPos() {
/* 19 */     return this.pos;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.BlockMetaDataDummy
 * JD-Core Version:    0.6.2
 */
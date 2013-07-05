/*    */ package org.schema.game.common.controller.elements.dockingBlock;
/*    */ 
/*    */ import Ad;
/*    */ import org.schema.game.common.controller.elements.BlockMetaDataDummy;
/*    */ 
/*    */ public class DockingMetaDataDummy extends BlockMetaDataDummy
/*    */ {
/*    */   public byte orientation;
/*    */ 
/*    */   protected void fromTagStructrePriv(Ad paramAd)
/*    */   {
/* 14 */     this.orientation = ((Byte)paramAd.a()).byteValue();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.dockingBlock.DockingMetaDataDummy
 * JD-Core Version:    0.6.2
 */
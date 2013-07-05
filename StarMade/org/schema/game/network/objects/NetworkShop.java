/*    */ package org.schema.game.network.objects;
/*    */ 
/*    */ import ka;
/*    */ import mh;
/*    */ import ml;
/*    */ import org.schema.game.network.objects.remote.RemoteInventoryBuffer;
/*    */ import org.schema.game.network.objects.remote.RemoteInventoryMultModBuffer;
/*    */ import org.schema.schine.network.StateInterface;
/*    */ import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;
/*    */ 
/*    */ public class NetworkShop extends NetworkSegmentController
/*    */   implements ml
/*    */ {
/* 15 */   public RemoteIntArrayBuffer pricesUpdateBuffer = new RemoteIntArrayBuffer(2, this);
/* 16 */   public RemoteIntArrayBuffer inventoryUpdateBuffer = new RemoteIntArrayBuffer(3, this);
/* 17 */   public RemoteIntArrayBuffer inventoryActivateBuffer = new RemoteIntArrayBuffer(3, this);
/* 18 */   public RemoteInventoryMultModBuffer inventoryMultModBuffer = new RemoteInventoryMultModBuffer(this);
/*    */   public RemoteInventoryBuffer inventoryBuffer;
/*    */ 
/*    */   public RemoteIntArrayBuffer getInventoryActivateBuffer()
/*    */   {
/* 23 */     return this.inventoryActivateBuffer;
/*    */   }
/*    */ 
/*    */   public NetworkShop(StateInterface paramStateInterface, ka paramka) {
/* 27 */     super(paramStateInterface, paramka);
/* 28 */     this.inventoryBuffer = new RemoteInventoryBuffer((mh)paramka, this);
/*    */   }
/*    */ 
/*    */   public RemoteInventoryBuffer getInventoriesChangeBuffer()
/*    */   {
/* 36 */     return this.inventoryBuffer;
/*    */   }
/*    */ 
/*    */   public RemoteIntArrayBuffer getInventoryUpdateBuffer()
/*    */   {
/* 44 */     return this.inventoryUpdateBuffer;
/*    */   }
/*    */ 
/*    */   public RemoteInventoryMultModBuffer getInventoryMultModBuffer()
/*    */   {
/* 49 */     return this.inventoryMultModBuffer;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.NetworkShop
 * JD-Core Version:    0.6.2
 */
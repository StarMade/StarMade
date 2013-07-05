/*    */ package org.schema.game.network.objects;
/*    */ 
/*    */ import org.schema.game.network.objects.remote.RemoteItemBuffer;
/*    */ import org.schema.schine.network.StateInterface;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteBoolean;
/*    */ import org.schema.schine.network.objects.remote.RemoteVector3i;
/*    */ 
/*    */ public class NetworkSector extends NetworkObject
/*    */ {
/* 15 */   public RemoteBoolean active = new RemoteBoolean(this);
/* 16 */   public RemoteVector3i pos = new RemoteVector3i(this);
/* 17 */   public RemoteItemBuffer itemBuffer = new RemoteItemBuffer(this);
/*    */ 
/*    */   public NetworkSector(StateInterface paramStateInterface) {
/* 20 */     super(paramStateInterface);
/*    */   }
/*    */ 
/*    */   public void onDelete(StateInterface paramStateInterface)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void onInit(StateInterface paramStateInterface)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.NetworkSector
 * JD-Core Version:    0.6.2
 */
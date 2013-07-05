/*    */ package org.schema.game.network.objects;
/*    */ 
/*    */ import kc;
/*    */ import org.schema.game.network.objects.remote.RemoteControlStructureBuffer;
/*    */ import org.schema.game.network.objects.remote.RemoteInventoryBuffer;
/*    */ import org.schema.game.network.objects.remote.RemoteSegmentRemoteObj;
/*    */ import org.schema.game.network.objects.remote.RemoteSegmentSignature;
/*    */ import org.schema.schine.network.StateInterface;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteBoolean;
/*    */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*    */ import org.schema.schine.network.objects.remote.RemoteVector3i;
/*    */ 
/*    */ public class NetworkSegmentProvider extends NetworkObject
/*    */ {
/* 18 */   public RemoteBuffer signatureRequestBuffer = new RemoteBuffer(RemoteVector3i.class, this);
/* 19 */   public RemoteBuffer segmentRequestBuffer = new RemoteBuffer(RemoteVector3i.class, this);
/*    */ 
/* 21 */   public RemoteBuffer signatureBuffer = new RemoteBuffer(RemoteSegmentSignature.class, this);
/* 22 */   public RemoteBuffer segmentBuffer = new RemoteBuffer(RemoteSegmentRemoteObj.class, this);
/*    */ 
/* 24 */   public RemoteBoolean requestedInitialControlMap = new RemoteBoolean(this);
/*    */   public RemoteControlStructureBuffer initialControlMap;
/*    */   public RemoteInventoryBuffer invetoryBuffer;
/* 29 */   public RemoteBoolean connectionReady = new RemoteBoolean(false, this);
/* 30 */   public RemoteBoolean signalDelete = new RemoteBoolean(false, this);
/*    */ 
/*    */   public NetworkSegmentProvider(StateInterface paramStateInterface, kc paramkc) {
/* 33 */     super(paramStateInterface);
/* 34 */     this.initialControlMap = new RemoteControlStructureBuffer(paramkc, this);
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
 * Qualified Name:     org.schema.game.network.objects.NetworkSegmentProvider
 * JD-Core Version:    0.6.2
 */
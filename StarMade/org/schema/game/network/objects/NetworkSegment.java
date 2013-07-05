/*    */ package org.schema.game.network.objects;
/*    */ 
/*    */ import mw;
/*    */ import org.schema.game.network.objects.remote.RemoteSegmentGZipPackage;
/*    */ import org.schema.schine.network.StateInterface;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ 
/*    */ @Deprecated
/*    */ public class NetworkSegment extends NetworkObject
/*    */ {
/*    */   public RemoteSegmentGZipPackage pack;
/*    */   private mw segment;
/*    */ 
/*    */   public NetworkSegment(StateInterface paramStateInterface, mw parammw)
/*    */   {
/* 17 */     super(paramStateInterface);
/* 18 */     this.segment = parammw;
/* 19 */     this.pack = new RemoteSegmentGZipPackage(parammw, this);
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
 * Qualified Name:     org.schema.game.network.objects.NetworkSegment
 * JD-Core Version:    0.6.2
 */
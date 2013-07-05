/*    */ package org.schema.game.network.objects.remote;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import ka;
/*    */ import kc;
/*    */ import org.schema.game.common.data.element.ControlElementMap;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteField;
/*    */ 
/*    */ public class RemoteControlStructure extends RemoteField
/*    */ {
/*    */   private kc segmentController;
/*    */ 
/*    */   public RemoteControlStructure(kc paramkc, NetworkObject paramNetworkObject)
/*    */   {
/* 19 */     super(Boolean.valueOf(true), paramNetworkObject);
/* 20 */     this.segmentController = paramkc;
/*    */   }
/*    */   public RemoteControlStructure(kc paramkc, boolean paramBoolean) {
/* 23 */     super(Boolean.valueOf(true), paramBoolean);
/* 24 */     this.segmentController = paramkc;
/*    */   }
/*    */   public boolean initialSynchUpdateOnly() {
/* 27 */     return true;
/*    */   }
/*    */ 
/*    */   public int byteLength() {
/* 31 */     return 0;
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt) {
/* 35 */     this.segmentController.a().getControlElementMap().deserializeZipped(paramDataInputStream);
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*    */   {
/* 40 */     this.segmentController.a().getControlElementMap().serializeZipped(paramDataOutputStream);
/* 41 */     return 1;
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream, ka paramka) {
/* 45 */     paramka.getControlElementMap().serializeZipped(paramDataOutputStream);
/* 46 */     return 1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteControlStructure
 * JD-Core Version:    0.6.2
 */
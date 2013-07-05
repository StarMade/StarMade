/*    */ package org.schema.game.network.objects.remote;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import mq;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteField;
/*    */ 
/*    */ public class RemoteProximitySystem extends RemoteField
/*    */ {
/*    */   public RemoteProximitySystem(mq parammq, NetworkObject paramNetworkObject)
/*    */   {
/* 17 */     super(parammq, paramNetworkObject);
/*    */   }
/*    */ 
/*    */   public RemoteProximitySystem(mq parammq, boolean paramBoolean) {
/* 21 */     super(parammq, paramBoolean);
/*    */   }
/*    */ 
/*    */   public int byteLength()
/*    */   {
/* 26 */     return 255;
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*    */   {
/* 33 */     ((mq)get()).a(paramDataInputStream);
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*    */   {
/* 38 */     ((mq)get()).a(paramDataOutputStream);
/* 39 */     return byteLength();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteProximitySystem
 * JD-Core Version:    0.6.2
 */
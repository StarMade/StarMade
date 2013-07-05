/*    */ package org.schema.game.network.objects.remote;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import mp;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteField;
/*    */ 
/*    */ public class RemoteProximitySector extends RemoteField
/*    */ {
/*    */   public RemoteProximitySector(mp parammp, NetworkObject paramNetworkObject)
/*    */   {
/* 17 */     super(parammp, paramNetworkObject);
/*    */   }
/*    */   public RemoteProximitySector(mp parammp, boolean paramBoolean) {
/* 20 */     super(parammp, paramBoolean);
/*    */   }
/*    */ 
/*    */   public int byteLength()
/*    */   {
/* 25 */     return 5504;
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*    */   {
/* 32 */     ((mp)get()).a(paramDataInputStream);
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*    */   {
/* 37 */     ((mp)get()).a(paramDataOutputStream);
/* 38 */     return byteLength();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteProximitySector
 * JD-Core Version:    0.6.2
 */
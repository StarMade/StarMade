/*    */ package org.schema.schine.network.objects.remote;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import javax.vecmath.Vector3f;
/*    */ import org.schema.schine.network.NetworkGravity;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ 
/*    */ public class RemoteGravity extends RemoteField
/*    */ {
/*    */   public RemoteGravity(NetworkGravity paramNetworkGravity, NetworkObject paramNetworkObject)
/*    */   {
/* 16 */     super(paramNetworkGravity, paramNetworkObject);
/*    */   }
/*    */   public RemoteGravity(NetworkGravity paramNetworkGravity, boolean paramBoolean) {
/* 19 */     super(paramNetworkGravity, paramBoolean);
/*    */   }
/*    */ 
/*    */   public int byteLength()
/*    */   {
/* 24 */     return 1;
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt) {
/* 28 */     ((NetworkGravity)get()).gravityIdReceive = paramDataInputStream.readInt();
/* 29 */     ((NetworkGravity)get()).gravityReceive.set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
/* 30 */     ((NetworkGravity)get()).gravityReceived = true;
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*    */   {
/* 37 */     paramDataOutputStream.writeInt(((NetworkGravity)get()).gravityId);
/* 38 */     paramDataOutputStream.writeFloat(((NetworkGravity)get()).gravity.x);
/* 39 */     paramDataOutputStream.writeFloat(((NetworkGravity)get()).gravity.y);
/* 40 */     paramDataOutputStream.writeFloat(((NetworkGravity)get()).gravity.z);
/*    */ 
/* 42 */     return 1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteGravity
 * JD-Core Version:    0.6.2
 */
/*    */ package org.schema.schine.network.objects.remote;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ 
/*    */ public class RemoteInteger extends RemoteComparable
/*    */ {
/*    */   public RemoteInteger(Integer paramInteger, boolean paramBoolean)
/*    */   {
/* 15 */     super(paramInteger, paramBoolean);
/*    */   }
/*    */ 
/*    */   public RemoteInteger(Integer paramInteger, NetworkObject paramNetworkObject) {
/* 19 */     super(paramInteger, paramNetworkObject);
/*    */   }
/*    */   public RemoteInteger(NetworkObject paramNetworkObject) {
/* 22 */     this(Integer.valueOf(0), paramNetworkObject);
/*    */   }
/*    */   public RemoteInteger(boolean paramBoolean) {
/* 25 */     this(Integer.valueOf(0), paramBoolean);
/*    */   }
/*    */ 
/*    */   public int byteLength() {
/* 29 */     return 4;
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt) {
/* 33 */     set(Integer.valueOf(paramDataInputStream.readInt()));
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*    */   {
/* 38 */     paramDataOutputStream.writeInt(((Integer)get()).intValue());
/* 39 */     return 1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteInteger
 * JD-Core Version:    0.6.2
 */
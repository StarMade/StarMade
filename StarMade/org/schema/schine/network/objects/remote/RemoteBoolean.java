/*    */ package org.schema.schine.network.objects.remote;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ 
/*    */ public class RemoteBoolean extends RemoteComparable
/*    */ {
/*    */   public RemoteBoolean(boolean paramBoolean, NetworkObject paramNetworkObject)
/*    */   {
/* 15 */     super(Boolean.valueOf(paramBoolean), paramNetworkObject);
/*    */   }
/*    */   public RemoteBoolean(NetworkObject paramNetworkObject) {
/* 18 */     this(false, paramNetworkObject);
/*    */   }
/*    */   public RemoteBoolean(boolean paramBoolean) {
/* 21 */     this(false, paramBoolean);
/*    */   }
/*    */ 
/*    */   public RemoteBoolean(boolean paramBoolean1, boolean paramBoolean2) {
/* 25 */     super(Boolean.valueOf(paramBoolean1), paramBoolean2);
/*    */   }
/*    */ 
/*    */   public int byteLength() {
/* 29 */     return 1;
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*    */   {
/* 36 */     set(Boolean.valueOf(paramDataInputStream.readBoolean()));
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream) {
/* 40 */     paramDataOutputStream.writeBoolean(((Boolean)get()).booleanValue());
/* 41 */     return 1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteBoolean
 * JD-Core Version:    0.6.2
 */
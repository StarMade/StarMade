/*    */ package org.schema.schine.network.objects.remote;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ 
/*    */ public class RemoteLong extends RemoteComparable
/*    */ {
/*    */   public RemoteLong(Long paramLong, NetworkObject paramNetworkObject)
/*    */   {
/* 14 */     super(paramLong, paramNetworkObject);
/*    */   }
/*    */   public RemoteLong(NetworkObject paramNetworkObject) {
/* 17 */     this(Long.valueOf(0L), paramNetworkObject);
/*    */   }
/*    */   public RemoteLong(boolean paramBoolean) {
/* 20 */     this(0L, paramBoolean);
/*    */   }
/*    */ 
/*    */   public RemoteLong(long paramLong, boolean paramBoolean)
/*    */   {
/* 26 */     super(Long.valueOf(paramLong), paramBoolean);
/*    */   }
/*    */ 
/*    */   public int byteLength() {
/* 30 */     return 8;
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt) {
/* 34 */     set(Long.valueOf(paramDataInputStream.readLong()));
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream) {
/* 38 */     paramDataOutputStream.writeLong(((Long)get()).longValue());
/* 39 */     return byteLength();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteLong
 * JD-Core Version:    0.6.2
 */
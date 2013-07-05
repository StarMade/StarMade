/*    */ package org.schema.schine.network.objects.remote;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ 
/*    */ public class RemoteByte extends RemoteComparable
/*    */ {
/*    */   public RemoteByte(Byte paramByte, NetworkObject paramNetworkObject)
/*    */   {
/* 15 */     super(paramByte, paramNetworkObject);
/*    */   }
/*    */ 
/*    */   public RemoteByte(NetworkObject paramNetworkObject) {
/* 19 */     this(Byte.valueOf((byte)0), paramNetworkObject);
/*    */   }
/*    */   public RemoteByte(boolean paramBoolean) {
/* 22 */     this((byte)0, paramBoolean);
/*    */   }
/*    */ 
/*    */   public RemoteByte(byte paramByte, boolean paramBoolean) {
/* 26 */     super(Byte.valueOf(paramByte), paramBoolean);
/*    */   }
/*    */ 
/*    */   public int byteLength()
/*    */   {
/* 32 */     return 1;
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt) {
/* 36 */     paramDataInputStream = paramDataInputStream.readByte();
/* 37 */     set(Byte.valueOf(paramDataInputStream));
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*    */   {
/* 42 */     paramDataOutputStream.writeByte(((Byte)get()).byteValue());
/* 43 */     return 1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteByte
 * JD-Core Version:    0.6.2
 */
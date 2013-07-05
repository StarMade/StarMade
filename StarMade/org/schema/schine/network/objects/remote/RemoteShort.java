/*    */ package org.schema.schine.network.objects.remote;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ 
/*    */ public class RemoteShort extends RemoteComparable
/*    */ {
/*    */   public RemoteShort(Short paramShort, NetworkObject paramNetworkObject)
/*    */   {
/* 15 */     super(paramShort, paramNetworkObject);
/*    */   }
/*    */ 
/*    */   public RemoteShort(NetworkObject paramNetworkObject) {
/* 19 */     this(Short.valueOf((short)0), paramNetworkObject);
/*    */   }
/*    */ 
/*    */   public RemoteShort(boolean paramBoolean) {
/* 23 */     this((short)0, paramBoolean);
/*    */   }
/*    */ 
/*    */   public RemoteShort(short paramShort, boolean paramBoolean) {
/* 27 */     super(Short.valueOf(paramShort), paramBoolean);
/*    */   }
/*    */ 
/*    */   public int byteLength()
/*    */   {
/* 32 */     return 2;
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt) {
/* 36 */     paramDataInputStream = paramDataInputStream.readShort();
/* 37 */     set(Short.valueOf(paramDataInputStream));
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*    */   {
/* 42 */     paramDataOutputStream.writeShort(((Short)get()).shortValue());
/* 43 */     return 2;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteShort
 * JD-Core Version:    0.6.2
 */
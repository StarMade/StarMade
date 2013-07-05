/*    */ package org.schema.schine.network.objects.remote;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ 
/*    */ public class RemoteFloat extends RemoteComparable
/*    */ {
/*    */   public RemoteFloat(Float paramFloat, NetworkObject paramNetworkObject)
/*    */   {
/* 16 */     super(paramFloat, paramNetworkObject);
/*    */   }
/*    */   public RemoteFloat(NetworkObject paramNetworkObject) {
/* 19 */     this(Float.valueOf(0.0F), paramNetworkObject);
/*    */   }
/*    */   public RemoteFloat(boolean paramBoolean) {
/* 22 */     this(0.0F, paramBoolean);
/*    */   }
/*    */ 
/*    */   public RemoteFloat(float paramFloat, boolean paramBoolean)
/*    */   {
/* 27 */     super(Float.valueOf(paramFloat), paramBoolean);
/*    */   }
/*    */ 
/*    */   public int byteLength()
/*    */   {
/* 32 */     return 4;
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt) {
/* 36 */     set(Float.valueOf(paramDataInputStream.readFloat()));
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream) {
/* 40 */     paramDataOutputStream.writeFloat(((Float)get()).floatValue());
/* 41 */     return 4;
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 45 */     return ((Float)get()).toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteFloat
 * JD-Core Version:    0.6.2
 */
/*    */ package org.schema.schine.network.objects.remote;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ 
/*    */ public class RemoteString extends RemoteComparable
/*    */ {
/*    */   public RemoteString(NetworkObject paramNetworkObject)
/*    */   {
/* 15 */     super("", paramNetworkObject);
/*    */   }
/*    */   public RemoteString(String paramString, NetworkObject paramNetworkObject) {
/* 18 */     super(paramString, paramNetworkObject);
/*    */   }
/*    */ 
/*    */   public RemoteString(boolean paramBoolean)
/*    */   {
/* 26 */     super("", paramBoolean);
/*    */   }
/*    */ 
/*    */   public int byteLength() {
/* 30 */     return ((String)get()).length() + 4;
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*    */   {
/* 35 */     set(paramDataInputStream.readUTF());
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream) {
/* 39 */     paramDataOutputStream.writeUTF((String)get());
/* 40 */     return byteLength();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteString
 * JD-Core Version:    0.6.2
 */
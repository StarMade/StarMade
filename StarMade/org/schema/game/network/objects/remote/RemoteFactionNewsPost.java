/*    */ package org.schema.game.network.objects.remote;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import lW;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteField;
/*    */ 
/*    */ public class RemoteFactionNewsPost extends RemoteField
/*    */ {
/*    */   public RemoteFactionNewsPost(lW paramlW, NetworkObject paramNetworkObject)
/*    */   {
/* 17 */     super(paramlW, paramNetworkObject);
/*    */   }
/*    */   public RemoteFactionNewsPost(lW paramlW, boolean paramBoolean) {
/* 20 */     super(paramlW, paramBoolean);
/*    */   }
/*    */ 
/*    */   public int byteLength()
/*    */   {
/* 25 */     return 4;
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*    */   {
/* 30 */     paramInt = paramDataInputStream.readInt();
/* 31 */     String str1 = paramDataInputStream.readUTF();
/* 32 */     long l = paramDataInputStream.readLong();
/* 33 */     String str2 = paramDataInputStream.readUTF();
/* 34 */     paramDataInputStream = paramDataInputStream.readInt();
/*    */ 
/* 36 */     ((lW)get()).a(paramInt, str1, l, str2, paramDataInputStream);
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*    */   {
/* 43 */     paramDataOutputStream.writeInt(((lW)get()).b());
/* 44 */     paramDataOutputStream.writeUTF(((lW)get()).a());
/* 45 */     paramDataOutputStream.writeLong(((lW)get()).a());
/* 46 */     paramDataOutputStream.writeUTF(((lW)get()).b());
/* 47 */     paramDataOutputStream.writeInt(((lW)get()).a());
/*    */ 
/* 51 */     return byteLength();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteFactionNewsPost
 * JD-Core Version:    0.6.2
 */
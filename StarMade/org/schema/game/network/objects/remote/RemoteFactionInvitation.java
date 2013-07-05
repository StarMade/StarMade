/*    */ package org.schema.game.network.objects.remote;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import lR;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteField;
/*    */ 
/*    */ public class RemoteFactionInvitation extends RemoteField
/*    */ {
/*    */   public RemoteFactionInvitation(lR paramlR, NetworkObject paramNetworkObject)
/*    */   {
/* 17 */     super(paramlR, paramNetworkObject);
/*    */   }
/*    */   public RemoteFactionInvitation(lR paramlR, boolean paramBoolean) {
/* 20 */     super(paramlR, paramBoolean);
/*    */   }
/*    */ 
/*    */   public int byteLength()
/*    */   {
/* 25 */     return 4;
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt) {
/* 29 */     paramInt = paramDataInputStream.readUTF();
/* 30 */     String str = paramDataInputStream.readUTF();
/* 31 */     int i = paramDataInputStream.readInt();
/* 32 */     long l = paramDataInputStream.readLong();
/*    */ 
/* 34 */     ((lR)get()).a(paramInt, str, i, l);
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*    */   {
/* 42 */     paramDataOutputStream.writeUTF(((lR)get()).b());
/* 43 */     paramDataOutputStream.writeUTF(((lR)get()).a());
/* 44 */     paramDataOutputStream.writeInt(((lR)get()).a());
/* 45 */     paramDataOutputStream.writeLong(((lR)get()).a());
/*    */ 
/* 48 */     return byteLength();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteFactionInvitation
 * JD-Core Version:    0.6.2
 */
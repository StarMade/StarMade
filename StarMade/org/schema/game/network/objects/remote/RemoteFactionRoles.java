/*    */ package org.schema.game.network.objects.remote;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import mc;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteField;
/*    */ 
/*    */ public class RemoteFactionRoles extends RemoteField
/*    */ {
/*    */   public RemoteFactionRoles(mc parammc, NetworkObject paramNetworkObject)
/*    */   {
/* 17 */     super(parammc, paramNetworkObject);
/*    */   }
/*    */   public RemoteFactionRoles(mc parammc, boolean paramBoolean) {
/* 20 */     super(parammc, paramBoolean);
/*    */   }
/*    */ 
/*    */   public int byteLength()
/*    */   {
/* 25 */     return 4;
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt) {
/* 29 */     ((mc)get()).a = paramDataInputStream.readInt();
/* 30 */     for (paramInt = 0; paramInt < 5; paramInt++) {
/* 31 */       ((mc)get()).a()[paramInt] = paramDataInputStream.readLong();
/* 32 */       ((mc)get()).a()[paramInt] = paramDataInputStream.readUTF();
/*    */     }
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*    */   {
/* 38 */     paramDataOutputStream.writeInt(((mc)get()).a);
/* 39 */     for (int i = 0; i < 5; i++) {
/* 40 */       paramDataOutputStream.writeLong(((mc)get()).a()[i]);
/* 41 */       paramDataOutputStream.writeUTF(((mc)get()).a()[i]);
/*    */     }
/*    */ 
/* 44 */     return byteLength();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteFactionRoles
 * JD-Core Version:    0.6.2
 */
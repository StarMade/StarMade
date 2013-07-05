/*    */ package org.schema.game.network.objects.remote;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import lw;
/*    */ import org.schema.common.util.ByteUtil;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*    */ 
/*    */ public class RemoteMissileUpdateBuffer extends RemoteBuffer
/*    */ {
/*    */   public RemoteMissileUpdateBuffer(NetworkObject paramNetworkObject)
/*    */   {
/* 21 */     super(RemoteMissileUpdate.class, paramNetworkObject);
/*    */   }
/*    */ 
/*    */   public void cacheConstructor()
/*    */   {
/*    */   }
/*    */ 
/*    */   public void clearReceiveBuffer()
/*    */   {
/* 44 */     getReceiveBuffer().clear();
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*    */   {
/* 49 */     paramInt = ByteUtil.a(paramDataInputStream);
/*    */ 
/* 51 */     for (int i = 0; i < paramInt; i++)
/*    */     {
/* 53 */       Object localObject = lw.a(paramDataInputStream);
/* 54 */       localObject = new RemoteMissileUpdate((lw)localObject, this.onServer);
/* 55 */       getReceiveBuffer().add(localObject);
/*    */     }
/*    */   }
/*    */ 
/*    */   public boolean add(RemoteMissileUpdate paramRemoteMissileUpdate)
/*    */   {
/* 65 */     return super.add(paramRemoteMissileUpdate);
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*    */   {
/* 70 */     int i = 0;
/* 71 */     synchronized ((ObjectArrayList)get())
/*    */     {
/* 73 */       paramDataOutputStream.writeInt(((ObjectArrayList)get()).size());
/* 74 */       i += 4;
/*    */ 
/* 77 */       for (RemoteMissileUpdate localRemoteMissileUpdate : (ObjectArrayList)get()) {
/* 78 */         i += localRemoteMissileUpdate.toByteStream(paramDataOutputStream);
/*    */       }
/*    */ 
/* 81 */       ((ObjectArrayList)get()).clear();
/*    */     }
/*    */ 
/* 84 */     return i;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteMissileUpdateBuffer
 * JD-Core Version:    0.6.2
 */
/*    */ package org.schema.game.network.objects.remote;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import java.lang.reflect.Constructor;
/*    */ import lW;
/*    */ import org.schema.common.util.ByteUtil;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*    */ 
/*    */ public class RemoteFactionNewsPostBuffer extends RemoteBuffer
/*    */ {
/*    */   private static Constructor staticConstructor;
/*    */ 
/*    */   public RemoteFactionNewsPostBuffer(NetworkObject paramNetworkObject)
/*    */   {
/* 32 */     super(RemoteFactionNewsPost.class, paramNetworkObject);
/*    */   }
/*    */ 
/*    */   public void cacheConstructor()
/*    */   {
/*    */   }
/*    */ 
/*    */   public void clearReceiveBuffer() {
/* 40 */     getReceiveBuffer().clear();
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*    */   {
/* 45 */     int i = ByteUtil.a(paramDataInputStream);
/*    */ 
/* 47 */     for (int j = 0; j < i; j++)
/*    */     {
/*    */       RemoteFactionNewsPost localRemoteFactionNewsPost;
/* 48 */       (
/* 49 */         localRemoteFactionNewsPost = (RemoteFactionNewsPost)staticConstructor.newInstance(new Object[] { new lW(), Boolean.valueOf(this.onServer) }))
/* 49 */         .fromByteStream(paramDataInputStream, paramInt);
/* 50 */       getReceiveBuffer().add(localRemoteFactionNewsPost);
/*    */     }
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*    */   {
/* 57 */     int i = 0;
/* 58 */     synchronized ((ObjectArrayList)get())
/*    */     {
/* 60 */       paramDataOutputStream.writeInt(((ObjectArrayList)get()).size());
/* 61 */       i += 4;
/*    */ 
/* 64 */       for (RemoteFactionNewsPost localRemoteFactionNewsPost : (ObjectArrayList)get()) {
/* 65 */         i += localRemoteFactionNewsPost.toByteStream(paramDataOutputStream);
/*    */       }
/*    */ 
/* 68 */       ((ObjectArrayList)get()).clear();
/*    */     }
/*    */ 
/* 71 */     return i;
/*    */   }
/*    */ 
/*    */   static
/*    */   {
/*    */     try
/*    */     {
/* 18 */       if (staticConstructor == null) {
/* 19 */         staticConstructor = RemoteFactionNewsPost.class.getConstructor(new Class[] { lW.class, Boolean.TYPE });
/*    */       }
/*    */ 
/*    */       return;
/*    */     }
/*    */     catch (SecurityException localSecurityException)
/*    */     {
/* 28 */       localSecurityException.printStackTrace();
/*    */ 
/* 24 */       if (!$assertionsDisabled) throw new AssertionError(); return;
/*    */     }
/*    */     catch (NoSuchMethodException localNoSuchMethodException)
/*    */     {
/* 28 */       localNoSuchMethodException.printStackTrace();
/*    */ 
/* 27 */       if (!$assertionsDisabled) throw new AssertionError();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteFactionNewsPostBuffer
 * JD-Core Version:    0.6.2
 */
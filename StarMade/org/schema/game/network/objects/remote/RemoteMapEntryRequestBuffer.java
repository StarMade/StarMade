/*    */ package org.schema.game.network.objects.remote;
/*    */ 
/*    */ import cI;
/*    */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import java.lang.reflect.Constructor;
/*    */ import org.schema.common.util.ByteUtil;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*    */ 
/*    */ public class RemoteMapEntryRequestBuffer extends RemoteBuffer
/*    */ {
/*    */   private static Constructor staticConstructor;
/*    */ 
/*    */   public RemoteMapEntryRequestBuffer(NetworkObject paramNetworkObject)
/*    */   {
/* 34 */     super(RemoteMapEntryRequest.class, paramNetworkObject);
/*    */   }
/*    */ 
/*    */   public void cacheConstructor()
/*    */   {
/*    */   }
/*    */ 
/*    */   public void clearReceiveBuffer()
/*    */   {
/* 43 */     getReceiveBuffer().clear();
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*    */   {
/* 48 */     int i = ByteUtil.a(paramDataInputStream);
/*    */ 
/* 50 */     for (int j = 0; j < i; j++)
/*    */     {
/*    */       RemoteMapEntryRequest localRemoteMapEntryRequest;
/* 51 */       (
/* 52 */         localRemoteMapEntryRequest = (RemoteMapEntryRequest)staticConstructor.newInstance(new Object[] { new cI(), Boolean.valueOf(this.onServer) }))
/* 52 */         .fromByteStream(paramDataInputStream, paramInt);
/* 53 */       getReceiveBuffer().add(localRemoteMapEntryRequest);
/*    */     }
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*    */   {
/* 60 */     int i = 0;
/* 61 */     synchronized ((ObjectArrayList)get())
/*    */     {
/* 63 */       paramDataOutputStream.writeInt(((ObjectArrayList)get()).size());
/* 64 */       i += 4;
/*    */ 
/* 67 */       for (RemoteMapEntryRequest localRemoteMapEntryRequest : (ObjectArrayList)get()) {
/* 68 */         i += localRemoteMapEntryRequest.toByteStream(paramDataOutputStream);
/*    */       }
/*    */ 
/* 71 */       ((ObjectArrayList)get()).clear();
/*    */     }
/*    */ 
/* 74 */     return i;
/*    */   }
/*    */ 
/*    */   static
/*    */   {
/*    */     try
/*    */     {
/* 19 */       if (staticConstructor == null) {
/* 20 */         staticConstructor = RemoteMapEntryRequest.class.getConstructor(new Class[] { cI.class, Boolean.TYPE });
/*    */       }
/*    */ 
/*    */       return;
/*    */     }
/*    */     catch (SecurityException localSecurityException)
/*    */     {
/* 29 */       localSecurityException.printStackTrace();
/*    */ 
/* 25 */       if (!$assertionsDisabled) throw new AssertionError(); return;
/*    */     }
/*    */     catch (NoSuchMethodException localNoSuchMethodException)
/*    */     {
/* 29 */       localNoSuchMethodException.printStackTrace();
/*    */ 
/* 28 */       if (!$assertionsDisabled) throw new AssertionError();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteMapEntryRequestBuffer
 * JD-Core Version:    0.6.2
 */
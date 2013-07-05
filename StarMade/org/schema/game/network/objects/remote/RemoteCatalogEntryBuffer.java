/*    */ package org.schema.game.network.objects.remote;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import java.lang.reflect.Constructor;
/*    */ import lL;
/*    */ import org.schema.common.util.ByteUtil;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*    */ 
/*    */ public class RemoteCatalogEntryBuffer extends RemoteBuffer
/*    */ {
/*    */   private static Constructor staticConstructor;
/*    */ 
/*    */   public RemoteCatalogEntryBuffer(NetworkObject paramNetworkObject)
/*    */   {
/* 33 */     super(RemoteCatalogEntry.class, paramNetworkObject);
/*    */   }
/*    */ 
/*    */   public void cacheConstructor()
/*    */   {
/*    */   }
/*    */ 
/*    */   public void clearReceiveBuffer() {
/* 41 */     getReceiveBuffer().clear();
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*    */   {
/* 46 */     int i = ByteUtil.a(paramDataInputStream);
/*    */ 
/* 48 */     for (int j = 0; j < i; j++)
/*    */     {
/*    */       RemoteCatalogEntry localRemoteCatalogEntry;
/* 49 */       (
/* 50 */         localRemoteCatalogEntry = (RemoteCatalogEntry)staticConstructor.newInstance(new Object[] { new lL(), Boolean.valueOf(this.onServer) }))
/* 50 */         .fromByteStream(paramDataInputStream, paramInt);
/* 51 */       getReceiveBuffer().add(localRemoteCatalogEntry);
/*    */     }
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*    */   {
/* 58 */     int i = 0;
/* 59 */     synchronized ((ObjectArrayList)get())
/*    */     {
/* 61 */       paramDataOutputStream.writeInt(((ObjectArrayList)get()).size());
/* 62 */       i += 4;
/*    */ 
/* 65 */       for (RemoteCatalogEntry localRemoteCatalogEntry : (ObjectArrayList)get()) {
/* 66 */         i += localRemoteCatalogEntry.toByteStream(paramDataOutputStream);
/*    */       }
/*    */ 
/* 69 */       ((ObjectArrayList)get()).clear();
/*    */     }
/*    */ 
/* 72 */     return i;
/*    */   }
/*    */ 
/*    */   static
/*    */   {
/*    */     try
/*    */     {
/* 18 */       if (staticConstructor == null) {
/* 19 */         staticConstructor = RemoteCatalogEntry.class.getConstructor(new Class[] { lL.class, Boolean.TYPE });
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
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteCatalogEntryBuffer
 * JD-Core Version:    0.6.2
 */
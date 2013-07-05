/*    */ package org.schema.game.network.objects.remote;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import java.lang.reflect.Constructor;
/*    */ import mf;
/*    */ import mh;
/*    */ import org.schema.common.util.ByteUtil;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*    */ 
/*    */ public class RemoteInventoryBuffer extends RemoteBuffer
/*    */ {
/*    */   private mh holder;
/*    */   private static Constructor staticConstructor;
/*    */ 
/*    */   public RemoteInventoryBuffer(mh parammh, NetworkObject paramNetworkObject)
/*    */   {
/* 35 */     super(RemoteInventory.class, paramNetworkObject);
/* 36 */     this.holder = parammh;
/*    */   }
/*    */ 
/*    */   public void cacheConstructor()
/*    */   {
/*    */   }
/*    */ 
/*    */   public void clearReceiveBuffer() {
/* 44 */     getReceiveBuffer().clear();
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*    */   {
/* 49 */     int i = ByteUtil.a(paramDataInputStream);
/*    */ 
/* 51 */     for (int j = 0; j < i; j++)
/*    */     {
/*    */       RemoteInventory localRemoteInventory;
/* 52 */       (
/* 53 */         localRemoteInventory = (RemoteInventory)staticConstructor.newInstance(new Object[] { null, this.holder, Boolean.valueOf(false), Boolean.valueOf(this.onServer) }))
/* 53 */         .fromByteStream(paramDataInputStream, paramInt);
/* 54 */       getReceiveBuffer().add(localRemoteInventory);
/*    */     }
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*    */   {
/* 61 */     int i = 0;
/* 62 */     synchronized ((ObjectArrayList)get())
/*    */     {
/* 64 */       paramDataOutputStream.writeInt(((ObjectArrayList)get()).size());
/* 65 */       i += 4;
/*    */ 
/* 68 */       for (RemoteInventory localRemoteInventory : (ObjectArrayList)get()) {
/* 69 */         i += localRemoteInventory.toByteStream(paramDataOutputStream);
/*    */       }
/*    */ 
/* 72 */       ((ObjectArrayList)get()).clear();
/*    */     }
/*    */ 
/* 75 */     return i;
/*    */   }
/*    */ 
/*    */   static
/*    */   {
/*    */     try
/*    */     {
/* 20 */       if (staticConstructor == null) {
/* 21 */         staticConstructor = RemoteInventory.class.getConstructor(new Class[] { mf.class, mh.class, Boolean.TYPE, Boolean.TYPE });
/*    */       }
/*    */ 
/*    */       return;
/*    */     }
/*    */     catch (SecurityException localSecurityException)
/*    */     {
/* 30 */       localSecurityException.printStackTrace();
/*    */ 
/* 26 */       if (!$assertionsDisabled) throw new AssertionError(); return;
/*    */     }
/*    */     catch (NoSuchMethodException localNoSuchMethodException)
/*    */     {
/* 30 */       localNoSuchMethodException.printStackTrace();
/*    */ 
/* 29 */       if (!$assertionsDisabled) throw new AssertionError();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteInventoryBuffer
 * JD-Core Version:    0.6.2
 */
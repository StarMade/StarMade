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
/*    */ public class RemoteInventoryCluster extends RemoteBuffer
/*    */ {
/*    */   private mh holder;
/*    */   private static Constructor staticConstructor;
/*    */ 
/*    */   public RemoteInventoryCluster(mh parammh, NetworkObject paramNetworkObject)
/*    */   {
/* 35 */     super(RemoteInventory.class, paramNetworkObject);
/* 36 */     this.holder = parammh;
/*    */   }
/*    */ 
/*    */   public void cacheConstructor()
/*    */   {
/*    */   }
/*    */ 
/*    */   public void clearReceiveBuffer()
/*    */   {
/* 45 */     getReceiveBuffer().clear();
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*    */   {
/* 50 */     int i = ByteUtil.a(paramDataInputStream);
/*    */ 
/* 52 */     for (int j = 0; j < i; j++)
/*    */     {
/*    */       RemoteInventory localRemoteInventory;
/* 53 */       (
/* 54 */         localRemoteInventory = (RemoteInventory)staticConstructor.newInstance(new Object[] { null, this.holder, Boolean.valueOf(false), Boolean.valueOf(this.onServer) }))
/* 54 */         .fromByteStream(paramDataInputStream, paramInt);
/* 55 */       getReceiveBuffer().add(localRemoteInventory);
/*    */     }
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*    */   {
/* 62 */     int i = 0;
/* 63 */     synchronized ((ObjectArrayList)get())
/*    */     {
/* 65 */       paramDataOutputStream.writeInt(((ObjectArrayList)get()).size());
/* 66 */       i += 4;
/*    */ 
/* 69 */       for (RemoteInventory localRemoteInventory : (ObjectArrayList)get()) {
/* 70 */         i += localRemoteInventory.toByteStream(paramDataOutputStream);
/*    */       }
/*    */ 
/* 73 */       ((ObjectArrayList)get()).clear();
/*    */     }
/*    */ 
/* 76 */     return i;
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
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteInventoryCluster
 * JD-Core Version:    0.6.2
 */
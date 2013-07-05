/*    */ package org.schema.game.network.objects.remote;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import java.lang.reflect.Constructor;
/*    */ import mi;
/*    */ import org.schema.common.util.ByteUtil;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*    */ 
/*    */ public class RemoteInventoryMultModBuffer extends RemoteBuffer
/*    */ {
/*    */   private static Constructor staticConstructor;
/*    */ 
/*    */   public RemoteInventoryMultModBuffer(NetworkObject paramNetworkObject)
/*    */   {
/* 33 */     super(RemoteInventoryMultMod.class, paramNetworkObject);
/*    */   }
/*    */   public RemoteInventoryMultModBuffer(boolean paramBoolean) {
/* 36 */     super(RemoteInventoryMultMod.class, paramBoolean);
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
/*    */       RemoteInventoryMultMod localRemoteInventoryMultMod;
/* 53 */       (
/* 54 */         localRemoteInventoryMultMod = (RemoteInventoryMultMod)staticConstructor.newInstance(new Object[] { new mi(), Boolean.valueOf(this.onServer) }))
/* 54 */         .fromByteStream(paramDataInputStream, paramInt);
/* 55 */       getReceiveBuffer().add(localRemoteInventoryMultMod);
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
/* 69 */       for (RemoteInventoryMultMod localRemoteInventoryMultMod : (ObjectArrayList)get()) {
/* 70 */         i += localRemoteInventoryMultMod.toByteStream(paramDataOutputStream);
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
/* 18 */       if (staticConstructor == null) {
/* 19 */         staticConstructor = RemoteInventoryMultMod.class.getConstructor(new Class[] { mi.class, Boolean.TYPE });
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
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteInventoryMultModBuffer
 * JD-Core Version:    0.6.2
 */
/*    */ package org.schema.game.network.objects.remote;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import java.lang.reflect.Constructor;
/*    */ import me;
/*    */ import org.schema.common.util.ByteUtil;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*    */ 
/*    */ public class RemoteItemBuffer extends RemoteBuffer
/*    */ {
/*    */   private Constructor constructor;
/*    */   private static Constructor staticConstructor;
/*    */ 
/*    */   public RemoteItemBuffer(NetworkObject paramNetworkObject)
/*    */   {
/* 31 */     super(RemoteItem.class, paramNetworkObject);
/*    */   }
/*    */   public RemoteItemBuffer(boolean paramBoolean) {
/* 34 */     super(RemoteItem.class, paramBoolean);
/*    */   }
/*    */ 
/*    */   public void cacheConstructor()
/*    */   {
/* 39 */     this.constructor = staticConstructor;
/*    */   }
/*    */ 
/*    */   public void clearReceiveBuffer() {
/* 43 */     getReceiveBuffer().clear();
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*    */   {
/* 48 */     int i = ByteUtil.a(paramDataInputStream);
/*    */ 
/* 50 */     for (int j = 0; j < i; j++)
/*    */     {
/*    */       RemoteItem localRemoteItem;
/* 51 */       (
/* 52 */         localRemoteItem = (RemoteItem)this.constructor.newInstance(new Object[] { new me(), Boolean.valueOf(false), Boolean.valueOf(this.onServer) }))
/* 52 */         .fromByteStream(paramDataInputStream, paramInt);
/* 53 */       getReceiveBuffer().add(localRemoteItem);
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
/* 67 */       for (RemoteItem localRemoteItem : (ObjectArrayList)get()) {
/* 68 */         i += localRemoteItem.toByteStream(paramDataOutputStream);
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
/* 19 */       staticConstructor = RemoteItem.class.getConstructor(new Class[] { me.class, Boolean.class, Boolean.TYPE });
/*    */       return;
/*    */     }
/*    */     catch (SecurityException localSecurityException)
/*    */     {
/* 26 */       localSecurityException.printStackTrace();
/*    */ 
/* 22 */       if (!$assertionsDisabled) throw new AssertionError(); return;
/*    */     }
/*    */     catch (NoSuchMethodException localNoSuchMethodException)
/*    */     {
/* 26 */       localNoSuchMethodException.printStackTrace();
/*    */ 
/* 25 */       if (!$assertionsDisabled) throw new AssertionError();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteItemBuffer
 * JD-Core Version:    0.6.2
 */
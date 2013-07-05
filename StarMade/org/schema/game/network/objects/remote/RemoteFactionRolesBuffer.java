/*    */ package org.schema.game.network.objects.remote;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import java.lang.reflect.Constructor;
/*    */ import mc;
/*    */ import org.schema.common.util.ByteUtil;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*    */ 
/*    */ public class RemoteFactionRolesBuffer extends RemoteBuffer
/*    */ {
/*    */   private static Constructor staticConstructor;
/*    */ 
/*    */   public RemoteFactionRolesBuffer(boolean paramBoolean)
/*    */   {
/* 32 */     super(RemoteFactionRoles.class, paramBoolean);
/*    */   }
/*    */ 
/*    */   public RemoteFactionRolesBuffer(NetworkObject paramNetworkObject) {
/* 36 */     super(RemoteFactionRoles.class, paramNetworkObject);
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
/*    */       RemoteFactionRoles localRemoteFactionRoles;
/* 52 */       (
/* 53 */         localRemoteFactionRoles = (RemoteFactionRoles)staticConstructor.newInstance(new Object[] { new mc(), Boolean.valueOf(this.onServer) }))
/* 53 */         .fromByteStream(paramDataInputStream, paramInt);
/* 54 */       getReceiveBuffer().add(localRemoteFactionRoles);
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
/* 68 */       for (RemoteFactionRoles localRemoteFactionRoles : (ObjectArrayList)get()) {
/* 69 */         i += localRemoteFactionRoles.toByteStream(paramDataOutputStream);
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
/* 18 */       if (staticConstructor == null) {
/* 19 */         staticConstructor = RemoteFactionRoles.class.getConstructor(new Class[] { mc.class, Boolean.TYPE });
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
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteFactionRolesBuffer
 * JD-Core Version:    0.6.2
 */
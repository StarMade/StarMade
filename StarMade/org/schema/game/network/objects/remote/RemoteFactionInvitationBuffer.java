/*    */ package org.schema.game.network.objects.remote;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import java.lang.reflect.Constructor;
/*    */ import lR;
/*    */ import org.schema.common.util.ByteUtil;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*    */ 
/*    */ public class RemoteFactionInvitationBuffer extends RemoteBuffer
/*    */ {
/*    */   private static Constructor staticConstructor;
/*    */ 
/*    */   public RemoteFactionInvitationBuffer(NetworkObject paramNetworkObject)
/*    */   {
/* 30 */     super(RemoteFactionInvitation.class, paramNetworkObject);
/*    */   }
/*    */ 
/*    */   public void cacheConstructor()
/*    */   {
/*    */   }
/*    */ 
/*    */   public void clearReceiveBuffer() {
/* 38 */     getReceiveBuffer().clear();
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*    */   {
/* 43 */     int i = ByteUtil.a(paramDataInputStream);
/*    */ 
/* 45 */     for (int j = 0; j < i; j++)
/*    */     {
/*    */       RemoteFactionInvitation localRemoteFactionInvitation;
/* 46 */       (
/* 47 */         localRemoteFactionInvitation = (RemoteFactionInvitation)staticConstructor.newInstance(new Object[] { new lR(), Boolean.valueOf(this.onServer) }))
/* 47 */         .fromByteStream(paramDataInputStream, paramInt);
/* 48 */       getReceiveBuffer().add(localRemoteFactionInvitation);
/*    */     }
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*    */   {
/* 55 */     int i = 0;
/* 56 */     synchronized ((ObjectArrayList)get())
/*    */     {
/* 58 */       paramDataOutputStream.writeInt(((ObjectArrayList)get()).size());
/* 59 */       i += 4;
/*    */ 
/* 62 */       for (RemoteFactionInvitation localRemoteFactionInvitation : (ObjectArrayList)get()) {
/* 63 */         i += localRemoteFactionInvitation.toByteStream(paramDataOutputStream);
/*    */       }
/*    */ 
/* 66 */       ((ObjectArrayList)get()).clear();
/*    */     }
/*    */ 
/* 69 */     return i;
/*    */   }
/*    */ 
/*    */   static
/*    */   {
/*    */     try
/*    */     {
/* 18 */       staticConstructor = RemoteFactionInvitation.class.getConstructor(new Class[] { lR.class, Boolean.TYPE });
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
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteFactionInvitationBuffer
 * JD-Core Version:    0.6.2
 */
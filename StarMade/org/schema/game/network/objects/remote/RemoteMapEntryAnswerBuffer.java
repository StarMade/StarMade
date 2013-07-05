/*    */ package org.schema.game.network.objects.remote;
/*    */ 
/*    */ import cH;
/*    */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import java.lang.reflect.Constructor;
/*    */ import org.schema.common.util.ByteUtil;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*    */ 
/*    */ public class RemoteMapEntryAnswerBuffer extends RemoteBuffer
/*    */ {
/*    */   private static Constructor staticConstructor;
/*    */ 
/*    */   public RemoteMapEntryAnswerBuffer(NetworkObject paramNetworkObject)
/*    */   {
/* 36 */     super(RemoteMapEntryAnswer.class, paramNetworkObject);
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
/*    */       RemoteMapEntryAnswer localRemoteMapEntryAnswer;
/* 53 */       (
/* 54 */         localRemoteMapEntryAnswer = (RemoteMapEntryAnswer)staticConstructor.newInstance(new Object[] { new cH(), Boolean.valueOf(this.onServer) }))
/* 54 */         .fromByteStream(paramDataInputStream, paramInt);
/* 55 */       getReceiveBuffer().add(localRemoteMapEntryAnswer);
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
/* 69 */       for (RemoteMapEntryAnswer localRemoteMapEntryAnswer : (ObjectArrayList)get()) {
/* 70 */         i += localRemoteMapEntryAnswer.toByteStream(paramDataOutputStream);
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
/* 21 */       if (staticConstructor == null) {
/* 22 */         staticConstructor = RemoteMapEntryAnswer.class.getConstructor(new Class[] { cH.class, Boolean.TYPE });
/*    */       }
/*    */ 
/*    */       return;
/*    */     }
/*    */     catch (SecurityException localSecurityException)
/*    */     {
/* 31 */       localSecurityException.printStackTrace();
/*    */ 
/* 27 */       if (!$assertionsDisabled) throw new AssertionError(); return;
/*    */     }
/*    */     catch (NoSuchMethodException localNoSuchMethodException)
/*    */     {
/* 31 */       localNoSuchMethodException.printStackTrace();
/*    */ 
/* 30 */       if (!$assertionsDisabled) throw new AssertionError();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteMapEntryAnswerBuffer
 * JD-Core Version:    0.6.2
 */
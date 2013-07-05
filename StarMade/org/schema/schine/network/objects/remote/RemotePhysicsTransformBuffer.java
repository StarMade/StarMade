/*    */ package org.schema.schine.network.objects.remote;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.PrintStream;
/*    */ import java.lang.reflect.Constructor;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.NetworkTransformation;
/*    */ 
/*    */ public class RemotePhysicsTransformBuffer extends RemoteBuffer
/*    */ {
/*    */   private Constructor constructor;
/*    */   private static Constructor staticConstructor;
/*    */ 
/*    */   public RemotePhysicsTransformBuffer(NetworkObject paramNetworkObject)
/*    */   {
/* 20 */     super(RemotePhysicsTransform.class, paramNetworkObject);
/*    */   }
/*    */ 
/*    */   public void cacheConstructor()
/*    */   {
/*    */     try {
/* 26 */       if (staticConstructor == null) {
/* 27 */         staticConstructor = RemotePhysicsTransform.class.getConstructor(new Class[] { NetworkTransformation.class, Boolean.TYPE });
/* 29 */       }this.constructor = staticConstructor;
/*    */       return; } catch (SecurityException localSecurityException) {
/* 31 */       System.err.println("CLASS " + this.clazz);
/* 32 */       localSecurityException.printStackTrace();
/*    */ 
/* 34 */       if (!$assertionsDisabled) throw new AssertionError(); return;
/*    */     } catch (NoSuchMethodException localNoSuchMethodException) {
/* 36 */       System.err.println("CLASS " + this.clazz);
/* 37 */       localNoSuchMethodException.printStackTrace();
/* 38 */       if (!$assertionsDisabled) throw new AssertionError(); 
/*    */     }
/*    */   }
/*    */ 
/*    */   public void clearReceiveBuffer()
/*    */   {
/* 43 */     getReceiveBuffer().clear();
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*    */   {
/* 48 */     int i = paramDataInputStream.readInt();
/*    */ 
/* 50 */     for (int j = 0; j < i; j++)
/*    */     {
/*    */       RemotePhysicsTransform localRemotePhysicsTransform;
/* 51 */       (
/* 52 */         localRemotePhysicsTransform = (RemotePhysicsTransform)this.constructor.newInstance(new Object[] { new NetworkTransformation(), Boolean.valueOf(this.onServer) }))
/* 52 */         .fromByteStream(paramDataInputStream, paramInt);
/* 53 */       getReceiveBuffer().add(localRemotePhysicsTransform);
/*    */     }
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*    */   {
/* 60 */     int i = 0;
/*    */ 
/* 63 */     paramDataOutputStream.writeInt(((ObjectArrayList)get()).size());
/* 64 */     i += 4;
/*    */ 
/* 67 */     for (RemotePhysicsTransform localRemotePhysicsTransform : (ObjectArrayList)get()) {
/* 68 */       i += localRemotePhysicsTransform.toByteStream(paramDataOutputStream);
/*    */     }
/*    */ 
/* 71 */     ((ObjectArrayList)get()).clear();
/* 72 */     if (!this.onServer) {
/* 73 */       System.err.println("###########################----------------> SENT " + this);
/*    */     }
/*    */ 
/* 76 */     return i;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemotePhysicsTransformBuffer
 * JD-Core Version:    0.6.2
 */
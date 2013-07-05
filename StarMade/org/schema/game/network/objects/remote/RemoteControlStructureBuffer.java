/*    */ package org.schema.game.network.objects.remote;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.PrintStream;
/*    */ import java.lang.reflect.Constructor;
/*    */ import ka;
/*    */ import kc;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*    */ 
/*    */ public class RemoteControlStructureBuffer extends RemoteBuffer
/*    */ {
/*    */   private kc segmentProvider;
/*    */   private Constructor constructor;
/*    */   private static Constructor staticConstructor;
/*    */ 
/*    */   public RemoteControlStructureBuffer(kc paramkc, NetworkObject paramNetworkObject)
/*    */   {
/* 23 */     super(RemoteControlStructure.class, paramNetworkObject);
/* 24 */     this.segmentProvider = paramkc;
/*    */   }
/*    */   public RemoteControlStructureBuffer(kc paramkc, boolean paramBoolean) {
/* 27 */     super(RemoteControlStructure.class, paramBoolean);
/* 28 */     this.segmentProvider = paramkc;
/*    */   }
/*    */ 
/*    */   public void cacheConstructor()
/*    */   {
/*    */     try {
/* 34 */       if (staticConstructor == null) {
/* 35 */         staticConstructor = RemoteControlStructure.class.getConstructor(new Class[] { kc.class, Boolean.TYPE });
/* 37 */       }this.constructor = staticConstructor;
/*    */       return; } catch (SecurityException localSecurityException) {
/* 39 */       System.err.println("CLASS " + this.clazz);
/* 40 */       localSecurityException.printStackTrace();
/*    */ 
/* 42 */       if (!$assertionsDisabled) throw new AssertionError(); return;
/*    */     } catch (NoSuchMethodException localNoSuchMethodException) {
/* 44 */       System.err.println("CLASS " + this.clazz);
/* 45 */       localNoSuchMethodException.printStackTrace();
/* 46 */       if (!$assertionsDisabled) throw new AssertionError(); 
/*    */     }
/*    */   }
/*    */ 
/*    */   public void clearReceiveBuffer()
/*    */   {
/* 51 */     getReceiveBuffer().clear();
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*    */   {
/* 56 */     int i = paramDataInputStream.readInt();
/*    */ 
/* 58 */     for (int j = 0; j < i; j++)
/* 59 */       ((RemoteControlStructure)this.constructor.newInstance(new Object[] { this.segmentProvider, Boolean.valueOf(this.onServer) }))
/* 60 */         .fromByteStream(paramDataInputStream, paramInt);
/*    */   }
/*    */ 
/*    */   public boolean add(RemoteControlStructure paramRemoteControlStructure)
/*    */   {
/* 70 */     return super.add(paramRemoteControlStructure);
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream) {
/* 74 */     int i = 0;
/*    */     ka localka;
/* 81 */     if ((
/* 81 */       localka = this.segmentProvider.a()) != null)
/*    */     {
/* 83 */       paramDataOutputStream.writeInt(((ObjectArrayList)get()).size());
/* 84 */       i += 4;
/* 85 */       for (RemoteControlStructure localRemoteControlStructure : (ObjectArrayList)get())
/* 86 */         i += localRemoteControlStructure.toByteStream(paramDataOutputStream, localka);
/*    */     }
/*    */     else {
/* 89 */       paramDataOutputStream.writeInt(0);
/*    */     }
/* 91 */     ((ObjectArrayList)get()).clear();
/*    */ 
/* 93 */     return i;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteControlStructureBuffer
 * JD-Core Version:    0.6.2
 */
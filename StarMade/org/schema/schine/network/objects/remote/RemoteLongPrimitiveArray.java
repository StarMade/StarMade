/*     */ package org.schema.schine.network.objects.remote;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ 
/*     */ public class RemoteLongPrimitiveArray
/*     */   implements Streamable, StreamableArray
/*     */ {
/*     */   private boolean changed;
/*     */   private NetworkChangeObserver observer;
/*     */   protected boolean keepChanged;
/*     */   private final boolean onServer;
/*     */   private boolean forcedClientSending;
/*     */   private long[] array;
/*     */ 
/*     */   public RemoteLongPrimitiveArray(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
/*     */   {
/*  21 */     this.array = new long[paramInt];
/*     */ 
/*  23 */     this.onServer = paramBoolean2;
/*  24 */     this.changed = paramBoolean1;
/*     */   }
/*     */ 
/*     */   public RemoteLongPrimitiveArray(int paramInt, boolean paramBoolean, NetworkObject paramNetworkObject) {
/*  28 */     this(paramInt, paramBoolean, paramNetworkObject.isOnServer());
/*  29 */     assert (paramNetworkObject != null);
/*     */   }
/*     */   public RemoteLongPrimitiveArray(int paramInt, boolean paramBoolean) {
/*  32 */     this(paramInt, false, paramBoolean);
/*     */   }
/*     */   public RemoteLongPrimitiveArray(int paramInt, NetworkObject paramNetworkObject) {
/*  35 */     this(paramInt, false, paramNetworkObject);
/*     */   }
/*     */ 
/*     */   public boolean hasChanged()
/*     */   {
/*  40 */     return this.changed;
/*     */   }
/*     */ 
/*     */   public void setObserver(NetworkChangeObserver paramNetworkChangeObserver)
/*     */   {
/*  45 */     this.observer = paramNetworkChangeObserver;
/*     */   }
/*     */ 
/*     */   public void setChanged(boolean paramBoolean)
/*     */   {
/*  50 */     this.changed = paramBoolean;
/*     */   }
/*     */ 
/*     */   public boolean keepChanged()
/*     */   {
/*  55 */     return this.keepChanged;
/*     */   }
/*     */ 
/*     */   public int byteLength()
/*     */   {
/*  60 */     return 4;
/*     */   }
/*     */ 
/*     */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*     */   {
/*  66 */     for (paramInt = 0; paramInt < this.array.length; paramInt++)
/*  67 */       set(paramInt, paramDataInputStream.readLong(), this.forcedClientSending);
/*     */   }
/*     */ 
/*     */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*     */   {
/*  73 */     for (int i = 0; i < this.array.length; i++) {
/*  74 */       paramDataOutputStream.writeLong(this.array[i]);
/*     */     }
/*  76 */     return 4;
/*     */   }
/*     */   public void forceClientUpdates() {
/*  79 */     this.forcedClientSending = true;
/*     */   }
/*     */   public void set(long[] paramArrayOfLong) {
/*  82 */     set(paramArrayOfLong, this.forcedClientSending);
/*     */   }
/*     */ 
/*     */   public void set(Long[] paramArrayOfLong) {
/*  86 */     for (int i = 0; i < paramArrayOfLong.length; i++)
/*  87 */       set(i, paramArrayOfLong[i].longValue(), this.forcedClientSending);
/*     */   }
/*     */ 
/*     */   public void set(int paramInt, long paramLong) {
/*  91 */     set(paramInt, paramLong, this.forcedClientSending);
/*     */   }
/*     */   public void set(int paramInt, long paramLong, boolean paramBoolean) {
/*  94 */     if ((this.onServer) || (paramBoolean))
/*     */     {
/*  96 */       setChanged((hasChanged()) || (paramLong != this.array[paramInt]));
/*     */     }
/*  98 */     this.array[paramInt] = paramLong;
/*     */ 
/* 100 */     if ((hasChanged()) && (this.observer != null))
/*     */     {
/* 102 */       this.observer.update(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   public long[] getLongArray()
/*     */   {
/* 109 */     return this.array;
/*     */   }
/*     */   public void set(long[] paramArrayOfLong, boolean paramBoolean) {
/* 112 */     for (int i = 0; i < paramArrayOfLong.length; i++)
/* 113 */       set(i, paramArrayOfLong[i], paramBoolean);
/*     */   }
/*     */ 
/*     */   public void set(Long[] paramArrayOfLong, boolean paramBoolean)
/*     */   {
/* 118 */     for (int i = 0; i < paramArrayOfLong.length; i++)
/* 119 */       set(i, paramArrayOfLong[i].longValue(), paramBoolean);
/*     */   }
/*     */ 
/*     */   public Long[] get()
/*     */   {
/* 129 */     if (!$assertionsDisabled) throw new AssertionError();
/* 130 */     Long[] arrayOfLong = new Long[this.array.length];
/* 131 */     for (int i = 0; i < arrayOfLong.length; i++) {
/* 132 */       arrayOfLong[i] = Long.valueOf(this.array[i]);
/*     */     }
/* 134 */     return arrayOfLong;
/*     */   }
/*     */ 
/*     */   public void cleanAtRelease()
/*     */   {
/*     */   }
/*     */ 
/*     */   public int arrayLength()
/*     */   {
/* 145 */     return this.array.length;
/*     */   }
/*     */ 
/*     */   public boolean initialSynchUpdateOnly()
/*     */   {
/* 151 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteLongPrimitiveArray
 * JD-Core Version:    0.6.2
 */
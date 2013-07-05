/*     */ package org.schema.schine.network.objects.remote;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ 
/*     */ public class RemoteLongPrimitive
/*     */   implements Streamable
/*     */ {
/*     */   private boolean changed;
/*     */   private NetworkChangeObserver observer;
/*     */   protected boolean keepChanged;
/*     */   private long value;
/*     */   private final boolean onServer;
/*     */   private boolean forcedClientSending;
/*     */ 
/*     */   public RemoteLongPrimitive(long paramLong, boolean paramBoolean1, boolean paramBoolean2)
/*     */   {
/*  19 */     this.value = paramLong;
/*     */ 
/*  21 */     this.onServer = paramBoolean2;
/*  22 */     this.changed = paramBoolean1;
/*     */   }
/*     */ 
/*     */   public RemoteLongPrimitive(long paramLong, boolean paramBoolean, NetworkObject paramNetworkObject) {
/*  26 */     this(paramLong, paramBoolean, paramNetworkObject.isOnServer());
/*  27 */     assert (paramNetworkObject != null);
/*     */   }
/*     */   public RemoteLongPrimitive(long paramLong, boolean paramBoolean) {
/*  30 */     this(paramLong, false, paramBoolean);
/*     */   }
/*     */   public RemoteLongPrimitive(long paramLong, NetworkObject paramNetworkObject) {
/*  33 */     this(paramLong, false, paramNetworkObject);
/*     */   }
/*     */ 
/*     */   public boolean hasChanged()
/*     */   {
/*  38 */     return this.changed;
/*     */   }
/*     */ 
/*     */   public void setObserver(NetworkChangeObserver paramNetworkChangeObserver)
/*     */   {
/*  43 */     this.observer = paramNetworkChangeObserver;
/*     */   }
/*     */ 
/*     */   public void setChanged(boolean paramBoolean)
/*     */   {
/*  48 */     this.changed = paramBoolean;
/*     */   }
/*     */ 
/*     */   public boolean keepChanged()
/*     */   {
/*  53 */     return this.keepChanged;
/*     */   }
/*     */ 
/*     */   public int byteLength()
/*     */   {
/*  58 */     return 4;
/*     */   }
/*     */ 
/*     */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*     */   {
/*  64 */     set(paramDataInputStream.readLong());
/*     */   }
/*     */ 
/*     */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*     */   {
/*  69 */     paramDataOutputStream.writeLong(this.value);
/*  70 */     return 4;
/*     */   }
/*     */   public void forceClientUpdates() {
/*  73 */     this.forcedClientSending = true;
/*     */   }
/*     */   public void set(long paramLong) {
/*  76 */     set(paramLong, this.forcedClientSending);
/*     */   }
/*     */ 
/*     */   public void set(Long paramLong) {
/*  80 */     set(paramLong.longValue());
/*     */   }
/*     */   public void set(long paramLong, boolean paramBoolean) {
/*  83 */     if ((this.onServer) || (paramBoolean))
/*     */     {
/*  85 */       setChanged((hasChanged()) || (paramLong != this.value));
/*     */     }
/*  87 */     this.value = paramLong;
/*     */ 
/*  89 */     if ((hasChanged()) && (this.observer != null))
/*     */     {
/*  91 */       this.observer.update(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void set(Long paramLong, boolean paramBoolean)
/*     */   {
/*  98 */     set(paramLong);
/*     */   }
/*     */ 
/*     */   public long getLong() {
/* 102 */     return this.value;
/*     */   }
/*     */ 
/*     */   public Long get() {
/* 106 */     return Long.valueOf(this.value);
/*     */   }
/*     */ 
/*     */   public void cleanAtRelease()
/*     */   {
/*     */   }
/*     */ 
/*     */   public boolean initialSynchUpdateOnly()
/*     */   {
/* 118 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteLongPrimitive
 * JD-Core Version:    0.6.2
 */
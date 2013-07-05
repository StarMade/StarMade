/*     */ package org.schema.schine.network.objects.remote;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ 
/*     */ public abstract class RemoteField
/*     */   implements Streamable
/*     */ {
/*     */   protected NetworkChangeObserver observer;
/*     */   private boolean changed;
/*     */   private Object value;
/*     */   public final boolean onServer;
/*     */   protected boolean keepChanged;
/*  22 */   protected boolean forcedClientSending = false;
/*     */ 
/*     */   public RemoteField(Object paramObject, boolean paramBoolean1, boolean paramBoolean2)
/*     */   {
/*  26 */     this.value = paramObject;
/*     */ 
/*  28 */     this.onServer = paramBoolean2;
/*  29 */     this.changed = paramBoolean1;
/*     */   }
/*     */ 
/*     */   public RemoteField(Object paramObject, boolean paramBoolean, NetworkObject paramNetworkObject) {
/*  33 */     this(paramObject, paramBoolean, paramNetworkObject.isOnServer());
/*  34 */     assert (paramNetworkObject != null);
/*     */   }
/*     */   public RemoteField(Object paramObject, boolean paramBoolean) {
/*  37 */     this(paramObject, false, paramBoolean);
/*     */   }
/*     */   public RemoteField(Object paramObject, NetworkObject paramNetworkObject) {
/*  40 */     this(paramObject, false, paramNetworkObject);
/*     */   }
/*     */ 
/*     */   public void forceClientUpdates() {
/*  44 */     this.forcedClientSending = true;
/*     */   }
/*     */ 
/*     */   public abstract void fromByteStream(DataInputStream paramDataInputStream, int paramInt);
/*     */ 
/*     */   public void cleanAtRelease()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Object get() {
/*  54 */     return this.value;
/*     */   }
/*     */ 
/*     */   public final boolean hasChanged()
/*     */   {
/*  66 */     return this.changed;
/*     */   }
/*     */ 
/*     */   public boolean keepChanged()
/*     */   {
/*  76 */     return this.keepChanged;
/*     */   }
/*     */   public boolean initialSynchUpdateOnly() {
/*  79 */     return false;
/*     */   }
/*     */ 
/*     */   public void set(Object paramObject) {
/*  83 */     this.value = paramObject;
/*     */   }
/*     */   public void set(Object paramObject, boolean paramBoolean) {
/*  86 */     set(paramObject);
/*     */   }
/*     */ 
/*     */   public synchronized void setObserver(NetworkChangeObserver paramNetworkChangeObserver)
/*     */   {
/*  96 */     this.observer = paramNetworkChangeObserver;
/*     */   }
/*     */ 
/*     */   public void setChanged(boolean paramBoolean)
/*     */   {
/* 103 */     this.changed = paramBoolean;
/*     */   }
/*     */ 
/*     */   public abstract int toByteStream(DataOutputStream paramDataOutputStream);
/*     */ 
/*     */   public String toString()
/*     */   {
/* 110 */     return this.value.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteField
 * JD-Core Version:    0.6.2
 */
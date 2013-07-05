/*     */ package org.schema.schine.network.objects.remote;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ 
/*     */ public class RemoteIntPrimitive
/*     */   implements Streamable
/*     */ {
/*     */   private boolean changed;
/*     */   private NetworkChangeObserver observer;
/*     */   protected boolean keepChanged;
/*     */   private int value;
/*     */   private final boolean onServer;
/*     */   private boolean forcedClientSending;
/*     */ 
/*     */   public RemoteIntPrimitive(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
/*     */   {
/*  19 */     this.value = paramInt;
/*     */ 
/*  21 */     this.onServer = paramBoolean2;
/*  22 */     this.changed = paramBoolean1;
/*     */   }
/*     */ 
/*     */   public RemoteIntPrimitive(int paramInt, boolean paramBoolean, NetworkObject paramNetworkObject) {
/*  26 */     this(paramInt, paramBoolean, paramNetworkObject.isOnServer());
/*  27 */     assert (paramNetworkObject != null);
/*     */   }
/*     */   public RemoteIntPrimitive(int paramInt, boolean paramBoolean) {
/*  30 */     this(paramInt, false, paramBoolean);
/*     */   }
/*     */   public RemoteIntPrimitive(int paramInt, NetworkObject paramNetworkObject) {
/*  33 */     this(paramInt, false, paramNetworkObject);
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
/*  64 */     set(paramDataInputStream.readInt());
/*     */   }
/*     */ 
/*     */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*     */   {
/*  69 */     paramDataOutputStream.writeInt(this.value);
/*  70 */     return 4;
/*     */   }
/*     */   public void forceClientUpdates() {
/*  73 */     this.forcedClientSending = true;
/*     */   }
/*     */   public void set(int paramInt) {
/*  76 */     set(paramInt, this.forcedClientSending);
/*     */   }
/*     */ 
/*     */   public void set(Integer paramInteger) {
/*  80 */     set(paramInteger.intValue());
/*     */   }
/*     */   public void set(int paramInt, boolean paramBoolean) {
/*  83 */     if ((this.onServer) || (paramBoolean))
/*     */     {
/*  85 */       setChanged((hasChanged()) || (paramInt != this.value));
/*     */     }
/*  87 */     this.value = paramInt;
/*     */ 
/*  89 */     if ((hasChanged()) && (this.observer != null))
/*     */     {
/*  91 */       this.observer.update(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void set(Integer paramInteger, boolean paramBoolean)
/*     */   {
/*  98 */     set(paramInteger);
/*     */   }
/*     */ 
/*     */   public int getInt() {
/* 102 */     return this.value;
/*     */   }
/*     */ 
/*     */   public Integer get() {
/* 106 */     return Integer.valueOf(this.value);
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
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteIntPrimitive
 * JD-Core Version:    0.6.2
 */
/*     */ package org.schema.schine.network.objects.remote;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ 
/*     */ public class RemoteFloatPrimitive
/*     */   implements Streamable
/*     */ {
/*     */   private boolean changed;
/*     */   private NetworkChangeObserver observer;
/*     */   protected boolean keepChanged;
/*     */   private float value;
/*     */   private final boolean onServer;
/*     */   private boolean forcedClientSending;
/*     */ 
/*     */   public RemoteFloatPrimitive(float paramFloat, boolean paramBoolean1, boolean paramBoolean2)
/*     */   {
/*  19 */     this.value = paramFloat;
/*     */ 
/*  21 */     this.onServer = paramBoolean2;
/*  22 */     this.changed = paramBoolean1;
/*     */   }
/*     */ 
/*     */   public RemoteFloatPrimitive(float paramFloat, boolean paramBoolean, NetworkObject paramNetworkObject) {
/*  26 */     this(paramFloat, paramBoolean, paramNetworkObject.isOnServer());
/*  27 */     assert (paramNetworkObject != null);
/*     */   }
/*     */   public RemoteFloatPrimitive(float paramFloat, boolean paramBoolean) {
/*  30 */     this(paramFloat, false, paramBoolean);
/*     */   }
/*     */   public RemoteFloatPrimitive(float paramFloat, NetworkObject paramNetworkObject) {
/*  33 */     this(paramFloat, false, paramNetworkObject);
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
/*  64 */     set(paramDataInputStream.readFloat());
/*     */   }
/*     */ 
/*     */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*     */   {
/*  69 */     paramDataOutputStream.writeFloat(this.value);
/*  70 */     return 4;
/*     */   }
/*     */   public void forceClientUpdates() {
/*  73 */     this.forcedClientSending = true;
/*     */   }
/*     */   public void set(float paramFloat) {
/*  76 */     set(paramFloat, this.forcedClientSending);
/*     */   }
/*     */ 
/*     */   public void set(Float paramFloat) {
/*  80 */     set(paramFloat.floatValue());
/*     */   }
/*     */   public void set(float paramFloat, boolean paramBoolean) {
/*  83 */     if ((this.onServer) || (paramBoolean))
/*     */     {
/*  85 */       setChanged((hasChanged()) || (paramFloat != this.value));
/*     */     }
/*  87 */     this.value = paramFloat;
/*     */ 
/*  89 */     if ((hasChanged()) && (this.observer != null))
/*     */     {
/*  91 */       this.observer.update(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void set(Float paramFloat, boolean paramBoolean)
/*     */   {
/*  98 */     set(paramFloat);
/*     */   }
/*     */ 
/*     */   public float getFloat() {
/* 102 */     return this.value;
/*     */   }
/*     */ 
/*     */   public Float get() {
/* 106 */     return Float.valueOf(this.value);
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
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteFloatPrimitive
 * JD-Core Version:    0.6.2
 */
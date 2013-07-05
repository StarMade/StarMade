/*     */ package org.schema.schine.network.objects.remote;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ 
/*     */ public class RemoteFloatPrimitiveArray
/*     */   implements Streamable, StreamableArray
/*     */ {
/*     */   private boolean changed;
/*     */   private NetworkChangeObserver observer;
/*     */   protected boolean keepChanged;
/*     */   private final boolean onServer;
/*     */   private boolean forcedClientSending;
/*     */   private float[] array;
/*     */ 
/*     */   public RemoteFloatPrimitiveArray(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
/*     */   {
/*  21 */     this.array = new float[paramInt];
/*     */ 
/*  23 */     this.onServer = paramBoolean2;
/*  24 */     this.changed = paramBoolean1;
/*     */   }
/*     */ 
/*     */   public RemoteFloatPrimitiveArray(int paramInt, boolean paramBoolean, NetworkObject paramNetworkObject) {
/*  28 */     this(paramInt, paramBoolean, paramNetworkObject.isOnServer());
/*  29 */     assert (paramNetworkObject != null);
/*     */   }
/*     */   public RemoteFloatPrimitiveArray(int paramInt, boolean paramBoolean) {
/*  32 */     this(paramInt, false, paramBoolean);
/*     */   }
/*     */   public RemoteFloatPrimitiveArray(int paramInt, NetworkObject paramNetworkObject) {
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
/*  67 */       set(paramInt, paramDataInputStream.readFloat(), this.forcedClientSending);
/*     */   }
/*     */ 
/*     */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*     */   {
/*  73 */     for (int i = 0; i < this.array.length; i++) {
/*  74 */       paramDataOutputStream.writeFloat(this.array[i]);
/*     */     }
/*  76 */     return 4;
/*     */   }
/*     */   public void forceClientUpdates() {
/*  79 */     this.forcedClientSending = true;
/*     */   }
/*     */   public void set(float[] paramArrayOfFloat) {
/*  82 */     set(paramArrayOfFloat, this.forcedClientSending);
/*     */   }
/*     */ 
/*     */   public void set(Float[] paramArrayOfFloat) {
/*  86 */     for (int i = 0; i < paramArrayOfFloat.length; i++)
/*  87 */       set(i, paramArrayOfFloat[i].floatValue(), this.forcedClientSending);
/*     */   }
/*     */ 
/*     */   public void set(int paramInt, float paramFloat) {
/*  91 */     set(paramInt, paramFloat, this.forcedClientSending);
/*     */   }
/*     */   public void set(int paramInt, float paramFloat, boolean paramBoolean) {
/*  94 */     if ((this.onServer) || (paramBoolean))
/*     */     {
/*  96 */       setChanged((hasChanged()) || (paramFloat != this.array[paramInt]));
/*     */     }
/*  98 */     this.array[paramInt] = paramFloat;
/*     */ 
/* 100 */     if ((hasChanged()) && (this.observer != null))
/*     */     {
/* 102 */       this.observer.update(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   public float[] getFloatArray()
/*     */   {
/* 109 */     return this.array;
/*     */   }
/*     */   public void set(float[] paramArrayOfFloat, boolean paramBoolean) {
/* 112 */     for (int i = 0; i < paramArrayOfFloat.length; i++)
/* 113 */       set(i, paramArrayOfFloat[i], paramBoolean);
/*     */   }
/*     */ 
/*     */   public void set(Float[] paramArrayOfFloat, boolean paramBoolean)
/*     */   {
/* 118 */     for (int i = 0; i < paramArrayOfFloat.length; i++)
/* 119 */       set(i, paramArrayOfFloat[i].floatValue(), paramBoolean);
/*     */   }
/*     */ 
/*     */   public Float[] get()
/*     */   {
/* 129 */     if (!$assertionsDisabled) throw new AssertionError();
/* 130 */     Float[] arrayOfFloat = new Float[this.array.length];
/* 131 */     for (int i = 0; i < arrayOfFloat.length; i++) {
/* 132 */       arrayOfFloat[i] = Float.valueOf(this.array[i]);
/*     */     }
/* 134 */     return arrayOfFloat;
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
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteFloatPrimitiveArray
 * JD-Core Version:    0.6.2
 */
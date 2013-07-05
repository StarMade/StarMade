/*     */ package org.schema.schine.network.objects.remote;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ 
/*     */ public class RemoteIntArray
/*     */   implements Streamable, StreamableArray
/*     */ {
/*     */   private boolean changed;
/*     */   private NetworkChangeObserver observer;
/*     */   protected boolean keepChanged;
/*     */   private final boolean onServer;
/*     */   private boolean forcedClientSending;
/*     */   private int[] array;
/*     */ 
/*     */   public RemoteIntArray(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
/*     */   {
/*  21 */     this.array = new int[paramInt];
/*     */ 
/*  23 */     this.onServer = paramBoolean2;
/*  24 */     this.changed = paramBoolean1;
/*     */   }
/*     */ 
/*     */   public RemoteIntArray(int paramInt, boolean paramBoolean, NetworkObject paramNetworkObject) {
/*  28 */     this(paramInt, paramBoolean, paramNetworkObject.isOnServer());
/*  29 */     assert (paramNetworkObject != null);
/*     */   }
/*     */   public RemoteIntArray(int paramInt, boolean paramBoolean) {
/*  32 */     this(paramInt, false, paramBoolean);
/*     */   }
/*     */   public RemoteIntArray(int paramInt, NetworkObject paramNetworkObject) {
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
/*  67 */       set(paramInt, paramDataInputStream.readInt(), this.forcedClientSending);
/*     */   }
/*     */ 
/*     */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*     */   {
/*  73 */     for (int i = 0; i < this.array.length; i++) {
/*  74 */       paramDataOutputStream.writeInt(this.array[i]);
/*     */     }
/*  76 */     return 4;
/*     */   }
/*     */   public void forceClientUpdates() {
/*  79 */     this.forcedClientSending = true;
/*     */   }
/*     */   public void set(int[] paramArrayOfInt) {
/*  82 */     set(paramArrayOfInt, this.forcedClientSending);
/*     */   }
/*     */ 
/*     */   public void set(Integer[] paramArrayOfInteger) {
/*  86 */     for (int i = 0; i < paramArrayOfInteger.length; i++)
/*  87 */       set(i, paramArrayOfInteger[i].intValue(), this.forcedClientSending);
/*     */   }
/*     */ 
/*     */   public void set(int paramInt1, int paramInt2) {
/*  91 */     set(paramInt1, paramInt2, this.forcedClientSending);
/*     */   }
/*     */   public void set(int paramInt1, int paramInt2, boolean paramBoolean) {
/*  94 */     if ((this.onServer) || (paramBoolean))
/*     */     {
/*  96 */       setChanged((hasChanged()) || (paramInt2 != this.array[paramInt1]));
/*     */     }
/*  98 */     this.array[paramInt1] = paramInt2;
/*     */ 
/* 100 */     if ((hasChanged()) && (this.observer != null))
/*     */     {
/* 102 */       this.observer.update(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   public int[] getIntArray()
/*     */   {
/* 109 */     return this.array;
/*     */   }
/*     */   public void set(int[] paramArrayOfInt, boolean paramBoolean) {
/* 112 */     for (int i = 0; i < paramArrayOfInt.length; i++)
/* 113 */       set(i, paramArrayOfInt[i], paramBoolean);
/*     */   }
/*     */ 
/*     */   public void set(Integer[] paramArrayOfInteger, boolean paramBoolean)
/*     */   {
/* 118 */     for (int i = 0; i < paramArrayOfInteger.length; i++)
/* 119 */       set(i, paramArrayOfInteger[i].intValue(), paramBoolean);
/*     */   }
/*     */ 
/*     */   public Integer[] get()
/*     */   {
/* 129 */     if (!$assertionsDisabled) throw new AssertionError();
/* 130 */     Integer[] arrayOfInteger = new Integer[this.array.length];
/* 131 */     for (int i = 0; i < arrayOfInteger.length; i++) {
/* 132 */       arrayOfInteger[i] = Integer.valueOf(this.array[i]);
/*     */     }
/* 134 */     return arrayOfInteger;
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
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteIntArray
 * JD-Core Version:    0.6.2
 */
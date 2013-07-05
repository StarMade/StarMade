/*     */ package org.schema.schine.network.objects.remote;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ import org.schema.schine.network.objects.remote.pool.ArrayBufferPool;
/*     */ 
/*     */ public class RemoteArrayBuffer extends RemoteBuffer
/*     */ {
/*     */   private ArrayBufferPool pool;
/*     */   private final int arraySize;
/*     */ 
/*     */   public RemoteArrayBuffer(int paramInt, Class paramClass, NetworkObject paramNetworkObject)
/*     */   {
/*  18 */     super(paramClass, paramNetworkObject);
/*     */ 
/*  20 */     this.arraySize = paramInt;
/*  21 */     cacheConstructor();
/*     */   }
/*     */   public RemoteArrayBuffer(int paramInt, Class paramClass, boolean paramBoolean) {
/*  24 */     super(paramClass, paramBoolean);
/*     */ 
/*  26 */     this.arraySize = paramInt;
/*  27 */     cacheConstructor();
/*     */   }
/*     */ 
/*     */   public boolean add(RemoteArray paramRemoteArray)
/*     */   {
/*  35 */     assert (((RemoteField[])paramRemoteArray.get()).length == this.arraySize) : ("Invalid Array Size: " + ((RemoteField[])paramRemoteArray.get()).length + " != " + this.arraySize);
/*  36 */     return super.add(paramRemoteArray);
/*     */   }
/*     */ 
/*     */   public int byteLength()
/*     */   {
/*  45 */     return 4;
/*     */   }
/*     */ 
/*     */   public void cacheConstructor()
/*     */   {
/*  50 */     this.pool = ArrayBufferPool.get(this.clazz, Integer.valueOf(this.arraySize));
/*     */   }
/*     */ 
/*     */   public void clearReceiveBuffer() {
/*  54 */     for (int i = 0; i < getReceiveBuffer().size(); i++) {
/*  55 */       this.pool.release((StreamableArray)getReceiveBuffer().get(i));
/*     */     }
/*  57 */     getReceiveBuffer().clear();
/*     */   }
/*     */ 
/*     */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt) {
/*  61 */     int i = paramDataInputStream.readInt();
/*     */ 
/*  65 */     for (int j = 0; j < i; j++) {
/*  66 */       RemoteArray localRemoteArray = (RemoteArray)this.pool.get(this.onServer);
/*  67 */       assert (localRemoteArray.arrayLength() == this.arraySize) : (localRemoteArray.byteLength() + " / " + this.arraySize);
/*  68 */       localRemoteArray.fromByteStream(paramDataInputStream, paramInt);
/*  69 */       this.receiveBuffer.add(localRemoteArray);
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getArraySize()
/*     */   {
/*  80 */     return this.arraySize;
/*     */   }
/*     */ 
/*     */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*     */   {
/*  88 */     int i = Math.min(8, ((ObjectArrayList)get()).size());
/*  89 */     paramDataOutputStream.writeInt(i);
/*     */ 
/*  91 */     int j = 0;
/*  92 */     if (!((ObjectArrayList)get()).isEmpty())
/*     */     {
/*  95 */       for (int k = 0; k < i; k++) {
/*  96 */         RemoteField localRemoteField = (RemoteField)((ObjectArrayList)get()).remove(0);
/*     */ 
/*  98 */         j += localRemoteField.toByteStream(paramDataOutputStream);
/*  99 */         localRemoteField.setChanged(false);
/*     */       }
/*     */     }
/*     */ 
/* 103 */     this.keepChanged = (!((ObjectArrayList)get()).isEmpty());
/*     */ 
/* 106 */     return j + 1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteArrayBuffer
 * JD-Core Version:    0.6.2
 */
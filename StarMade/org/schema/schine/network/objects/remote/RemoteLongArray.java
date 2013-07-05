/*    */ package org.schema.schine.network.objects.remote;
/*    */ 
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ 
/*    */ public class RemoteLongArray extends RemoteArray
/*    */ {
/*    */   private long[] transientArray;
/*    */ 
/*    */   public RemoteLongArray(int paramInt, NetworkObject paramNetworkObject)
/*    */   {
/* 13 */     super(new RemoteLong[paramInt], paramNetworkObject);
/*    */   }
/*    */   public RemoteLongArray(int paramInt, boolean paramBoolean) {
/* 16 */     super(new RemoteLong[paramInt], paramBoolean);
/*    */   }
/*    */ 
/*    */   public int byteLength()
/*    */   {
/* 21 */     return ((RemoteField[])get()).length << 3;
/*    */   }
/*    */ 
/*    */   public long[] getTransientArray()
/*    */   {
/* 28 */     return this.transientArray;
/*    */   }
/*    */ 
/*    */   protected void init(RemoteField[] paramArrayOfRemoteField)
/*    */   {
/* 33 */     set(paramArrayOfRemoteField);
/*    */   }
/*    */ 
/*    */   public void set(int paramInt, Long paramLong)
/*    */   {
/* 47 */     this.transientArray[paramInt] = paramLong.longValue();
/* 48 */     ((RemoteField[])super.get())[paramInt].set(paramLong, this.forcedClientSending);
/*    */   }
/*    */ 
/*    */   public void set(RemoteField[] paramArrayOfRemoteField)
/*    */   {
/* 55 */     super.set(paramArrayOfRemoteField);
/* 56 */     for (int i = 0; i < paramArrayOfRemoteField.length; i++) {
/* 57 */       ((RemoteField[])get())[i] = new RemoteLong(0L, this.onServer);
/*    */     }
/* 59 */     this.transientArray = new long[paramArrayOfRemoteField.length];
/* 60 */     addObservers();
/*    */   }
/*    */ 
/*    */   public void setArray(long[] paramArrayOfLong)
/*    */   {
/* 66 */     if (paramArrayOfLong.length != ((RemoteField[])get()).length) {
/* 67 */       throw new IllegalArgumentException("Cannot change array size of remote array");
/*    */     }
/*    */ 
/* 70 */     for (int i = 0; i < this.transientArray.length; i++) {
/* 71 */       this.transientArray[i] = paramArrayOfLong[i];
/* 72 */       get(i).set(Long.valueOf(paramArrayOfLong[i]), this.forcedClientSending);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteLongArray
 * JD-Core Version:    0.6.2
 */
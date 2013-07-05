/*    */ package org.schema.schine.network.objects.remote;
/*    */ 
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ 
/*    */ public class RemoteIntegerArray extends RemoteArray
/*    */ {
/*    */   private int[] transientArray;
/*    */ 
/*    */   public RemoteIntegerArray(int paramInt, NetworkObject paramNetworkObject)
/*    */   {
/* 13 */     super(new RemoteInteger[paramInt], paramNetworkObject);
/*    */   }
/*    */ 
/*    */   public RemoteIntegerArray(int paramInt, boolean paramBoolean) {
/* 17 */     super(new RemoteInteger[paramInt], paramBoolean);
/*    */   }
/*    */ 
/*    */   public int byteLength()
/*    */   {
/* 23 */     return ((RemoteField[])get()).length << 2;
/*    */   }
/*    */ 
/*    */   public int[] getTransientArray()
/*    */   {
/* 30 */     return this.transientArray;
/*    */   }
/*    */ 
/*    */   protected void init(RemoteField[] paramArrayOfRemoteField)
/*    */   {
/* 35 */     set(paramArrayOfRemoteField);
/*    */   }
/*    */ 
/*    */   public void set(int paramInt, Integer paramInteger)
/*    */   {
/* 45 */     this.transientArray[paramInt] = paramInteger.intValue();
/* 46 */     ((RemoteField[])super.get())[paramInt].set(paramInteger, this.forcedClientSending);
/*    */   }
/*    */ 
/*    */   public void set(RemoteField[] paramArrayOfRemoteField)
/*    */   {
/* 53 */     super.set(paramArrayOfRemoteField);
/* 54 */     for (int i = 0; i < paramArrayOfRemoteField.length; i++) {
/* 55 */       ((RemoteField[])get())[i] = new RemoteInteger(Integer.valueOf(0), this.onServer);
/*    */     }
/* 57 */     this.transientArray = new int[paramArrayOfRemoteField.length];
/* 58 */     addObservers();
/*    */   }
/*    */ 
/*    */   public void setArray(int[] paramArrayOfInt)
/*    */   {
/* 64 */     if (paramArrayOfInt.length != ((RemoteField[])get()).length) {
/* 65 */       throw new IllegalArgumentException("Cannot change array size of remote array");
/*    */     }
/*    */ 
/* 68 */     for (int i = 0; i < this.transientArray.length; i++) {
/* 69 */       this.transientArray[i] = paramArrayOfInt[i];
/* 70 */       get(i).set(Integer.valueOf(paramArrayOfInt[i]), this.forcedClientSending);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteIntegerArray
 * JD-Core Version:    0.6.2
 */
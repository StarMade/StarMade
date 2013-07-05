/*    */ package org.schema.schine.network.objects.remote;
/*    */ 
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ 
/*    */ public class RemoteBooleanArray extends RemoteArray
/*    */ {
/*    */   private boolean[] transientArray;
/*    */ 
/*    */   public RemoteBooleanArray(int paramInt, NetworkObject paramNetworkObject)
/*    */   {
/* 14 */     super(new RemoteBoolean[paramInt], paramNetworkObject);
/*    */   }
/*    */ 
/*    */   public RemoteBooleanArray(int paramInt, boolean paramBoolean) {
/* 18 */     super(new RemoteBoolean[paramInt], paramBoolean);
/*    */   }
/*    */ 
/*    */   public int byteLength()
/*    */   {
/* 24 */     return ((RemoteField[])get()).length;
/*    */   }
/*    */ 
/*    */   public boolean[] getTransientArray()
/*    */   {
/* 31 */     return this.transientArray;
/*    */   }
/*    */ 
/*    */   protected void init(RemoteField[] paramArrayOfRemoteField)
/*    */   {
/* 36 */     set(paramArrayOfRemoteField);
/*    */   }
/*    */ 
/*    */   public void set(int paramInt, Boolean paramBoolean)
/*    */   {
/* 60 */     this.transientArray[paramInt] = paramBoolean.booleanValue();
/* 61 */     ((RemoteField[])super.get())[paramInt].set(paramBoolean, this.forcedClientSending);
/*    */   }
/*    */ 
/*    */   public void set(RemoteField[] paramArrayOfRemoteField)
/*    */   {
/* 67 */     super.set(paramArrayOfRemoteField);
/*    */ 
/* 69 */     for (int i = 0; i < paramArrayOfRemoteField.length; i++) {
/* 70 */       ((RemoteField[])get())[i] = new RemoteBoolean(false, this.onServer);
/*    */     }
/* 72 */     this.transientArray = new boolean[paramArrayOfRemoteField.length];
/* 73 */     addObservers();
/*    */   }
/*    */ 
/*    */   public void setArray(boolean[] paramArrayOfBoolean)
/*    */   {
/* 79 */     if (paramArrayOfBoolean.length != ((RemoteField[])get()).length) {
/* 80 */       throw new IllegalArgumentException("Cannot change array size of remote array");
/*    */     }
/*    */ 
/* 83 */     for (int i = 0; i < this.transientArray.length; i++)
/*    */     {
/* 85 */       this.transientArray[i] = paramArrayOfBoolean[i];
/* 86 */       get(i).set(Boolean.valueOf(paramArrayOfBoolean[i]), this.forcedClientSending);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteBooleanArray
 * JD-Core Version:    0.6.2
 */
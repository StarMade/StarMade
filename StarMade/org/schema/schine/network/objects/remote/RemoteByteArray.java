/*    */ package org.schema.schine.network.objects.remote;
/*    */ 
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ 
/*    */ public class RemoteByteArray extends RemoteArray
/*    */ {
/*    */   private byte[] transientArray;
/*    */ 
/*    */   public RemoteByteArray(int paramInt, NetworkObject paramNetworkObject)
/*    */   {
/* 12 */     super(new RemoteByte[paramInt], paramNetworkObject);
/*    */   }
/*    */   public RemoteByteArray(int paramInt, boolean paramBoolean) {
/* 15 */     super(new RemoteByte[paramInt], paramBoolean);
/*    */   }
/*    */ 
/*    */   public int byteLength()
/*    */   {
/* 21 */     return ((RemoteField[])get()).length;
/*    */   }
/*    */ 
/*    */   public byte[] getTransientArray()
/*    */   {
/* 28 */     return this.transientArray;
/*    */   }
/*    */ 
/*    */   protected void init(RemoteField[] paramArrayOfRemoteField)
/*    */   {
/* 33 */     set(paramArrayOfRemoteField);
/*    */   }
/*    */ 
/*    */   public void set(int paramInt, Byte paramByte)
/*    */   {
/* 49 */     this.transientArray[paramInt] = paramByte.byteValue();
/* 50 */     ((RemoteField[])super.get())[paramInt].set(paramByte, this.forcedClientSending);
/*    */   }
/*    */ 
/*    */   public void set(RemoteField[] paramArrayOfRemoteField)
/*    */   {
/* 57 */     super.set(paramArrayOfRemoteField);
/* 58 */     for (int i = 0; i < paramArrayOfRemoteField.length; i++) {
/* 59 */       ((RemoteField[])get())[i] = new RemoteByte(0, this.onServer);
/*    */     }
/* 61 */     this.transientArray = new byte[paramArrayOfRemoteField.length];
/* 62 */     addObservers();
/*    */   }
/*    */ 
/*    */   public void setArray(byte[] paramArrayOfByte)
/*    */   {
/* 75 */     if (paramArrayOfByte.length != ((RemoteField[])get()).length) {
/* 76 */       throw new IllegalArgumentException("Cannot change array size of remote array");
/*    */     }
/*    */ 
/* 79 */     for (int i = 0; i < this.transientArray.length; i++) {
/* 80 */       this.transientArray[i] = paramArrayOfByte[i];
/* 81 */       get(i).set(Byte.valueOf(paramArrayOfByte[i]), this.forcedClientSending);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteByteArray
 * JD-Core Version:    0.6.2
 */
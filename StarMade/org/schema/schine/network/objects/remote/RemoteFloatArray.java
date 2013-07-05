/*    */ package org.schema.schine.network.objects.remote;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ 
/*    */ public class RemoteFloatArray extends RemoteArray
/*    */ {
/*    */   private float[] transientArray;
/*    */ 
/*    */   public RemoteFloatArray(int paramInt, NetworkObject paramNetworkObject)
/*    */   {
/* 17 */     super(new RemoteFloat[paramInt], paramNetworkObject);
/*    */   }
/*    */   public RemoteFloatArray(int paramInt, boolean paramBoolean) {
/* 20 */     super(new RemoteFloat[paramInt], paramBoolean);
/*    */   }
/*    */ 
/*    */   public int byteLength()
/*    */   {
/* 25 */     return ((RemoteField[])get()).length << 2;
/*    */   }
/*    */ 
/*    */   public float[] getTransientArray()
/*    */   {
/* 32 */     return this.transientArray;
/*    */   }
/*    */ 
/*    */   protected void init(RemoteField[] paramArrayOfRemoteField)
/*    */   {
/* 37 */     set(paramArrayOfRemoteField);
/*    */   }
/*    */ 
/*    */   public void set(int paramInt, Float paramFloat)
/*    */   {
/* 52 */     this.transientArray[paramInt] = paramFloat.floatValue();
/* 53 */     ((RemoteField[])super.get())[paramInt].set(paramFloat, this.forcedClientSending);
/*    */   }
/*    */ 
/*    */   public void set(RemoteField[] paramArrayOfRemoteField)
/*    */   {
/* 61 */     super.set(paramArrayOfRemoteField);
/* 62 */     for (int i = 0; i < paramArrayOfRemoteField.length; i++) {
/* 63 */       ((RemoteField[])get())[i] = new RemoteFloat(0.0F, this.onServer);
/*    */     }
/*    */ 
/* 66 */     this.transientArray = new float[paramArrayOfRemoteField.length];
/* 67 */     addObservers();
/*    */   }
/*    */ 
/*    */   public void setArray(float[] paramArrayOfFloat)
/*    */   {
/* 74 */     if (paramArrayOfFloat == null) {
/* 75 */       throw new NullPointerException("cannot set array Null");
/*    */     }
/* 77 */     if (paramArrayOfFloat.length != ((RemoteField[])get()).length) {
/* 78 */       throw new IllegalArgumentException("Cannot change array size of remote array");
/*    */     }
/*    */ 
/* 81 */     for (int i = 0; i < this.transientArray.length; i++) {
/* 82 */       this.transientArray[i] = paramArrayOfFloat[i];
/* 83 */       get(i).set(Float.valueOf(paramArrayOfFloat[i]), this.forcedClientSending);
/*    */     }
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 90 */     return "(rfA" + Arrays.toString(this.transientArray) + ")";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteFloatArray
 * JD-Core Version:    0.6.2
 */
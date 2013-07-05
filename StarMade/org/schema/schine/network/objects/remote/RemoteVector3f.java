/*    */ package org.schema.schine.network.objects.remote;
/*    */ 
/*    */ import javax.vecmath.Vector3f;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ 
/*    */ public class RemoteVector3f extends RemoteFloatPrimitiveArray
/*    */ {
/*    */   public RemoteVector3f(NetworkObject paramNetworkObject)
/*    */   {
/* 10 */     super(3, paramNetworkObject);
/*    */   }
/*    */   public RemoteVector3f(NetworkObject paramNetworkObject, Vector3f paramVector3f) {
/* 13 */     this(paramNetworkObject);
/* 14 */     set(paramVector3f);
/*    */   }
/*    */   public RemoteVector3f(boolean paramBoolean) {
/* 17 */     super(3, paramBoolean);
/*    */   }
/*    */   public RemoteVector3f(boolean paramBoolean, Vector3f paramVector3f) {
/* 20 */     this(paramBoolean);
/* 21 */     set(paramVector3f);
/*    */   }
/*    */ 
/*    */   public Vector3f getVector() {
/* 25 */     return getVector(new Vector3f());
/*    */   }
/*    */   public Vector3f getVector(Vector3f paramVector3f) {
/* 28 */     paramVector3f.set(super.getFloatArray()[0], super.getFloatArray()[1], super.getFloatArray()[2]);
/* 29 */     return paramVector3f;
/*    */   }
/*    */   public void set(Vector3f paramVector3f) {
/* 32 */     super.set(0, paramVector3f.x);
/* 33 */     super.set(1, paramVector3f.y);
/* 34 */     super.set(2, paramVector3f.z);
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 38 */     return "(r" + getVector() + ")";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteVector3f
 * JD-Core Version:    0.6.2
 */
/*    */ package org.schema.schine.network.objects.remote;
/*    */ 
/*    */ import javax.vecmath.Vector4f;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ 
/*    */ public class RemoteVector4f extends RemoteFloatPrimitiveArray
/*    */ {
/*    */   public RemoteVector4f(NetworkObject paramNetworkObject)
/*    */   {
/* 11 */     super(4, paramNetworkObject);
/*    */   }
/*    */   public RemoteVector4f(Vector4f paramVector4f, NetworkObject paramNetworkObject) {
/* 14 */     super(4, paramNetworkObject);
/* 15 */     set(paramVector4f);
/*    */   }
/*    */ 
/*    */   public RemoteVector4f(boolean paramBoolean) {
/* 19 */     super(4, paramBoolean);
/*    */   }
/*    */   public RemoteVector4f(Vector4f paramVector4f, boolean paramBoolean) {
/* 22 */     super(4, paramBoolean);
/* 23 */     set(paramVector4f);
/*    */   }
/*    */ 
/*    */   public Vector4f getVector()
/*    */   {
/* 28 */     return getVector(new Vector4f());
/*    */   }
/*    */   public Vector4f getVector(Vector4f paramVector4f) {
/* 31 */     paramVector4f.set(super.getFloatArray()[0], super.getFloatArray()[1], super.getFloatArray()[2], super.getFloatArray()[3]);
/* 32 */     return paramVector4f;
/*    */   }
/*    */   public void set(Vector4f paramVector4f) {
/* 35 */     super.set(0, paramVector4f.x);
/* 36 */     super.set(1, paramVector4f.y);
/* 37 */     super.set(2, paramVector4f.z);
/* 38 */     super.set(3, paramVector4f.w);
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 42 */     return "(r" + getVector() + ")";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteVector4f
 * JD-Core Version:    0.6.2
 */
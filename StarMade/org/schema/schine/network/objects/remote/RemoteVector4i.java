/*    */ package org.schema.schine.network.objects.remote;
/*    */ 
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import s;
/*    */ 
/*    */ public class RemoteVector4i extends RemoteIntArray
/*    */ {
/*    */   public RemoteVector4i(NetworkObject paramNetworkObject)
/*    */   {
/* 10 */     super(4, paramNetworkObject);
/*    */   }
/*    */   public RemoteVector4i(s params, NetworkObject paramNetworkObject) {
/* 13 */     super(4, paramNetworkObject);
/* 14 */     set(params);
/*    */   }
/*    */ 
/*    */   public RemoteVector4i(boolean paramBoolean) {
/* 18 */     super(4, paramBoolean);
/*    */   }
/*    */   public RemoteVector4i(s params, boolean paramBoolean) {
/* 21 */     super(4, paramBoolean);
/* 22 */     set(params);
/*    */   }
/*    */ 
/*    */   public s getVector()
/*    */   {
/* 27 */     return getVector(new s());
/*    */   }
/*    */   public s getVector(s params) {
/* 30 */     params.a(super.getIntArray()[0], super.getIntArray()[1], super.getIntArray()[2], super.getIntArray()[3]);
/* 31 */     return params;
/*    */   }
/*    */   public void set(s params) {
/* 34 */     super.set(0, params.a);
/* 35 */     super.set(1, params.b);
/* 36 */     super.set(2, params.c);
/* 37 */     super.set(3, params.d);
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 41 */     return "(r" + getVector() + ")";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteVector4i
 * JD-Core Version:    0.6.2
 */
/*    */ package org.schema.schine.network.objects.remote;
/*    */ 
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import q;
/*    */ 
/*    */ public class RemoteVector3i extends RemoteIntArray
/*    */ {
/*    */   public RemoteVector3i(NetworkObject paramNetworkObject)
/*    */   {
/* 10 */     super(3, paramNetworkObject);
/*    */   }
/*    */   public RemoteVector3i(q paramq, NetworkObject paramNetworkObject) {
/* 13 */     super(3, paramNetworkObject);
/* 14 */     set(paramq);
/*    */   }
/*    */   public RemoteVector3i(boolean paramBoolean) {
/* 17 */     super(3, paramBoolean);
/*    */   }
/*    */   public RemoteVector3i(q paramq, boolean paramBoolean) {
/* 20 */     super(3, paramBoolean);
/* 21 */     set(paramq);
/*    */   }
/*    */ 
/*    */   public q getVector() {
/* 25 */     return getVector(new q());
/*    */   }
/*    */   public q getVector(q paramq) {
/* 28 */     paramq.b(super.getIntArray()[0], super.getIntArray()[1], super.getIntArray()[2]);
/* 29 */     return paramq;
/*    */   }
/*    */   public void set(q paramq) {
/* 32 */     super.set(0, paramq.a);
/* 33 */     super.set(1, paramq.b);
/* 34 */     super.set(2, paramq.c);
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 38 */     return "(r" + getVector() + ")";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteVector3i
 * JD-Core Version:    0.6.2
 */
/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteIntArray;
/*    */ import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;
/*    */ 
/*    */ public abstract class md extends mf
/*    */ {
/*    */   boolean a;
/*    */ 
/*    */   public md(mh parammh, q paramq)
/*    */   {
/* 16 */     super(parammh, paramq);
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 22 */     return "ActiveInventory: " + this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.toString();
/*    */   }
/*    */ 
/*    */   public abstract void a();
/*    */ 
/*    */   public final void b() {
/* 28 */     this.jdField_a_of_type_Boolean = true;
/*    */   }
/*    */ 
/*    */   public final boolean a()
/*    */   {
/* 34 */     return this.jdField_a_of_type_Boolean;
/*    */   }
/*    */ 
/*    */   public final void c()
/*    */   {
/*    */     RemoteIntArray localRemoteIntArray;
/* 38 */     (
/* 39 */       localRemoteIntArray = new RemoteIntArray(3, (NetworkObject)this.jdField_a_of_type_Mh.getInventoryNetworkObject()))
/* 39 */       .set(0, this.jdField_a_of_type_Q.a);
/* 40 */     localRemoteIntArray.set(1, this.jdField_a_of_type_Q.b);
/* 41 */     localRemoteIntArray.set(2, this.jdField_a_of_type_Q.c);
/* 42 */     this.jdField_a_of_type_Mh.getInventoryNetworkObject().getInventoryActivateBuffer().add(localRemoteIntArray);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     md
 * JD-Core Version:    0.6.2
 */
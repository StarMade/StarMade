/*    */ package org.schema.game.network.objects;
/*    */ 
/*    */ import org.schema.schine.network.StateInterface;
/*    */ import org.schema.schine.network.objects.NetworkEntity;
/*    */ import org.schema.schine.network.objects.remote.RemoteBoolean;
/*    */ import org.schema.schine.network.objects.remote.RemoteIntPrimitive;
/*    */ import org.schema.schine.network.objects.remote.RemoteVector3f;
/*    */ 
/*    */ public class NetworkMissile extends NetworkEntity
/*    */ {
/* 15 */   public RemoteVector3f dir = new RemoteVector3f(this);
/* 16 */   public RemoteIntPrimitive targetId = new RemoteIntPrimitive(-1, this);
/* 17 */   public RemoteBoolean alive = new RemoteBoolean(true, this);
/*    */ 
/*    */   public NetworkMissile(StateInterface paramStateInterface) {
/* 20 */     super(paramStateInterface);
/*    */   }
/*    */ 
/*    */   public void onDelete(StateInterface paramStateInterface)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void onInit(StateInterface paramStateInterface)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.NetworkMissile
 * JD-Core Version:    0.6.2
 */
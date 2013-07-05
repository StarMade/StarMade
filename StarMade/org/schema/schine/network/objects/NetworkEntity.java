/*    */ package org.schema.schine.network.objects;
/*    */ 
/*    */ import org.schema.schine.network.NetworkGravity;
/*    */ import org.schema.schine.network.StateInterface;
/*    */ import org.schema.schine.network.objects.remote.RemoteBoolean;
/*    */ import org.schema.schine.network.objects.remote.RemoteFloatArrayBuffer;
/*    */ import org.schema.schine.network.objects.remote.RemoteFloatPrimitive;
/*    */ import org.schema.schine.network.objects.remote.RemoteFloatPrimitiveArray;
/*    */ import org.schema.schine.network.objects.remote.RemoteGravity;
/*    */ import org.schema.schine.network.objects.remote.RemoteIntPrimitive;
/*    */ import org.schema.schine.network.objects.remote.RemotePhysicsTransform;
/*    */ 
/*    */ public abstract class NetworkEntity extends NetworkObject
/*    */ {
/*    */   public static final int NEUTRAL_PLAYER_ID = 0;
/* 70 */   public RemoteIntPrimitive sector = new RemoteIntPrimitive(-1, this);
/*    */ 
/* 73 */   public RemoteBoolean hidden = new RemoteBoolean(false, this);
/*    */ 
/* 78 */   public RemoteFloatPrimitive mass = new RemoteFloatPrimitive(0.0F, this);
/* 79 */   public RemoteIntPrimitive factionCode = new RemoteIntPrimitive(0, this);
/*    */ 
/* 82 */   public RemoteGravity gravity = new RemoteGravity(new NetworkGravity(), this);
/*    */ 
/* 86 */   public RemoteFloatArrayBuffer warpingTransformation = new RemoteFloatArrayBuffer(16, this);
/*    */ 
/* 90 */   public RemotePhysicsTransform transformationBuffer = new RemotePhysicsTransform(new NetworkTransformation(), this);
/*    */ 
/* 93 */   public RemoteFloatPrimitiveArray initialTransform = new RemoteFloatPrimitiveArray(16, this);
/*    */ 
/*    */   public NetworkEntity(StateInterface paramStateInterface)
/*    */   {
/* 98 */     super(paramStateInterface);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.NetworkEntity
 * JD-Core Version:    0.6.2
 */
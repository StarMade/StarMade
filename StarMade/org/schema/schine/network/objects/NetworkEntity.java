/*  1:   */package org.schema.schine.network.objects;
/*  2:   */
/*  3:   */import org.schema.schine.network.NetworkGravity;
/*  4:   */import org.schema.schine.network.StateInterface;
/*  5:   */import org.schema.schine.network.objects.remote.RemoteBoolean;
/*  6:   */import org.schema.schine.network.objects.remote.RemoteFloatArrayBuffer;
/*  7:   */import org.schema.schine.network.objects.remote.RemoteFloatPrimitive;
/*  8:   */import org.schema.schine.network.objects.remote.RemoteFloatPrimitiveArray;
/*  9:   */import org.schema.schine.network.objects.remote.RemoteGravity;
/* 10:   */import org.schema.schine.network.objects.remote.RemoteIntPrimitive;
/* 11:   */import org.schema.schine.network.objects.remote.RemotePhysicsTransform;
/* 12:   */
/* 66:   */public abstract class NetworkEntity
/* 67:   */  extends NetworkObject
/* 68:   */{
/* 69:   */  public static final int NEUTRAL_PLAYER_ID = 0;
/* 70:70 */  public RemoteIntPrimitive sector = new RemoteIntPrimitive(-1, this);
/* 71:   */  
/* 73:73 */  public RemoteBoolean hidden = new RemoteBoolean(false, this);
/* 74:   */  
/* 78:78 */  public RemoteFloatPrimitive mass = new RemoteFloatPrimitive(0.0F, this);
/* 79:79 */  public RemoteIntPrimitive factionCode = new RemoteIntPrimitive(0, this);
/* 80:   */  
/* 82:82 */  public RemoteGravity gravity = new RemoteGravity(new NetworkGravity(), this);
/* 83:   */  
/* 86:86 */  public RemoteFloatArrayBuffer warpingTransformation = new RemoteFloatArrayBuffer(16, this);
/* 87:   */  
/* 90:90 */  public RemotePhysicsTransform transformationBuffer = new RemotePhysicsTransform(new NetworkTransformation(), this);
/* 91:   */  
/* 93:93 */  public RemoteFloatPrimitiveArray initialTransform = new RemoteFloatPrimitiveArray(16, this);
/* 94:   */  
/* 96:   */  public NetworkEntity(StateInterface paramStateInterface)
/* 97:   */  {
/* 98:98 */    super(paramStateInterface);
/* 99:   */  }
/* 100:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.NetworkEntity
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
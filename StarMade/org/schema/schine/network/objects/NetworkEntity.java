package org.schema.schine.network.objects;

import org.schema.schine.network.NetworkGravity;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.remote.RemoteBoolean;
import org.schema.schine.network.objects.remote.RemoteFloatArrayBuffer;
import org.schema.schine.network.objects.remote.RemoteFloatPrimitive;
import org.schema.schine.network.objects.remote.RemoteFloatPrimitiveArray;
import org.schema.schine.network.objects.remote.RemoteGravity;
import org.schema.schine.network.objects.remote.RemoteIntPrimitive;
import org.schema.schine.network.objects.remote.RemotePhysicsTransform;

public abstract class NetworkEntity
  extends NetworkObject
{
  public static final int NEUTRAL_PLAYER_ID = 0;
  public RemoteIntPrimitive sector = new RemoteIntPrimitive(-1, this);
  public RemoteBoolean hidden = new RemoteBoolean(false, this);
  public RemoteFloatPrimitive mass = new RemoteFloatPrimitive(0.0F, this);
  public RemoteIntPrimitive factionCode = new RemoteIntPrimitive(0, this);
  public RemoteGravity gravity = new RemoteGravity(new NetworkGravity(), this);
  public RemoteFloatArrayBuffer warpingTransformation = new RemoteFloatArrayBuffer(16, this);
  public RemotePhysicsTransform transformationBuffer = new RemotePhysicsTransform(new NetworkTransformation(), this);
  public RemoteFloatPrimitiveArray initialTransform = new RemoteFloatPrimitiveArray(16, this);
  
  public NetworkEntity(StateInterface paramStateInterface)
  {
    super(paramStateInterface);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.NetworkEntity
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
package org.schema.game.network.objects;

import class_643;
import class_747;
import class_778;
import org.schema.game.common.controller.elements.DistributionInterface;
import org.schema.game.common.controller.elements.shield.NTShieldManagerInterface;
import org.schema.game.network.objects.remote.RemoteInventoryBuffer;
import org.schema.game.network.objects.remote.RemoteInventoryMultModBuffer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
import org.schema.schine.network.objects.remote.RemoteBoolean;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;
import org.schema.schine.network.objects.remote.RemoteLongPrimitive;
import org.schema.schine.network.objects.remote.RemoteString;
import org.schema.schine.network.objects.remote.RemoteStringArray;
import org.schema.schine.network.objects.remote.RemoteVector3f;
import org.schema.schine.network.objects.remote.RemoteVector4i;

public class NetworkShip
  extends NetworkSegmentController
  implements class_778, class_643, DistributionInterface, NTShieldManagerInterface, NetworkDoorInterface
{
  private RemoteIntArrayBuffer shieldUpdate = new RemoteIntArrayBuffer(4, this);
  public RemoteVector3f moveDir = new RemoteVector3f(this);
  public RemoteVector3f orientationDir = new RemoteVector3f(this);
  public RemoteVector3f targetPosition = new RemoteVector3f(this);
  public RemoteVector3f targetVelocity = new RemoteVector3f(this);
  public RemoteLongPrimitive coreDestructionStarted = new RemoteLongPrimitive(-1L, this);
  public RemoteLongPrimitive coreDestructionDuration = new RemoteLongPrimitive(-1L, this);
  public RemoteBuffer onHitNotices = new RemoteBuffer(RemoteBoolean.class, this);
  public RemoteBuffer doorActivate = new RemoteBuffer(RemoteVector4i.class, this);
  public RemoteString debugState = new RemoteString("", this);
  public RemoteArrayBuffer aiSettingsModification = new RemoteArrayBuffer(2, RemoteStringArray.class, this);
  public RemoteBoolean jamming = new RemoteBoolean(this);
  public RemoteBoolean cloaked = new RemoteBoolean(this);
  public RemoteIntArrayBuffer distributionModification = new RemoteIntArrayBuffer(9, this);
  public RemoteInventoryBuffer inventoryBuffer = new RemoteInventoryBuffer(paramclass_747.a112(), this);
  public RemoteInventoryMultModBuffer inventoryMultModBuffer = new RemoteInventoryMultModBuffer(this);
  public RemoteIntArrayBuffer inventoryUpdateBuffer = new RemoteIntArrayBuffer(6, this);
  public RemoteIntArrayBuffer inventoryActivateBuffer = new RemoteIntArrayBuffer(3, this);
  
  public RemoteIntArrayBuffer getInventoryActivateBuffer()
  {
    return this.inventoryActivateBuffer;
  }
  
  public NetworkShip(StateInterface paramStateInterface, class_747 paramclass_747)
  {
    super(paramStateInterface, paramclass_747);
  }
  
  public RemoteArrayBuffer getAiSettingsModification()
  {
    return this.aiSettingsModification;
  }
  
  public RemoteString getDebugState()
  {
    return this.debugState;
  }
  
  public RemoteIntArrayBuffer getDistributionModification()
  {
    return this.distributionModification;
  }
  
  public RemoteInventoryBuffer getInventoriesChangeBuffer()
  {
    return this.inventoryBuffer;
  }
  
  public RemoteIntArrayBuffer getInventoryUpdateBuffer()
  {
    return this.inventoryUpdateBuffer;
  }
  
  public RemoteIntArrayBuffer getShieldUpdate()
  {
    return this.shieldUpdate;
  }
  
  public RemoteBuffer getDoorActivate()
  {
    return this.doorActivate;
  }
  
  public RemoteInventoryMultModBuffer getInventoryMultModBuffer()
  {
    return this.inventoryMultModBuffer;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.NetworkShip
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
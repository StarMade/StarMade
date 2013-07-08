package org.schema.game.network.objects;

import class_643;
import class_753;
import class_778;
import class_798;
import org.schema.game.common.controller.elements.DistributionInterface;
import org.schema.game.common.controller.elements.shield.NTShieldManagerInterface;
import org.schema.game.network.objects.remote.RemoteInventoryBuffer;
import org.schema.game.network.objects.remote.RemoteInventoryMultModBuffer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;
import org.schema.schine.network.objects.remote.RemoteString;
import org.schema.schine.network.objects.remote.RemoteStringArray;
import org.schema.schine.network.objects.remote.RemoteVector4i;

public class NetworkSpaceStation
  extends NetworkSegmentController
  implements class_778, class_643, DistributionInterface, NTShieldManagerInterface, NetworkDoorInterface, NetworkLiftInterface
{
  private RemoteIntArrayBuffer shieldUpdate = new RemoteIntArrayBuffer(4, this);
  public RemoteInventoryMultModBuffer inventoryMultModBuffer = new RemoteInventoryMultModBuffer(this);
  public RemoteBuffer liftActivate = new RemoteBuffer(RemoteVector4i.class, this);
  public RemoteInventoryBuffer inventoryBuffer = new RemoteInventoryBuffer(((class_798)paramclass_753).a(), this);
  public RemoteIntArrayBuffer inventoryUpdateBuffer = new RemoteIntArrayBuffer(6, this);
  public RemoteIntArrayBuffer distributionModification = new RemoteIntArrayBuffer(9, this);
  public RemoteBuffer doorActivate = new RemoteBuffer(RemoteVector4i.class, this);
  public RemoteString debugState = new RemoteString("", this);
  public RemoteArrayBuffer aiSettingsModification = new RemoteArrayBuffer(2, RemoteStringArray.class, this);
  public RemoteIntArrayBuffer inventoryActivateBuffer = new RemoteIntArrayBuffer(3, this);
  
  public RemoteIntArrayBuffer getInventoryActivateBuffer()
  {
    return this.inventoryActivateBuffer;
  }
  
  public NetworkSpaceStation(StateInterface paramStateInterface, class_753 paramclass_753)
  {
    super(paramStateInterface, paramclass_753);
  }
  
  public RemoteArrayBuffer getAiSettingsModification()
  {
    return this.aiSettingsModification;
  }
  
  public RemoteString getDebugState()
  {
    return this.debugState;
  }
  
  public RemoteBuffer getDoorActivate()
  {
    return this.doorActivate;
  }
  
  public RemoteInventoryBuffer getInventoriesChangeBuffer()
  {
    return this.inventoryBuffer;
  }
  
  public RemoteIntArrayBuffer getInventoryUpdateBuffer()
  {
    return this.inventoryUpdateBuffer;
  }
  
  public RemoteBuffer getLiftActivate()
  {
    return this.liftActivate;
  }
  
  public RemoteIntArrayBuffer getShieldUpdate()
  {
    return this.shieldUpdate;
  }
  
  public RemoteIntArrayBuffer getDistributionModification()
  {
    return this.distributionModification;
  }
  
  public RemoteInventoryMultModBuffer getInventoryMultModBuffer()
  {
    return this.inventoryMultModBuffer;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.NetworkSpaceStation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
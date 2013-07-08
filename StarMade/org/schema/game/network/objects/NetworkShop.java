package org.schema.game.network.objects;

import class_635;
import class_643;
import class_753;
import org.schema.game.network.objects.remote.RemoteInventoryBuffer;
import org.schema.game.network.objects.remote.RemoteInventoryMultModBuffer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;

public class NetworkShop
  extends NetworkSegmentController
  implements class_643
{
  public RemoteIntArrayBuffer pricesUpdateBuffer = new RemoteIntArrayBuffer(2, this);
  public RemoteIntArrayBuffer inventoryUpdateBuffer = new RemoteIntArrayBuffer(3, this);
  public RemoteIntArrayBuffer inventoryActivateBuffer = new RemoteIntArrayBuffer(3, this);
  public RemoteInventoryMultModBuffer inventoryMultModBuffer = new RemoteInventoryMultModBuffer(this);
  public RemoteInventoryBuffer inventoryBuffer = new RemoteInventoryBuffer((class_635)paramclass_753, this);
  
  public RemoteIntArrayBuffer getInventoryActivateBuffer()
  {
    return this.inventoryActivateBuffer;
  }
  
  public NetworkShop(StateInterface paramStateInterface, class_753 paramclass_753)
  {
    super(paramStateInterface, paramclass_753);
  }
  
  public RemoteInventoryBuffer getInventoriesChangeBuffer()
  {
    return this.inventoryBuffer;
  }
  
  public RemoteIntArrayBuffer getInventoryUpdateBuffer()
  {
    return this.inventoryUpdateBuffer;
  }
  
  public RemoteInventoryMultModBuffer getInventoryMultModBuffer()
  {
    return this.inventoryMultModBuffer;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.NetworkShop
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
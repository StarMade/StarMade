package org.schema.game.network.objects;

import org.schema.game.network.objects.remote.RemoteItemBuffer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteBoolean;
import org.schema.schine.network.objects.remote.RemoteVector3i;

public class NetworkSector
  extends NetworkObject
{
  public RemoteBoolean active = new RemoteBoolean(this);
  public RemoteVector3i pos = new RemoteVector3i(this);
  public RemoteItemBuffer itemBuffer = new RemoteItemBuffer(this);
  
  public NetworkSector(StateInterface paramStateInterface)
  {
    super(paramStateInterface);
  }
  
  public void onDelete(StateInterface paramStateInterface) {}
  
  public void onInit(StateInterface paramStateInterface) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.NetworkSector
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
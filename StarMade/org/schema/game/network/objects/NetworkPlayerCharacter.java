package org.schema.game.network.objects;

import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.NetworkEntity;
import org.schema.schine.network.objects.remote.RemoteBoolean;
import org.schema.schine.network.objects.remote.RemoteInteger;
import org.schema.schine.network.objects.remote.RemoteString;

public class NetworkPlayerCharacter
  extends NetworkEntity
{
  public RemoteInteger clientOwnerId = new RemoteInteger(Integer.valueOf(-1), this);
  public RemoteString uniqueId = new RemoteString(this);
  public RemoteBoolean spawnedOnServer = new RemoteBoolean(this);
  
  public NetworkPlayerCharacter(StateInterface paramStateInterface)
  {
    super(paramStateInterface);
  }
  
  public void onDelete(StateInterface paramStateInterface) {}
  
  public void onInit(StateInterface paramStateInterface) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.NetworkPlayerCharacter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
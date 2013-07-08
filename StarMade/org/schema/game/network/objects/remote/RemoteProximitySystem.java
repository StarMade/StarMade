package org.schema.game.network.objects.remote;

import class_659;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteField;

public class RemoteProximitySystem
  extends RemoteField
{
  public RemoteProximitySystem(class_659 paramclass_659, NetworkObject paramNetworkObject)
  {
    super(paramclass_659, paramNetworkObject);
  }
  
  public RemoteProximitySystem(class_659 paramclass_659, boolean paramBoolean)
  {
    super(paramclass_659, paramBoolean);
  }
  
  public int byteLength()
  {
    return 255;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    ((class_659)get()).a2(paramDataInputStream);
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    ((class_659)get()).a3(paramDataOutputStream);
    return byteLength();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteProximitySystem
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
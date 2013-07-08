package org.schema.game.network.objects.remote;

import class_611;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteField;

public class RemoteMissileUpdate
  extends RemoteField
{
  public RemoteMissileUpdate(class_611 paramclass_611, NetworkObject paramNetworkObject)
  {
    super(paramclass_611, paramNetworkObject);
  }
  
  public RemoteMissileUpdate(class_611 paramclass_611, boolean paramBoolean)
  {
    super(paramclass_611, paramBoolean);
  }
  
  public int byteLength()
  {
    return 4;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    set(class_611.a3(paramDataInputStream));
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    ((class_611)get()).b(paramDataOutputStream);
    return 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteMissileUpdate
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
package org.schema.game.network.objects.remote;

import class_653;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteField;

public class RemoteProximitySector
  extends RemoteField
{
  public RemoteProximitySector(class_653 paramclass_653, NetworkObject paramNetworkObject)
  {
    super(paramclass_653, paramNetworkObject);
  }
  
  public RemoteProximitySector(class_653 paramclass_653, boolean paramBoolean)
  {
    super(paramclass_653, paramBoolean);
  }
  
  public int byteLength()
  {
    return 5504;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    ((class_653)get()).a3(paramDataInputStream);
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    ((class_653)get()).a4(paramDataOutputStream);
    return byteLength();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteProximitySector
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
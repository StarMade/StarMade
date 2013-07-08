package org.schema.schine.network.objects.remote;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;

public class RemoteBoolean
  extends RemoteComparable
{
  public RemoteBoolean(boolean paramBoolean, NetworkObject paramNetworkObject)
  {
    super(Boolean.valueOf(paramBoolean), paramNetworkObject);
  }
  
  public RemoteBoolean(NetworkObject paramNetworkObject)
  {
    this(false, paramNetworkObject);
  }
  
  public RemoteBoolean(boolean paramBoolean)
  {
    this(false, paramBoolean);
  }
  
  public RemoteBoolean(boolean paramBoolean1, boolean paramBoolean2)
  {
    super(Boolean.valueOf(paramBoolean1), paramBoolean2);
  }
  
  public int byteLength()
  {
    return 1;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    set(Boolean.valueOf(paramDataInputStream.readBoolean()));
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeBoolean(((Boolean)get()).booleanValue());
    return 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteBoolean
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
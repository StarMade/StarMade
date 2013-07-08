package org.schema.schine.network.objects.remote;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;

public class RemoteInteger
  extends RemoteComparable
{
  public RemoteInteger(Integer paramInteger, boolean paramBoolean)
  {
    super(paramInteger, paramBoolean);
  }
  
  public RemoteInteger(Integer paramInteger, NetworkObject paramNetworkObject)
  {
    super(paramInteger, paramNetworkObject);
  }
  
  public RemoteInteger(NetworkObject paramNetworkObject)
  {
    this(Integer.valueOf(0), paramNetworkObject);
  }
  
  public RemoteInteger(boolean paramBoolean)
  {
    this(Integer.valueOf(0), paramBoolean);
  }
  
  public int byteLength()
  {
    return 4;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    set(Integer.valueOf(paramDataInputStream.readInt()));
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeInt(((Integer)get()).intValue());
    return 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteInteger
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
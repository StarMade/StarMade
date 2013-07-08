package org.schema.schine.network.objects.remote;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;

public class RemoteLong
  extends RemoteComparable
{
  public RemoteLong(Long paramLong, NetworkObject paramNetworkObject)
  {
    super(paramLong, paramNetworkObject);
  }
  
  public RemoteLong(NetworkObject paramNetworkObject)
  {
    this(Long.valueOf(0L), paramNetworkObject);
  }
  
  public RemoteLong(boolean paramBoolean)
  {
    this(0L, paramBoolean);
  }
  
  public RemoteLong(long paramLong, boolean paramBoolean)
  {
    super(Long.valueOf(paramLong), paramBoolean);
  }
  
  public int byteLength()
  {
    return 8;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    set(Long.valueOf(paramDataInputStream.readLong()));
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeLong(((Long)get()).longValue());
    return byteLength();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteLong
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
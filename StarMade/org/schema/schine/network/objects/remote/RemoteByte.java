package org.schema.schine.network.objects.remote;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;

public class RemoteByte
  extends RemoteComparable
{
  public RemoteByte(Byte paramByte, NetworkObject paramNetworkObject)
  {
    super(paramByte, paramNetworkObject);
  }
  
  public RemoteByte(NetworkObject paramNetworkObject)
  {
    this(Byte.valueOf((byte)0), paramNetworkObject);
  }
  
  public RemoteByte(boolean paramBoolean)
  {
    this((byte)0, paramBoolean);
  }
  
  public RemoteByte(byte paramByte, boolean paramBoolean)
  {
    super(Byte.valueOf(paramByte), paramBoolean);
  }
  
  public int byteLength()
  {
    return 1;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    paramDataInputStream = paramDataInputStream.readByte();
    set(Byte.valueOf(paramDataInputStream));
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeByte(((Byte)get()).byteValue());
    return 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteByte
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
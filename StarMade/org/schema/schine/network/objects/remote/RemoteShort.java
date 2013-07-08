package org.schema.schine.network.objects.remote;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;

public class RemoteShort
  extends RemoteComparable
{
  public RemoteShort(Short paramShort, NetworkObject paramNetworkObject)
  {
    super(paramShort, paramNetworkObject);
  }
  
  public RemoteShort(NetworkObject paramNetworkObject)
  {
    this(Short.valueOf((short)0), paramNetworkObject);
  }
  
  public RemoteShort(boolean paramBoolean)
  {
    this((short)0, paramBoolean);
  }
  
  public RemoteShort(short paramShort, boolean paramBoolean)
  {
    super(Short.valueOf(paramShort), paramBoolean);
  }
  
  public int byteLength()
  {
    return 2;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    paramDataInputStream = paramDataInputStream.readShort();
    set(Short.valueOf(paramDataInputStream));
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeShort(((Short)get()).shortValue());
    return 2;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteShort
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
package org.schema.schine.network.objects.remote;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;

public class RemoteFloat
  extends RemoteComparable
{
  public RemoteFloat(Float paramFloat, NetworkObject paramNetworkObject)
  {
    super(paramFloat, paramNetworkObject);
  }
  
  public RemoteFloat(NetworkObject paramNetworkObject)
  {
    this(Float.valueOf(0.0F), paramNetworkObject);
  }
  
  public RemoteFloat(boolean paramBoolean)
  {
    this(0.0F, paramBoolean);
  }
  
  public RemoteFloat(float paramFloat, boolean paramBoolean)
  {
    super(Float.valueOf(paramFloat), paramBoolean);
  }
  
  public int byteLength()
  {
    return 4;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    set(Float.valueOf(paramDataInputStream.readFloat()));
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeFloat(((Float)get()).floatValue());
    return 4;
  }
  
  public String toString()
  {
    return ((Float)get()).toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteFloat
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
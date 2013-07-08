package org.schema.schine.network.objects.remote;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;

public class RemoteString
  extends RemoteComparable
{
  public RemoteString(NetworkObject paramNetworkObject)
  {
    super("", paramNetworkObject);
  }
  
  public RemoteString(String paramString, NetworkObject paramNetworkObject)
  {
    super(paramString, paramNetworkObject);
  }
  
  public RemoteString(boolean paramBoolean)
  {
    super("", paramBoolean);
  }
  
  public int byteLength()
  {
    return ((String)get()).length() + 4;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    set(paramDataInputStream.readUTF());
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeUTF((String)get());
    return byteLength();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteString
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
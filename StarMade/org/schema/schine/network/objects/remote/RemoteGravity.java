package org.schema.schine.network.objects.remote;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.vecmath.Vector3f;
import org.schema.schine.network.NetworkGravity;
import org.schema.schine.network.objects.NetworkObject;

public class RemoteGravity
  extends RemoteField
{
  public RemoteGravity(NetworkGravity paramNetworkGravity, NetworkObject paramNetworkObject)
  {
    super(paramNetworkGravity, paramNetworkObject);
  }
  
  public RemoteGravity(NetworkGravity paramNetworkGravity, boolean paramBoolean)
  {
    super(paramNetworkGravity, paramBoolean);
  }
  
  public int byteLength()
  {
    return 1;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    ((NetworkGravity)get()).gravityIdReceive = paramDataInputStream.readInt();
    ((NetworkGravity)get()).gravityReceive.set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
    ((NetworkGravity)get()).gravityReceived = true;
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeInt(((NetworkGravity)get()).gravityId);
    paramDataOutputStream.writeFloat(((NetworkGravity)get()).gravity.field_615);
    paramDataOutputStream.writeFloat(((NetworkGravity)get()).gravity.field_616);
    paramDataOutputStream.writeFloat(((NetworkGravity)get()).gravity.field_617);
    return 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteGravity
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
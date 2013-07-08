package org.schema.game.network.objects.remote;

import class_48;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.game.network.objects.DockingRequest;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteField;

public class RemoteDockLand
  extends RemoteField
{
  public RemoteDockLand(DockingRequest paramDockingRequest, NetworkObject paramNetworkObject)
  {
    super(paramDockingRequest, paramNetworkObject);
  }
  
  public RemoteDockLand(DockingRequest paramDockingRequest, boolean paramBoolean)
  {
    super(paramDockingRequest, paramBoolean);
  }
  
  public int byteLength()
  {
    return 12 + ((DockingRequest)get()).field_1858.length() + 4 + 1;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    paramInt = paramDataInputStream.readBoolean();
    String str = paramDataInputStream.readUTF();
    int i = paramDataInputStream.readInt();
    int j = paramDataInputStream.readInt();
    paramDataInputStream = paramDataInputStream.readInt();
    ((DockingRequest)get()).set(paramInt, str, new class_48(i, j, paramDataInputStream));
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeBoolean(((DockingRequest)get()).dock);
    paramDataOutputStream.writeUTF(((DockingRequest)get()).field_1858);
    paramDataOutputStream.writeInt(((DockingRequest)get()).pos.field_475);
    paramDataOutputStream.writeInt(((DockingRequest)get()).pos.field_476);
    paramDataOutputStream.writeInt(((DockingRequest)get()).pos.field_477);
    return byteLength();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteDockLand
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
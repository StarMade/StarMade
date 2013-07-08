package org.schema.game.network.objects.remote;

import class_625;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteField;

public class RemoteFactionRoles
  extends RemoteField
{
  public RemoteFactionRoles(class_625 paramclass_625, NetworkObject paramNetworkObject)
  {
    super(paramclass_625, paramNetworkObject);
  }
  
  public RemoteFactionRoles(class_625 paramclass_625, boolean paramBoolean)
  {
    super(paramclass_625, paramBoolean);
  }
  
  public int byteLength()
  {
    return 4;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    ((class_625)get()).field_136 = paramDataInputStream.readInt();
    for (paramInt = 0; paramInt < 5; paramInt++)
    {
      ((class_625)get()).a25()[paramInt] = paramDataInputStream.readLong();
      ((class_625)get()).a26()[paramInt] = paramDataInputStream.readUTF();
    }
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeInt(((class_625)get()).field_136);
    for (int i = 0; i < 5; i++)
    {
      paramDataOutputStream.writeLong(((class_625)get()).a25()[i]);
      paramDataOutputStream.writeUTF(((class_625)get()).a26()[i]);
    }
    return byteLength();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteFactionRoles
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
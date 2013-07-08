package org.schema.game.network.objects.remote;

import class_777;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteField;

public class RemoteFactionInvitation
  extends RemoteField
{
  public RemoteFactionInvitation(class_777 paramclass_777, NetworkObject paramNetworkObject)
  {
    super(paramclass_777, paramNetworkObject);
  }
  
  public RemoteFactionInvitation(class_777 paramclass_777, boolean paramBoolean)
  {
    super(paramclass_777, paramBoolean);
  }
  
  public int byteLength()
  {
    return 4;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    paramInt = paramDataInputStream.readUTF();
    String str = paramDataInputStream.readUTF();
    int i = paramDataInputStream.readInt();
    long l = paramDataInputStream.readLong();
    ((class_777)get()).a188(paramInt, str, i, l);
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeUTF(((class_777)get()).b());
    paramDataOutputStream.writeUTF(((class_777)get()).a());
    paramDataOutputStream.writeInt(((class_777)get()).a3());
    paramDataOutputStream.writeLong(((class_777)get()).a28());
    return byteLength();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteFactionInvitation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
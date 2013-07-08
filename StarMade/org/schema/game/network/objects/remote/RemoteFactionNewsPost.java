package org.schema.game.network.objects.remote;

import class_771;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteField;

public class RemoteFactionNewsPost
  extends RemoteField
{
  public RemoteFactionNewsPost(class_771 paramclass_771, NetworkObject paramNetworkObject)
  {
    super(paramclass_771, paramNetworkObject);
  }
  
  public RemoteFactionNewsPost(class_771 paramclass_771, boolean paramBoolean)
  {
    super(paramclass_771, paramBoolean);
  }
  
  public int byteLength()
  {
    return 4;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    paramInt = paramDataInputStream.readInt();
    String str1 = paramDataInputStream.readUTF();
    long l = paramDataInputStream.readLong();
    String str2 = paramDataInputStream.readUTF();
    paramDataInputStream = paramDataInputStream.readInt();
    ((class_771)get()).a170(paramInt, str1, l, str2, paramDataInputStream);
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeInt(((class_771)get()).b5());
    paramDataOutputStream.writeUTF(((class_771)get()).a());
    paramDataOutputStream.writeLong(((class_771)get()).a28());
    paramDataOutputStream.writeUTF(((class_771)get()).b());
    paramDataOutputStream.writeInt(((class_771)get()).a3());
    return byteLength();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteFactionNewsPost
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
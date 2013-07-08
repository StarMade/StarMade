package org.schema.game.network.objects.remote;

import class_48;
import class_625;
import class_773;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteField;

public class RemoteFaction
  extends RemoteField
{
  public RemoteFaction(class_773 paramclass_773, NetworkObject paramNetworkObject)
  {
    super(paramclass_773, paramNetworkObject);
  }
  
  public RemoteFaction(class_773 paramclass_773, boolean paramBoolean)
  {
    super(paramclass_773, paramBoolean);
  }
  
  public int byteLength()
  {
    return ((class_773)get()).b().length() + 4 + ((class_773)get()).a().length() + 4 + ((class_773)get()).c5().length() + 4 + 4;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    paramInt = paramDataInputStream.readUTF();
    String str1 = paramDataInputStream.readUTF();
    String str2 = paramDataInputStream.readUTF();
    int i = paramDataInputStream.readInt();
    boolean bool1 = paramDataInputStream.readBoolean();
    boolean bool2 = paramDataInputStream.readBoolean();
    boolean bool3 = paramDataInputStream.readBoolean();
    boolean bool4 = paramDataInputStream.readBoolean();
    String str3 = paramDataInputStream.readUTF();
    int j = paramDataInputStream.readInt();
    int k = paramDataInputStream.readInt();
    int m = paramDataInputStream.readInt();
    ((class_773)get()).a180().field_136 = paramDataInputStream.readInt();
    for (int n = 0; n < 5; n++)
    {
      ((class_773)get()).a180().a25()[n] = paramDataInputStream.readLong();
      ((class_773)get()).a180().a26()[n] = paramDataInputStream.readUTF();
    }
    ((class_773)get()).a1(paramDataInputStream);
    ((class_773)get()).a44().b(j, k, m);
    ((class_773)get()).d9(str3);
    ((class_773)get()).b31(paramInt);
    ((class_773)get()).a106(str1);
    ((class_773)get()).c18(str2);
    ((class_773)get()).a36(i);
    ((class_773)get()).b13(bool2);
    ((class_773)get()).a72(bool1);
    ((class_773)get()).c6(bool3);
    ((class_773)get()).d11(bool4);
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeUTF(((class_773)get()).b());
    paramDataOutputStream.writeUTF(((class_773)get()).a());
    paramDataOutputStream.writeUTF(((class_773)get()).c5());
    paramDataOutputStream.writeInt(((class_773)get()).a3());
    paramDataOutputStream.writeBoolean(((class_773)get()).a7());
    paramDataOutputStream.writeBoolean(((class_773)get()).b2());
    paramDataOutputStream.writeBoolean(((class_773)get()).c3());
    paramDataOutputStream.writeBoolean(((class_773)get()).d10());
    paramDataOutputStream.writeUTF(((class_773)get()).d8());
    paramDataOutputStream.writeInt(((class_773)get()).a44().field_475);
    paramDataOutputStream.writeInt(((class_773)get()).a44().field_476);
    paramDataOutputStream.writeInt(((class_773)get()).a44().field_477);
    paramDataOutputStream.writeInt(((class_773)get()).a180().field_136);
    for (int i = 0; i < 5; i++)
    {
      paramDataOutputStream.writeLong(((class_773)get()).a180().a25()[i]);
      paramDataOutputStream.writeUTF(((class_773)get()).a180().a26()[i]);
    }
    ((class_773)get()).a2(paramDataOutputStream);
    return byteLength();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteFaction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
package org.schema.game.network.objects.remote;

import class_48;
import class_802;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteField;

public class RemoteSegmentSignature
  extends RemoteField
{
  static int len = 24;
  
  public RemoteSegmentSignature(NetworkObject paramNetworkObject)
  {
    super(new class_802(), paramNetworkObject);
  }
  
  public RemoteSegmentSignature(class_802 paramclass_802, NetworkObject paramNetworkObject)
  {
    super(paramclass_802, paramNetworkObject);
  }
  
  public RemoteSegmentSignature(boolean paramBoolean)
  {
    super(new class_802(), paramBoolean);
  }
  
  public RemoteSegmentSignature(class_802 paramclass_802, boolean paramBoolean)
  {
    super(paramclass_802, paramBoolean);
  }
  
  public int byteLength()
  {
    return len;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    long l = paramDataInputStream.readLong();
    paramInt = paramDataInputStream.readInt();
    int i = paramDataInputStream.readInt();
    int j = paramDataInputStream.readInt();
    int k = paramDataInputStream.readInt();
    paramDataInputStream = paramDataInputStream.readBoolean();
    set(new class_802(new class_48(i, j, k), l, paramInt, paramDataInputStream));
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeLong(((class_802)get()).jdField_field_1059_of_type_Long);
    paramDataOutputStream.writeInt(((class_802)get()).jdField_field_1059_of_type_Int);
    paramDataOutputStream.writeInt(((class_802)get()).jdField_field_1059_of_type_Class_48.field_475);
    paramDataOutputStream.writeInt(((class_802)get()).jdField_field_1059_of_type_Class_48.field_476);
    paramDataOutputStream.writeInt(((class_802)get()).jdField_field_1059_of_type_Class_48.field_477);
    paramDataOutputStream.writeBoolean(((class_802)get()).jdField_field_1059_of_type_Boolean);
    return byteLength();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteSegmentSignature
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
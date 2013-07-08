package org.schema.game.network.objects.remote;

import class_341;
import class_347;
import class_48;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteField;

public class RemoteMapEntryRequest
  extends RemoteField
{
  private class_347[] reply;
  
  public RemoteMapEntryRequest(class_341 paramclass_341, NetworkObject paramNetworkObject)
  {
    super(paramclass_341, paramNetworkObject);
  }
  
  public RemoteMapEntryRequest(class_341 paramclass_341, boolean paramBoolean)
  {
    super(paramclass_341, paramBoolean);
  }
  
  public int byteLength()
  {
    return 1;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    ((class_341)get()).jdField_field_234_of_type_Class_48 = new class_48(paramDataInputStream.readInt(), paramDataInputStream.readInt(), paramDataInputStream.readInt());
    ((class_341)get()).jdField_field_234_of_type_Byte = paramDataInputStream.readByte();
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeInt(((class_341)get()).jdField_field_234_of_type_Class_48.field_475);
    paramDataOutputStream.writeInt(((class_341)get()).jdField_field_234_of_type_Class_48.field_476);
    paramDataOutputStream.writeInt(((class_341)get()).jdField_field_234_of_type_Class_48.field_477);
    paramDataOutputStream.writeByte(((class_341)get()).jdField_field_234_of_type_Byte);
    return byteLength();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteMapEntryRequest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
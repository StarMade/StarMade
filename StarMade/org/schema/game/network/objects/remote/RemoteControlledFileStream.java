package org.schema.game.network.objects.remote;

import class_1274;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteField;

public class RemoteControlledFileStream
  extends RemoteField
{
  public RemoteControlledFileStream(class_1274 paramclass_1274, NetworkObject paramNetworkObject)
  {
    super(paramclass_1274, paramNetworkObject);
  }
  
  public RemoteControlledFileStream(NetworkObject paramNetworkObject)
  {
    super(new class_1274(), paramNetworkObject);
  }
  
  public RemoteControlledFileStream(class_1274 paramclass_1274, boolean paramBoolean)
  {
    super(paramclass_1274, paramBoolean);
  }
  
  public RemoteControlledFileStream(boolean paramBoolean)
  {
    super(new class_1274(), paramBoolean);
  }
  
  public int byteLength()
  {
    return ((class_1274)get()).jdField_field_1460_of_type_Short;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    ((class_1274)get()).jdField_field_1460_of_type_Boolean = paramDataInputStream.readBoolean();
    ((class_1274)get()).jdField_field_1460_of_type_Short = paramDataInputStream.readShort();
    paramDataInputStream.readFully(((class_1274)get()).jdField_field_1460_of_type_ArrayOfByte, 0, ((class_1274)get()).jdField_field_1460_of_type_Short);
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeBoolean(((class_1274)get()).jdField_field_1460_of_type_Boolean);
    paramDataOutputStream.writeShort(((class_1274)get()).jdField_field_1460_of_type_Short);
    paramDataOutputStream.write(((class_1274)get()).jdField_field_1460_of_type_ArrayOfByte, 0, ((class_1274)get()).jdField_field_1460_of_type_Short);
    return byteLength();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteControlledFileStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
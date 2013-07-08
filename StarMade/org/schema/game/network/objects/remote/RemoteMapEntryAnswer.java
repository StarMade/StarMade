package org.schema.game.network.objects.remote;

import class_339;
import class_345;
import class_48;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteField;

public class RemoteMapEntryAnswer
  extends RemoteField
{
  public RemoteMapEntryAnswer(class_339 paramclass_339, NetworkObject paramNetworkObject)
  {
    super(paramclass_339, paramNetworkObject);
  }
  
  public RemoteMapEntryAnswer(class_339 paramclass_339, boolean paramBoolean)
  {
    super(paramclass_339, paramBoolean);
  }
  
  public int byteLength()
  {
    return 1;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    ((class_339)get()).jdField_field_683_of_type_Class_48 = new class_48(paramDataInputStream.readInt(), paramDataInputStream.readInt(), paramDataInputStream.readInt());
    ((class_339)get()).jdField_field_683_of_type_Byte = paramDataInputStream.readByte();
    ((class_339)get()).jdField_field_683_of_type_ArrayOfClass_347 = class_345.a15(paramDataInputStream);
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeInt(((class_339)get()).jdField_field_683_of_type_Class_48.field_475);
    paramDataOutputStream.writeInt(((class_339)get()).jdField_field_683_of_type_Class_48.field_476);
    paramDataOutputStream.writeInt(((class_339)get()).jdField_field_683_of_type_Class_48.field_477);
    paramDataOutputStream.writeByte(((class_339)get()).jdField_field_683_of_type_Byte);
    class_345.a16(paramDataOutputStream, ((class_339)get()).jdField_field_683_of_type_ArrayOfClass_347);
    return byteLength();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteMapEntryAnswer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
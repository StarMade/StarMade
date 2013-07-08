package org.schema.game.network.objects.remote;

import class_781;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteField;

public class RemoteCatalogEntry
  extends RemoteField
{
  public RemoteCatalogEntry(class_781 paramclass_781, NetworkObject paramNetworkObject)
  {
    super(paramclass_781, paramNetworkObject);
  }
  
  public RemoteCatalogEntry(class_781 paramclass_781, boolean paramBoolean)
  {
    super(paramclass_781, paramBoolean);
  }
  
  public int byteLength()
  {
    return 1;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    paramInt = paramDataInputStream.readUTF();
    String str1 = paramDataInputStream.readUTF();
    String str2 = paramDataInputStream.readUTF();
    int i = paramDataInputStream.readInt();
    int j = paramDataInputStream.readInt();
    boolean bool = paramDataInputStream.readBoolean();
    paramDataInputStream = paramDataInputStream.readFloat();
    ((class_781)get()).jdField_field_136_of_type_JavaLangString = paramInt;
    ((class_781)get()).jdField_field_139_of_type_JavaLangString = str1;
    ((class_781)get()).jdField_field_182_of_type_Int = i;
    ((class_781)get()).jdField_field_182_of_type_JavaLangString = str2;
    ((class_781)get()).jdField_field_139_of_type_Int = j;
    ((class_781)get()).jdField_field_136_of_type_Boolean = bool;
    ((class_781)get()).jdField_field_136_of_type_Float = paramDataInputStream;
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeUTF(((class_781)get()).jdField_field_136_of_type_JavaLangString);
    paramDataOutputStream.writeUTF(((class_781)get()).jdField_field_139_of_type_JavaLangString);
    paramDataOutputStream.writeUTF(((class_781)get()).jdField_field_182_of_type_JavaLangString);
    paramDataOutputStream.writeInt(((class_781)get()).jdField_field_182_of_type_Int);
    paramDataOutputStream.writeInt(((class_781)get()).jdField_field_139_of_type_Int);
    paramDataOutputStream.writeBoolean(((class_781)get()).jdField_field_136_of_type_Boolean);
    paramDataOutputStream.writeFloat(((class_781)get()).jdField_field_136_of_type_Float);
    return byteLength();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteCatalogEntry
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
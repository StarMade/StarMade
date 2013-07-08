package org.schema.game.network.objects.remote;

import class_48;
import class_639;
import class_645;
import class_647;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Collection;
import java.util.Iterator;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteField;

public class RemoteInventoryMultMod
  extends RemoteField
{
  public RemoteInventoryMultMod(class_645 paramclass_645, NetworkObject paramNetworkObject)
  {
    super(paramclass_645, paramNetworkObject);
  }
  
  public RemoteInventoryMultMod(class_645 paramclass_645, boolean paramBoolean)
  {
    super(paramclass_645, paramBoolean);
  }
  
  public int byteLength()
  {
    return 4;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    paramInt = paramDataInputStream.readInt();
    int i = paramDataInputStream.readInt();
    int j = paramDataInputStream.readInt();
    ((class_645)get()).jdField_field_920_of_type_Class_48 = new class_48(paramInt, i, j);
    paramInt = paramDataInputStream.readShort();
    ((class_645)get()).jdField_field_920_of_type_ArrayOfClass_647 = new class_647[paramInt];
    for (i = 0; i < paramInt; i++)
    {
      ((class_645)get()).jdField_field_920_of_type_ArrayOfClass_647[i] = new class_647();
      j = paramDataInputStream.readShort();
      short s;
      if ((s = paramDataInputStream.readShort()) != 0)
      {
        int k = paramDataInputStream.readInt();
        ((class_645)get()).jdField_field_920_of_type_ArrayOfClass_647[i].b(k);
      }
      ((class_645)get()).jdField_field_920_of_type_ArrayOfClass_647[i].jdField_field_921_of_type_Int = j;
      ((class_645)get()).jdField_field_920_of_type_ArrayOfClass_647[i].jdField_field_921_of_type_Short = s;
    }
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    class_639 localclass_639 = ((class_645)get()).jdField_field_920_of_type_Class_639;
    if (((class_645)get()).jdField_field_920_of_type_Class_48 != null)
    {
      paramDataOutputStream.writeInt(((class_645)get()).jdField_field_920_of_type_Class_48.field_475);
      paramDataOutputStream.writeInt(((class_645)get()).jdField_field_920_of_type_Class_48.field_476);
      paramDataOutputStream.writeInt(((class_645)get()).jdField_field_920_of_type_Class_48.field_477);
    }
    else
    {
      paramDataOutputStream.writeInt(0);
      paramDataOutputStream.writeInt(0);
      paramDataOutputStream.writeInt(0);
    }
    paramDataOutputStream.writeShort((short)((class_645)get()).jdField_field_920_of_type_JavaUtilCollection.size());
    Iterator localIterator = ((class_645)get()).jdField_field_920_of_type_JavaUtilCollection.iterator();
    while (localIterator.hasNext())
    {
      int i = ((Integer)localIterator.next()).intValue();
      paramDataOutputStream.writeShort((short)i);
      int j = localclass_639.a45(i);
      paramDataOutputStream.writeShort(j);
      if (j != 0) {
        paramDataOutputStream.writeInt(localclass_639.a41(i));
      }
    }
    return byteLength();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteInventoryMultMod
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
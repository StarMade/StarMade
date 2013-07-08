package org.schema.schine.network.objects.remote;

import class_35;
import org.schema.schine.network.objects.NetworkObject;

public class RemoteVector3b
  extends RemoteByteArray
{
  public RemoteVector3b(NetworkObject paramNetworkObject)
  {
    super(3, paramNetworkObject);
  }
  
  public RemoteVector3b(boolean paramBoolean)
  {
    super(3, paramBoolean);
  }
  
  public class_35 getVector()
  {
    return getVector(new class_35());
  }
  
  public class_35 getVector(class_35 paramclass_35)
  {
    paramclass_35.b(((Byte)((RemoteField[])super.get())[0].get()).byteValue(), ((Byte)((RemoteField[])super.get())[1].get()).byteValue(), ((Byte)((RemoteField[])super.get())[2].get()).byteValue());
    return paramclass_35;
  }
  
  public void set(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    super.set(0, Byte.valueOf(paramByte1));
    super.set(1, Byte.valueOf(paramByte2));
    super.set(2, Byte.valueOf(paramByte3));
  }
  
  public void set(class_35 paramclass_35)
  {
    super.set(0, Byte.valueOf(paramclass_35.field_453));
    super.set(1, Byte.valueOf(paramclass_35.field_454));
    super.set(2, Byte.valueOf(paramclass_35.field_455));
  }
  
  public String toString()
  {
    return "(r" + getVector(new class_35()) + ")";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteVector3b
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
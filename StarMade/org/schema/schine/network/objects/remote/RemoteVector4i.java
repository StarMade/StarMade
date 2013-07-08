package org.schema.schine.network.objects.remote;

import class_46;
import org.schema.schine.network.objects.NetworkObject;

public class RemoteVector4i
  extends RemoteIntArray
{
  public RemoteVector4i(NetworkObject paramNetworkObject)
  {
    super(4, paramNetworkObject);
  }
  
  public RemoteVector4i(class_46 paramclass_46, NetworkObject paramNetworkObject)
  {
    super(4, paramNetworkObject);
    set(paramclass_46);
  }
  
  public RemoteVector4i(boolean paramBoolean)
  {
    super(4, paramBoolean);
  }
  
  public RemoteVector4i(class_46 paramclass_46, boolean paramBoolean)
  {
    super(4, paramBoolean);
    set(paramclass_46);
  }
  
  public class_46 getVector()
  {
    return getVector(new class_46());
  }
  
  public class_46 getVector(class_46 paramclass_46)
  {
    paramclass_46.a(super.getIntArray()[0], super.getIntArray()[1], super.getIntArray()[2], super.getIntArray()[3]);
    return paramclass_46;
  }
  
  public void set(class_46 paramclass_46)
  {
    super.set(0, paramclass_46.field_467);
    super.set(1, paramclass_46.field_468);
    super.set(2, paramclass_46.field_469);
    super.set(3, paramclass_46.field_470);
  }
  
  public String toString()
  {
    return "(r" + getVector() + ")";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteVector4i
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
package org.schema.schine.network.objects.remote;

import class_48;
import org.schema.schine.network.objects.NetworkObject;

public class RemoteVector3i
  extends RemoteIntArray
{
  public RemoteVector3i(NetworkObject paramNetworkObject)
  {
    super(3, paramNetworkObject);
  }
  
  public RemoteVector3i(class_48 paramclass_48, NetworkObject paramNetworkObject)
  {
    super(3, paramNetworkObject);
    set(paramclass_48);
  }
  
  public RemoteVector3i(boolean paramBoolean)
  {
    super(3, paramBoolean);
  }
  
  public RemoteVector3i(class_48 paramclass_48, boolean paramBoolean)
  {
    super(3, paramBoolean);
    set(paramclass_48);
  }
  
  public class_48 getVector()
  {
    return getVector(new class_48());
  }
  
  public class_48 getVector(class_48 paramclass_48)
  {
    paramclass_48.b(super.getIntArray()[0], super.getIntArray()[1], super.getIntArray()[2]);
    return paramclass_48;
  }
  
  public void set(class_48 paramclass_48)
  {
    super.set(0, paramclass_48.field_475);
    super.set(1, paramclass_48.field_476);
    super.set(2, paramclass_48.field_477);
  }
  
  public String toString()
  {
    return "(r" + getVector() + ")";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteVector3i
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
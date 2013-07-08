package org.schema.schine.network.objects.remote;

import javax.vecmath.Vector3f;
import org.schema.schine.network.objects.NetworkObject;

public class RemoteVector3f
  extends RemoteFloatPrimitiveArray
{
  public RemoteVector3f(NetworkObject paramNetworkObject)
  {
    super(3, paramNetworkObject);
  }
  
  public RemoteVector3f(NetworkObject paramNetworkObject, Vector3f paramVector3f)
  {
    this(paramNetworkObject);
    set(paramVector3f);
  }
  
  public RemoteVector3f(boolean paramBoolean)
  {
    super(3, paramBoolean);
  }
  
  public RemoteVector3f(boolean paramBoolean, Vector3f paramVector3f)
  {
    this(paramBoolean);
    set(paramVector3f);
  }
  
  public Vector3f getVector()
  {
    return getVector(new Vector3f());
  }
  
  public Vector3f getVector(Vector3f paramVector3f)
  {
    paramVector3f.set(super.getFloatArray()[0], super.getFloatArray()[1], super.getFloatArray()[2]);
    return paramVector3f;
  }
  
  public void set(Vector3f paramVector3f)
  {
    super.set(0, paramVector3f.field_615);
    super.set(1, paramVector3f.field_616);
    super.set(2, paramVector3f.field_617);
  }
  
  public String toString()
  {
    return "(r" + getVector() + ")";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteVector3f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
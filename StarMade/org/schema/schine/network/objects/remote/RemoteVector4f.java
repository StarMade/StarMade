package org.schema.schine.network.objects.remote;

import javax.vecmath.Vector4f;
import org.schema.schine.network.objects.NetworkObject;

public class RemoteVector4f
  extends RemoteFloatPrimitiveArray
{
  public RemoteVector4f(NetworkObject paramNetworkObject)
  {
    super(4, paramNetworkObject);
  }
  
  public RemoteVector4f(Vector4f paramVector4f, NetworkObject paramNetworkObject)
  {
    super(4, paramNetworkObject);
    set(paramVector4f);
  }
  
  public RemoteVector4f(boolean paramBoolean)
  {
    super(4, paramBoolean);
  }
  
  public RemoteVector4f(Vector4f paramVector4f, boolean paramBoolean)
  {
    super(4, paramBoolean);
    set(paramVector4f);
  }
  
  public Vector4f getVector()
  {
    return getVector(new Vector4f());
  }
  
  public Vector4f getVector(Vector4f paramVector4f)
  {
    paramVector4f.set(super.getFloatArray()[0], super.getFloatArray()[1], super.getFloatArray()[2], super.getFloatArray()[3]);
    return paramVector4f;
  }
  
  public void set(Vector4f paramVector4f)
  {
    super.set(0, paramVector4f.field_596);
    super.set(1, paramVector4f.field_597);
    super.set(2, paramVector4f.field_598);
    super.set(3, paramVector4f.field_599);
  }
  
  public String toString()
  {
    return "(r" + getVector() + ")";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteVector4f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
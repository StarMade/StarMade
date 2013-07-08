package org.schema.schine.network.objects.remote;

import java.util.Arrays;
import org.schema.schine.network.objects.NetworkObject;

public class RemoteFloatArray
  extends RemoteArray
{
  private float[] transientArray;
  
  public RemoteFloatArray(int paramInt, NetworkObject paramNetworkObject)
  {
    super(new RemoteFloat[paramInt], paramNetworkObject);
  }
  
  public RemoteFloatArray(int paramInt, boolean paramBoolean)
  {
    super(new RemoteFloat[paramInt], paramBoolean);
  }
  
  public int byteLength()
  {
    return ((RemoteField[])get()).length << 2;
  }
  
  public float[] getTransientArray()
  {
    return this.transientArray;
  }
  
  protected void init(RemoteField[] paramArrayOfRemoteField)
  {
    set(paramArrayOfRemoteField);
  }
  
  public void set(int paramInt, Float paramFloat)
  {
    this.transientArray[paramInt] = paramFloat.floatValue();
    ((RemoteField[])super.get())[paramInt].set(paramFloat, this.forcedClientSending);
  }
  
  public void set(RemoteField[] paramArrayOfRemoteField)
  {
    super.set(paramArrayOfRemoteField);
    for (int i = 0; i < paramArrayOfRemoteField.length; i++) {
      ((RemoteField[])get())[i] = new RemoteFloat(0.0F, this.onServer);
    }
    this.transientArray = new float[paramArrayOfRemoteField.length];
    addObservers();
  }
  
  public void setArray(float[] paramArrayOfFloat)
  {
    if (paramArrayOfFloat == null) {
      throw new NullPointerException("cannot set array Null");
    }
    if (paramArrayOfFloat.length != ((RemoteField[])get()).length) {
      throw new IllegalArgumentException("Cannot change array size of remote array");
    }
    for (int i = 0; i < this.transientArray.length; i++)
    {
      this.transientArray[i] = paramArrayOfFloat[i];
      get(i).set(Float.valueOf(paramArrayOfFloat[i]), this.forcedClientSending);
    }
  }
  
  public String toString()
  {
    return "(rfA" + Arrays.toString(this.transientArray) + ")";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteFloatArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
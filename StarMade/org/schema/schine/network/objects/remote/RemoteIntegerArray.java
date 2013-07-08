package org.schema.schine.network.objects.remote;

import org.schema.schine.network.objects.NetworkObject;

public class RemoteIntegerArray
  extends RemoteArray
{
  private int[] transientArray;
  
  public RemoteIntegerArray(int paramInt, NetworkObject paramNetworkObject)
  {
    super(new RemoteInteger[paramInt], paramNetworkObject);
  }
  
  public RemoteIntegerArray(int paramInt, boolean paramBoolean)
  {
    super(new RemoteInteger[paramInt], paramBoolean);
  }
  
  public int byteLength()
  {
    return ((RemoteField[])get()).length << 2;
  }
  
  public int[] getTransientArray()
  {
    return this.transientArray;
  }
  
  protected void init(RemoteField[] paramArrayOfRemoteField)
  {
    set(paramArrayOfRemoteField);
  }
  
  public void set(int paramInt, Integer paramInteger)
  {
    this.transientArray[paramInt] = paramInteger.intValue();
    ((RemoteField[])super.get())[paramInt].set(paramInteger, this.forcedClientSending);
  }
  
  public void set(RemoteField[] paramArrayOfRemoteField)
  {
    super.set(paramArrayOfRemoteField);
    for (int i = 0; i < paramArrayOfRemoteField.length; i++) {
      ((RemoteField[])get())[i] = new RemoteInteger(Integer.valueOf(0), this.onServer);
    }
    this.transientArray = new int[paramArrayOfRemoteField.length];
    addObservers();
  }
  
  public void setArray(int[] paramArrayOfInt)
  {
    if (paramArrayOfInt.length != ((RemoteField[])get()).length) {
      throw new IllegalArgumentException("Cannot change array size of remote array");
    }
    for (int i = 0; i < this.transientArray.length; i++)
    {
      this.transientArray[i] = paramArrayOfInt[i];
      get(i).set(Integer.valueOf(paramArrayOfInt[i]), this.forcedClientSending);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteIntegerArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
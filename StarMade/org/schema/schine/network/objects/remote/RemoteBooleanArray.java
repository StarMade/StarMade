package org.schema.schine.network.objects.remote;

import org.schema.schine.network.objects.NetworkObject;

public class RemoteBooleanArray
  extends RemoteArray
{
  private boolean[] transientArray;
  
  public RemoteBooleanArray(int paramInt, NetworkObject paramNetworkObject)
  {
    super(new RemoteBoolean[paramInt], paramNetworkObject);
  }
  
  public RemoteBooleanArray(int paramInt, boolean paramBoolean)
  {
    super(new RemoteBoolean[paramInt], paramBoolean);
  }
  
  public int byteLength()
  {
    return ((RemoteField[])get()).length;
  }
  
  public boolean[] getTransientArray()
  {
    return this.transientArray;
  }
  
  protected void init(RemoteField[] paramArrayOfRemoteField)
  {
    set(paramArrayOfRemoteField);
  }
  
  public void set(int paramInt, Boolean paramBoolean)
  {
    this.transientArray[paramInt] = paramBoolean.booleanValue();
    ((RemoteField[])super.get())[paramInt].set(paramBoolean, this.forcedClientSending);
  }
  
  public void set(RemoteField[] paramArrayOfRemoteField)
  {
    super.set(paramArrayOfRemoteField);
    for (int i = 0; i < paramArrayOfRemoteField.length; i++) {
      ((RemoteField[])get())[i] = new RemoteBoolean(false, this.onServer);
    }
    this.transientArray = new boolean[paramArrayOfRemoteField.length];
    addObservers();
  }
  
  public void setArray(boolean[] paramArrayOfBoolean)
  {
    if (paramArrayOfBoolean.length != ((RemoteField[])get()).length) {
      throw new IllegalArgumentException("Cannot change array size of remote array");
    }
    for (int i = 0; i < this.transientArray.length; i++)
    {
      this.transientArray[i] = paramArrayOfBoolean[i];
      get(i).set(Boolean.valueOf(paramArrayOfBoolean[i]), this.forcedClientSending);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteBooleanArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
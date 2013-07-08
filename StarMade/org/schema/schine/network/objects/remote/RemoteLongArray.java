package org.schema.schine.network.objects.remote;

import org.schema.schine.network.objects.NetworkObject;

public class RemoteLongArray
  extends RemoteArray
{
  private long[] transientArray;
  
  public RemoteLongArray(int paramInt, NetworkObject paramNetworkObject)
  {
    super(new RemoteLong[paramInt], paramNetworkObject);
  }
  
  public RemoteLongArray(int paramInt, boolean paramBoolean)
  {
    super(new RemoteLong[paramInt], paramBoolean);
  }
  
  public int byteLength()
  {
    return ((RemoteField[])get()).length << 3;
  }
  
  public long[] getTransientArray()
  {
    return this.transientArray;
  }
  
  protected void init(RemoteField[] paramArrayOfRemoteField)
  {
    set(paramArrayOfRemoteField);
  }
  
  public void set(int paramInt, Long paramLong)
  {
    this.transientArray[paramInt] = paramLong.longValue();
    ((RemoteField[])super.get())[paramInt].set(paramLong, this.forcedClientSending);
  }
  
  public void set(RemoteField[] paramArrayOfRemoteField)
  {
    super.set(paramArrayOfRemoteField);
    for (int i = 0; i < paramArrayOfRemoteField.length; i++) {
      ((RemoteField[])get())[i] = new RemoteLong(0L, this.onServer);
    }
    this.transientArray = new long[paramArrayOfRemoteField.length];
    addObservers();
  }
  
  public void setArray(long[] paramArrayOfLong)
  {
    if (paramArrayOfLong.length != ((RemoteField[])get()).length) {
      throw new IllegalArgumentException("Cannot change array size of remote array");
    }
    for (int i = 0; i < this.transientArray.length; i++)
    {
      this.transientArray[i] = paramArrayOfLong[i];
      get(i).set(Long.valueOf(paramArrayOfLong[i]), this.forcedClientSending);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteLongArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
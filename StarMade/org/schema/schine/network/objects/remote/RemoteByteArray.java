package org.schema.schine.network.objects.remote;

import org.schema.schine.network.objects.NetworkObject;

public class RemoteByteArray
  extends RemoteArray
{
  private byte[] transientArray;
  
  public RemoteByteArray(int paramInt, NetworkObject paramNetworkObject)
  {
    super(new RemoteByte[paramInt], paramNetworkObject);
  }
  
  public RemoteByteArray(int paramInt, boolean paramBoolean)
  {
    super(new RemoteByte[paramInt], paramBoolean);
  }
  
  public int byteLength()
  {
    return ((RemoteField[])get()).length;
  }
  
  public byte[] getTransientArray()
  {
    return this.transientArray;
  }
  
  protected void init(RemoteField[] paramArrayOfRemoteField)
  {
    set(paramArrayOfRemoteField);
  }
  
  public void set(int paramInt, Byte paramByte)
  {
    this.transientArray[paramInt] = paramByte.byteValue();
    ((RemoteField[])super.get())[paramInt].set(paramByte, this.forcedClientSending);
  }
  
  public void set(RemoteField[] paramArrayOfRemoteField)
  {
    super.set(paramArrayOfRemoteField);
    for (int i = 0; i < paramArrayOfRemoteField.length; i++) {
      ((RemoteField[])get())[i] = new RemoteByte(0, this.onServer);
    }
    this.transientArray = new byte[paramArrayOfRemoteField.length];
    addObservers();
  }
  
  public void setArray(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte.length != ((RemoteField[])get()).length) {
      throw new IllegalArgumentException("Cannot change array size of remote array");
    }
    for (int i = 0; i < this.transientArray.length; i++)
    {
      this.transientArray[i] = paramArrayOfByte[i];
      get(i).set(Byte.valueOf(paramArrayOfByte[i]), this.forcedClientSending);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteByteArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
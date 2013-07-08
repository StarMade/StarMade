package org.schema.schine.network.objects.remote;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.pool.ArrayBufferPool;

public class RemoteArrayBuffer
  extends RemoteBuffer
{
  private ArrayBufferPool pool;
  private final int arraySize;
  
  public RemoteArrayBuffer(int paramInt, Class paramClass, NetworkObject paramNetworkObject)
  {
    super(paramClass, paramNetworkObject);
    this.arraySize = paramInt;
    cacheConstructor();
  }
  
  public RemoteArrayBuffer(int paramInt, Class paramClass, boolean paramBoolean)
  {
    super(paramClass, paramBoolean);
    this.arraySize = paramInt;
    cacheConstructor();
  }
  
  public boolean add(RemoteArray paramRemoteArray)
  {
    assert (((RemoteField[])paramRemoteArray.get()).length == this.arraySize) : ("Invalid Array Size: " + ((RemoteField[])paramRemoteArray.get()).length + " != " + this.arraySize);
    return super.add(paramRemoteArray);
  }
  
  public int byteLength()
  {
    return 4;
  }
  
  public void cacheConstructor()
  {
    this.pool = ArrayBufferPool.get(this.clazz, Integer.valueOf(this.arraySize));
  }
  
  public void clearReceiveBuffer()
  {
    for (int i = 0; i < getReceiveBuffer().size(); i++) {
      this.pool.release((StreamableArray)getReceiveBuffer().get(i));
    }
    getReceiveBuffer().clear();
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    int i = paramDataInputStream.readInt();
    for (int j = 0; j < i; j++)
    {
      RemoteArray localRemoteArray = (RemoteArray)this.pool.get(this.onServer);
      assert (localRemoteArray.arrayLength() == this.arraySize) : (localRemoteArray.byteLength() + " / " + this.arraySize);
      localRemoteArray.fromByteStream(paramDataInputStream, paramInt);
      this.receiveBuffer.add(localRemoteArray);
    }
  }
  
  public int getArraySize()
  {
    return this.arraySize;
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    int i = Math.min(8, ((ObjectArrayList)get()).size());
    paramDataOutputStream.writeInt(i);
    int j = 0;
    if (!((ObjectArrayList)get()).isEmpty()) {
      for (int k = 0; k < i; k++)
      {
        RemoteField localRemoteField = (RemoteField)((ObjectArrayList)get()).remove(0);
        j += localRemoteField.toByteStream(paramDataOutputStream);
        localRemoteField.setChanged(false);
      }
    }
    this.keepChanged = (!((ObjectArrayList)get()).isEmpty());
    return j + 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteArrayBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
package org.schema.schine.network.objects.remote;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.pool.ArrayBufferPool;

public class RemoteIntArrayBuffer
  extends RemoteBuffer
{
  private ArrayBufferPool pool;
  private final int arraySize;
  
  public RemoteIntArrayBuffer(int paramInt, NetworkObject paramNetworkObject)
  {
    super(RemoteIntArray.class, paramNetworkObject);
    this.arraySize = paramInt;
    cacheConstructor();
  }
  
  public RemoteIntArrayBuffer(int paramInt, boolean paramBoolean)
  {
    super(RemoteIntArray.class, paramBoolean);
    this.arraySize = paramInt;
    cacheConstructor();
  }
  
  public boolean add(RemoteIntArray paramRemoteIntArray)
  {
    assert (paramRemoteIntArray.getIntArray().length == this.arraySize) : ("Invalid Array Size: " + paramRemoteIntArray.getIntArray().length + " != " + this.arraySize);
    return super.add(paramRemoteIntArray);
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
      RemoteIntArray localRemoteIntArray = (RemoteIntArray)this.pool.get(this.onServer);
      assert (localRemoteIntArray.arrayLength() == this.arraySize) : (localRemoteIntArray.byteLength() + " / " + this.arraySize);
      localRemoteIntArray.fromByteStream(paramDataInputStream, paramInt);
      this.receiveBuffer.add(localRemoteIntArray);
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
        Streamable localStreamable = (Streamable)((ObjectArrayList)get()).remove(0);
        j += localStreamable.toByteStream(paramDataOutputStream);
        localStreamable.setChanged(false);
      }
    }
    this.keepChanged = (!((ObjectArrayList)get()).isEmpty());
    return j + 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteIntArrayBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
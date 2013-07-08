package org.schema.schine.network.objects.remote;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.pool.ArrayBufferPool;

public class RemoteFloatArrayBuffer
  extends RemoteBuffer
{
  private ArrayBufferPool pool;
  private final int arraySize;
  
  public RemoteFloatArrayBuffer(int paramInt, NetworkObject paramNetworkObject)
  {
    super(RemoteFloatPrimitiveArray.class, paramNetworkObject);
    this.arraySize = paramInt;
    cacheConstructor();
  }
  
  public RemoteFloatArrayBuffer(int paramInt, boolean paramBoolean)
  {
    super(RemoteFloatPrimitiveArray.class, paramBoolean);
    this.arraySize = paramInt;
    cacheConstructor();
  }
  
  public boolean add(RemoteFloatPrimitiveArray paramRemoteFloatPrimitiveArray)
  {
    assert (paramRemoteFloatPrimitiveArray.getFloatArray().length == this.arraySize) : ("Invalid Array Size: " + paramRemoteFloatPrimitiveArray.getFloatArray().length + " != " + this.arraySize);
    return super.add(paramRemoteFloatPrimitiveArray);
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
      RemoteFloatPrimitiveArray localRemoteFloatPrimitiveArray = (RemoteFloatPrimitiveArray)this.pool.get(this.onServer);
      assert (localRemoteFloatPrimitiveArray.arrayLength() == this.arraySize) : (localRemoteFloatPrimitiveArray.byteLength() + " / " + this.arraySize);
      localRemoteFloatPrimitiveArray.fromByteStream(paramDataInputStream, paramInt);
      this.receiveBuffer.add(localRemoteFloatPrimitiveArray);
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
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteFloatArrayBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
package org.schema.schine.network.objects.remote;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;

public class RemoteLongPrimitiveArray
  implements Streamable, StreamableArray
{
  private boolean changed;
  private NetworkChangeObserver observer;
  protected boolean keepChanged;
  private final boolean onServer;
  private boolean forcedClientSending;
  private long[] array;
  
  public RemoteLongPrimitiveArray(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.array = new long[paramInt];
    this.onServer = paramBoolean2;
    this.changed = paramBoolean1;
  }
  
  public RemoteLongPrimitiveArray(int paramInt, boolean paramBoolean, NetworkObject paramNetworkObject)
  {
    this(paramInt, paramBoolean, paramNetworkObject.isOnServer());
    assert (paramNetworkObject != null);
  }
  
  public RemoteLongPrimitiveArray(int paramInt, boolean paramBoolean)
  {
    this(paramInt, false, paramBoolean);
  }
  
  public RemoteLongPrimitiveArray(int paramInt, NetworkObject paramNetworkObject)
  {
    this(paramInt, false, paramNetworkObject);
  }
  
  public boolean hasChanged()
  {
    return this.changed;
  }
  
  public void setObserver(NetworkChangeObserver paramNetworkChangeObserver)
  {
    this.observer = paramNetworkChangeObserver;
  }
  
  public void setChanged(boolean paramBoolean)
  {
    this.changed = paramBoolean;
  }
  
  public boolean keepChanged()
  {
    return this.keepChanged;
  }
  
  public int byteLength()
  {
    return 4;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    for (paramInt = 0; paramInt < this.array.length; paramInt++) {
      set(paramInt, paramDataInputStream.readLong(), this.forcedClientSending);
    }
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    for (int i = 0; i < this.array.length; i++) {
      paramDataOutputStream.writeLong(this.array[i]);
    }
    return 4;
  }
  
  public void forceClientUpdates()
  {
    this.forcedClientSending = true;
  }
  
  public void set(long[] paramArrayOfLong)
  {
    set(paramArrayOfLong, this.forcedClientSending);
  }
  
  public void set(Long[] paramArrayOfLong)
  {
    for (int i = 0; i < paramArrayOfLong.length; i++) {
      set(i, paramArrayOfLong[i].longValue(), this.forcedClientSending);
    }
  }
  
  public void set(int paramInt, long paramLong)
  {
    set(paramInt, paramLong, this.forcedClientSending);
  }
  
  public void set(int paramInt, long paramLong, boolean paramBoolean)
  {
    if ((this.onServer) || (paramBoolean)) {
      setChanged((hasChanged()) || (paramLong != this.array[paramInt]));
    }
    this.array[paramInt] = paramLong;
    if ((hasChanged()) && (this.observer != null)) {
      this.observer.update(this);
    }
  }
  
  public long[] getLongArray()
  {
    return this.array;
  }
  
  public void set(long[] paramArrayOfLong, boolean paramBoolean)
  {
    for (int i = 0; i < paramArrayOfLong.length; i++) {
      set(i, paramArrayOfLong[i], paramBoolean);
    }
  }
  
  public void set(Long[] paramArrayOfLong, boolean paramBoolean)
  {
    for (int i = 0; i < paramArrayOfLong.length; i++) {
      set(i, paramArrayOfLong[i].longValue(), paramBoolean);
    }
  }
  
  public Long[] get()
  {
    if (!$assertionsDisabled) {
      throw new AssertionError();
    }
    Long[] arrayOfLong = new Long[this.array.length];
    for (int i = 0; i < arrayOfLong.length; i++) {
      arrayOfLong[i] = Long.valueOf(this.array[i]);
    }
    return arrayOfLong;
  }
  
  public void cleanAtRelease() {}
  
  public int arrayLength()
  {
    return this.array.length;
  }
  
  public boolean initialSynchUpdateOnly()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteLongPrimitiveArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
package org.schema.schine.network.objects.remote;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;

public class RemoteLongPrimitive
  implements Streamable
{
  private boolean changed;
  private NetworkChangeObserver observer;
  protected boolean keepChanged;
  private long value;
  private final boolean onServer;
  private boolean forcedClientSending;
  
  public RemoteLongPrimitive(long paramLong, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.value = paramLong;
    this.onServer = paramBoolean2;
    this.changed = paramBoolean1;
  }
  
  public RemoteLongPrimitive(long paramLong, boolean paramBoolean, NetworkObject paramNetworkObject)
  {
    this(paramLong, paramBoolean, paramNetworkObject.isOnServer());
    assert (paramNetworkObject != null);
  }
  
  public RemoteLongPrimitive(long paramLong, boolean paramBoolean)
  {
    this(paramLong, false, paramBoolean);
  }
  
  public RemoteLongPrimitive(long paramLong, NetworkObject paramNetworkObject)
  {
    this(paramLong, false, paramNetworkObject);
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
    set(paramDataInputStream.readLong());
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeLong(this.value);
    return 4;
  }
  
  public void forceClientUpdates()
  {
    this.forcedClientSending = true;
  }
  
  public void set(long paramLong)
  {
    set(paramLong, this.forcedClientSending);
  }
  
  public void set(Long paramLong)
  {
    set(paramLong.longValue());
  }
  
  public void set(long paramLong, boolean paramBoolean)
  {
    if ((this.onServer) || (paramBoolean)) {
      setChanged((hasChanged()) || (paramLong != this.value));
    }
    this.value = paramLong;
    if ((hasChanged()) && (this.observer != null)) {
      this.observer.update(this);
    }
  }
  
  public void set(Long paramLong, boolean paramBoolean)
  {
    set(paramLong);
  }
  
  public long getLong()
  {
    return this.value;
  }
  
  public Long get()
  {
    return Long.valueOf(this.value);
  }
  
  public void cleanAtRelease() {}
  
  public boolean initialSynchUpdateOnly()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteLongPrimitive
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
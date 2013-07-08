package org.schema.schine.network.objects.remote;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;

public class RemoteIntPrimitive
  implements Streamable
{
  private boolean changed;
  private NetworkChangeObserver observer;
  protected boolean keepChanged;
  private int value;
  private final boolean onServer;
  private boolean forcedClientSending;
  
  public RemoteIntPrimitive(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.value = paramInt;
    this.onServer = paramBoolean2;
    this.changed = paramBoolean1;
  }
  
  public RemoteIntPrimitive(int paramInt, boolean paramBoolean, NetworkObject paramNetworkObject)
  {
    this(paramInt, paramBoolean, paramNetworkObject.isOnServer());
    assert (paramNetworkObject != null);
  }
  
  public RemoteIntPrimitive(int paramInt, boolean paramBoolean)
  {
    this(paramInt, false, paramBoolean);
  }
  
  public RemoteIntPrimitive(int paramInt, NetworkObject paramNetworkObject)
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
    set(paramDataInputStream.readInt());
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeInt(this.value);
    return 4;
  }
  
  public void forceClientUpdates()
  {
    this.forcedClientSending = true;
  }
  
  public void set(int paramInt)
  {
    set(paramInt, this.forcedClientSending);
  }
  
  public void set(Integer paramInteger)
  {
    set(paramInteger.intValue());
  }
  
  public void set(int paramInt, boolean paramBoolean)
  {
    if ((this.onServer) || (paramBoolean)) {
      setChanged((hasChanged()) || (paramInt != this.value));
    }
    this.value = paramInt;
    if ((hasChanged()) && (this.observer != null)) {
      this.observer.update(this);
    }
  }
  
  public void set(Integer paramInteger, boolean paramBoolean)
  {
    set(paramInteger);
  }
  
  public int getInt()
  {
    return this.value;
  }
  
  public Integer get()
  {
    return Integer.valueOf(this.value);
  }
  
  public void cleanAtRelease() {}
  
  public boolean initialSynchUpdateOnly()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteIntPrimitive
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
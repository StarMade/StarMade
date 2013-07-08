package org.schema.schine.network.objects.remote;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;

public abstract class RemoteField
  implements Streamable
{
  protected NetworkChangeObserver observer;
  private boolean changed;
  private Object value;
  public final boolean onServer;
  protected boolean keepChanged;
  protected boolean forcedClientSending = false;
  
  public RemoteField(Object paramObject, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.value = paramObject;
    this.onServer = paramBoolean2;
    this.changed = paramBoolean1;
  }
  
  public RemoteField(Object paramObject, boolean paramBoolean, NetworkObject paramNetworkObject)
  {
    this(paramObject, paramBoolean, paramNetworkObject.isOnServer());
    assert (paramNetworkObject != null);
  }
  
  public RemoteField(Object paramObject, boolean paramBoolean)
  {
    this(paramObject, false, paramBoolean);
  }
  
  public RemoteField(Object paramObject, NetworkObject paramNetworkObject)
  {
    this(paramObject, false, paramNetworkObject);
  }
  
  public void forceClientUpdates()
  {
    this.forcedClientSending = true;
  }
  
  public abstract void fromByteStream(DataInputStream paramDataInputStream, int paramInt);
  
  public void cleanAtRelease() {}
  
  public Object get()
  {
    return this.value;
  }
  
  public final boolean hasChanged()
  {
    return this.changed;
  }
  
  public boolean keepChanged()
  {
    return this.keepChanged;
  }
  
  public boolean initialSynchUpdateOnly()
  {
    return false;
  }
  
  public void set(Object paramObject)
  {
    this.value = paramObject;
  }
  
  public void set(Object paramObject, boolean paramBoolean)
  {
    set(paramObject);
  }
  
  public synchronized void setObserver(NetworkChangeObserver paramNetworkChangeObserver)
  {
    this.observer = paramNetworkChangeObserver;
  }
  
  public void setChanged(boolean paramBoolean)
  {
    this.changed = paramBoolean;
  }
  
  public abstract int toByteStream(DataOutputStream paramDataOutputStream);
  
  public String toString()
  {
    return this.value.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteField
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
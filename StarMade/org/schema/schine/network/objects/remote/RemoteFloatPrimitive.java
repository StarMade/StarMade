package org.schema.schine.network.objects.remote;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;

public class RemoteFloatPrimitive
  implements Streamable
{
  private boolean changed;
  private NetworkChangeObserver observer;
  protected boolean keepChanged;
  private float value;
  private final boolean onServer;
  private boolean forcedClientSending;
  
  public RemoteFloatPrimitive(float paramFloat, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.value = paramFloat;
    this.onServer = paramBoolean2;
    this.changed = paramBoolean1;
  }
  
  public RemoteFloatPrimitive(float paramFloat, boolean paramBoolean, NetworkObject paramNetworkObject)
  {
    this(paramFloat, paramBoolean, paramNetworkObject.isOnServer());
    assert (paramNetworkObject != null);
  }
  
  public RemoteFloatPrimitive(float paramFloat, boolean paramBoolean)
  {
    this(paramFloat, false, paramBoolean);
  }
  
  public RemoteFloatPrimitive(float paramFloat, NetworkObject paramNetworkObject)
  {
    this(paramFloat, false, paramNetworkObject);
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
    set(paramDataInputStream.readFloat());
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeFloat(this.value);
    return 4;
  }
  
  public void forceClientUpdates()
  {
    this.forcedClientSending = true;
  }
  
  public void set(float paramFloat)
  {
    set(paramFloat, this.forcedClientSending);
  }
  
  public void set(Float paramFloat)
  {
    set(paramFloat.floatValue());
  }
  
  public void set(float paramFloat, boolean paramBoolean)
  {
    if ((this.onServer) || (paramBoolean)) {
      setChanged((hasChanged()) || (paramFloat != this.value));
    }
    this.value = paramFloat;
    if ((hasChanged()) && (this.observer != null)) {
      this.observer.update(this);
    }
  }
  
  public void set(Float paramFloat, boolean paramBoolean)
  {
    set(paramFloat);
  }
  
  public float getFloat()
  {
    return this.value;
  }
  
  public Float get()
  {
    return Float.valueOf(this.value);
  }
  
  public void cleanAtRelease() {}
  
  public boolean initialSynchUpdateOnly()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteFloatPrimitive
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
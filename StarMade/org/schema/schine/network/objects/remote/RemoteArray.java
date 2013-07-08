package org.schema.schine.network.objects.remote;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;

public abstract class RemoteArray
  extends RemoteField
  implements NetworkChangeObserver, StreamableArray
{
  public RemoteArray(RemoteField[] paramArrayOfRemoteField, NetworkObject paramNetworkObject)
  {
    super(paramArrayOfRemoteField, paramNetworkObject);
    init(paramArrayOfRemoteField);
  }
  
  public RemoteArray(RemoteField[] paramArrayOfRemoteField, boolean paramBoolean)
  {
    super(paramArrayOfRemoteField, paramBoolean);
    init(paramArrayOfRemoteField);
  }
  
  protected void addObservers()
  {
    for (int i = 0; i < ((RemoteField[])get()).length; i++) {
      ((RemoteField[])get())[i].observer = this;
    }
  }
  
  public int arrayLength()
  {
    return ((RemoteField[])get()).length;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    for (int i = 0; i < ((RemoteField[])get()).length; i++)
    {
      ((RemoteField[])get())[i].fromByteStream(paramDataInputStream, paramInt);
      set(i, (Comparable)get(i).get());
    }
  }
  
  public RemoteField get(int paramInt)
  {
    return ((RemoteField[])super.get())[paramInt];
  }
  
  protected abstract void init(RemoteField[] paramArrayOfRemoteField);
  
  public abstract void set(int paramInt, Comparable paramComparable);
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    for (int i = 0; i < ((RemoteField[])get()).length; i++) {
      ((RemoteField[])get())[i].toByteStream(paramDataOutputStream);
    }
    return byteLength();
  }
  
  public void update(Streamable paramStreamable)
  {
    setChanged(true);
    paramStreamable.setChanged(false);
    if (this.observer != null) {
      this.observer.update(this);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
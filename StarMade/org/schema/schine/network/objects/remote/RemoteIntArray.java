package org.schema.schine.network.objects.remote;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;

public class RemoteIntArray
  implements Streamable, StreamableArray
{
  private boolean changed;
  private NetworkChangeObserver observer;
  protected boolean keepChanged;
  private final boolean onServer;
  private boolean forcedClientSending;
  private int[] array;
  
  public RemoteIntArray(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.array = new int[paramInt];
    this.onServer = paramBoolean2;
    this.changed = paramBoolean1;
  }
  
  public RemoteIntArray(int paramInt, boolean paramBoolean, NetworkObject paramNetworkObject)
  {
    this(paramInt, paramBoolean, paramNetworkObject.isOnServer());
    assert (paramNetworkObject != null);
  }
  
  public RemoteIntArray(int paramInt, boolean paramBoolean)
  {
    this(paramInt, false, paramBoolean);
  }
  
  public RemoteIntArray(int paramInt, NetworkObject paramNetworkObject)
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
      set(paramInt, paramDataInputStream.readInt(), this.forcedClientSending);
    }
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    for (int i = 0; i < this.array.length; i++) {
      paramDataOutputStream.writeInt(this.array[i]);
    }
    return 4;
  }
  
  public void forceClientUpdates()
  {
    this.forcedClientSending = true;
  }
  
  public void set(int[] paramArrayOfInt)
  {
    set(paramArrayOfInt, this.forcedClientSending);
  }
  
  public void set(Integer[] paramArrayOfInteger)
  {
    for (int i = 0; i < paramArrayOfInteger.length; i++) {
      set(i, paramArrayOfInteger[i].intValue(), this.forcedClientSending);
    }
  }
  
  public void set(int paramInt1, int paramInt2)
  {
    set(paramInt1, paramInt2, this.forcedClientSending);
  }
  
  public void set(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if ((this.onServer) || (paramBoolean)) {
      setChanged((hasChanged()) || (paramInt2 != this.array[paramInt1]));
    }
    this.array[paramInt1] = paramInt2;
    if ((hasChanged()) && (this.observer != null)) {
      this.observer.update(this);
    }
  }
  
  public int[] getIntArray()
  {
    return this.array;
  }
  
  public void set(int[] paramArrayOfInt, boolean paramBoolean)
  {
    for (int i = 0; i < paramArrayOfInt.length; i++) {
      set(i, paramArrayOfInt[i], paramBoolean);
    }
  }
  
  public void set(Integer[] paramArrayOfInteger, boolean paramBoolean)
  {
    for (int i = 0; i < paramArrayOfInteger.length; i++) {
      set(i, paramArrayOfInteger[i].intValue(), paramBoolean);
    }
  }
  
  public Integer[] get()
  {
    if (!$assertionsDisabled) {
      throw new AssertionError();
    }
    Integer[] arrayOfInteger = new Integer[this.array.length];
    for (int i = 0; i < arrayOfInteger.length; i++) {
      arrayOfInteger[i] = Integer.valueOf(this.array[i]);
    }
    return arrayOfInteger;
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
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteIntArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
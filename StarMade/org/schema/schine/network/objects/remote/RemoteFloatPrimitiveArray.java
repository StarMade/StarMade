package org.schema.schine.network.objects.remote;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.schine.network.objects.NetworkObject;

public class RemoteFloatPrimitiveArray
  implements Streamable, StreamableArray
{
  private boolean changed;
  private NetworkChangeObserver observer;
  protected boolean keepChanged;
  private final boolean onServer;
  private boolean forcedClientSending;
  private float[] array;
  
  public RemoteFloatPrimitiveArray(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.array = new float[paramInt];
    this.onServer = paramBoolean2;
    this.changed = paramBoolean1;
  }
  
  public RemoteFloatPrimitiveArray(int paramInt, boolean paramBoolean, NetworkObject paramNetworkObject)
  {
    this(paramInt, paramBoolean, paramNetworkObject.isOnServer());
    assert (paramNetworkObject != null);
  }
  
  public RemoteFloatPrimitiveArray(int paramInt, boolean paramBoolean)
  {
    this(paramInt, false, paramBoolean);
  }
  
  public RemoteFloatPrimitiveArray(int paramInt, NetworkObject paramNetworkObject)
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
      set(paramInt, paramDataInputStream.readFloat(), this.forcedClientSending);
    }
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    for (int i = 0; i < this.array.length; i++) {
      paramDataOutputStream.writeFloat(this.array[i]);
    }
    return 4;
  }
  
  public void forceClientUpdates()
  {
    this.forcedClientSending = true;
  }
  
  public void set(float[] paramArrayOfFloat)
  {
    set(paramArrayOfFloat, this.forcedClientSending);
  }
  
  public void set(Float[] paramArrayOfFloat)
  {
    for (int i = 0; i < paramArrayOfFloat.length; i++) {
      set(i, paramArrayOfFloat[i].floatValue(), this.forcedClientSending);
    }
  }
  
  public void set(int paramInt, float paramFloat)
  {
    set(paramInt, paramFloat, this.forcedClientSending);
  }
  
  public void set(int paramInt, float paramFloat, boolean paramBoolean)
  {
    if ((this.onServer) || (paramBoolean)) {
      setChanged((hasChanged()) || (paramFloat != this.array[paramInt]));
    }
    this.array[paramInt] = paramFloat;
    if ((hasChanged()) && (this.observer != null)) {
      this.observer.update(this);
    }
  }
  
  public float[] getFloatArray()
  {
    return this.array;
  }
  
  public void set(float[] paramArrayOfFloat, boolean paramBoolean)
  {
    for (int i = 0; i < paramArrayOfFloat.length; i++) {
      set(i, paramArrayOfFloat[i], paramBoolean);
    }
  }
  
  public void set(Float[] paramArrayOfFloat, boolean paramBoolean)
  {
    for (int i = 0; i < paramArrayOfFloat.length; i++) {
      set(i, paramArrayOfFloat[i].floatValue(), paramBoolean);
    }
  }
  
  public Float[] get()
  {
    if (!$assertionsDisabled) {
      throw new AssertionError();
    }
    Float[] arrayOfFloat = new Float[this.array.length];
    for (int i = 0; i < arrayOfFloat.length; i++) {
      arrayOfFloat[i] = Float.valueOf(this.array[i]);
    }
    return arrayOfFloat;
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
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteFloatPrimitiveArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
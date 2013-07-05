package org.schema.schine.network.objects.remote;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public abstract interface Streamable extends NetworkChangeObservable
{
  public abstract int byteLength();

  public abstract void fromByteStream(DataInputStream paramDataInputStream, int paramInt);

  public abstract int toByteStream(DataOutputStream paramDataOutputStream);

  public abstract void set(Object paramObject);

  public abstract void set(Object paramObject, boolean paramBoolean);

  public abstract Object get();

  public abstract void cleanAtRelease();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.Streamable
 * JD-Core Version:    0.6.2
 */
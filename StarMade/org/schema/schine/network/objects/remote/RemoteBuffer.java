package org.schema.schine.network.objects.remote;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.pool.PrimitiveBufferPool;

public class RemoteBuffer
  extends RemoteField
  implements List
{
  public Class clazz;
  protected ObjectArrayList receiveBuffer;
  private PrimitiveBufferPool pool;
  protected static final int MAX_BATCH = 8;
  
  public RemoteBuffer(Class paramClass, NetworkObject paramNetworkObject)
  {
    super(new ObjectArrayList(), false, paramNetworkObject);
    this.clazz = paramClass;
    setReceiveBuffer(new ObjectArrayList());
    cacheConstructor();
  }
  
  public RemoteBuffer(Class paramClass, boolean paramBoolean)
  {
    super(new ObjectArrayList(), false, paramBoolean);
    this.clazz = paramClass;
    setReceiveBuffer(new ObjectArrayList());
    cacheConstructor();
  }
  
  public boolean add(Streamable paramStreamable)
  {
    paramStreamable = ((ObjectArrayList)get()).add(paramStreamable);
    setChanged(paramStreamable);
    this.observer.update(this);
    return paramStreamable;
  }
  
  public void add(int paramInt, Streamable paramStreamable)
  {
    ((ObjectArrayList)get()).add(paramInt, paramStreamable);
    setChanged(true);
    this.observer.update(this);
  }
  
  public boolean addAll(Collection paramCollection)
  {
    if ((paramCollection = ((ObjectArrayList)get()).addAll(paramCollection)))
    {
      setChanged(paramCollection);
      this.observer.update(this);
    }
    return paramCollection;
  }
  
  public boolean addAll(int paramInt, Collection paramCollection)
  {
    if ((paramInt = ((ObjectArrayList)get()).addAll(paramInt, paramCollection)))
    {
      setChanged(paramInt);
      this.observer.update(this);
    }
    return paramInt;
  }
  
  public int byteLength()
  {
    return 4;
  }
  
  public void cacheConstructor()
  {
    this.pool = PrimitiveBufferPool.get(this.clazz);
    assert (this.pool != null) : (" pool is null for " + this.clazz);
  }
  
  public void clear()
  {
    ((ObjectArrayList)get()).clear();
  }
  
  public void clearReceiveBuffer()
  {
    for (int i = 0; i < getReceiveBuffer().size(); i++)
    {
      assert (getReceiveBuffer() != null) : "ReceiveBuffer null";
      assert (this.pool != null) : ("pool null for " + this.clazz);
      assert (getReceiveBuffer().get(i) != null) : ("element null " + i);
      this.pool.release((Streamable)getReceiveBuffer().get(i));
    }
    getReceiveBuffer().clear();
  }
  
  public boolean contains(Object paramObject)
  {
    return ((ObjectArrayList)get()).contains(paramObject);
  }
  
  public boolean containsAll(Collection paramCollection)
  {
    return ((ObjectArrayList)get()).containsAll(paramCollection);
  }
  
  public Streamable get(int paramInt)
  {
    return (Streamable)((ObjectArrayList)get()).get(paramInt);
  }
  
  public ObjectArrayList getReceiveBuffer()
  {
    return this.receiveBuffer;
  }
  
  public int indexOf(Object paramObject)
  {
    return ((ObjectArrayList)get()).indexOf(paramObject);
  }
  
  public boolean isEmpty()
  {
    return ((ObjectArrayList)get()).isEmpty();
  }
  
  public Iterator iterator()
  {
    return ((ObjectArrayList)get()).iterator();
  }
  
  public int lastIndexOf(Object paramObject)
  {
    return ((ObjectArrayList)get()).lastIndexOf(paramObject);
  }
  
  public ListIterator listIterator()
  {
    return ((ObjectArrayList)get()).listIterator();
  }
  
  public ListIterator listIterator(int paramInt)
  {
    return ((ObjectArrayList)get()).listIterator(paramInt);
  }
  
  public Streamable remove(int paramInt)
  {
    return (Streamable)((ObjectArrayList)get()).remove(paramInt);
  }
  
  public boolean remove(Object paramObject)
  {
    return ((ObjectArrayList)get()).remove(paramObject);
  }
  
  public boolean removeAll(Collection paramCollection)
  {
    return ((ObjectArrayList)get()).removeAll(paramCollection);
  }
  
  public boolean retainAll(Collection paramCollection)
  {
    return ((ObjectArrayList)get()).retainAll(paramCollection);
  }
  
  public Streamable set(int paramInt, Streamable paramStreamable)
  {
    paramInt = (Streamable)((ObjectArrayList)get()).set(paramInt, paramStreamable);
    setChanged(true);
    this.observer.update(this);
    return paramInt;
  }
  
  public void setReceiveBuffer(ObjectArrayList paramObjectArrayList)
  {
    this.receiveBuffer = paramObjectArrayList;
  }
  
  public int size()
  {
    return ((ObjectArrayList)get()).size();
  }
  
  public List subList(int paramInt1, int paramInt2)
  {
    return ((ObjectArrayList)get()).subList(paramInt1, paramInt2);
  }
  
  public Object[] toArray()
  {
    return ((ObjectArrayList)get()).toArray();
  }
  
  public Object[] toArray(Object[] paramArrayOfObject)
  {
    return ((ObjectArrayList)get()).toArray(paramArrayOfObject);
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    int i = paramDataInputStream.readInt();
    for (int j = 0; j < i; j++)
    {
      Streamable localStreamable;
      (localStreamable = this.pool.get(this.onServer)).fromByteStream(paramDataInputStream, paramInt);
      this.receiveBuffer.add(localStreamable);
    }
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    int i = Math.min(8, ((ObjectArrayList)get()).size());
    paramDataOutputStream.writeInt(i);
    int j = 0;
    for (int k = 0; k < i; k++)
    {
      Streamable localStreamable = (Streamable)((ObjectArrayList)get()).remove(0);
      j += localStreamable.toByteStream(paramDataOutputStream);
      localStreamable.setChanged(false);
    }
    this.keepChanged = (!((ObjectArrayList)get()).isEmpty());
    return j + 4;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
package it.unimi.dsi.fastutil.objects;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;

public class ObjectArraySet<K>
  extends AbstractObjectSet<K>
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = 1L;
  private transient Object[] field_75;
  private int size;
  
  public ObjectArraySet(Object[] local_a)
  {
    this.field_75 = local_a;
    this.size = local_a.length;
  }
  
  public ObjectArraySet()
  {
    this.field_75 = ObjectArrays.EMPTY_ARRAY;
  }
  
  public ObjectArraySet(int capacity)
  {
    this.field_75 = new Object[capacity];
  }
  
  public ObjectArraySet(ObjectCollection<K> local_c)
  {
    this(local_c.size());
    addAll(local_c);
  }
  
  public ObjectArraySet(Collection<? extends K> local_c)
  {
    this(local_c.size());
    addAll(local_c);
  }
  
  public ObjectArraySet(Object[] local_a, int size)
  {
    this.field_75 = local_a;
    this.size = size;
    if (size > local_a.length) {
      throw new IllegalArgumentException("The provided size (" + size + ") is larger than or equal to the array size (" + local_a.length + ")");
    }
  }
  
  private int findKey(Object local_o)
  {
    int local_i = this.size;
    while (local_i-- != 0) {
      if (this.field_75[local_i] == null ? local_o == null : this.field_75[local_i].equals(local_o)) {
        return local_i;
      }
    }
    return -1;
  }
  
  public ObjectIterator<K> iterator()
  {
    return ObjectIterators.wrap((Object[])this.field_75, 0, this.size);
  }
  
  public boolean contains(Object local_k)
  {
    return findKey(local_k) != -1;
  }
  
  public int size()
  {
    return this.size;
  }
  
  public boolean remove(Object local_k)
  {
    int pos = findKey(local_k);
    if (pos == -1) {
      return false;
    }
    int tail = this.size - pos - 1;
    for (int local_i = 0; local_i < tail; local_i++) {
      this.field_75[(pos + local_i)] = this.field_75[(pos + local_i + 1)];
    }
    this.size -= 1;
    this.field_75[this.size] = null;
    return true;
  }
  
  public boolean add(K local_k)
  {
    int pos = findKey(local_k);
    if (pos != -1) {
      return false;
    }
    if (this.size == this.field_75.length)
    {
      Object[] local_b = new Object[this.size == 0 ? 2 : this.size * 2];
      int local_i = this.size;
      while (local_i-- != 0) {
        local_b[local_i] = this.field_75[local_i];
      }
      this.field_75 = local_b;
    }
    this.field_75[(this.size++)] = local_k;
    return true;
  }
  
  public void clear()
  {
    int local_i = this.size;
    while (local_i-- != 0) {
      this.field_75[local_i] = null;
    }
    this.size = 0;
  }
  
  public boolean isEmpty()
  {
    return this.size == 0;
  }
  
  public ObjectArraySet<K> clone()
  {
    ObjectArraySet<K> local_c;
    try
    {
      local_c = (ObjectArraySet)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.field_75 = ((Object[])this.field_75.clone());
    return local_c;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    local_s.defaultWriteObject();
    for (int local_i = 0; local_i < this.size; local_i++) {
      local_s.writeObject(this.field_75[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_75 = new Object[this.size];
    for (int local_i = 0; local_i < this.size; local_i++) {
      this.field_75[local_i] = local_s.readObject();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectArraySet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
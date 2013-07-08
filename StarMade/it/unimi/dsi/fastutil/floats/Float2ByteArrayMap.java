package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.bytes.ByteArraySet;
import it.unimi.dsi.fastutil.bytes.ByteArrays;
import it.unimi.dsi.fastutil.bytes.ByteCollection;
import it.unimi.dsi.fastutil.bytes.ByteCollections;
import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Float2ByteArrayMap
  extends AbstractFloat2ByteMap
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = 1L;
  private transient float[] key;
  private transient byte[] value;
  private int size;
  
  public Float2ByteArrayMap(float[] key, byte[] value)
  {
    this.key = key;
    this.value = value;
    this.size = key.length;
    if (key.length != value.length) {
      throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
    }
  }
  
  public Float2ByteArrayMap()
  {
    this.key = FloatArrays.EMPTY_ARRAY;
    this.value = ByteArrays.EMPTY_ARRAY;
  }
  
  public Float2ByteArrayMap(int capacity)
  {
    this.key = new float[capacity];
    this.value = new byte[capacity];
  }
  
  public Float2ByteArrayMap(Float2ByteMap local_m)
  {
    this(local_m.size());
    putAll(local_m);
  }
  
  public Float2ByteArrayMap(Map<? extends Float, ? extends Byte> local_m)
  {
    this(local_m.size());
    putAll(local_m);
  }
  
  public Float2ByteArrayMap(float[] key, byte[] value, int size)
  {
    this.key = key;
    this.value = value;
    this.size = size;
    if (key.length != value.length) {
      throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
    }
    if (size > key.length) {
      throw new IllegalArgumentException("The provided size (" + size + ") is larger than or equal to the backing-arrays size (" + key.length + ")");
    }
  }
  
  public Float2ByteMap.FastEntrySet float2ByteEntrySet()
  {
    return new EntrySet(null);
  }
  
  private int findKey(float local_k)
  {
    float[] key = this.key;
    int local_i = this.size;
    while (local_i-- != 0) {
      if (key[local_i] == local_k) {
        return local_i;
      }
    }
    return -1;
  }
  
  public byte get(float local_k)
  {
    float[] key = this.key;
    int local_i = this.size;
    while (local_i-- != 0) {
      if (key[local_i] == local_k) {
        return this.value[local_i];
      }
    }
    return this.defRetValue;
  }
  
  public int size()
  {
    return this.size;
  }
  
  public void clear()
  {
    this.size = 0;
  }
  
  public boolean containsKey(float local_k)
  {
    return findKey(local_k) != -1;
  }
  
  public boolean containsValue(byte local_v)
  {
    int local_i = this.size;
    while (local_i-- != 0) {
      if (this.value[local_i] == local_v) {
        return true;
      }
    }
    return false;
  }
  
  public boolean isEmpty()
  {
    return this.size == 0;
  }
  
  public byte put(float local_k, byte local_v)
  {
    int oldKey = findKey(local_k);
    if (oldKey != -1)
    {
      byte oldValue = this.value[oldKey];
      this.value[oldKey] = local_v;
      return oldValue;
    }
    if (this.size == this.key.length)
    {
      float[] oldValue = new float[this.size == 0 ? 2 : this.size * 2];
      byte[] newValue = new byte[this.size == 0 ? 2 : this.size * 2];
      int local_i = this.size;
      while (local_i-- != 0)
      {
        oldValue[local_i] = this.key[local_i];
        newValue[local_i] = this.value[local_i];
      }
      this.key = oldValue;
      this.value = newValue;
    }
    this.key[this.size] = local_k;
    this.value[this.size] = local_v;
    this.size += 1;
    return this.defRetValue;
  }
  
  public byte remove(float local_k)
  {
    int oldPos = findKey(local_k);
    if (oldPos == -1) {
      return this.defRetValue;
    }
    byte oldValue = this.value[oldPos];
    int tail = this.size - oldPos - 1;
    for (int local_i = 0; local_i < tail; local_i++)
    {
      this.key[(oldPos + local_i)] = this.key[(oldPos + local_i + 1)];
      this.value[(oldPos + local_i)] = this.value[(oldPos + local_i + 1)];
    }
    this.size -= 1;
    return oldValue;
  }
  
  public FloatSet keySet()
  {
    return new FloatArraySet(this.key, this.size);
  }
  
  public ByteCollection values()
  {
    return ByteCollections.unmodifiable(new ByteArraySet(this.value, this.size));
  }
  
  public Float2ByteArrayMap clone()
  {
    Float2ByteArrayMap local_c;
    try
    {
      local_c = (Float2ByteArrayMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.key = ((float[])this.key.clone());
    local_c.value = ((byte[])this.value.clone());
    return local_c;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    local_s.defaultWriteObject();
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      local_s.writeFloat(this.key[local_i]);
      local_s.writeByte(this.value[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.key = new float[this.size];
    this.value = new byte[this.size];
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      this.key[local_i] = local_s.readFloat();
      this.value[local_i] = local_s.readByte();
    }
  }
  
  private final class EntrySet
    extends AbstractObjectSet<Float2ByteMap.Entry>
    implements Float2ByteMap.FastEntrySet
  {
    private EntrySet() {}
    
    public ObjectIterator<Float2ByteMap.Entry> iterator()
    {
      new AbstractObjectIterator()
      {
        int next = 0;
        
        public boolean hasNext()
        {
          return this.next < Float2ByteArrayMap.this.size;
        }
        
        public Float2ByteMap.Entry next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return new AbstractFloat2ByteMap.BasicEntry(Float2ByteArrayMap.this.key[this.next], Float2ByteArrayMap.this.value[(this.next++)]);
        }
      };
    }
    
    public ObjectIterator<Float2ByteMap.Entry> fastIterator()
    {
      new AbstractObjectIterator()
      {
        int next = 0;
        final AbstractFloat2ByteMap.BasicEntry entry = new AbstractFloat2ByteMap.BasicEntry(0.0F, (byte)0);
        
        public boolean hasNext()
        {
          return this.next < Float2ByteArrayMap.this.size;
        }
        
        public Float2ByteMap.Entry next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          this.entry.key = Float2ByteArrayMap.this.key[this.next];
          this.entry.value = Float2ByteArrayMap.this.value[(this.next++)];
          return this.entry;
        }
      };
    }
    
    public int size()
    {
      return Float2ByteArrayMap.this.size;
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Float, Byte> local_e = (Map.Entry)local_o;
      float local_k = ((Float)local_e.getKey()).floatValue();
      return (Float2ByteArrayMap.this.containsKey(local_k)) && (Float2ByteArrayMap.this.get(local_k) == ((Byte)local_e.getValue()).byteValue());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2ByteArrayMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.objects.AbstractObjectCollection;
import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractByte2ObjectSortedMap<V>
  extends AbstractByte2ObjectMap<V>
  implements Byte2ObjectSortedMap<V>
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Byte2ObjectSortedMap<V> headMap(Byte local_to)
  {
    return headMap(local_to.byteValue());
  }
  
  public Byte2ObjectSortedMap<V> tailMap(Byte from)
  {
    return tailMap(from.byteValue());
  }
  
  public Byte2ObjectSortedMap<V> subMap(Byte from, Byte local_to)
  {
    return subMap(from.byteValue(), local_to.byteValue());
  }
  
  public Byte firstKey()
  {
    return Byte.valueOf(firstByteKey());
  }
  
  public Byte lastKey()
  {
    return Byte.valueOf(lastByteKey());
  }
  
  public ByteSortedSet keySet()
  {
    return new KeySet();
  }
  
  public ObjectCollection<V> values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Byte, V>> entrySet()
  {
    return byte2ObjectEntrySet();
  }
  
  protected static class ValuesIterator<V>
    extends AbstractObjectIterator<V>
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Byte, V>> field_3;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Byte, V>> local_i)
    {
      this.field_3 = local_i;
    }
    
    public V next()
    {
      return ((Map.Entry)this.field_3.next()).getValue();
    }
    
    public boolean hasNext()
    {
      return this.field_3.hasNext();
    }
  }
  
  protected class ValuesCollection
    extends AbstractObjectCollection<V>
  {
    protected ValuesCollection() {}
    
    public ObjectIterator<V> iterator()
    {
      return new AbstractByte2ObjectSortedMap.ValuesIterator(AbstractByte2ObjectSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(Object local_k)
    {
      return AbstractByte2ObjectSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractByte2ObjectSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractByte2ObjectSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator<V>
    extends AbstractByteBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Byte, V>> field_58;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Byte, V>> local_i)
    {
      this.field_58 = local_i;
    }
    
    public byte nextByte()
    {
      return ((Byte)((Map.Entry)this.field_58.next()).getKey()).byteValue();
    }
    
    public byte previousByte()
    {
      return ((Byte)((Map.Entry)this.field_58.previous()).getKey()).byteValue();
    }
    
    public boolean hasNext()
    {
      return this.field_58.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_58.hasPrevious();
    }
  }
  
  protected class KeySet
    extends AbstractByteSortedSet
  {
    protected KeySet() {}
    
    public boolean contains(byte local_k)
    {
      return AbstractByte2ObjectSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractByte2ObjectSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractByte2ObjectSortedMap.this.clear();
    }
    
    public ByteComparator comparator()
    {
      return AbstractByte2ObjectSortedMap.this.comparator();
    }
    
    public byte firstByte()
    {
      return AbstractByte2ObjectSortedMap.this.firstByteKey();
    }
    
    public byte lastByte()
    {
      return AbstractByte2ObjectSortedMap.this.lastByteKey();
    }
    
    public ByteSortedSet headSet(byte local_to)
    {
      return AbstractByte2ObjectSortedMap.this.headMap(local_to).keySet();
    }
    
    public ByteSortedSet tailSet(byte from)
    {
      return AbstractByte2ObjectSortedMap.this.tailMap(from).keySet();
    }
    
    public ByteSortedSet subSet(byte from, byte local_to)
    {
      return AbstractByte2ObjectSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public ByteBidirectionalIterator iterator(byte from)
    {
      return new AbstractByte2ObjectSortedMap.KeySetIterator(AbstractByte2ObjectSortedMap.this.entrySet().iterator(new AbstractByte2ObjectMap.BasicEntry(from, null)));
    }
    
    public ByteBidirectionalIterator iterator()
    {
      return new AbstractByte2ObjectSortedMap.KeySetIterator(AbstractByte2ObjectSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByte2ObjectSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
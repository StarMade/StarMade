package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
import it.unimi.dsi.fastutil.objects.AbstractReferenceCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import java.util.Map.Entry;

public abstract class AbstractByte2ReferenceSortedMap<V>
  extends AbstractByte2ReferenceMap<V>
  implements Byte2ReferenceSortedMap<V>
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Byte2ReferenceSortedMap<V> headMap(Byte local_to)
  {
    return headMap(local_to.byteValue());
  }
  
  public Byte2ReferenceSortedMap<V> tailMap(Byte from)
  {
    return tailMap(from.byteValue());
  }
  
  public Byte2ReferenceSortedMap<V> subMap(Byte from, Byte local_to)
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
  
  public ReferenceCollection<V> values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Byte, V>> entrySet()
  {
    return byte2ReferenceEntrySet();
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
    extends AbstractReferenceCollection<V>
  {
    protected ValuesCollection() {}
    
    public ObjectIterator<V> iterator()
    {
      return new AbstractByte2ReferenceSortedMap.ValuesIterator(AbstractByte2ReferenceSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(Object local_k)
    {
      return AbstractByte2ReferenceSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractByte2ReferenceSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractByte2ReferenceSortedMap.this.clear();
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
      return AbstractByte2ReferenceSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractByte2ReferenceSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractByte2ReferenceSortedMap.this.clear();
    }
    
    public ByteComparator comparator()
    {
      return AbstractByte2ReferenceSortedMap.this.comparator();
    }
    
    public byte firstByte()
    {
      return AbstractByte2ReferenceSortedMap.this.firstByteKey();
    }
    
    public byte lastByte()
    {
      return AbstractByte2ReferenceSortedMap.this.lastByteKey();
    }
    
    public ByteSortedSet headSet(byte local_to)
    {
      return AbstractByte2ReferenceSortedMap.this.headMap(local_to).keySet();
    }
    
    public ByteSortedSet tailSet(byte from)
    {
      return AbstractByte2ReferenceSortedMap.this.tailMap(from).keySet();
    }
    
    public ByteSortedSet subSet(byte from, byte local_to)
    {
      return AbstractByte2ReferenceSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public ByteBidirectionalIterator iterator(byte from)
    {
      return new AbstractByte2ReferenceSortedMap.KeySetIterator(AbstractByte2ReferenceSortedMap.this.entrySet().iterator(new AbstractByte2ReferenceMap.BasicEntry(from, null)));
    }
    
    public ByteBidirectionalIterator iterator()
    {
      return new AbstractByte2ReferenceSortedMap.KeySetIterator(AbstractByte2ReferenceSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByte2ReferenceSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
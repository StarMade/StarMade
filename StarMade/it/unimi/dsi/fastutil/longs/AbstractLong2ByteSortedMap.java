package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.bytes.AbstractByteCollection;
import it.unimi.dsi.fastutil.bytes.AbstractByteIterator;
import it.unimi.dsi.fastutil.bytes.ByteCollection;
import it.unimi.dsi.fastutil.bytes.ByteIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractLong2ByteSortedMap
  extends AbstractLong2ByteMap
  implements Long2ByteSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Long2ByteSortedMap headMap(Long local_to)
  {
    return headMap(local_to.longValue());
  }
  
  public Long2ByteSortedMap tailMap(Long from)
  {
    return tailMap(from.longValue());
  }
  
  public Long2ByteSortedMap subMap(Long from, Long local_to)
  {
    return subMap(from.longValue(), local_to.longValue());
  }
  
  public Long firstKey()
  {
    return Long.valueOf(firstLongKey());
  }
  
  public Long lastKey()
  {
    return Long.valueOf(lastLongKey());
  }
  
  public LongSortedSet keySet()
  {
    return new KeySet();
  }
  
  public ByteCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Long, Byte>> entrySet()
  {
    return long2ByteEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractByteIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Long, Byte>> field_58;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Long, Byte>> local_i)
    {
      this.field_58 = local_i;
    }
    
    public byte nextByte()
    {
      return ((Byte)((Map.Entry)this.field_58.next()).getValue()).byteValue();
    }
    
    public boolean hasNext()
    {
      return this.field_58.hasNext();
    }
  }
  
  protected class ValuesCollection
    extends AbstractByteCollection
  {
    protected ValuesCollection() {}
    
    public ByteIterator iterator()
    {
      return new AbstractLong2ByteSortedMap.ValuesIterator(AbstractLong2ByteSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(byte local_k)
    {
      return AbstractLong2ByteSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractLong2ByteSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractLong2ByteSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractLongBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Long, Byte>> field_1;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Long, Byte>> local_i)
    {
      this.field_1 = local_i;
    }
    
    public long nextLong()
    {
      return ((Long)((Map.Entry)this.field_1.next()).getKey()).longValue();
    }
    
    public long previousLong()
    {
      return ((Long)((Map.Entry)this.field_1.previous()).getKey()).longValue();
    }
    
    public boolean hasNext()
    {
      return this.field_1.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_1.hasPrevious();
    }
  }
  
  protected class KeySet
    extends AbstractLongSortedSet
  {
    protected KeySet() {}
    
    public boolean contains(long local_k)
    {
      return AbstractLong2ByteSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractLong2ByteSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractLong2ByteSortedMap.this.clear();
    }
    
    public LongComparator comparator()
    {
      return AbstractLong2ByteSortedMap.this.comparator();
    }
    
    public long firstLong()
    {
      return AbstractLong2ByteSortedMap.this.firstLongKey();
    }
    
    public long lastLong()
    {
      return AbstractLong2ByteSortedMap.this.lastLongKey();
    }
    
    public LongSortedSet headSet(long local_to)
    {
      return AbstractLong2ByteSortedMap.this.headMap(local_to).keySet();
    }
    
    public LongSortedSet tailSet(long from)
    {
      return AbstractLong2ByteSortedMap.this.tailMap(from).keySet();
    }
    
    public LongSortedSet subSet(long from, long local_to)
    {
      return AbstractLong2ByteSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public LongBidirectionalIterator iterator(long from)
    {
      return new AbstractLong2ByteSortedMap.KeySetIterator(AbstractLong2ByteSortedMap.this.entrySet().iterator(new AbstractLong2ByteMap.BasicEntry(from, (byte)0)));
    }
    
    public LongBidirectionalIterator iterator()
    {
      return new AbstractLong2ByteSortedMap.KeySetIterator(AbstractLong2ByteSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2ByteSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
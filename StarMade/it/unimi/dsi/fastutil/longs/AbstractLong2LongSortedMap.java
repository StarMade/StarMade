package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractLong2LongSortedMap
  extends AbstractLong2LongMap
  implements Long2LongSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Long2LongSortedMap headMap(Long local_to)
  {
    return headMap(local_to.longValue());
  }
  
  public Long2LongSortedMap tailMap(Long from)
  {
    return tailMap(from.longValue());
  }
  
  public Long2LongSortedMap subMap(Long from, Long local_to)
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
  
  public LongCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Long, Long>> entrySet()
  {
    return long2LongEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractLongIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Long, Long>> field_1;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Long, Long>> local_i)
    {
      this.field_1 = local_i;
    }
    
    public long nextLong()
    {
      return ((Long)((Map.Entry)this.field_1.next()).getValue()).longValue();
    }
    
    public boolean hasNext()
    {
      return this.field_1.hasNext();
    }
  }
  
  protected class ValuesCollection
    extends AbstractLongCollection
  {
    protected ValuesCollection() {}
    
    public LongIterator iterator()
    {
      return new AbstractLong2LongSortedMap.ValuesIterator(AbstractLong2LongSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(long local_k)
    {
      return AbstractLong2LongSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractLong2LongSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractLong2LongSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractLongBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Long, Long>> field_1;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Long, Long>> local_i)
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
      return AbstractLong2LongSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractLong2LongSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractLong2LongSortedMap.this.clear();
    }
    
    public LongComparator comparator()
    {
      return AbstractLong2LongSortedMap.this.comparator();
    }
    
    public long firstLong()
    {
      return AbstractLong2LongSortedMap.this.firstLongKey();
    }
    
    public long lastLong()
    {
      return AbstractLong2LongSortedMap.this.lastLongKey();
    }
    
    public LongSortedSet headSet(long local_to)
    {
      return AbstractLong2LongSortedMap.this.headMap(local_to).keySet();
    }
    
    public LongSortedSet tailSet(long from)
    {
      return AbstractLong2LongSortedMap.this.tailMap(from).keySet();
    }
    
    public LongSortedSet subSet(long from, long local_to)
    {
      return AbstractLong2LongSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public LongBidirectionalIterator iterator(long from)
    {
      return new AbstractLong2LongSortedMap.KeySetIterator(AbstractLong2LongSortedMap.this.entrySet().iterator(new AbstractLong2LongMap.BasicEntry(from, 0L)));
    }
    
    public LongBidirectionalIterator iterator()
    {
      return new AbstractLong2LongSortedMap.KeySetIterator(AbstractLong2LongSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2LongSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
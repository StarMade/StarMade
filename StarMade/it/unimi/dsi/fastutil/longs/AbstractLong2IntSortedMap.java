package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.ints.AbstractIntCollection;
import it.unimi.dsi.fastutil.ints.AbstractIntIterator;
import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractLong2IntSortedMap
  extends AbstractLong2IntMap
  implements Long2IntSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Long2IntSortedMap headMap(Long local_to)
  {
    return headMap(local_to.longValue());
  }
  
  public Long2IntSortedMap tailMap(Long from)
  {
    return tailMap(from.longValue());
  }
  
  public Long2IntSortedMap subMap(Long from, Long local_to)
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
  
  public IntCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Long, Integer>> entrySet()
  {
    return long2IntEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractIntIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Long, Integer>> field_70;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Long, Integer>> local_i)
    {
      this.field_70 = local_i;
    }
    
    public int nextInt()
    {
      return ((Integer)((Map.Entry)this.field_70.next()).getValue()).intValue();
    }
    
    public boolean hasNext()
    {
      return this.field_70.hasNext();
    }
  }
  
  protected class ValuesCollection
    extends AbstractIntCollection
  {
    protected ValuesCollection() {}
    
    public IntIterator iterator()
    {
      return new AbstractLong2IntSortedMap.ValuesIterator(AbstractLong2IntSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(int local_k)
    {
      return AbstractLong2IntSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractLong2IntSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractLong2IntSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractLongBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Long, Integer>> field_1;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Long, Integer>> local_i)
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
      return AbstractLong2IntSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractLong2IntSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractLong2IntSortedMap.this.clear();
    }
    
    public LongComparator comparator()
    {
      return AbstractLong2IntSortedMap.this.comparator();
    }
    
    public long firstLong()
    {
      return AbstractLong2IntSortedMap.this.firstLongKey();
    }
    
    public long lastLong()
    {
      return AbstractLong2IntSortedMap.this.lastLongKey();
    }
    
    public LongSortedSet headSet(long local_to)
    {
      return AbstractLong2IntSortedMap.this.headMap(local_to).keySet();
    }
    
    public LongSortedSet tailSet(long from)
    {
      return AbstractLong2IntSortedMap.this.tailMap(from).keySet();
    }
    
    public LongSortedSet subSet(long from, long local_to)
    {
      return AbstractLong2IntSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public LongBidirectionalIterator iterator(long from)
    {
      return new AbstractLong2IntSortedMap.KeySetIterator(AbstractLong2IntSortedMap.this.entrySet().iterator(new AbstractLong2IntMap.BasicEntry(from, 0)));
    }
    
    public LongBidirectionalIterator iterator()
    {
      return new AbstractLong2IntSortedMap.KeySetIterator(AbstractLong2IntSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2IntSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.shorts.AbstractShortCollection;
import it.unimi.dsi.fastutil.shorts.AbstractShortIterator;
import it.unimi.dsi.fastutil.shorts.ShortCollection;
import it.unimi.dsi.fastutil.shorts.ShortIterator;
import java.util.Map.Entry;

public abstract class AbstractLong2ShortSortedMap
  extends AbstractLong2ShortMap
  implements Long2ShortSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Long2ShortSortedMap headMap(Long local_to)
  {
    return headMap(local_to.longValue());
  }
  
  public Long2ShortSortedMap tailMap(Long from)
  {
    return tailMap(from.longValue());
  }
  
  public Long2ShortSortedMap subMap(Long from, Long local_to)
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
  
  public ShortCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Long, Short>> entrySet()
  {
    return long2ShortEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractShortIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Long, Short>> field_53;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Long, Short>> local_i)
    {
      this.field_53 = local_i;
    }
    
    public short nextShort()
    {
      return ((Short)((Map.Entry)this.field_53.next()).getValue()).shortValue();
    }
    
    public boolean hasNext()
    {
      return this.field_53.hasNext();
    }
  }
  
  protected class ValuesCollection
    extends AbstractShortCollection
  {
    protected ValuesCollection() {}
    
    public ShortIterator iterator()
    {
      return new AbstractLong2ShortSortedMap.ValuesIterator(AbstractLong2ShortSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(short local_k)
    {
      return AbstractLong2ShortSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractLong2ShortSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractLong2ShortSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractLongBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Long, Short>> field_1;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Long, Short>> local_i)
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
      return AbstractLong2ShortSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractLong2ShortSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractLong2ShortSortedMap.this.clear();
    }
    
    public LongComparator comparator()
    {
      return AbstractLong2ShortSortedMap.this.comparator();
    }
    
    public long firstLong()
    {
      return AbstractLong2ShortSortedMap.this.firstLongKey();
    }
    
    public long lastLong()
    {
      return AbstractLong2ShortSortedMap.this.lastLongKey();
    }
    
    public LongSortedSet headSet(long local_to)
    {
      return AbstractLong2ShortSortedMap.this.headMap(local_to).keySet();
    }
    
    public LongSortedSet tailSet(long from)
    {
      return AbstractLong2ShortSortedMap.this.tailMap(from).keySet();
    }
    
    public LongSortedSet subSet(long from, long local_to)
    {
      return AbstractLong2ShortSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public LongBidirectionalIterator iterator(long from)
    {
      return new AbstractLong2ShortSortedMap.KeySetIterator(AbstractLong2ShortSortedMap.this.entrySet().iterator(new AbstractLong2ShortMap.BasicEntry(from, (short)0)));
    }
    
    public LongBidirectionalIterator iterator()
    {
      return new AbstractLong2ShortSortedMap.KeySetIterator(AbstractLong2ShortSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2ShortSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
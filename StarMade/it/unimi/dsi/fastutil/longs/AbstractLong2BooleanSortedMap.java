package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection;
import it.unimi.dsi.fastutil.booleans.AbstractBooleanIterator;
import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.booleans.BooleanIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractLong2BooleanSortedMap
  extends AbstractLong2BooleanMap
  implements Long2BooleanSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Long2BooleanSortedMap headMap(Long local_to)
  {
    return headMap(local_to.longValue());
  }
  
  public Long2BooleanSortedMap tailMap(Long from)
  {
    return tailMap(from.longValue());
  }
  
  public Long2BooleanSortedMap subMap(Long from, Long local_to)
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
  
  public BooleanCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Long, Boolean>> entrySet()
  {
    return long2BooleanEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractBooleanIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Long, Boolean>> field_60;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Long, Boolean>> local_i)
    {
      this.field_60 = local_i;
    }
    
    public boolean nextBoolean()
    {
      return ((Boolean)((Map.Entry)this.field_60.next()).getValue()).booleanValue();
    }
    
    public boolean hasNext()
    {
      return this.field_60.hasNext();
    }
  }
  
  protected class ValuesCollection
    extends AbstractBooleanCollection
  {
    protected ValuesCollection() {}
    
    public BooleanIterator iterator()
    {
      return new AbstractLong2BooleanSortedMap.ValuesIterator(AbstractLong2BooleanSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(boolean local_k)
    {
      return AbstractLong2BooleanSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractLong2BooleanSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractLong2BooleanSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractLongBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Long, Boolean>> field_1;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Long, Boolean>> local_i)
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
      return AbstractLong2BooleanSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractLong2BooleanSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractLong2BooleanSortedMap.this.clear();
    }
    
    public LongComparator comparator()
    {
      return AbstractLong2BooleanSortedMap.this.comparator();
    }
    
    public long firstLong()
    {
      return AbstractLong2BooleanSortedMap.this.firstLongKey();
    }
    
    public long lastLong()
    {
      return AbstractLong2BooleanSortedMap.this.lastLongKey();
    }
    
    public LongSortedSet headSet(long local_to)
    {
      return AbstractLong2BooleanSortedMap.this.headMap(local_to).keySet();
    }
    
    public LongSortedSet tailSet(long from)
    {
      return AbstractLong2BooleanSortedMap.this.tailMap(from).keySet();
    }
    
    public LongSortedSet subSet(long from, long local_to)
    {
      return AbstractLong2BooleanSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public LongBidirectionalIterator iterator(long from)
    {
      return new AbstractLong2BooleanSortedMap.KeySetIterator(AbstractLong2BooleanSortedMap.this.entrySet().iterator(new AbstractLong2BooleanMap.BasicEntry(from, false)));
    }
    
    public LongBidirectionalIterator iterator()
    {
      return new AbstractLong2BooleanSortedMap.KeySetIterator(AbstractLong2BooleanSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2BooleanSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
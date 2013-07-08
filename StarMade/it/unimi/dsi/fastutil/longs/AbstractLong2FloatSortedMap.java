package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.floats.AbstractFloatCollection;
import it.unimi.dsi.fastutil.floats.AbstractFloatIterator;
import it.unimi.dsi.fastutil.floats.FloatCollection;
import it.unimi.dsi.fastutil.floats.FloatIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractLong2FloatSortedMap
  extends AbstractLong2FloatMap
  implements Long2FloatSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Long2FloatSortedMap headMap(Long local_to)
  {
    return headMap(local_to.longValue());
  }
  
  public Long2FloatSortedMap tailMap(Long from)
  {
    return tailMap(from.longValue());
  }
  
  public Long2FloatSortedMap subMap(Long from, Long local_to)
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
  
  public FloatCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Long, Float>> entrySet()
  {
    return long2FloatEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractFloatIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Long, Float>> field_52;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Long, Float>> local_i)
    {
      this.field_52 = local_i;
    }
    
    public float nextFloat()
    {
      return ((Float)((Map.Entry)this.field_52.next()).getValue()).floatValue();
    }
    
    public boolean hasNext()
    {
      return this.field_52.hasNext();
    }
  }
  
  protected class ValuesCollection
    extends AbstractFloatCollection
  {
    protected ValuesCollection() {}
    
    public FloatIterator iterator()
    {
      return new AbstractLong2FloatSortedMap.ValuesIterator(AbstractLong2FloatSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(float local_k)
    {
      return AbstractLong2FloatSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractLong2FloatSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractLong2FloatSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractLongBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Long, Float>> field_1;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Long, Float>> local_i)
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
      return AbstractLong2FloatSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractLong2FloatSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractLong2FloatSortedMap.this.clear();
    }
    
    public LongComparator comparator()
    {
      return AbstractLong2FloatSortedMap.this.comparator();
    }
    
    public long firstLong()
    {
      return AbstractLong2FloatSortedMap.this.firstLongKey();
    }
    
    public long lastLong()
    {
      return AbstractLong2FloatSortedMap.this.lastLongKey();
    }
    
    public LongSortedSet headSet(long local_to)
    {
      return AbstractLong2FloatSortedMap.this.headMap(local_to).keySet();
    }
    
    public LongSortedSet tailSet(long from)
    {
      return AbstractLong2FloatSortedMap.this.tailMap(from).keySet();
    }
    
    public LongSortedSet subSet(long from, long local_to)
    {
      return AbstractLong2FloatSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public LongBidirectionalIterator iterator(long from)
    {
      return new AbstractLong2FloatSortedMap.KeySetIterator(AbstractLong2FloatSortedMap.this.entrySet().iterator(new AbstractLong2FloatMap.BasicEntry(from, 0.0F)));
    }
    
    public LongBidirectionalIterator iterator()
    {
      return new AbstractLong2FloatSortedMap.KeySetIterator(AbstractLong2FloatSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2FloatSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
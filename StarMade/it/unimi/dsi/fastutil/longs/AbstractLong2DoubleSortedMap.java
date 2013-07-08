package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection;
import it.unimi.dsi.fastutil.doubles.AbstractDoubleIterator;
import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.doubles.DoubleIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractLong2DoubleSortedMap
  extends AbstractLong2DoubleMap
  implements Long2DoubleSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Long2DoubleSortedMap headMap(Long local_to)
  {
    return headMap(local_to.longValue());
  }
  
  public Long2DoubleSortedMap tailMap(Long from)
  {
    return tailMap(from.longValue());
  }
  
  public Long2DoubleSortedMap subMap(Long from, Long local_to)
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
  
  public DoubleCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Long, Double>> entrySet()
  {
    return long2DoubleEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractDoubleIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Long, Double>> field_68;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Long, Double>> local_i)
    {
      this.field_68 = local_i;
    }
    
    public double nextDouble()
    {
      return ((Double)((Map.Entry)this.field_68.next()).getValue()).doubleValue();
    }
    
    public boolean hasNext()
    {
      return this.field_68.hasNext();
    }
  }
  
  protected class ValuesCollection
    extends AbstractDoubleCollection
  {
    protected ValuesCollection() {}
    
    public DoubleIterator iterator()
    {
      return new AbstractLong2DoubleSortedMap.ValuesIterator(AbstractLong2DoubleSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(double local_k)
    {
      return AbstractLong2DoubleSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractLong2DoubleSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractLong2DoubleSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractLongBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Long, Double>> field_1;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Long, Double>> local_i)
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
      return AbstractLong2DoubleSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractLong2DoubleSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractLong2DoubleSortedMap.this.clear();
    }
    
    public LongComparator comparator()
    {
      return AbstractLong2DoubleSortedMap.this.comparator();
    }
    
    public long firstLong()
    {
      return AbstractLong2DoubleSortedMap.this.firstLongKey();
    }
    
    public long lastLong()
    {
      return AbstractLong2DoubleSortedMap.this.lastLongKey();
    }
    
    public LongSortedSet headSet(long local_to)
    {
      return AbstractLong2DoubleSortedMap.this.headMap(local_to).keySet();
    }
    
    public LongSortedSet tailSet(long from)
    {
      return AbstractLong2DoubleSortedMap.this.tailMap(from).keySet();
    }
    
    public LongSortedSet subSet(long from, long local_to)
    {
      return AbstractLong2DoubleSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public LongBidirectionalIterator iterator(long from)
    {
      return new AbstractLong2DoubleSortedMap.KeySetIterator(AbstractLong2DoubleSortedMap.this.entrySet().iterator(new AbstractLong2DoubleMap.BasicEntry(from, 0.0D)));
    }
    
    public LongBidirectionalIterator iterator()
    {
      return new AbstractLong2DoubleSortedMap.KeySetIterator(AbstractLong2DoubleSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2DoubleSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
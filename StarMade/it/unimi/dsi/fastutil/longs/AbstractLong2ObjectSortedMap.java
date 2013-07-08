package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.objects.AbstractObjectCollection;
import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractLong2ObjectSortedMap<V>
  extends AbstractLong2ObjectMap<V>
  implements Long2ObjectSortedMap<V>
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Long2ObjectSortedMap<V> headMap(Long local_to)
  {
    return headMap(local_to.longValue());
  }
  
  public Long2ObjectSortedMap<V> tailMap(Long from)
  {
    return tailMap(from.longValue());
  }
  
  public Long2ObjectSortedMap<V> subMap(Long from, Long local_to)
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
  
  public ObjectCollection<V> values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Long, V>> entrySet()
  {
    return long2ObjectEntrySet();
  }
  
  protected static class ValuesIterator<V>
    extends AbstractObjectIterator<V>
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Long, V>> field_3;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Long, V>> local_i)
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
      return new AbstractLong2ObjectSortedMap.ValuesIterator(AbstractLong2ObjectSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(Object local_k)
    {
      return AbstractLong2ObjectSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractLong2ObjectSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractLong2ObjectSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator<V>
    extends AbstractLongBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Long, V>> field_1;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Long, V>> local_i)
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
      return AbstractLong2ObjectSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractLong2ObjectSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractLong2ObjectSortedMap.this.clear();
    }
    
    public LongComparator comparator()
    {
      return AbstractLong2ObjectSortedMap.this.comparator();
    }
    
    public long firstLong()
    {
      return AbstractLong2ObjectSortedMap.this.firstLongKey();
    }
    
    public long lastLong()
    {
      return AbstractLong2ObjectSortedMap.this.lastLongKey();
    }
    
    public LongSortedSet headSet(long local_to)
    {
      return AbstractLong2ObjectSortedMap.this.headMap(local_to).keySet();
    }
    
    public LongSortedSet tailSet(long from)
    {
      return AbstractLong2ObjectSortedMap.this.tailMap(from).keySet();
    }
    
    public LongSortedSet subSet(long from, long local_to)
    {
      return AbstractLong2ObjectSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public LongBidirectionalIterator iterator(long from)
    {
      return new AbstractLong2ObjectSortedMap.KeySetIterator(AbstractLong2ObjectSortedMap.this.entrySet().iterator(new AbstractLong2ObjectMap.BasicEntry(from, null)));
    }
    
    public LongBidirectionalIterator iterator()
    {
      return new AbstractLong2ObjectSortedMap.KeySetIterator(AbstractLong2ObjectSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2ObjectSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.longs.AbstractLongCollection;
import it.unimi.dsi.fastutil.longs.AbstractLongIterator;
import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.longs.LongIterator;
import java.util.Comparator;
import java.util.Map.Entry;

public abstract class AbstractReference2LongSortedMap<K>
  extends AbstractReference2LongMap<K>
  implements Reference2LongSortedMap<K>
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public ReferenceSortedSet<K> keySet()
  {
    return new KeySet();
  }
  
  public LongCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<K, Long>> entrySet()
  {
    return reference2LongEntrySet();
  }
  
  protected static class ValuesIterator<K>
    extends AbstractLongIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<K, Long>> field_1;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<K, Long>> local_i)
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
      return new AbstractReference2LongSortedMap.ValuesIterator(AbstractReference2LongSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(long local_k)
    {
      return AbstractReference2LongSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractReference2LongSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractReference2LongSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator<K>
    extends AbstractObjectBidirectionalIterator<K>
  {
    protected final ObjectBidirectionalIterator<Map.Entry<K, Long>> field_3;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<K, Long>> local_i)
    {
      this.field_3 = local_i;
    }
    
    public K next()
    {
      return ((Map.Entry)this.field_3.next()).getKey();
    }
    
    public K previous()
    {
      return ((Map.Entry)this.field_3.previous()).getKey();
    }
    
    public boolean hasNext()
    {
      return this.field_3.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_3.hasPrevious();
    }
  }
  
  protected class KeySet
    extends AbstractReferenceSortedSet<K>
  {
    protected KeySet() {}
    
    public boolean contains(Object local_k)
    {
      return AbstractReference2LongSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractReference2LongSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractReference2LongSortedMap.this.clear();
    }
    
    public Comparator<? super K> comparator()
    {
      return AbstractReference2LongSortedMap.this.comparator();
    }
    
    public K first()
    {
      return AbstractReference2LongSortedMap.this.firstKey();
    }
    
    public K last()
    {
      return AbstractReference2LongSortedMap.this.lastKey();
    }
    
    public ReferenceSortedSet<K> headSet(K local_to)
    {
      return AbstractReference2LongSortedMap.this.headMap(local_to).keySet();
    }
    
    public ReferenceSortedSet<K> tailSet(K from)
    {
      return AbstractReference2LongSortedMap.this.tailMap(from).keySet();
    }
    
    public ReferenceSortedSet<K> subSet(K from, K local_to)
    {
      return AbstractReference2LongSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public ObjectBidirectionalIterator<K> iterator(K from)
    {
      return new AbstractReference2LongSortedMap.KeySetIterator(AbstractReference2LongSortedMap.this.entrySet().iterator(new AbstractReference2LongMap.BasicEntry(from, 0L)));
    }
    
    public ObjectBidirectionalIterator<K> iterator()
    {
      return new AbstractReference2LongSortedMap.KeySetIterator(AbstractReference2LongSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2LongSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
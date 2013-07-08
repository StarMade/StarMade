package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.ints.AbstractIntCollection;
import it.unimi.dsi.fastutil.ints.AbstractIntIterator;
import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.ints.IntIterator;
import java.util.Comparator;
import java.util.Map.Entry;

public abstract class AbstractReference2IntSortedMap<K>
  extends AbstractReference2IntMap<K>
  implements Reference2IntSortedMap<K>
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public ReferenceSortedSet<K> keySet()
  {
    return new KeySet();
  }
  
  public IntCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<K, Integer>> entrySet()
  {
    return reference2IntEntrySet();
  }
  
  protected static class ValuesIterator<K>
    extends AbstractIntIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<K, Integer>> field_70;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<K, Integer>> local_i)
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
      return new AbstractReference2IntSortedMap.ValuesIterator(AbstractReference2IntSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(int local_k)
    {
      return AbstractReference2IntSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractReference2IntSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractReference2IntSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator<K>
    extends AbstractObjectBidirectionalIterator<K>
  {
    protected final ObjectBidirectionalIterator<Map.Entry<K, Integer>> field_3;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<K, Integer>> local_i)
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
      return AbstractReference2IntSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractReference2IntSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractReference2IntSortedMap.this.clear();
    }
    
    public Comparator<? super K> comparator()
    {
      return AbstractReference2IntSortedMap.this.comparator();
    }
    
    public K first()
    {
      return AbstractReference2IntSortedMap.this.firstKey();
    }
    
    public K last()
    {
      return AbstractReference2IntSortedMap.this.lastKey();
    }
    
    public ReferenceSortedSet<K> headSet(K local_to)
    {
      return AbstractReference2IntSortedMap.this.headMap(local_to).keySet();
    }
    
    public ReferenceSortedSet<K> tailSet(K from)
    {
      return AbstractReference2IntSortedMap.this.tailMap(from).keySet();
    }
    
    public ReferenceSortedSet<K> subSet(K from, K local_to)
    {
      return AbstractReference2IntSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public ObjectBidirectionalIterator<K> iterator(K from)
    {
      return new AbstractReference2IntSortedMap.KeySetIterator(AbstractReference2IntSortedMap.this.entrySet().iterator(new AbstractReference2IntMap.BasicEntry(from, 0)));
    }
    
    public ObjectBidirectionalIterator<K> iterator()
    {
      return new AbstractReference2IntSortedMap.KeySetIterator(AbstractReference2IntSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2IntSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
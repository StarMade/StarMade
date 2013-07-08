package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
import it.unimi.dsi.fastutil.objects.AbstractReferenceCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import java.util.Map.Entry;

public abstract class AbstractInt2ReferenceSortedMap<V>
  extends AbstractInt2ReferenceMap<V>
  implements Int2ReferenceSortedMap<V>
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Int2ReferenceSortedMap<V> headMap(Integer local_to)
  {
    return headMap(local_to.intValue());
  }
  
  public Int2ReferenceSortedMap<V> tailMap(Integer from)
  {
    return tailMap(from.intValue());
  }
  
  public Int2ReferenceSortedMap<V> subMap(Integer from, Integer local_to)
  {
    return subMap(from.intValue(), local_to.intValue());
  }
  
  public Integer firstKey()
  {
    return Integer.valueOf(firstIntKey());
  }
  
  public Integer lastKey()
  {
    return Integer.valueOf(lastIntKey());
  }
  
  public IntSortedSet keySet()
  {
    return new KeySet();
  }
  
  public ReferenceCollection<V> values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Integer, V>> entrySet()
  {
    return int2ReferenceEntrySet();
  }
  
  protected static class ValuesIterator<V>
    extends AbstractObjectIterator<V>
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Integer, V>> field_3;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Integer, V>> local_i)
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
    extends AbstractReferenceCollection<V>
  {
    protected ValuesCollection() {}
    
    public ObjectIterator<V> iterator()
    {
      return new AbstractInt2ReferenceSortedMap.ValuesIterator(AbstractInt2ReferenceSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(Object local_k)
    {
      return AbstractInt2ReferenceSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractInt2ReferenceSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractInt2ReferenceSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator<V>
    extends AbstractIntBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Integer, V>> field_70;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Integer, V>> local_i)
    {
      this.field_70 = local_i;
    }
    
    public int nextInt()
    {
      return ((Integer)((Map.Entry)this.field_70.next()).getKey()).intValue();
    }
    
    public int previousInt()
    {
      return ((Integer)((Map.Entry)this.field_70.previous()).getKey()).intValue();
    }
    
    public boolean hasNext()
    {
      return this.field_70.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_70.hasPrevious();
    }
  }
  
  protected class KeySet
    extends AbstractIntSortedSet
  {
    protected KeySet() {}
    
    public boolean contains(int local_k)
    {
      return AbstractInt2ReferenceSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractInt2ReferenceSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractInt2ReferenceSortedMap.this.clear();
    }
    
    public IntComparator comparator()
    {
      return AbstractInt2ReferenceSortedMap.this.comparator();
    }
    
    public int firstInt()
    {
      return AbstractInt2ReferenceSortedMap.this.firstIntKey();
    }
    
    public int lastInt()
    {
      return AbstractInt2ReferenceSortedMap.this.lastIntKey();
    }
    
    public IntSortedSet headSet(int local_to)
    {
      return AbstractInt2ReferenceSortedMap.this.headMap(local_to).keySet();
    }
    
    public IntSortedSet tailSet(int from)
    {
      return AbstractInt2ReferenceSortedMap.this.tailMap(from).keySet();
    }
    
    public IntSortedSet subSet(int from, int local_to)
    {
      return AbstractInt2ReferenceSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public IntBidirectionalIterator iterator(int from)
    {
      return new AbstractInt2ReferenceSortedMap.KeySetIterator(AbstractInt2ReferenceSortedMap.this.entrySet().iterator(new AbstractInt2ReferenceMap.BasicEntry(from, null)));
    }
    
    public IntBidirectionalIterator iterator()
    {
      return new AbstractInt2ReferenceSortedMap.KeySetIterator(AbstractInt2ReferenceSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2ReferenceSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
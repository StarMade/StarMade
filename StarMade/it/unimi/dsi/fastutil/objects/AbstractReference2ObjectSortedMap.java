package it.unimi.dsi.fastutil.objects;

import java.util.Comparator;
import java.util.Map.Entry;

public abstract class AbstractReference2ObjectSortedMap<K, V>
  extends AbstractReference2ObjectMap<K, V>
  implements Reference2ObjectSortedMap<K, V>
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public ReferenceSortedSet<K> keySet()
  {
    return new KeySet();
  }
  
  public ObjectCollection<V> values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<K, V>> entrySet()
  {
    return reference2ObjectEntrySet();
  }
  
  protected static class ValuesIterator<K, V>
    extends AbstractObjectIterator<V>
  {
    protected final ObjectBidirectionalIterator<Map.Entry<K, V>> field_3;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<K, V>> local_i)
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
      return new AbstractReference2ObjectSortedMap.ValuesIterator(AbstractReference2ObjectSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(Object local_k)
    {
      return AbstractReference2ObjectSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractReference2ObjectSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractReference2ObjectSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator<K, V>
    extends AbstractObjectBidirectionalIterator<K>
  {
    protected final ObjectBidirectionalIterator<Map.Entry<K, V>> field_3;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<K, V>> local_i)
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
      return AbstractReference2ObjectSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractReference2ObjectSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractReference2ObjectSortedMap.this.clear();
    }
    
    public Comparator<? super K> comparator()
    {
      return AbstractReference2ObjectSortedMap.this.comparator();
    }
    
    public K first()
    {
      return AbstractReference2ObjectSortedMap.this.firstKey();
    }
    
    public K last()
    {
      return AbstractReference2ObjectSortedMap.this.lastKey();
    }
    
    public ReferenceSortedSet<K> headSet(K local_to)
    {
      return AbstractReference2ObjectSortedMap.this.headMap(local_to).keySet();
    }
    
    public ReferenceSortedSet<K> tailSet(K from)
    {
      return AbstractReference2ObjectSortedMap.this.tailMap(from).keySet();
    }
    
    public ReferenceSortedSet<K> subSet(K from, K local_to)
    {
      return AbstractReference2ObjectSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public ObjectBidirectionalIterator<K> iterator(K from)
    {
      return new AbstractReference2ObjectSortedMap.KeySetIterator(AbstractReference2ObjectSortedMap.this.entrySet().iterator(new AbstractReference2ObjectMap.BasicEntry(from, null)));
    }
    
    public ObjectBidirectionalIterator<K> iterator()
    {
      return new AbstractReference2ObjectSortedMap.KeySetIterator(AbstractReference2ObjectSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2ObjectSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
package it.unimi.dsi.fastutil.objects;

import java.util.Comparator;
import java.util.Map.Entry;

public abstract class AbstractObject2ObjectSortedMap<K, V>
  extends AbstractObject2ObjectMap<K, V>
  implements Object2ObjectSortedMap<K, V>
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public ObjectSortedSet<K> keySet()
  {
    return new KeySet();
  }
  
  public ObjectCollection<V> values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<K, V>> entrySet()
  {
    return object2ObjectEntrySet();
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
      return new AbstractObject2ObjectSortedMap.ValuesIterator(AbstractObject2ObjectSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(Object local_k)
    {
      return AbstractObject2ObjectSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractObject2ObjectSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractObject2ObjectSortedMap.this.clear();
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
    extends AbstractObjectSortedSet<K>
  {
    protected KeySet() {}
    
    public boolean contains(Object local_k)
    {
      return AbstractObject2ObjectSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractObject2ObjectSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractObject2ObjectSortedMap.this.clear();
    }
    
    public Comparator<? super K> comparator()
    {
      return AbstractObject2ObjectSortedMap.this.comparator();
    }
    
    public K first()
    {
      return AbstractObject2ObjectSortedMap.this.firstKey();
    }
    
    public K last()
    {
      return AbstractObject2ObjectSortedMap.this.lastKey();
    }
    
    public ObjectSortedSet<K> headSet(K local_to)
    {
      return AbstractObject2ObjectSortedMap.this.headMap(local_to).keySet();
    }
    
    public ObjectSortedSet<K> tailSet(K from)
    {
      return AbstractObject2ObjectSortedMap.this.tailMap(from).keySet();
    }
    
    public ObjectSortedSet<K> subSet(K from, K local_to)
    {
      return AbstractObject2ObjectSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public ObjectBidirectionalIterator<K> iterator(K from)
    {
      return new AbstractObject2ObjectSortedMap.KeySetIterator(AbstractObject2ObjectSortedMap.this.entrySet().iterator(new AbstractObject2ObjectMap.BasicEntry(from, null)));
    }
    
    public ObjectBidirectionalIterator<K> iterator()
    {
      return new AbstractObject2ObjectSortedMap.KeySetIterator(AbstractObject2ObjectSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObject2ObjectSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection;
import it.unimi.dsi.fastutil.doubles.AbstractDoubleIterator;
import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.doubles.DoubleIterator;
import java.util.Comparator;
import java.util.Map.Entry;

public abstract class AbstractObject2DoubleSortedMap<K>
  extends AbstractObject2DoubleMap<K>
  implements Object2DoubleSortedMap<K>
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public ObjectSortedSet<K> keySet()
  {
    return new KeySet();
  }
  
  public DoubleCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<K, Double>> entrySet()
  {
    return object2DoubleEntrySet();
  }
  
  protected static class ValuesIterator<K>
    extends AbstractDoubleIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<K, Double>> field_68;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<K, Double>> local_i)
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
      return new AbstractObject2DoubleSortedMap.ValuesIterator(AbstractObject2DoubleSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(double local_k)
    {
      return AbstractObject2DoubleSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractObject2DoubleSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractObject2DoubleSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator<K>
    extends AbstractObjectBidirectionalIterator<K>
  {
    protected final ObjectBidirectionalIterator<Map.Entry<K, Double>> field_3;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<K, Double>> local_i)
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
      return AbstractObject2DoubleSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractObject2DoubleSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractObject2DoubleSortedMap.this.clear();
    }
    
    public Comparator<? super K> comparator()
    {
      return AbstractObject2DoubleSortedMap.this.comparator();
    }
    
    public K first()
    {
      return AbstractObject2DoubleSortedMap.this.firstKey();
    }
    
    public K last()
    {
      return AbstractObject2DoubleSortedMap.this.lastKey();
    }
    
    public ObjectSortedSet<K> headSet(K local_to)
    {
      return AbstractObject2DoubleSortedMap.this.headMap(local_to).keySet();
    }
    
    public ObjectSortedSet<K> tailSet(K from)
    {
      return AbstractObject2DoubleSortedMap.this.tailMap(from).keySet();
    }
    
    public ObjectSortedSet<K> subSet(K from, K local_to)
    {
      return AbstractObject2DoubleSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public ObjectBidirectionalIterator<K> iterator(K from)
    {
      return new AbstractObject2DoubleSortedMap.KeySetIterator(AbstractObject2DoubleSortedMap.this.entrySet().iterator(new AbstractObject2DoubleMap.BasicEntry(from, 0.0D)));
    }
    
    public ObjectBidirectionalIterator<K> iterator()
    {
      return new AbstractObject2DoubleSortedMap.KeySetIterator(AbstractObject2DoubleSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObject2DoubleSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.objects.AbstractObjectCollection;
import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractDouble2ObjectSortedMap<V>
  extends AbstractDouble2ObjectMap<V>
  implements Double2ObjectSortedMap<V>
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Double2ObjectSortedMap<V> headMap(Double local_to)
  {
    return headMap(local_to.doubleValue());
  }
  
  public Double2ObjectSortedMap<V> tailMap(Double from)
  {
    return tailMap(from.doubleValue());
  }
  
  public Double2ObjectSortedMap<V> subMap(Double from, Double local_to)
  {
    return subMap(from.doubleValue(), local_to.doubleValue());
  }
  
  public Double firstKey()
  {
    return Double.valueOf(firstDoubleKey());
  }
  
  public Double lastKey()
  {
    return Double.valueOf(lastDoubleKey());
  }
  
  public DoubleSortedSet keySet()
  {
    return new KeySet();
  }
  
  public ObjectCollection<V> values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Double, V>> entrySet()
  {
    return double2ObjectEntrySet();
  }
  
  protected static class ValuesIterator<V>
    extends AbstractObjectIterator<V>
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Double, V>> field_3;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Double, V>> local_i)
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
      return new AbstractDouble2ObjectSortedMap.ValuesIterator(AbstractDouble2ObjectSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(Object local_k)
    {
      return AbstractDouble2ObjectSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractDouble2ObjectSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractDouble2ObjectSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator<V>
    extends AbstractDoubleBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Double, V>> field_68;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Double, V>> local_i)
    {
      this.field_68 = local_i;
    }
    
    public double nextDouble()
    {
      return ((Double)((Map.Entry)this.field_68.next()).getKey()).doubleValue();
    }
    
    public double previousDouble()
    {
      return ((Double)((Map.Entry)this.field_68.previous()).getKey()).doubleValue();
    }
    
    public boolean hasNext()
    {
      return this.field_68.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_68.hasPrevious();
    }
  }
  
  protected class KeySet
    extends AbstractDoubleSortedSet
  {
    protected KeySet() {}
    
    public boolean contains(double local_k)
    {
      return AbstractDouble2ObjectSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractDouble2ObjectSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractDouble2ObjectSortedMap.this.clear();
    }
    
    public DoubleComparator comparator()
    {
      return AbstractDouble2ObjectSortedMap.this.comparator();
    }
    
    public double firstDouble()
    {
      return AbstractDouble2ObjectSortedMap.this.firstDoubleKey();
    }
    
    public double lastDouble()
    {
      return AbstractDouble2ObjectSortedMap.this.lastDoubleKey();
    }
    
    public DoubleSortedSet headSet(double local_to)
    {
      return AbstractDouble2ObjectSortedMap.this.headMap(local_to).keySet();
    }
    
    public DoubleSortedSet tailSet(double from)
    {
      return AbstractDouble2ObjectSortedMap.this.tailMap(from).keySet();
    }
    
    public DoubleSortedSet subSet(double from, double local_to)
    {
      return AbstractDouble2ObjectSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public DoubleBidirectionalIterator iterator(double from)
    {
      return new AbstractDouble2ObjectSortedMap.KeySetIterator(AbstractDouble2ObjectSortedMap.this.entrySet().iterator(new AbstractDouble2ObjectMap.BasicEntry(from, null)));
    }
    
    public DoubleBidirectionalIterator iterator()
    {
      return new AbstractDouble2ObjectSortedMap.KeySetIterator(AbstractDouble2ObjectSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2ObjectSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
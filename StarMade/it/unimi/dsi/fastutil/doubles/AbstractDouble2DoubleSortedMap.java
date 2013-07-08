package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractDouble2DoubleSortedMap
  extends AbstractDouble2DoubleMap
  implements Double2DoubleSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Double2DoubleSortedMap headMap(Double local_to)
  {
    return headMap(local_to.doubleValue());
  }
  
  public Double2DoubleSortedMap tailMap(Double from)
  {
    return tailMap(from.doubleValue());
  }
  
  public Double2DoubleSortedMap subMap(Double from, Double local_to)
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
  
  public DoubleCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Double, Double>> entrySet()
  {
    return double2DoubleEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractDoubleIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Double, Double>> field_68;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Double, Double>> local_i)
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
      return new AbstractDouble2DoubleSortedMap.ValuesIterator(AbstractDouble2DoubleSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(double local_k)
    {
      return AbstractDouble2DoubleSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractDouble2DoubleSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractDouble2DoubleSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractDoubleBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Double, Double>> field_68;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Double, Double>> local_i)
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
      return AbstractDouble2DoubleSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractDouble2DoubleSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractDouble2DoubleSortedMap.this.clear();
    }
    
    public DoubleComparator comparator()
    {
      return AbstractDouble2DoubleSortedMap.this.comparator();
    }
    
    public double firstDouble()
    {
      return AbstractDouble2DoubleSortedMap.this.firstDoubleKey();
    }
    
    public double lastDouble()
    {
      return AbstractDouble2DoubleSortedMap.this.lastDoubleKey();
    }
    
    public DoubleSortedSet headSet(double local_to)
    {
      return AbstractDouble2DoubleSortedMap.this.headMap(local_to).keySet();
    }
    
    public DoubleSortedSet tailSet(double from)
    {
      return AbstractDouble2DoubleSortedMap.this.tailMap(from).keySet();
    }
    
    public DoubleSortedSet subSet(double from, double local_to)
    {
      return AbstractDouble2DoubleSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public DoubleBidirectionalIterator iterator(double from)
    {
      return new AbstractDouble2DoubleSortedMap.KeySetIterator(AbstractDouble2DoubleSortedMap.this.entrySet().iterator(new AbstractDouble2DoubleMap.BasicEntry(from, 0.0D)));
    }
    
    public DoubleBidirectionalIterator iterator()
    {
      return new AbstractDouble2DoubleSortedMap.KeySetIterator(AbstractDouble2DoubleSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2DoubleSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
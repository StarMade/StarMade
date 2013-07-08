package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.shorts.AbstractShortCollection;
import it.unimi.dsi.fastutil.shorts.AbstractShortIterator;
import it.unimi.dsi.fastutil.shorts.ShortCollection;
import it.unimi.dsi.fastutil.shorts.ShortIterator;
import java.util.Map.Entry;

public abstract class AbstractDouble2ShortSortedMap
  extends AbstractDouble2ShortMap
  implements Double2ShortSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Double2ShortSortedMap headMap(Double local_to)
  {
    return headMap(local_to.doubleValue());
  }
  
  public Double2ShortSortedMap tailMap(Double from)
  {
    return tailMap(from.doubleValue());
  }
  
  public Double2ShortSortedMap subMap(Double from, Double local_to)
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
  
  public ShortCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Double, Short>> entrySet()
  {
    return double2ShortEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractShortIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Double, Short>> field_53;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Double, Short>> local_i)
    {
      this.field_53 = local_i;
    }
    
    public short nextShort()
    {
      return ((Short)((Map.Entry)this.field_53.next()).getValue()).shortValue();
    }
    
    public boolean hasNext()
    {
      return this.field_53.hasNext();
    }
  }
  
  protected class ValuesCollection
    extends AbstractShortCollection
  {
    protected ValuesCollection() {}
    
    public ShortIterator iterator()
    {
      return new AbstractDouble2ShortSortedMap.ValuesIterator(AbstractDouble2ShortSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(short local_k)
    {
      return AbstractDouble2ShortSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractDouble2ShortSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractDouble2ShortSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractDoubleBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Double, Short>> field_68;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Double, Short>> local_i)
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
      return AbstractDouble2ShortSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractDouble2ShortSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractDouble2ShortSortedMap.this.clear();
    }
    
    public DoubleComparator comparator()
    {
      return AbstractDouble2ShortSortedMap.this.comparator();
    }
    
    public double firstDouble()
    {
      return AbstractDouble2ShortSortedMap.this.firstDoubleKey();
    }
    
    public double lastDouble()
    {
      return AbstractDouble2ShortSortedMap.this.lastDoubleKey();
    }
    
    public DoubleSortedSet headSet(double local_to)
    {
      return AbstractDouble2ShortSortedMap.this.headMap(local_to).keySet();
    }
    
    public DoubleSortedSet tailSet(double from)
    {
      return AbstractDouble2ShortSortedMap.this.tailMap(from).keySet();
    }
    
    public DoubleSortedSet subSet(double from, double local_to)
    {
      return AbstractDouble2ShortSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public DoubleBidirectionalIterator iterator(double from)
    {
      return new AbstractDouble2ShortSortedMap.KeySetIterator(AbstractDouble2ShortSortedMap.this.entrySet().iterator(new AbstractDouble2ShortMap.BasicEntry(from, (short)0)));
    }
    
    public DoubleBidirectionalIterator iterator()
    {
      return new AbstractDouble2ShortSortedMap.KeySetIterator(AbstractDouble2ShortSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2ShortSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
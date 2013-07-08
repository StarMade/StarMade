package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection;
import it.unimi.dsi.fastutil.booleans.AbstractBooleanIterator;
import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.booleans.BooleanIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractDouble2BooleanSortedMap
  extends AbstractDouble2BooleanMap
  implements Double2BooleanSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Double2BooleanSortedMap headMap(Double local_to)
  {
    return headMap(local_to.doubleValue());
  }
  
  public Double2BooleanSortedMap tailMap(Double from)
  {
    return tailMap(from.doubleValue());
  }
  
  public Double2BooleanSortedMap subMap(Double from, Double local_to)
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
  
  public BooleanCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Double, Boolean>> entrySet()
  {
    return double2BooleanEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractBooleanIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Double, Boolean>> field_60;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Double, Boolean>> local_i)
    {
      this.field_60 = local_i;
    }
    
    public boolean nextBoolean()
    {
      return ((Boolean)((Map.Entry)this.field_60.next()).getValue()).booleanValue();
    }
    
    public boolean hasNext()
    {
      return this.field_60.hasNext();
    }
  }
  
  protected class ValuesCollection
    extends AbstractBooleanCollection
  {
    protected ValuesCollection() {}
    
    public BooleanIterator iterator()
    {
      return new AbstractDouble2BooleanSortedMap.ValuesIterator(AbstractDouble2BooleanSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(boolean local_k)
    {
      return AbstractDouble2BooleanSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractDouble2BooleanSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractDouble2BooleanSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractDoubleBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Double, Boolean>> field_68;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Double, Boolean>> local_i)
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
      return AbstractDouble2BooleanSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractDouble2BooleanSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractDouble2BooleanSortedMap.this.clear();
    }
    
    public DoubleComparator comparator()
    {
      return AbstractDouble2BooleanSortedMap.this.comparator();
    }
    
    public double firstDouble()
    {
      return AbstractDouble2BooleanSortedMap.this.firstDoubleKey();
    }
    
    public double lastDouble()
    {
      return AbstractDouble2BooleanSortedMap.this.lastDoubleKey();
    }
    
    public DoubleSortedSet headSet(double local_to)
    {
      return AbstractDouble2BooleanSortedMap.this.headMap(local_to).keySet();
    }
    
    public DoubleSortedSet tailSet(double from)
    {
      return AbstractDouble2BooleanSortedMap.this.tailMap(from).keySet();
    }
    
    public DoubleSortedSet subSet(double from, double local_to)
    {
      return AbstractDouble2BooleanSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public DoubleBidirectionalIterator iterator(double from)
    {
      return new AbstractDouble2BooleanSortedMap.KeySetIterator(AbstractDouble2BooleanSortedMap.this.entrySet().iterator(new AbstractDouble2BooleanMap.BasicEntry(from, false)));
    }
    
    public DoubleBidirectionalIterator iterator()
    {
      return new AbstractDouble2BooleanSortedMap.KeySetIterator(AbstractDouble2BooleanSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2BooleanSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.floats.AbstractFloatCollection;
import it.unimi.dsi.fastutil.floats.AbstractFloatIterator;
import it.unimi.dsi.fastutil.floats.FloatCollection;
import it.unimi.dsi.fastutil.floats.FloatIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractDouble2FloatSortedMap
  extends AbstractDouble2FloatMap
  implements Double2FloatSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Double2FloatSortedMap headMap(Double local_to)
  {
    return headMap(local_to.doubleValue());
  }
  
  public Double2FloatSortedMap tailMap(Double from)
  {
    return tailMap(from.doubleValue());
  }
  
  public Double2FloatSortedMap subMap(Double from, Double local_to)
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
  
  public FloatCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Double, Float>> entrySet()
  {
    return double2FloatEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractFloatIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Double, Float>> field_52;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Double, Float>> local_i)
    {
      this.field_52 = local_i;
    }
    
    public float nextFloat()
    {
      return ((Float)((Map.Entry)this.field_52.next()).getValue()).floatValue();
    }
    
    public boolean hasNext()
    {
      return this.field_52.hasNext();
    }
  }
  
  protected class ValuesCollection
    extends AbstractFloatCollection
  {
    protected ValuesCollection() {}
    
    public FloatIterator iterator()
    {
      return new AbstractDouble2FloatSortedMap.ValuesIterator(AbstractDouble2FloatSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(float local_k)
    {
      return AbstractDouble2FloatSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractDouble2FloatSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractDouble2FloatSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractDoubleBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Double, Float>> field_68;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Double, Float>> local_i)
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
      return AbstractDouble2FloatSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractDouble2FloatSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractDouble2FloatSortedMap.this.clear();
    }
    
    public DoubleComparator comparator()
    {
      return AbstractDouble2FloatSortedMap.this.comparator();
    }
    
    public double firstDouble()
    {
      return AbstractDouble2FloatSortedMap.this.firstDoubleKey();
    }
    
    public double lastDouble()
    {
      return AbstractDouble2FloatSortedMap.this.lastDoubleKey();
    }
    
    public DoubleSortedSet headSet(double local_to)
    {
      return AbstractDouble2FloatSortedMap.this.headMap(local_to).keySet();
    }
    
    public DoubleSortedSet tailSet(double from)
    {
      return AbstractDouble2FloatSortedMap.this.tailMap(from).keySet();
    }
    
    public DoubleSortedSet subSet(double from, double local_to)
    {
      return AbstractDouble2FloatSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public DoubleBidirectionalIterator iterator(double from)
    {
      return new AbstractDouble2FloatSortedMap.KeySetIterator(AbstractDouble2FloatSortedMap.this.entrySet().iterator(new AbstractDouble2FloatMap.BasicEntry(from, 0.0F)));
    }
    
    public DoubleBidirectionalIterator iterator()
    {
      return new AbstractDouble2FloatSortedMap.KeySetIterator(AbstractDouble2FloatSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2FloatSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
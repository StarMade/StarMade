package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.longs.AbstractLongCollection;
import it.unimi.dsi.fastutil.longs.AbstractLongIterator;
import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractDouble2LongSortedMap
  extends AbstractDouble2LongMap
  implements Double2LongSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Double2LongSortedMap headMap(Double local_to)
  {
    return headMap(local_to.doubleValue());
  }
  
  public Double2LongSortedMap tailMap(Double from)
  {
    return tailMap(from.doubleValue());
  }
  
  public Double2LongSortedMap subMap(Double from, Double local_to)
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
  
  public LongCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Double, Long>> entrySet()
  {
    return double2LongEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractLongIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Double, Long>> field_1;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Double, Long>> local_i)
    {
      this.field_1 = local_i;
    }
    
    public long nextLong()
    {
      return ((Long)((Map.Entry)this.field_1.next()).getValue()).longValue();
    }
    
    public boolean hasNext()
    {
      return this.field_1.hasNext();
    }
  }
  
  protected class ValuesCollection
    extends AbstractLongCollection
  {
    protected ValuesCollection() {}
    
    public LongIterator iterator()
    {
      return new AbstractDouble2LongSortedMap.ValuesIterator(AbstractDouble2LongSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(long local_k)
    {
      return AbstractDouble2LongSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractDouble2LongSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractDouble2LongSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractDoubleBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Double, Long>> field_68;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Double, Long>> local_i)
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
      return AbstractDouble2LongSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractDouble2LongSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractDouble2LongSortedMap.this.clear();
    }
    
    public DoubleComparator comparator()
    {
      return AbstractDouble2LongSortedMap.this.comparator();
    }
    
    public double firstDouble()
    {
      return AbstractDouble2LongSortedMap.this.firstDoubleKey();
    }
    
    public double lastDouble()
    {
      return AbstractDouble2LongSortedMap.this.lastDoubleKey();
    }
    
    public DoubleSortedSet headSet(double local_to)
    {
      return AbstractDouble2LongSortedMap.this.headMap(local_to).keySet();
    }
    
    public DoubleSortedSet tailSet(double from)
    {
      return AbstractDouble2LongSortedMap.this.tailMap(from).keySet();
    }
    
    public DoubleSortedSet subSet(double from, double local_to)
    {
      return AbstractDouble2LongSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public DoubleBidirectionalIterator iterator(double from)
    {
      return new AbstractDouble2LongSortedMap.KeySetIterator(AbstractDouble2LongSortedMap.this.entrySet().iterator(new AbstractDouble2LongMap.BasicEntry(from, 0L)));
    }
    
    public DoubleBidirectionalIterator iterator()
    {
      return new AbstractDouble2LongSortedMap.KeySetIterator(AbstractDouble2LongSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2LongSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
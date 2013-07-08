package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection;
import it.unimi.dsi.fastutil.doubles.AbstractDoubleIterator;
import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.doubles.DoubleIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractInt2DoubleSortedMap
  extends AbstractInt2DoubleMap
  implements Int2DoubleSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Int2DoubleSortedMap headMap(Integer local_to)
  {
    return headMap(local_to.intValue());
  }
  
  public Int2DoubleSortedMap tailMap(Integer from)
  {
    return tailMap(from.intValue());
  }
  
  public Int2DoubleSortedMap subMap(Integer from, Integer local_to)
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
  
  public DoubleCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Integer, Double>> entrySet()
  {
    return int2DoubleEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractDoubleIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Integer, Double>> field_68;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Integer, Double>> local_i)
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
      return new AbstractInt2DoubleSortedMap.ValuesIterator(AbstractInt2DoubleSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(double local_k)
    {
      return AbstractInt2DoubleSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractInt2DoubleSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractInt2DoubleSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractIntBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Integer, Double>> field_70;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Integer, Double>> local_i)
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
      return AbstractInt2DoubleSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractInt2DoubleSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractInt2DoubleSortedMap.this.clear();
    }
    
    public IntComparator comparator()
    {
      return AbstractInt2DoubleSortedMap.this.comparator();
    }
    
    public int firstInt()
    {
      return AbstractInt2DoubleSortedMap.this.firstIntKey();
    }
    
    public int lastInt()
    {
      return AbstractInt2DoubleSortedMap.this.lastIntKey();
    }
    
    public IntSortedSet headSet(int local_to)
    {
      return AbstractInt2DoubleSortedMap.this.headMap(local_to).keySet();
    }
    
    public IntSortedSet tailSet(int from)
    {
      return AbstractInt2DoubleSortedMap.this.tailMap(from).keySet();
    }
    
    public IntSortedSet subSet(int from, int local_to)
    {
      return AbstractInt2DoubleSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public IntBidirectionalIterator iterator(int from)
    {
      return new AbstractInt2DoubleSortedMap.KeySetIterator(AbstractInt2DoubleSortedMap.this.entrySet().iterator(new AbstractInt2DoubleMap.BasicEntry(from, 0.0D)));
    }
    
    public IntBidirectionalIterator iterator()
    {
      return new AbstractInt2DoubleSortedMap.KeySetIterator(AbstractInt2DoubleSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2DoubleSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
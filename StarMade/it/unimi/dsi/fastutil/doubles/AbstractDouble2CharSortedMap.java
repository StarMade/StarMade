package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.chars.AbstractCharCollection;
import it.unimi.dsi.fastutil.chars.AbstractCharIterator;
import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.chars.CharIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractDouble2CharSortedMap
  extends AbstractDouble2CharMap
  implements Double2CharSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Double2CharSortedMap headMap(Double local_to)
  {
    return headMap(local_to.doubleValue());
  }
  
  public Double2CharSortedMap tailMap(Double from)
  {
    return tailMap(from.doubleValue());
  }
  
  public Double2CharSortedMap subMap(Double from, Double local_to)
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
  
  public CharCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Double, Character>> entrySet()
  {
    return double2CharEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractCharIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Double, Character>> field_67;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Double, Character>> local_i)
    {
      this.field_67 = local_i;
    }
    
    public char nextChar()
    {
      return ((Character)((Map.Entry)this.field_67.next()).getValue()).charValue();
    }
    
    public boolean hasNext()
    {
      return this.field_67.hasNext();
    }
  }
  
  protected class ValuesCollection
    extends AbstractCharCollection
  {
    protected ValuesCollection() {}
    
    public CharIterator iterator()
    {
      return new AbstractDouble2CharSortedMap.ValuesIterator(AbstractDouble2CharSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(char local_k)
    {
      return AbstractDouble2CharSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractDouble2CharSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractDouble2CharSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractDoubleBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Double, Character>> field_68;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Double, Character>> local_i)
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
      return AbstractDouble2CharSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractDouble2CharSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractDouble2CharSortedMap.this.clear();
    }
    
    public DoubleComparator comparator()
    {
      return AbstractDouble2CharSortedMap.this.comparator();
    }
    
    public double firstDouble()
    {
      return AbstractDouble2CharSortedMap.this.firstDoubleKey();
    }
    
    public double lastDouble()
    {
      return AbstractDouble2CharSortedMap.this.lastDoubleKey();
    }
    
    public DoubleSortedSet headSet(double local_to)
    {
      return AbstractDouble2CharSortedMap.this.headMap(local_to).keySet();
    }
    
    public DoubleSortedSet tailSet(double from)
    {
      return AbstractDouble2CharSortedMap.this.tailMap(from).keySet();
    }
    
    public DoubleSortedSet subSet(double from, double local_to)
    {
      return AbstractDouble2CharSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public DoubleBidirectionalIterator iterator(double from)
    {
      return new AbstractDouble2CharSortedMap.KeySetIterator(AbstractDouble2CharSortedMap.this.entrySet().iterator(new AbstractDouble2CharMap.BasicEntry(from, '\000')));
    }
    
    public DoubleBidirectionalIterator iterator()
    {
      return new AbstractDouble2CharSortedMap.KeySetIterator(AbstractDouble2CharSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2CharSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection;
import it.unimi.dsi.fastutil.doubles.AbstractDoubleIterator;
import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.doubles.DoubleIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractShort2DoubleSortedMap
  extends AbstractShort2DoubleMap
  implements Short2DoubleSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Short2DoubleSortedMap headMap(Short local_to)
  {
    return headMap(local_to.shortValue());
  }
  
  public Short2DoubleSortedMap tailMap(Short from)
  {
    return tailMap(from.shortValue());
  }
  
  public Short2DoubleSortedMap subMap(Short from, Short local_to)
  {
    return subMap(from.shortValue(), local_to.shortValue());
  }
  
  public Short firstKey()
  {
    return Short.valueOf(firstShortKey());
  }
  
  public Short lastKey()
  {
    return Short.valueOf(lastShortKey());
  }
  
  public ShortSortedSet keySet()
  {
    return new KeySet();
  }
  
  public DoubleCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Short, Double>> entrySet()
  {
    return short2DoubleEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractDoubleIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Short, Double>> field_68;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Short, Double>> local_i)
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
      return new AbstractShort2DoubleSortedMap.ValuesIterator(AbstractShort2DoubleSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(double local_k)
    {
      return AbstractShort2DoubleSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractShort2DoubleSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractShort2DoubleSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractShortBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Short, Double>> field_53;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Short, Double>> local_i)
    {
      this.field_53 = local_i;
    }
    
    public short nextShort()
    {
      return ((Short)((Map.Entry)this.field_53.next()).getKey()).shortValue();
    }
    
    public short previousShort()
    {
      return ((Short)((Map.Entry)this.field_53.previous()).getKey()).shortValue();
    }
    
    public boolean hasNext()
    {
      return this.field_53.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_53.hasPrevious();
    }
  }
  
  protected class KeySet
    extends AbstractShortSortedSet
  {
    protected KeySet() {}
    
    public boolean contains(short local_k)
    {
      return AbstractShort2DoubleSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractShort2DoubleSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractShort2DoubleSortedMap.this.clear();
    }
    
    public ShortComparator comparator()
    {
      return AbstractShort2DoubleSortedMap.this.comparator();
    }
    
    public short firstShort()
    {
      return AbstractShort2DoubleSortedMap.this.firstShortKey();
    }
    
    public short lastShort()
    {
      return AbstractShort2DoubleSortedMap.this.lastShortKey();
    }
    
    public ShortSortedSet headSet(short local_to)
    {
      return AbstractShort2DoubleSortedMap.this.headMap(local_to).keySet();
    }
    
    public ShortSortedSet tailSet(short from)
    {
      return AbstractShort2DoubleSortedMap.this.tailMap(from).keySet();
    }
    
    public ShortSortedSet subSet(short from, short local_to)
    {
      return AbstractShort2DoubleSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public ShortBidirectionalIterator iterator(short from)
    {
      return new AbstractShort2DoubleSortedMap.KeySetIterator(AbstractShort2DoubleSortedMap.this.entrySet().iterator(new AbstractShort2DoubleMap.BasicEntry(from, 0.0D)));
    }
    
    public ShortBidirectionalIterator iterator()
    {
      return new AbstractShort2DoubleSortedMap.KeySetIterator(AbstractShort2DoubleSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2DoubleSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.shorts.AbstractShortCollection;
import it.unimi.dsi.fastutil.shorts.AbstractShortIterator;
import it.unimi.dsi.fastutil.shorts.ShortCollection;
import it.unimi.dsi.fastutil.shorts.ShortIterator;
import java.util.Map.Entry;

public abstract class AbstractInt2ShortSortedMap
  extends AbstractInt2ShortMap
  implements Int2ShortSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Int2ShortSortedMap headMap(Integer local_to)
  {
    return headMap(local_to.intValue());
  }
  
  public Int2ShortSortedMap tailMap(Integer from)
  {
    return tailMap(from.intValue());
  }
  
  public Int2ShortSortedMap subMap(Integer from, Integer local_to)
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
  
  public ShortCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Integer, Short>> entrySet()
  {
    return int2ShortEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractShortIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Integer, Short>> field_53;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Integer, Short>> local_i)
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
      return new AbstractInt2ShortSortedMap.ValuesIterator(AbstractInt2ShortSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(short local_k)
    {
      return AbstractInt2ShortSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractInt2ShortSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractInt2ShortSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractIntBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Integer, Short>> field_70;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Integer, Short>> local_i)
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
      return AbstractInt2ShortSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractInt2ShortSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractInt2ShortSortedMap.this.clear();
    }
    
    public IntComparator comparator()
    {
      return AbstractInt2ShortSortedMap.this.comparator();
    }
    
    public int firstInt()
    {
      return AbstractInt2ShortSortedMap.this.firstIntKey();
    }
    
    public int lastInt()
    {
      return AbstractInt2ShortSortedMap.this.lastIntKey();
    }
    
    public IntSortedSet headSet(int local_to)
    {
      return AbstractInt2ShortSortedMap.this.headMap(local_to).keySet();
    }
    
    public IntSortedSet tailSet(int from)
    {
      return AbstractInt2ShortSortedMap.this.tailMap(from).keySet();
    }
    
    public IntSortedSet subSet(int from, int local_to)
    {
      return AbstractInt2ShortSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public IntBidirectionalIterator iterator(int from)
    {
      return new AbstractInt2ShortSortedMap.KeySetIterator(AbstractInt2ShortSortedMap.this.entrySet().iterator(new AbstractInt2ShortMap.BasicEntry(from, (short)0)));
    }
    
    public IntBidirectionalIterator iterator()
    {
      return new AbstractInt2ShortSortedMap.KeySetIterator(AbstractInt2ShortSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2ShortSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
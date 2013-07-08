package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractInt2IntSortedMap
  extends AbstractInt2IntMap
  implements Int2IntSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Int2IntSortedMap headMap(Integer local_to)
  {
    return headMap(local_to.intValue());
  }
  
  public Int2IntSortedMap tailMap(Integer from)
  {
    return tailMap(from.intValue());
  }
  
  public Int2IntSortedMap subMap(Integer from, Integer local_to)
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
  
  public IntCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Integer, Integer>> entrySet()
  {
    return int2IntEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractIntIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Integer, Integer>> field_70;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Integer, Integer>> local_i)
    {
      this.field_70 = local_i;
    }
    
    public int nextInt()
    {
      return ((Integer)((Map.Entry)this.field_70.next()).getValue()).intValue();
    }
    
    public boolean hasNext()
    {
      return this.field_70.hasNext();
    }
  }
  
  protected class ValuesCollection
    extends AbstractIntCollection
  {
    protected ValuesCollection() {}
    
    public IntIterator iterator()
    {
      return new AbstractInt2IntSortedMap.ValuesIterator(AbstractInt2IntSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(int local_k)
    {
      return AbstractInt2IntSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractInt2IntSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractInt2IntSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractIntBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Integer, Integer>> field_70;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Integer, Integer>> local_i)
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
      return AbstractInt2IntSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractInt2IntSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractInt2IntSortedMap.this.clear();
    }
    
    public IntComparator comparator()
    {
      return AbstractInt2IntSortedMap.this.comparator();
    }
    
    public int firstInt()
    {
      return AbstractInt2IntSortedMap.this.firstIntKey();
    }
    
    public int lastInt()
    {
      return AbstractInt2IntSortedMap.this.lastIntKey();
    }
    
    public IntSortedSet headSet(int local_to)
    {
      return AbstractInt2IntSortedMap.this.headMap(local_to).keySet();
    }
    
    public IntSortedSet tailSet(int from)
    {
      return AbstractInt2IntSortedMap.this.tailMap(from).keySet();
    }
    
    public IntSortedSet subSet(int from, int local_to)
    {
      return AbstractInt2IntSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public IntBidirectionalIterator iterator(int from)
    {
      return new AbstractInt2IntSortedMap.KeySetIterator(AbstractInt2IntSortedMap.this.entrySet().iterator(new AbstractInt2IntMap.BasicEntry(from, 0)));
    }
    
    public IntBidirectionalIterator iterator()
    {
      return new AbstractInt2IntSortedMap.KeySetIterator(AbstractInt2IntSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2IntSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
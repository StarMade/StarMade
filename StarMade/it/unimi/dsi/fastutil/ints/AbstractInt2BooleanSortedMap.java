package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection;
import it.unimi.dsi.fastutil.booleans.AbstractBooleanIterator;
import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.booleans.BooleanIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractInt2BooleanSortedMap
  extends AbstractInt2BooleanMap
  implements Int2BooleanSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Int2BooleanSortedMap headMap(Integer local_to)
  {
    return headMap(local_to.intValue());
  }
  
  public Int2BooleanSortedMap tailMap(Integer from)
  {
    return tailMap(from.intValue());
  }
  
  public Int2BooleanSortedMap subMap(Integer from, Integer local_to)
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
  
  public BooleanCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Integer, Boolean>> entrySet()
  {
    return int2BooleanEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractBooleanIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Integer, Boolean>> field_60;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Integer, Boolean>> local_i)
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
      return new AbstractInt2BooleanSortedMap.ValuesIterator(AbstractInt2BooleanSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(boolean local_k)
    {
      return AbstractInt2BooleanSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractInt2BooleanSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractInt2BooleanSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractIntBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Integer, Boolean>> field_70;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Integer, Boolean>> local_i)
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
      return AbstractInt2BooleanSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractInt2BooleanSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractInt2BooleanSortedMap.this.clear();
    }
    
    public IntComparator comparator()
    {
      return AbstractInt2BooleanSortedMap.this.comparator();
    }
    
    public int firstInt()
    {
      return AbstractInt2BooleanSortedMap.this.firstIntKey();
    }
    
    public int lastInt()
    {
      return AbstractInt2BooleanSortedMap.this.lastIntKey();
    }
    
    public IntSortedSet headSet(int local_to)
    {
      return AbstractInt2BooleanSortedMap.this.headMap(local_to).keySet();
    }
    
    public IntSortedSet tailSet(int from)
    {
      return AbstractInt2BooleanSortedMap.this.tailMap(from).keySet();
    }
    
    public IntSortedSet subSet(int from, int local_to)
    {
      return AbstractInt2BooleanSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public IntBidirectionalIterator iterator(int from)
    {
      return new AbstractInt2BooleanSortedMap.KeySetIterator(AbstractInt2BooleanSortedMap.this.entrySet().iterator(new AbstractInt2BooleanMap.BasicEntry(from, false)));
    }
    
    public IntBidirectionalIterator iterator()
    {
      return new AbstractInt2BooleanSortedMap.KeySetIterator(AbstractInt2BooleanSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2BooleanSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.longs.AbstractLongCollection;
import it.unimi.dsi.fastutil.longs.AbstractLongIterator;
import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractInt2LongSortedMap
  extends AbstractInt2LongMap
  implements Int2LongSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Int2LongSortedMap headMap(Integer local_to)
  {
    return headMap(local_to.intValue());
  }
  
  public Int2LongSortedMap tailMap(Integer from)
  {
    return tailMap(from.intValue());
  }
  
  public Int2LongSortedMap subMap(Integer from, Integer local_to)
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
  
  public LongCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Integer, Long>> entrySet()
  {
    return int2LongEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractLongIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Integer, Long>> field_1;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Integer, Long>> local_i)
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
      return new AbstractInt2LongSortedMap.ValuesIterator(AbstractInt2LongSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(long local_k)
    {
      return AbstractInt2LongSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractInt2LongSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractInt2LongSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractIntBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Integer, Long>> field_70;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Integer, Long>> local_i)
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
      return AbstractInt2LongSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractInt2LongSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractInt2LongSortedMap.this.clear();
    }
    
    public IntComparator comparator()
    {
      return AbstractInt2LongSortedMap.this.comparator();
    }
    
    public int firstInt()
    {
      return AbstractInt2LongSortedMap.this.firstIntKey();
    }
    
    public int lastInt()
    {
      return AbstractInt2LongSortedMap.this.lastIntKey();
    }
    
    public IntSortedSet headSet(int local_to)
    {
      return AbstractInt2LongSortedMap.this.headMap(local_to).keySet();
    }
    
    public IntSortedSet tailSet(int from)
    {
      return AbstractInt2LongSortedMap.this.tailMap(from).keySet();
    }
    
    public IntSortedSet subSet(int from, int local_to)
    {
      return AbstractInt2LongSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public IntBidirectionalIterator iterator(int from)
    {
      return new AbstractInt2LongSortedMap.KeySetIterator(AbstractInt2LongSortedMap.this.entrySet().iterator(new AbstractInt2LongMap.BasicEntry(from, 0L)));
    }
    
    public IntBidirectionalIterator iterator()
    {
      return new AbstractInt2LongSortedMap.KeySetIterator(AbstractInt2LongSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2LongSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
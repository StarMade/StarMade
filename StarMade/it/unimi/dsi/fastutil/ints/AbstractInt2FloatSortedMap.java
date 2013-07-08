package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.floats.AbstractFloatCollection;
import it.unimi.dsi.fastutil.floats.AbstractFloatIterator;
import it.unimi.dsi.fastutil.floats.FloatCollection;
import it.unimi.dsi.fastutil.floats.FloatIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractInt2FloatSortedMap
  extends AbstractInt2FloatMap
  implements Int2FloatSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Int2FloatSortedMap headMap(Integer local_to)
  {
    return headMap(local_to.intValue());
  }
  
  public Int2FloatSortedMap tailMap(Integer from)
  {
    return tailMap(from.intValue());
  }
  
  public Int2FloatSortedMap subMap(Integer from, Integer local_to)
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
  
  public FloatCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Integer, Float>> entrySet()
  {
    return int2FloatEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractFloatIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Integer, Float>> field_52;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Integer, Float>> local_i)
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
      return new AbstractInt2FloatSortedMap.ValuesIterator(AbstractInt2FloatSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(float local_k)
    {
      return AbstractInt2FloatSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractInt2FloatSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractInt2FloatSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractIntBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Integer, Float>> field_70;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Integer, Float>> local_i)
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
      return AbstractInt2FloatSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractInt2FloatSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractInt2FloatSortedMap.this.clear();
    }
    
    public IntComparator comparator()
    {
      return AbstractInt2FloatSortedMap.this.comparator();
    }
    
    public int firstInt()
    {
      return AbstractInt2FloatSortedMap.this.firstIntKey();
    }
    
    public int lastInt()
    {
      return AbstractInt2FloatSortedMap.this.lastIntKey();
    }
    
    public IntSortedSet headSet(int local_to)
    {
      return AbstractInt2FloatSortedMap.this.headMap(local_to).keySet();
    }
    
    public IntSortedSet tailSet(int from)
    {
      return AbstractInt2FloatSortedMap.this.tailMap(from).keySet();
    }
    
    public IntSortedSet subSet(int from, int local_to)
    {
      return AbstractInt2FloatSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public IntBidirectionalIterator iterator(int from)
    {
      return new AbstractInt2FloatSortedMap.KeySetIterator(AbstractInt2FloatSortedMap.this.entrySet().iterator(new AbstractInt2FloatMap.BasicEntry(from, 0.0F)));
    }
    
    public IntBidirectionalIterator iterator()
    {
      return new AbstractInt2FloatSortedMap.KeySetIterator(AbstractInt2FloatSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2FloatSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
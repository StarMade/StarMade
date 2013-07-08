package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.ints.AbstractIntCollection;
import it.unimi.dsi.fastutil.ints.AbstractIntIterator;
import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractFloat2IntSortedMap
  extends AbstractFloat2IntMap
  implements Float2IntSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Float2IntSortedMap headMap(Float local_to)
  {
    return headMap(local_to.floatValue());
  }
  
  public Float2IntSortedMap tailMap(Float from)
  {
    return tailMap(from.floatValue());
  }
  
  public Float2IntSortedMap subMap(Float from, Float local_to)
  {
    return subMap(from.floatValue(), local_to.floatValue());
  }
  
  public Float firstKey()
  {
    return Float.valueOf(firstFloatKey());
  }
  
  public Float lastKey()
  {
    return Float.valueOf(lastFloatKey());
  }
  
  public FloatSortedSet keySet()
  {
    return new KeySet();
  }
  
  public IntCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Float, Integer>> entrySet()
  {
    return float2IntEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractIntIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Float, Integer>> field_70;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Float, Integer>> local_i)
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
      return new AbstractFloat2IntSortedMap.ValuesIterator(AbstractFloat2IntSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(int local_k)
    {
      return AbstractFloat2IntSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractFloat2IntSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractFloat2IntSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractFloatBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Float, Integer>> field_52;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Float, Integer>> local_i)
    {
      this.field_52 = local_i;
    }
    
    public float nextFloat()
    {
      return ((Float)((Map.Entry)this.field_52.next()).getKey()).floatValue();
    }
    
    public float previousFloat()
    {
      return ((Float)((Map.Entry)this.field_52.previous()).getKey()).floatValue();
    }
    
    public boolean hasNext()
    {
      return this.field_52.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_52.hasPrevious();
    }
  }
  
  protected class KeySet
    extends AbstractFloatSortedSet
  {
    protected KeySet() {}
    
    public boolean contains(float local_k)
    {
      return AbstractFloat2IntSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractFloat2IntSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractFloat2IntSortedMap.this.clear();
    }
    
    public FloatComparator comparator()
    {
      return AbstractFloat2IntSortedMap.this.comparator();
    }
    
    public float firstFloat()
    {
      return AbstractFloat2IntSortedMap.this.firstFloatKey();
    }
    
    public float lastFloat()
    {
      return AbstractFloat2IntSortedMap.this.lastFloatKey();
    }
    
    public FloatSortedSet headSet(float local_to)
    {
      return AbstractFloat2IntSortedMap.this.headMap(local_to).keySet();
    }
    
    public FloatSortedSet tailSet(float from)
    {
      return AbstractFloat2IntSortedMap.this.tailMap(from).keySet();
    }
    
    public FloatSortedSet subSet(float from, float local_to)
    {
      return AbstractFloat2IntSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public FloatBidirectionalIterator iterator(float from)
    {
      return new AbstractFloat2IntSortedMap.KeySetIterator(AbstractFloat2IntSortedMap.this.entrySet().iterator(new AbstractFloat2IntMap.BasicEntry(from, 0)));
    }
    
    public FloatBidirectionalIterator iterator()
    {
      return new AbstractFloat2IntSortedMap.KeySetIterator(AbstractFloat2IntSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2IntSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.longs.AbstractLongCollection;
import it.unimi.dsi.fastutil.longs.AbstractLongIterator;
import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractFloat2LongSortedMap
  extends AbstractFloat2LongMap
  implements Float2LongSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Float2LongSortedMap headMap(Float local_to)
  {
    return headMap(local_to.floatValue());
  }
  
  public Float2LongSortedMap tailMap(Float from)
  {
    return tailMap(from.floatValue());
  }
  
  public Float2LongSortedMap subMap(Float from, Float local_to)
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
  
  public LongCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Float, Long>> entrySet()
  {
    return float2LongEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractLongIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Float, Long>> field_1;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Float, Long>> local_i)
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
      return new AbstractFloat2LongSortedMap.ValuesIterator(AbstractFloat2LongSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(long local_k)
    {
      return AbstractFloat2LongSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractFloat2LongSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractFloat2LongSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractFloatBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Float, Long>> field_52;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Float, Long>> local_i)
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
      return AbstractFloat2LongSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractFloat2LongSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractFloat2LongSortedMap.this.clear();
    }
    
    public FloatComparator comparator()
    {
      return AbstractFloat2LongSortedMap.this.comparator();
    }
    
    public float firstFloat()
    {
      return AbstractFloat2LongSortedMap.this.firstFloatKey();
    }
    
    public float lastFloat()
    {
      return AbstractFloat2LongSortedMap.this.lastFloatKey();
    }
    
    public FloatSortedSet headSet(float local_to)
    {
      return AbstractFloat2LongSortedMap.this.headMap(local_to).keySet();
    }
    
    public FloatSortedSet tailSet(float from)
    {
      return AbstractFloat2LongSortedMap.this.tailMap(from).keySet();
    }
    
    public FloatSortedSet subSet(float from, float local_to)
    {
      return AbstractFloat2LongSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public FloatBidirectionalIterator iterator(float from)
    {
      return new AbstractFloat2LongSortedMap.KeySetIterator(AbstractFloat2LongSortedMap.this.entrySet().iterator(new AbstractFloat2LongMap.BasicEntry(from, 0L)));
    }
    
    public FloatBidirectionalIterator iterator()
    {
      return new AbstractFloat2LongSortedMap.KeySetIterator(AbstractFloat2LongSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2LongSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
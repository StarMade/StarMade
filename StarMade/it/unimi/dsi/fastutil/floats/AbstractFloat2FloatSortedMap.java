package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractFloat2FloatSortedMap
  extends AbstractFloat2FloatMap
  implements Float2FloatSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Float2FloatSortedMap headMap(Float local_to)
  {
    return headMap(local_to.floatValue());
  }
  
  public Float2FloatSortedMap tailMap(Float from)
  {
    return tailMap(from.floatValue());
  }
  
  public Float2FloatSortedMap subMap(Float from, Float local_to)
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
  
  public FloatCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Float, Float>> entrySet()
  {
    return float2FloatEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractFloatIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Float, Float>> field_52;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Float, Float>> local_i)
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
      return new AbstractFloat2FloatSortedMap.ValuesIterator(AbstractFloat2FloatSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(float local_k)
    {
      return AbstractFloat2FloatSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractFloat2FloatSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractFloat2FloatSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractFloatBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Float, Float>> field_52;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Float, Float>> local_i)
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
      return AbstractFloat2FloatSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractFloat2FloatSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractFloat2FloatSortedMap.this.clear();
    }
    
    public FloatComparator comparator()
    {
      return AbstractFloat2FloatSortedMap.this.comparator();
    }
    
    public float firstFloat()
    {
      return AbstractFloat2FloatSortedMap.this.firstFloatKey();
    }
    
    public float lastFloat()
    {
      return AbstractFloat2FloatSortedMap.this.lastFloatKey();
    }
    
    public FloatSortedSet headSet(float local_to)
    {
      return AbstractFloat2FloatSortedMap.this.headMap(local_to).keySet();
    }
    
    public FloatSortedSet tailSet(float from)
    {
      return AbstractFloat2FloatSortedMap.this.tailMap(from).keySet();
    }
    
    public FloatSortedSet subSet(float from, float local_to)
    {
      return AbstractFloat2FloatSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public FloatBidirectionalIterator iterator(float from)
    {
      return new AbstractFloat2FloatSortedMap.KeySetIterator(AbstractFloat2FloatSortedMap.this.entrySet().iterator(new AbstractFloat2FloatMap.BasicEntry(from, 0.0F)));
    }
    
    public FloatBidirectionalIterator iterator()
    {
      return new AbstractFloat2FloatSortedMap.KeySetIterator(AbstractFloat2FloatSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2FloatSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
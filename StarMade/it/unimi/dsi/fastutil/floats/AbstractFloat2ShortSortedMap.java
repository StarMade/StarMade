package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.shorts.AbstractShortCollection;
import it.unimi.dsi.fastutil.shorts.AbstractShortIterator;
import it.unimi.dsi.fastutil.shorts.ShortCollection;
import it.unimi.dsi.fastutil.shorts.ShortIterator;
import java.util.Map.Entry;

public abstract class AbstractFloat2ShortSortedMap
  extends AbstractFloat2ShortMap
  implements Float2ShortSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Float2ShortSortedMap headMap(Float local_to)
  {
    return headMap(local_to.floatValue());
  }
  
  public Float2ShortSortedMap tailMap(Float from)
  {
    return tailMap(from.floatValue());
  }
  
  public Float2ShortSortedMap subMap(Float from, Float local_to)
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
  
  public ShortCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Float, Short>> entrySet()
  {
    return float2ShortEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractShortIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Float, Short>> field_53;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Float, Short>> local_i)
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
      return new AbstractFloat2ShortSortedMap.ValuesIterator(AbstractFloat2ShortSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(short local_k)
    {
      return AbstractFloat2ShortSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractFloat2ShortSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractFloat2ShortSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractFloatBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Float, Short>> field_52;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Float, Short>> local_i)
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
      return AbstractFloat2ShortSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractFloat2ShortSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractFloat2ShortSortedMap.this.clear();
    }
    
    public FloatComparator comparator()
    {
      return AbstractFloat2ShortSortedMap.this.comparator();
    }
    
    public float firstFloat()
    {
      return AbstractFloat2ShortSortedMap.this.firstFloatKey();
    }
    
    public float lastFloat()
    {
      return AbstractFloat2ShortSortedMap.this.lastFloatKey();
    }
    
    public FloatSortedSet headSet(float local_to)
    {
      return AbstractFloat2ShortSortedMap.this.headMap(local_to).keySet();
    }
    
    public FloatSortedSet tailSet(float from)
    {
      return AbstractFloat2ShortSortedMap.this.tailMap(from).keySet();
    }
    
    public FloatSortedSet subSet(float from, float local_to)
    {
      return AbstractFloat2ShortSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public FloatBidirectionalIterator iterator(float from)
    {
      return new AbstractFloat2ShortSortedMap.KeySetIterator(AbstractFloat2ShortSortedMap.this.entrySet().iterator(new AbstractFloat2ShortMap.BasicEntry(from, (short)0)));
    }
    
    public FloatBidirectionalIterator iterator()
    {
      return new AbstractFloat2ShortSortedMap.KeySetIterator(AbstractFloat2ShortSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2ShortSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
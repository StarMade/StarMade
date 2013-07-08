package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection;
import it.unimi.dsi.fastutil.booleans.AbstractBooleanIterator;
import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.booleans.BooleanIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractFloat2BooleanSortedMap
  extends AbstractFloat2BooleanMap
  implements Float2BooleanSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Float2BooleanSortedMap headMap(Float local_to)
  {
    return headMap(local_to.floatValue());
  }
  
  public Float2BooleanSortedMap tailMap(Float from)
  {
    return tailMap(from.floatValue());
  }
  
  public Float2BooleanSortedMap subMap(Float from, Float local_to)
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
  
  public BooleanCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Float, Boolean>> entrySet()
  {
    return float2BooleanEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractBooleanIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Float, Boolean>> field_60;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Float, Boolean>> local_i)
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
      return new AbstractFloat2BooleanSortedMap.ValuesIterator(AbstractFloat2BooleanSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(boolean local_k)
    {
      return AbstractFloat2BooleanSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractFloat2BooleanSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractFloat2BooleanSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractFloatBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Float, Boolean>> field_52;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Float, Boolean>> local_i)
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
      return AbstractFloat2BooleanSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractFloat2BooleanSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractFloat2BooleanSortedMap.this.clear();
    }
    
    public FloatComparator comparator()
    {
      return AbstractFloat2BooleanSortedMap.this.comparator();
    }
    
    public float firstFloat()
    {
      return AbstractFloat2BooleanSortedMap.this.firstFloatKey();
    }
    
    public float lastFloat()
    {
      return AbstractFloat2BooleanSortedMap.this.lastFloatKey();
    }
    
    public FloatSortedSet headSet(float local_to)
    {
      return AbstractFloat2BooleanSortedMap.this.headMap(local_to).keySet();
    }
    
    public FloatSortedSet tailSet(float from)
    {
      return AbstractFloat2BooleanSortedMap.this.tailMap(from).keySet();
    }
    
    public FloatSortedSet subSet(float from, float local_to)
    {
      return AbstractFloat2BooleanSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public FloatBidirectionalIterator iterator(float from)
    {
      return new AbstractFloat2BooleanSortedMap.KeySetIterator(AbstractFloat2BooleanSortedMap.this.entrySet().iterator(new AbstractFloat2BooleanMap.BasicEntry(from, false)));
    }
    
    public FloatBidirectionalIterator iterator()
    {
      return new AbstractFloat2BooleanSortedMap.KeySetIterator(AbstractFloat2BooleanSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2BooleanSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
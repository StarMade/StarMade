package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection;
import it.unimi.dsi.fastutil.doubles.AbstractDoubleIterator;
import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.doubles.DoubleIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractFloat2DoubleSortedMap
  extends AbstractFloat2DoubleMap
  implements Float2DoubleSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Float2DoubleSortedMap headMap(Float local_to)
  {
    return headMap(local_to.floatValue());
  }
  
  public Float2DoubleSortedMap tailMap(Float from)
  {
    return tailMap(from.floatValue());
  }
  
  public Float2DoubleSortedMap subMap(Float from, Float local_to)
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
  
  public DoubleCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Float, Double>> entrySet()
  {
    return float2DoubleEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractDoubleIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Float, Double>> field_68;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Float, Double>> local_i)
    {
      this.field_68 = local_i;
    }
    
    public double nextDouble()
    {
      return ((Double)((Map.Entry)this.field_68.next()).getValue()).doubleValue();
    }
    
    public boolean hasNext()
    {
      return this.field_68.hasNext();
    }
  }
  
  protected class ValuesCollection
    extends AbstractDoubleCollection
  {
    protected ValuesCollection() {}
    
    public DoubleIterator iterator()
    {
      return new AbstractFloat2DoubleSortedMap.ValuesIterator(AbstractFloat2DoubleSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(double local_k)
    {
      return AbstractFloat2DoubleSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractFloat2DoubleSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractFloat2DoubleSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractFloatBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Float, Double>> field_52;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Float, Double>> local_i)
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
      return AbstractFloat2DoubleSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractFloat2DoubleSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractFloat2DoubleSortedMap.this.clear();
    }
    
    public FloatComparator comparator()
    {
      return AbstractFloat2DoubleSortedMap.this.comparator();
    }
    
    public float firstFloat()
    {
      return AbstractFloat2DoubleSortedMap.this.firstFloatKey();
    }
    
    public float lastFloat()
    {
      return AbstractFloat2DoubleSortedMap.this.lastFloatKey();
    }
    
    public FloatSortedSet headSet(float local_to)
    {
      return AbstractFloat2DoubleSortedMap.this.headMap(local_to).keySet();
    }
    
    public FloatSortedSet tailSet(float from)
    {
      return AbstractFloat2DoubleSortedMap.this.tailMap(from).keySet();
    }
    
    public FloatSortedSet subSet(float from, float local_to)
    {
      return AbstractFloat2DoubleSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public FloatBidirectionalIterator iterator(float from)
    {
      return new AbstractFloat2DoubleSortedMap.KeySetIterator(AbstractFloat2DoubleSortedMap.this.entrySet().iterator(new AbstractFloat2DoubleMap.BasicEntry(from, 0.0D)));
    }
    
    public FloatBidirectionalIterator iterator()
    {
      return new AbstractFloat2DoubleSortedMap.KeySetIterator(AbstractFloat2DoubleSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2DoubleSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
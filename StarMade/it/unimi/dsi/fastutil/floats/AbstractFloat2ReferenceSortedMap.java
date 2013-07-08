package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
import it.unimi.dsi.fastutil.objects.AbstractReferenceCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import java.util.Map.Entry;

public abstract class AbstractFloat2ReferenceSortedMap<V>
  extends AbstractFloat2ReferenceMap<V>
  implements Float2ReferenceSortedMap<V>
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Float2ReferenceSortedMap<V> headMap(Float local_to)
  {
    return headMap(local_to.floatValue());
  }
  
  public Float2ReferenceSortedMap<V> tailMap(Float from)
  {
    return tailMap(from.floatValue());
  }
  
  public Float2ReferenceSortedMap<V> subMap(Float from, Float local_to)
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
  
  public ReferenceCollection<V> values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Float, V>> entrySet()
  {
    return float2ReferenceEntrySet();
  }
  
  protected static class ValuesIterator<V>
    extends AbstractObjectIterator<V>
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Float, V>> field_3;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Float, V>> local_i)
    {
      this.field_3 = local_i;
    }
    
    public V next()
    {
      return ((Map.Entry)this.field_3.next()).getValue();
    }
    
    public boolean hasNext()
    {
      return this.field_3.hasNext();
    }
  }
  
  protected class ValuesCollection
    extends AbstractReferenceCollection<V>
  {
    protected ValuesCollection() {}
    
    public ObjectIterator<V> iterator()
    {
      return new AbstractFloat2ReferenceSortedMap.ValuesIterator(AbstractFloat2ReferenceSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(Object local_k)
    {
      return AbstractFloat2ReferenceSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractFloat2ReferenceSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractFloat2ReferenceSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator<V>
    extends AbstractFloatBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Float, V>> field_52;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Float, V>> local_i)
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
      return AbstractFloat2ReferenceSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractFloat2ReferenceSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractFloat2ReferenceSortedMap.this.clear();
    }
    
    public FloatComparator comparator()
    {
      return AbstractFloat2ReferenceSortedMap.this.comparator();
    }
    
    public float firstFloat()
    {
      return AbstractFloat2ReferenceSortedMap.this.firstFloatKey();
    }
    
    public float lastFloat()
    {
      return AbstractFloat2ReferenceSortedMap.this.lastFloatKey();
    }
    
    public FloatSortedSet headSet(float local_to)
    {
      return AbstractFloat2ReferenceSortedMap.this.headMap(local_to).keySet();
    }
    
    public FloatSortedSet tailSet(float from)
    {
      return AbstractFloat2ReferenceSortedMap.this.tailMap(from).keySet();
    }
    
    public FloatSortedSet subSet(float from, float local_to)
    {
      return AbstractFloat2ReferenceSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public FloatBidirectionalIterator iterator(float from)
    {
      return new AbstractFloat2ReferenceSortedMap.KeySetIterator(AbstractFloat2ReferenceSortedMap.this.entrySet().iterator(new AbstractFloat2ReferenceMap.BasicEntry(from, null)));
    }
    
    public FloatBidirectionalIterator iterator()
    {
      return new AbstractFloat2ReferenceSortedMap.KeySetIterator(AbstractFloat2ReferenceSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2ReferenceSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
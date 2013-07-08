package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.floats.AbstractFloatCollection;
import it.unimi.dsi.fastutil.floats.AbstractFloatIterator;
import it.unimi.dsi.fastutil.floats.FloatCollection;
import it.unimi.dsi.fastutil.floats.FloatIterator;
import java.util.Comparator;
import java.util.Map.Entry;

public abstract class AbstractObject2FloatSortedMap<K>
  extends AbstractObject2FloatMap<K>
  implements Object2FloatSortedMap<K>
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public ObjectSortedSet<K> keySet()
  {
    return new KeySet();
  }
  
  public FloatCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<K, Float>> entrySet()
  {
    return object2FloatEntrySet();
  }
  
  protected static class ValuesIterator<K>
    extends AbstractFloatIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<K, Float>> field_52;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<K, Float>> local_i)
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
      return new AbstractObject2FloatSortedMap.ValuesIterator(AbstractObject2FloatSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(float local_k)
    {
      return AbstractObject2FloatSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractObject2FloatSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractObject2FloatSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator<K>
    extends AbstractObjectBidirectionalIterator<K>
  {
    protected final ObjectBidirectionalIterator<Map.Entry<K, Float>> field_3;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<K, Float>> local_i)
    {
      this.field_3 = local_i;
    }
    
    public K next()
    {
      return ((Map.Entry)this.field_3.next()).getKey();
    }
    
    public K previous()
    {
      return ((Map.Entry)this.field_3.previous()).getKey();
    }
    
    public boolean hasNext()
    {
      return this.field_3.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_3.hasPrevious();
    }
  }
  
  protected class KeySet
    extends AbstractObjectSortedSet<K>
  {
    protected KeySet() {}
    
    public boolean contains(Object local_k)
    {
      return AbstractObject2FloatSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractObject2FloatSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractObject2FloatSortedMap.this.clear();
    }
    
    public Comparator<? super K> comparator()
    {
      return AbstractObject2FloatSortedMap.this.comparator();
    }
    
    public K first()
    {
      return AbstractObject2FloatSortedMap.this.firstKey();
    }
    
    public K last()
    {
      return AbstractObject2FloatSortedMap.this.lastKey();
    }
    
    public ObjectSortedSet<K> headSet(K local_to)
    {
      return AbstractObject2FloatSortedMap.this.headMap(local_to).keySet();
    }
    
    public ObjectSortedSet<K> tailSet(K from)
    {
      return AbstractObject2FloatSortedMap.this.tailMap(from).keySet();
    }
    
    public ObjectSortedSet<K> subSet(K from, K local_to)
    {
      return AbstractObject2FloatSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public ObjectBidirectionalIterator<K> iterator(K from)
    {
      return new AbstractObject2FloatSortedMap.KeySetIterator(AbstractObject2FloatSortedMap.this.entrySet().iterator(new AbstractObject2FloatMap.BasicEntry(from, 0.0F)));
    }
    
    public ObjectBidirectionalIterator<K> iterator()
    {
      return new AbstractObject2FloatSortedMap.KeySetIterator(AbstractObject2FloatSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObject2FloatSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
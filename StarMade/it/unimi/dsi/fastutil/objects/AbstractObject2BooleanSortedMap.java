package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection;
import it.unimi.dsi.fastutil.booleans.AbstractBooleanIterator;
import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.booleans.BooleanIterator;
import java.util.Comparator;
import java.util.Map.Entry;

public abstract class AbstractObject2BooleanSortedMap<K>
  extends AbstractObject2BooleanMap<K>
  implements Object2BooleanSortedMap<K>
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public ObjectSortedSet<K> keySet()
  {
    return new KeySet();
  }
  
  public BooleanCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<K, Boolean>> entrySet()
  {
    return object2BooleanEntrySet();
  }
  
  protected static class ValuesIterator<K>
    extends AbstractBooleanIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<K, Boolean>> field_60;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<K, Boolean>> local_i)
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
      return new AbstractObject2BooleanSortedMap.ValuesIterator(AbstractObject2BooleanSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(boolean local_k)
    {
      return AbstractObject2BooleanSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractObject2BooleanSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractObject2BooleanSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator<K>
    extends AbstractObjectBidirectionalIterator<K>
  {
    protected final ObjectBidirectionalIterator<Map.Entry<K, Boolean>> field_3;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<K, Boolean>> local_i)
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
      return AbstractObject2BooleanSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractObject2BooleanSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractObject2BooleanSortedMap.this.clear();
    }
    
    public Comparator<? super K> comparator()
    {
      return AbstractObject2BooleanSortedMap.this.comparator();
    }
    
    public K first()
    {
      return AbstractObject2BooleanSortedMap.this.firstKey();
    }
    
    public K last()
    {
      return AbstractObject2BooleanSortedMap.this.lastKey();
    }
    
    public ObjectSortedSet<K> headSet(K local_to)
    {
      return AbstractObject2BooleanSortedMap.this.headMap(local_to).keySet();
    }
    
    public ObjectSortedSet<K> tailSet(K from)
    {
      return AbstractObject2BooleanSortedMap.this.tailMap(from).keySet();
    }
    
    public ObjectSortedSet<K> subSet(K from, K local_to)
    {
      return AbstractObject2BooleanSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public ObjectBidirectionalIterator<K> iterator(K from)
    {
      return new AbstractObject2BooleanSortedMap.KeySetIterator(AbstractObject2BooleanSortedMap.this.entrySet().iterator(new AbstractObject2BooleanMap.BasicEntry(from, false)));
    }
    
    public ObjectBidirectionalIterator<K> iterator()
    {
      return new AbstractObject2BooleanSortedMap.KeySetIterator(AbstractObject2BooleanSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObject2BooleanSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
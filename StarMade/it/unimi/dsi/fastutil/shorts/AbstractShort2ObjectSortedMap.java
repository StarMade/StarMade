package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.objects.AbstractObjectCollection;
import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractShort2ObjectSortedMap<V>
  extends AbstractShort2ObjectMap<V>
  implements Short2ObjectSortedMap<V>
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Short2ObjectSortedMap<V> headMap(Short local_to)
  {
    return headMap(local_to.shortValue());
  }
  
  public Short2ObjectSortedMap<V> tailMap(Short from)
  {
    return tailMap(from.shortValue());
  }
  
  public Short2ObjectSortedMap<V> subMap(Short from, Short local_to)
  {
    return subMap(from.shortValue(), local_to.shortValue());
  }
  
  public Short firstKey()
  {
    return Short.valueOf(firstShortKey());
  }
  
  public Short lastKey()
  {
    return Short.valueOf(lastShortKey());
  }
  
  public ShortSortedSet keySet()
  {
    return new KeySet();
  }
  
  public ObjectCollection<V> values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Short, V>> entrySet()
  {
    return short2ObjectEntrySet();
  }
  
  protected static class ValuesIterator<V>
    extends AbstractObjectIterator<V>
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Short, V>> field_3;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Short, V>> local_i)
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
    extends AbstractObjectCollection<V>
  {
    protected ValuesCollection() {}
    
    public ObjectIterator<V> iterator()
    {
      return new AbstractShort2ObjectSortedMap.ValuesIterator(AbstractShort2ObjectSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(Object local_k)
    {
      return AbstractShort2ObjectSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractShort2ObjectSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractShort2ObjectSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator<V>
    extends AbstractShortBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Short, V>> field_53;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Short, V>> local_i)
    {
      this.field_53 = local_i;
    }
    
    public short nextShort()
    {
      return ((Short)((Map.Entry)this.field_53.next()).getKey()).shortValue();
    }
    
    public short previousShort()
    {
      return ((Short)((Map.Entry)this.field_53.previous()).getKey()).shortValue();
    }
    
    public boolean hasNext()
    {
      return this.field_53.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_53.hasPrevious();
    }
  }
  
  protected class KeySet
    extends AbstractShortSortedSet
  {
    protected KeySet() {}
    
    public boolean contains(short local_k)
    {
      return AbstractShort2ObjectSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractShort2ObjectSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractShort2ObjectSortedMap.this.clear();
    }
    
    public ShortComparator comparator()
    {
      return AbstractShort2ObjectSortedMap.this.comparator();
    }
    
    public short firstShort()
    {
      return AbstractShort2ObjectSortedMap.this.firstShortKey();
    }
    
    public short lastShort()
    {
      return AbstractShort2ObjectSortedMap.this.lastShortKey();
    }
    
    public ShortSortedSet headSet(short local_to)
    {
      return AbstractShort2ObjectSortedMap.this.headMap(local_to).keySet();
    }
    
    public ShortSortedSet tailSet(short from)
    {
      return AbstractShort2ObjectSortedMap.this.tailMap(from).keySet();
    }
    
    public ShortSortedSet subSet(short from, short local_to)
    {
      return AbstractShort2ObjectSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public ShortBidirectionalIterator iterator(short from)
    {
      return new AbstractShort2ObjectSortedMap.KeySetIterator(AbstractShort2ObjectSortedMap.this.entrySet().iterator(new AbstractShort2ObjectMap.BasicEntry(from, null)));
    }
    
    public ShortBidirectionalIterator iterator()
    {
      return new AbstractShort2ObjectSortedMap.KeySetIterator(AbstractShort2ObjectSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2ObjectSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
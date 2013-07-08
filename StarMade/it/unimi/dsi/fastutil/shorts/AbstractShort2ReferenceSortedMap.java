package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
import it.unimi.dsi.fastutil.objects.AbstractReferenceCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import java.util.Map.Entry;

public abstract class AbstractShort2ReferenceSortedMap<V>
  extends AbstractShort2ReferenceMap<V>
  implements Short2ReferenceSortedMap<V>
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Short2ReferenceSortedMap<V> headMap(Short local_to)
  {
    return headMap(local_to.shortValue());
  }
  
  public Short2ReferenceSortedMap<V> tailMap(Short from)
  {
    return tailMap(from.shortValue());
  }
  
  public Short2ReferenceSortedMap<V> subMap(Short from, Short local_to)
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
  
  public ReferenceCollection<V> values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Short, V>> entrySet()
  {
    return short2ReferenceEntrySet();
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
    extends AbstractReferenceCollection<V>
  {
    protected ValuesCollection() {}
    
    public ObjectIterator<V> iterator()
    {
      return new AbstractShort2ReferenceSortedMap.ValuesIterator(AbstractShort2ReferenceSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(Object local_k)
    {
      return AbstractShort2ReferenceSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractShort2ReferenceSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractShort2ReferenceSortedMap.this.clear();
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
      return AbstractShort2ReferenceSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractShort2ReferenceSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractShort2ReferenceSortedMap.this.clear();
    }
    
    public ShortComparator comparator()
    {
      return AbstractShort2ReferenceSortedMap.this.comparator();
    }
    
    public short firstShort()
    {
      return AbstractShort2ReferenceSortedMap.this.firstShortKey();
    }
    
    public short lastShort()
    {
      return AbstractShort2ReferenceSortedMap.this.lastShortKey();
    }
    
    public ShortSortedSet headSet(short local_to)
    {
      return AbstractShort2ReferenceSortedMap.this.headMap(local_to).keySet();
    }
    
    public ShortSortedSet tailSet(short from)
    {
      return AbstractShort2ReferenceSortedMap.this.tailMap(from).keySet();
    }
    
    public ShortSortedSet subSet(short from, short local_to)
    {
      return AbstractShort2ReferenceSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public ShortBidirectionalIterator iterator(short from)
    {
      return new AbstractShort2ReferenceSortedMap.KeySetIterator(AbstractShort2ReferenceSortedMap.this.entrySet().iterator(new AbstractShort2ReferenceMap.BasicEntry(from, null)));
    }
    
    public ShortBidirectionalIterator iterator()
    {
      return new AbstractShort2ReferenceSortedMap.KeySetIterator(AbstractShort2ReferenceSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2ReferenceSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
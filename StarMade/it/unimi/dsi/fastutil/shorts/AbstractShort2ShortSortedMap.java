package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractShort2ShortSortedMap
  extends AbstractShort2ShortMap
  implements Short2ShortSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Short2ShortSortedMap headMap(Short local_to)
  {
    return headMap(local_to.shortValue());
  }
  
  public Short2ShortSortedMap tailMap(Short from)
  {
    return tailMap(from.shortValue());
  }
  
  public Short2ShortSortedMap subMap(Short from, Short local_to)
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
  
  public ShortCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Short, Short>> entrySet()
  {
    return short2ShortEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractShortIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Short, Short>> field_53;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Short, Short>> local_i)
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
      return new AbstractShort2ShortSortedMap.ValuesIterator(AbstractShort2ShortSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(short local_k)
    {
      return AbstractShort2ShortSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractShort2ShortSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractShort2ShortSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractShortBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Short, Short>> field_53;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Short, Short>> local_i)
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
      return AbstractShort2ShortSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractShort2ShortSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractShort2ShortSortedMap.this.clear();
    }
    
    public ShortComparator comparator()
    {
      return AbstractShort2ShortSortedMap.this.comparator();
    }
    
    public short firstShort()
    {
      return AbstractShort2ShortSortedMap.this.firstShortKey();
    }
    
    public short lastShort()
    {
      return AbstractShort2ShortSortedMap.this.lastShortKey();
    }
    
    public ShortSortedSet headSet(short local_to)
    {
      return AbstractShort2ShortSortedMap.this.headMap(local_to).keySet();
    }
    
    public ShortSortedSet tailSet(short from)
    {
      return AbstractShort2ShortSortedMap.this.tailMap(from).keySet();
    }
    
    public ShortSortedSet subSet(short from, short local_to)
    {
      return AbstractShort2ShortSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public ShortBidirectionalIterator iterator(short from)
    {
      return new AbstractShort2ShortSortedMap.KeySetIterator(AbstractShort2ShortSortedMap.this.entrySet().iterator(new AbstractShort2ShortMap.BasicEntry(from, (short)0)));
    }
    
    public ShortBidirectionalIterator iterator()
    {
      return new AbstractShort2ShortSortedMap.KeySetIterator(AbstractShort2ShortSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2ShortSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
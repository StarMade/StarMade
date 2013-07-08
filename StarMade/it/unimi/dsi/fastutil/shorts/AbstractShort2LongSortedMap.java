package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.longs.AbstractLongCollection;
import it.unimi.dsi.fastutil.longs.AbstractLongIterator;
import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractShort2LongSortedMap
  extends AbstractShort2LongMap
  implements Short2LongSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Short2LongSortedMap headMap(Short local_to)
  {
    return headMap(local_to.shortValue());
  }
  
  public Short2LongSortedMap tailMap(Short from)
  {
    return tailMap(from.shortValue());
  }
  
  public Short2LongSortedMap subMap(Short from, Short local_to)
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
  
  public LongCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Short, Long>> entrySet()
  {
    return short2LongEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractLongIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Short, Long>> field_1;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Short, Long>> local_i)
    {
      this.field_1 = local_i;
    }
    
    public long nextLong()
    {
      return ((Long)((Map.Entry)this.field_1.next()).getValue()).longValue();
    }
    
    public boolean hasNext()
    {
      return this.field_1.hasNext();
    }
  }
  
  protected class ValuesCollection
    extends AbstractLongCollection
  {
    protected ValuesCollection() {}
    
    public LongIterator iterator()
    {
      return new AbstractShort2LongSortedMap.ValuesIterator(AbstractShort2LongSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(long local_k)
    {
      return AbstractShort2LongSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractShort2LongSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractShort2LongSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractShortBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Short, Long>> field_53;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Short, Long>> local_i)
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
      return AbstractShort2LongSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractShort2LongSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractShort2LongSortedMap.this.clear();
    }
    
    public ShortComparator comparator()
    {
      return AbstractShort2LongSortedMap.this.comparator();
    }
    
    public short firstShort()
    {
      return AbstractShort2LongSortedMap.this.firstShortKey();
    }
    
    public short lastShort()
    {
      return AbstractShort2LongSortedMap.this.lastShortKey();
    }
    
    public ShortSortedSet headSet(short local_to)
    {
      return AbstractShort2LongSortedMap.this.headMap(local_to).keySet();
    }
    
    public ShortSortedSet tailSet(short from)
    {
      return AbstractShort2LongSortedMap.this.tailMap(from).keySet();
    }
    
    public ShortSortedSet subSet(short from, short local_to)
    {
      return AbstractShort2LongSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public ShortBidirectionalIterator iterator(short from)
    {
      return new AbstractShort2LongSortedMap.KeySetIterator(AbstractShort2LongSortedMap.this.entrySet().iterator(new AbstractShort2LongMap.BasicEntry(from, 0L)));
    }
    
    public ShortBidirectionalIterator iterator()
    {
      return new AbstractShort2LongSortedMap.KeySetIterator(AbstractShort2LongSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2LongSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
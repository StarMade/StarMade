package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection;
import it.unimi.dsi.fastutil.booleans.AbstractBooleanIterator;
import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.booleans.BooleanIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractShort2BooleanSortedMap
  extends AbstractShort2BooleanMap
  implements Short2BooleanSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Short2BooleanSortedMap headMap(Short local_to)
  {
    return headMap(local_to.shortValue());
  }
  
  public Short2BooleanSortedMap tailMap(Short from)
  {
    return tailMap(from.shortValue());
  }
  
  public Short2BooleanSortedMap subMap(Short from, Short local_to)
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
  
  public BooleanCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Short, Boolean>> entrySet()
  {
    return short2BooleanEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractBooleanIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Short, Boolean>> field_60;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Short, Boolean>> local_i)
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
      return new AbstractShort2BooleanSortedMap.ValuesIterator(AbstractShort2BooleanSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(boolean local_k)
    {
      return AbstractShort2BooleanSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractShort2BooleanSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractShort2BooleanSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractShortBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Short, Boolean>> field_53;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Short, Boolean>> local_i)
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
      return AbstractShort2BooleanSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractShort2BooleanSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractShort2BooleanSortedMap.this.clear();
    }
    
    public ShortComparator comparator()
    {
      return AbstractShort2BooleanSortedMap.this.comparator();
    }
    
    public short firstShort()
    {
      return AbstractShort2BooleanSortedMap.this.firstShortKey();
    }
    
    public short lastShort()
    {
      return AbstractShort2BooleanSortedMap.this.lastShortKey();
    }
    
    public ShortSortedSet headSet(short local_to)
    {
      return AbstractShort2BooleanSortedMap.this.headMap(local_to).keySet();
    }
    
    public ShortSortedSet tailSet(short from)
    {
      return AbstractShort2BooleanSortedMap.this.tailMap(from).keySet();
    }
    
    public ShortSortedSet subSet(short from, short local_to)
    {
      return AbstractShort2BooleanSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public ShortBidirectionalIterator iterator(short from)
    {
      return new AbstractShort2BooleanSortedMap.KeySetIterator(AbstractShort2BooleanSortedMap.this.entrySet().iterator(new AbstractShort2BooleanMap.BasicEntry(from, false)));
    }
    
    public ShortBidirectionalIterator iterator()
    {
      return new AbstractShort2BooleanSortedMap.KeySetIterator(AbstractShort2BooleanSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2BooleanSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
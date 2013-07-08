package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.floats.AbstractFloatCollection;
import it.unimi.dsi.fastutil.floats.AbstractFloatIterator;
import it.unimi.dsi.fastutil.floats.FloatCollection;
import it.unimi.dsi.fastutil.floats.FloatIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractShort2FloatSortedMap
  extends AbstractShort2FloatMap
  implements Short2FloatSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Short2FloatSortedMap headMap(Short local_to)
  {
    return headMap(local_to.shortValue());
  }
  
  public Short2FloatSortedMap tailMap(Short from)
  {
    return tailMap(from.shortValue());
  }
  
  public Short2FloatSortedMap subMap(Short from, Short local_to)
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
  
  public FloatCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Short, Float>> entrySet()
  {
    return short2FloatEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractFloatIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Short, Float>> field_52;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Short, Float>> local_i)
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
      return new AbstractShort2FloatSortedMap.ValuesIterator(AbstractShort2FloatSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(float local_k)
    {
      return AbstractShort2FloatSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractShort2FloatSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractShort2FloatSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractShortBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Short, Float>> field_53;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Short, Float>> local_i)
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
      return AbstractShort2FloatSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractShort2FloatSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractShort2FloatSortedMap.this.clear();
    }
    
    public ShortComparator comparator()
    {
      return AbstractShort2FloatSortedMap.this.comparator();
    }
    
    public short firstShort()
    {
      return AbstractShort2FloatSortedMap.this.firstShortKey();
    }
    
    public short lastShort()
    {
      return AbstractShort2FloatSortedMap.this.lastShortKey();
    }
    
    public ShortSortedSet headSet(short local_to)
    {
      return AbstractShort2FloatSortedMap.this.headMap(local_to).keySet();
    }
    
    public ShortSortedSet tailSet(short from)
    {
      return AbstractShort2FloatSortedMap.this.tailMap(from).keySet();
    }
    
    public ShortSortedSet subSet(short from, short local_to)
    {
      return AbstractShort2FloatSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public ShortBidirectionalIterator iterator(short from)
    {
      return new AbstractShort2FloatSortedMap.KeySetIterator(AbstractShort2FloatSortedMap.this.entrySet().iterator(new AbstractShort2FloatMap.BasicEntry(from, 0.0F)));
    }
    
    public ShortBidirectionalIterator iterator()
    {
      return new AbstractShort2FloatSortedMap.KeySetIterator(AbstractShort2FloatSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2FloatSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
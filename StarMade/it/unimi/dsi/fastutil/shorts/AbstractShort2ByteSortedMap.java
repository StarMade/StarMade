package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.bytes.AbstractByteCollection;
import it.unimi.dsi.fastutil.bytes.AbstractByteIterator;
import it.unimi.dsi.fastutil.bytes.ByteCollection;
import it.unimi.dsi.fastutil.bytes.ByteIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractShort2ByteSortedMap
  extends AbstractShort2ByteMap
  implements Short2ByteSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Short2ByteSortedMap headMap(Short local_to)
  {
    return headMap(local_to.shortValue());
  }
  
  public Short2ByteSortedMap tailMap(Short from)
  {
    return tailMap(from.shortValue());
  }
  
  public Short2ByteSortedMap subMap(Short from, Short local_to)
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
  
  public ByteCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Short, Byte>> entrySet()
  {
    return short2ByteEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractByteIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Short, Byte>> field_58;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Short, Byte>> local_i)
    {
      this.field_58 = local_i;
    }
    
    public byte nextByte()
    {
      return ((Byte)((Map.Entry)this.field_58.next()).getValue()).byteValue();
    }
    
    public boolean hasNext()
    {
      return this.field_58.hasNext();
    }
  }
  
  protected class ValuesCollection
    extends AbstractByteCollection
  {
    protected ValuesCollection() {}
    
    public ByteIterator iterator()
    {
      return new AbstractShort2ByteSortedMap.ValuesIterator(AbstractShort2ByteSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(byte local_k)
    {
      return AbstractShort2ByteSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractShort2ByteSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractShort2ByteSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractShortBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Short, Byte>> field_53;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Short, Byte>> local_i)
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
      return AbstractShort2ByteSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractShort2ByteSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractShort2ByteSortedMap.this.clear();
    }
    
    public ShortComparator comparator()
    {
      return AbstractShort2ByteSortedMap.this.comparator();
    }
    
    public short firstShort()
    {
      return AbstractShort2ByteSortedMap.this.firstShortKey();
    }
    
    public short lastShort()
    {
      return AbstractShort2ByteSortedMap.this.lastShortKey();
    }
    
    public ShortSortedSet headSet(short local_to)
    {
      return AbstractShort2ByteSortedMap.this.headMap(local_to).keySet();
    }
    
    public ShortSortedSet tailSet(short from)
    {
      return AbstractShort2ByteSortedMap.this.tailMap(from).keySet();
    }
    
    public ShortSortedSet subSet(short from, short local_to)
    {
      return AbstractShort2ByteSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public ShortBidirectionalIterator iterator(short from)
    {
      return new AbstractShort2ByteSortedMap.KeySetIterator(AbstractShort2ByteSortedMap.this.entrySet().iterator(new AbstractShort2ByteMap.BasicEntry(from, (byte)0)));
    }
    
    public ShortBidirectionalIterator iterator()
    {
      return new AbstractShort2ByteSortedMap.KeySetIterator(AbstractShort2ByteSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2ByteSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
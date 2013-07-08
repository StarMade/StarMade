package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection;
import it.unimi.dsi.fastutil.booleans.AbstractBooleanIterator;
import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.booleans.BooleanIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractByte2BooleanSortedMap
  extends AbstractByte2BooleanMap
  implements Byte2BooleanSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Byte2BooleanSortedMap headMap(Byte local_to)
  {
    return headMap(local_to.byteValue());
  }
  
  public Byte2BooleanSortedMap tailMap(Byte from)
  {
    return tailMap(from.byteValue());
  }
  
  public Byte2BooleanSortedMap subMap(Byte from, Byte local_to)
  {
    return subMap(from.byteValue(), local_to.byteValue());
  }
  
  public Byte firstKey()
  {
    return Byte.valueOf(firstByteKey());
  }
  
  public Byte lastKey()
  {
    return Byte.valueOf(lastByteKey());
  }
  
  public ByteSortedSet keySet()
  {
    return new KeySet();
  }
  
  public BooleanCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Byte, Boolean>> entrySet()
  {
    return byte2BooleanEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractBooleanIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Byte, Boolean>> field_60;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Boolean>> local_i)
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
      return new AbstractByte2BooleanSortedMap.ValuesIterator(AbstractByte2BooleanSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(boolean local_k)
    {
      return AbstractByte2BooleanSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractByte2BooleanSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractByte2BooleanSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractByteBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Byte, Boolean>> field_58;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Boolean>> local_i)
    {
      this.field_58 = local_i;
    }
    
    public byte nextByte()
    {
      return ((Byte)((Map.Entry)this.field_58.next()).getKey()).byteValue();
    }
    
    public byte previousByte()
    {
      return ((Byte)((Map.Entry)this.field_58.previous()).getKey()).byteValue();
    }
    
    public boolean hasNext()
    {
      return this.field_58.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_58.hasPrevious();
    }
  }
  
  protected class KeySet
    extends AbstractByteSortedSet
  {
    protected KeySet() {}
    
    public boolean contains(byte local_k)
    {
      return AbstractByte2BooleanSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractByte2BooleanSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractByte2BooleanSortedMap.this.clear();
    }
    
    public ByteComparator comparator()
    {
      return AbstractByte2BooleanSortedMap.this.comparator();
    }
    
    public byte firstByte()
    {
      return AbstractByte2BooleanSortedMap.this.firstByteKey();
    }
    
    public byte lastByte()
    {
      return AbstractByte2BooleanSortedMap.this.lastByteKey();
    }
    
    public ByteSortedSet headSet(byte local_to)
    {
      return AbstractByte2BooleanSortedMap.this.headMap(local_to).keySet();
    }
    
    public ByteSortedSet tailSet(byte from)
    {
      return AbstractByte2BooleanSortedMap.this.tailMap(from).keySet();
    }
    
    public ByteSortedSet subSet(byte from, byte local_to)
    {
      return AbstractByte2BooleanSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public ByteBidirectionalIterator iterator(byte from)
    {
      return new AbstractByte2BooleanSortedMap.KeySetIterator(AbstractByte2BooleanSortedMap.this.entrySet().iterator(new AbstractByte2BooleanMap.BasicEntry(from, false)));
    }
    
    public ByteBidirectionalIterator iterator()
    {
      return new AbstractByte2BooleanSortedMap.KeySetIterator(AbstractByte2BooleanSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByte2BooleanSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
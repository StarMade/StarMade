package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.longs.AbstractLongCollection;
import it.unimi.dsi.fastutil.longs.AbstractLongIterator;
import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractByte2LongSortedMap
  extends AbstractByte2LongMap
  implements Byte2LongSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Byte2LongSortedMap headMap(Byte local_to)
  {
    return headMap(local_to.byteValue());
  }
  
  public Byte2LongSortedMap tailMap(Byte from)
  {
    return tailMap(from.byteValue());
  }
  
  public Byte2LongSortedMap subMap(Byte from, Byte local_to)
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
  
  public LongCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Byte, Long>> entrySet()
  {
    return byte2LongEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractLongIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Byte, Long>> field_1;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Long>> local_i)
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
      return new AbstractByte2LongSortedMap.ValuesIterator(AbstractByte2LongSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(long local_k)
    {
      return AbstractByte2LongSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractByte2LongSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractByte2LongSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractByteBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Byte, Long>> field_58;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Long>> local_i)
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
      return AbstractByte2LongSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractByte2LongSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractByte2LongSortedMap.this.clear();
    }
    
    public ByteComparator comparator()
    {
      return AbstractByte2LongSortedMap.this.comparator();
    }
    
    public byte firstByte()
    {
      return AbstractByte2LongSortedMap.this.firstByteKey();
    }
    
    public byte lastByte()
    {
      return AbstractByte2LongSortedMap.this.lastByteKey();
    }
    
    public ByteSortedSet headSet(byte local_to)
    {
      return AbstractByte2LongSortedMap.this.headMap(local_to).keySet();
    }
    
    public ByteSortedSet tailSet(byte from)
    {
      return AbstractByte2LongSortedMap.this.tailMap(from).keySet();
    }
    
    public ByteSortedSet subSet(byte from, byte local_to)
    {
      return AbstractByte2LongSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public ByteBidirectionalIterator iterator(byte from)
    {
      return new AbstractByte2LongSortedMap.KeySetIterator(AbstractByte2LongSortedMap.this.entrySet().iterator(new AbstractByte2LongMap.BasicEntry(from, 0L)));
    }
    
    public ByteBidirectionalIterator iterator()
    {
      return new AbstractByte2LongSortedMap.KeySetIterator(AbstractByte2LongSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByte2LongSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.ints.AbstractIntCollection;
import it.unimi.dsi.fastutil.ints.AbstractIntIterator;
import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractByte2IntSortedMap
  extends AbstractByte2IntMap
  implements Byte2IntSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Byte2IntSortedMap headMap(Byte local_to)
  {
    return headMap(local_to.byteValue());
  }
  
  public Byte2IntSortedMap tailMap(Byte from)
  {
    return tailMap(from.byteValue());
  }
  
  public Byte2IntSortedMap subMap(Byte from, Byte local_to)
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
  
  public IntCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Byte, Integer>> entrySet()
  {
    return byte2IntEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractIntIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Byte, Integer>> field_70;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Integer>> local_i)
    {
      this.field_70 = local_i;
    }
    
    public int nextInt()
    {
      return ((Integer)((Map.Entry)this.field_70.next()).getValue()).intValue();
    }
    
    public boolean hasNext()
    {
      return this.field_70.hasNext();
    }
  }
  
  protected class ValuesCollection
    extends AbstractIntCollection
  {
    protected ValuesCollection() {}
    
    public IntIterator iterator()
    {
      return new AbstractByte2IntSortedMap.ValuesIterator(AbstractByte2IntSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(int local_k)
    {
      return AbstractByte2IntSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractByte2IntSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractByte2IntSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractByteBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Byte, Integer>> field_58;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Integer>> local_i)
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
      return AbstractByte2IntSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractByte2IntSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractByte2IntSortedMap.this.clear();
    }
    
    public ByteComparator comparator()
    {
      return AbstractByte2IntSortedMap.this.comparator();
    }
    
    public byte firstByte()
    {
      return AbstractByte2IntSortedMap.this.firstByteKey();
    }
    
    public byte lastByte()
    {
      return AbstractByte2IntSortedMap.this.lastByteKey();
    }
    
    public ByteSortedSet headSet(byte local_to)
    {
      return AbstractByte2IntSortedMap.this.headMap(local_to).keySet();
    }
    
    public ByteSortedSet tailSet(byte from)
    {
      return AbstractByte2IntSortedMap.this.tailMap(from).keySet();
    }
    
    public ByteSortedSet subSet(byte from, byte local_to)
    {
      return AbstractByte2IntSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public ByteBidirectionalIterator iterator(byte from)
    {
      return new AbstractByte2IntSortedMap.KeySetIterator(AbstractByte2IntSortedMap.this.entrySet().iterator(new AbstractByte2IntMap.BasicEntry(from, 0)));
    }
    
    public ByteBidirectionalIterator iterator()
    {
      return new AbstractByte2IntSortedMap.KeySetIterator(AbstractByte2IntSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByte2IntSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
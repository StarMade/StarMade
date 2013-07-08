package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractByte2ByteSortedMap
  extends AbstractByte2ByteMap
  implements Byte2ByteSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Byte2ByteSortedMap headMap(Byte local_to)
  {
    return headMap(local_to.byteValue());
  }
  
  public Byte2ByteSortedMap tailMap(Byte from)
  {
    return tailMap(from.byteValue());
  }
  
  public Byte2ByteSortedMap subMap(Byte from, Byte local_to)
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
  
  public ByteCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Byte, Byte>> entrySet()
  {
    return byte2ByteEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractByteIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Byte, Byte>> field_58;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Byte>> local_i)
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
      return new AbstractByte2ByteSortedMap.ValuesIterator(AbstractByte2ByteSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(byte local_k)
    {
      return AbstractByte2ByteSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractByte2ByteSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractByte2ByteSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractByteBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Byte, Byte>> field_58;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Byte>> local_i)
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
      return AbstractByte2ByteSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractByte2ByteSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractByte2ByteSortedMap.this.clear();
    }
    
    public ByteComparator comparator()
    {
      return AbstractByte2ByteSortedMap.this.comparator();
    }
    
    public byte firstByte()
    {
      return AbstractByte2ByteSortedMap.this.firstByteKey();
    }
    
    public byte lastByte()
    {
      return AbstractByte2ByteSortedMap.this.lastByteKey();
    }
    
    public ByteSortedSet headSet(byte local_to)
    {
      return AbstractByte2ByteSortedMap.this.headMap(local_to).keySet();
    }
    
    public ByteSortedSet tailSet(byte from)
    {
      return AbstractByte2ByteSortedMap.this.tailMap(from).keySet();
    }
    
    public ByteSortedSet subSet(byte from, byte local_to)
    {
      return AbstractByte2ByteSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public ByteBidirectionalIterator iterator(byte from)
    {
      return new AbstractByte2ByteSortedMap.KeySetIterator(AbstractByte2ByteSortedMap.this.entrySet().iterator(new AbstractByte2ByteMap.BasicEntry(from, (byte)0)));
    }
    
    public ByteBidirectionalIterator iterator()
    {
      return new AbstractByte2ByteSortedMap.KeySetIterator(AbstractByte2ByteSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByte2ByteSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
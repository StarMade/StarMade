package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.chars.AbstractCharCollection;
import it.unimi.dsi.fastutil.chars.AbstractCharIterator;
import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.chars.CharIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractByte2CharSortedMap
  extends AbstractByte2CharMap
  implements Byte2CharSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Byte2CharSortedMap headMap(Byte local_to)
  {
    return headMap(local_to.byteValue());
  }
  
  public Byte2CharSortedMap tailMap(Byte from)
  {
    return tailMap(from.byteValue());
  }
  
  public Byte2CharSortedMap subMap(Byte from, Byte local_to)
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
  
  public CharCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Byte, Character>> entrySet()
  {
    return byte2CharEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractCharIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Byte, Character>> field_67;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Character>> local_i)
    {
      this.field_67 = local_i;
    }
    
    public char nextChar()
    {
      return ((Character)((Map.Entry)this.field_67.next()).getValue()).charValue();
    }
    
    public boolean hasNext()
    {
      return this.field_67.hasNext();
    }
  }
  
  protected class ValuesCollection
    extends AbstractCharCollection
  {
    protected ValuesCollection() {}
    
    public CharIterator iterator()
    {
      return new AbstractByte2CharSortedMap.ValuesIterator(AbstractByte2CharSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(char local_k)
    {
      return AbstractByte2CharSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractByte2CharSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractByte2CharSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractByteBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Byte, Character>> field_58;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Character>> local_i)
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
      return AbstractByte2CharSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractByte2CharSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractByte2CharSortedMap.this.clear();
    }
    
    public ByteComparator comparator()
    {
      return AbstractByte2CharSortedMap.this.comparator();
    }
    
    public byte firstByte()
    {
      return AbstractByte2CharSortedMap.this.firstByteKey();
    }
    
    public byte lastByte()
    {
      return AbstractByte2CharSortedMap.this.lastByteKey();
    }
    
    public ByteSortedSet headSet(byte local_to)
    {
      return AbstractByte2CharSortedMap.this.headMap(local_to).keySet();
    }
    
    public ByteSortedSet tailSet(byte from)
    {
      return AbstractByte2CharSortedMap.this.tailMap(from).keySet();
    }
    
    public ByteSortedSet subSet(byte from, byte local_to)
    {
      return AbstractByte2CharSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public ByteBidirectionalIterator iterator(byte from)
    {
      return new AbstractByte2CharSortedMap.KeySetIterator(AbstractByte2CharSortedMap.this.entrySet().iterator(new AbstractByte2CharMap.BasicEntry(from, '\000')));
    }
    
    public ByteBidirectionalIterator iterator()
    {
      return new AbstractByte2CharSortedMap.KeySetIterator(AbstractByte2CharSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByte2CharSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
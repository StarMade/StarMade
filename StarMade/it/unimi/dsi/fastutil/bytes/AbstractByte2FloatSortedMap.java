package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.floats.AbstractFloatCollection;
import it.unimi.dsi.fastutil.floats.AbstractFloatIterator;
import it.unimi.dsi.fastutil.floats.FloatCollection;
import it.unimi.dsi.fastutil.floats.FloatIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractByte2FloatSortedMap
  extends AbstractByte2FloatMap
  implements Byte2FloatSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Byte2FloatSortedMap headMap(Byte local_to)
  {
    return headMap(local_to.byteValue());
  }
  
  public Byte2FloatSortedMap tailMap(Byte from)
  {
    return tailMap(from.byteValue());
  }
  
  public Byte2FloatSortedMap subMap(Byte from, Byte local_to)
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
  
  public FloatCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Byte, Float>> entrySet()
  {
    return byte2FloatEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractFloatIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Byte, Float>> field_52;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Float>> local_i)
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
      return new AbstractByte2FloatSortedMap.ValuesIterator(AbstractByte2FloatSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(float local_k)
    {
      return AbstractByte2FloatSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractByte2FloatSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractByte2FloatSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractByteBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Byte, Float>> field_58;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Float>> local_i)
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
      return AbstractByte2FloatSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractByte2FloatSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractByte2FloatSortedMap.this.clear();
    }
    
    public ByteComparator comparator()
    {
      return AbstractByte2FloatSortedMap.this.comparator();
    }
    
    public byte firstByte()
    {
      return AbstractByte2FloatSortedMap.this.firstByteKey();
    }
    
    public byte lastByte()
    {
      return AbstractByte2FloatSortedMap.this.lastByteKey();
    }
    
    public ByteSortedSet headSet(byte local_to)
    {
      return AbstractByte2FloatSortedMap.this.headMap(local_to).keySet();
    }
    
    public ByteSortedSet tailSet(byte from)
    {
      return AbstractByte2FloatSortedMap.this.tailMap(from).keySet();
    }
    
    public ByteSortedSet subSet(byte from, byte local_to)
    {
      return AbstractByte2FloatSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public ByteBidirectionalIterator iterator(byte from)
    {
      return new AbstractByte2FloatSortedMap.KeySetIterator(AbstractByte2FloatSortedMap.this.entrySet().iterator(new AbstractByte2FloatMap.BasicEntry(from, 0.0F)));
    }
    
    public ByteBidirectionalIterator iterator()
    {
      return new AbstractByte2FloatSortedMap.KeySetIterator(AbstractByte2FloatSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByte2FloatSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
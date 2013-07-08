package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection;
import it.unimi.dsi.fastutil.doubles.AbstractDoubleIterator;
import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.doubles.DoubleIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractByte2DoubleSortedMap
  extends AbstractByte2DoubleMap
  implements Byte2DoubleSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Byte2DoubleSortedMap headMap(Byte local_to)
  {
    return headMap(local_to.byteValue());
  }
  
  public Byte2DoubleSortedMap tailMap(Byte from)
  {
    return tailMap(from.byteValue());
  }
  
  public Byte2DoubleSortedMap subMap(Byte from, Byte local_to)
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
  
  public DoubleCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Byte, Double>> entrySet()
  {
    return byte2DoubleEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractDoubleIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Byte, Double>> field_68;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Double>> local_i)
    {
      this.field_68 = local_i;
    }
    
    public double nextDouble()
    {
      return ((Double)((Map.Entry)this.field_68.next()).getValue()).doubleValue();
    }
    
    public boolean hasNext()
    {
      return this.field_68.hasNext();
    }
  }
  
  protected class ValuesCollection
    extends AbstractDoubleCollection
  {
    protected ValuesCollection() {}
    
    public DoubleIterator iterator()
    {
      return new AbstractByte2DoubleSortedMap.ValuesIterator(AbstractByte2DoubleSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(double local_k)
    {
      return AbstractByte2DoubleSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractByte2DoubleSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractByte2DoubleSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractByteBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Byte, Double>> field_58;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Double>> local_i)
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
      return AbstractByte2DoubleSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractByte2DoubleSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractByte2DoubleSortedMap.this.clear();
    }
    
    public ByteComparator comparator()
    {
      return AbstractByte2DoubleSortedMap.this.comparator();
    }
    
    public byte firstByte()
    {
      return AbstractByte2DoubleSortedMap.this.firstByteKey();
    }
    
    public byte lastByte()
    {
      return AbstractByte2DoubleSortedMap.this.lastByteKey();
    }
    
    public ByteSortedSet headSet(byte local_to)
    {
      return AbstractByte2DoubleSortedMap.this.headMap(local_to).keySet();
    }
    
    public ByteSortedSet tailSet(byte from)
    {
      return AbstractByte2DoubleSortedMap.this.tailMap(from).keySet();
    }
    
    public ByteSortedSet subSet(byte from, byte local_to)
    {
      return AbstractByte2DoubleSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public ByteBidirectionalIterator iterator(byte from)
    {
      return new AbstractByte2DoubleSortedMap.KeySetIterator(AbstractByte2DoubleSortedMap.this.entrySet().iterator(new AbstractByte2DoubleMap.BasicEntry(from, 0.0D)));
    }
    
    public ByteBidirectionalIterator iterator()
    {
      return new AbstractByte2DoubleSortedMap.KeySetIterator(AbstractByte2DoubleSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByte2DoubleSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
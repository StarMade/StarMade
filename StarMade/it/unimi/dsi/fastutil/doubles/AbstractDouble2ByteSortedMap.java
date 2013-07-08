package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.bytes.AbstractByteCollection;
import it.unimi.dsi.fastutil.bytes.AbstractByteIterator;
import it.unimi.dsi.fastutil.bytes.ByteCollection;
import it.unimi.dsi.fastutil.bytes.ByteIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractDouble2ByteSortedMap
  extends AbstractDouble2ByteMap
  implements Double2ByteSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Double2ByteSortedMap headMap(Double local_to)
  {
    return headMap(local_to.doubleValue());
  }
  
  public Double2ByteSortedMap tailMap(Double from)
  {
    return tailMap(from.doubleValue());
  }
  
  public Double2ByteSortedMap subMap(Double from, Double local_to)
  {
    return subMap(from.doubleValue(), local_to.doubleValue());
  }
  
  public Double firstKey()
  {
    return Double.valueOf(firstDoubleKey());
  }
  
  public Double lastKey()
  {
    return Double.valueOf(lastDoubleKey());
  }
  
  public DoubleSortedSet keySet()
  {
    return new KeySet();
  }
  
  public ByteCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Double, Byte>> entrySet()
  {
    return double2ByteEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractByteIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Double, Byte>> field_58;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Double, Byte>> local_i)
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
      return new AbstractDouble2ByteSortedMap.ValuesIterator(AbstractDouble2ByteSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(byte local_k)
    {
      return AbstractDouble2ByteSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractDouble2ByteSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractDouble2ByteSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractDoubleBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Double, Byte>> field_68;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Double, Byte>> local_i)
    {
      this.field_68 = local_i;
    }
    
    public double nextDouble()
    {
      return ((Double)((Map.Entry)this.field_68.next()).getKey()).doubleValue();
    }
    
    public double previousDouble()
    {
      return ((Double)((Map.Entry)this.field_68.previous()).getKey()).doubleValue();
    }
    
    public boolean hasNext()
    {
      return this.field_68.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_68.hasPrevious();
    }
  }
  
  protected class KeySet
    extends AbstractDoubleSortedSet
  {
    protected KeySet() {}
    
    public boolean contains(double local_k)
    {
      return AbstractDouble2ByteSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractDouble2ByteSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractDouble2ByteSortedMap.this.clear();
    }
    
    public DoubleComparator comparator()
    {
      return AbstractDouble2ByteSortedMap.this.comparator();
    }
    
    public double firstDouble()
    {
      return AbstractDouble2ByteSortedMap.this.firstDoubleKey();
    }
    
    public double lastDouble()
    {
      return AbstractDouble2ByteSortedMap.this.lastDoubleKey();
    }
    
    public DoubleSortedSet headSet(double local_to)
    {
      return AbstractDouble2ByteSortedMap.this.headMap(local_to).keySet();
    }
    
    public DoubleSortedSet tailSet(double from)
    {
      return AbstractDouble2ByteSortedMap.this.tailMap(from).keySet();
    }
    
    public DoubleSortedSet subSet(double from, double local_to)
    {
      return AbstractDouble2ByteSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public DoubleBidirectionalIterator iterator(double from)
    {
      return new AbstractDouble2ByteSortedMap.KeySetIterator(AbstractDouble2ByteSortedMap.this.entrySet().iterator(new AbstractDouble2ByteMap.BasicEntry(from, (byte)0)));
    }
    
    public DoubleBidirectionalIterator iterator()
    {
      return new AbstractDouble2ByteSortedMap.KeySetIterator(AbstractDouble2ByteSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2ByteSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
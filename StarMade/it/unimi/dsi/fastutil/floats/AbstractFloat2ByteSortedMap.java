package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.bytes.AbstractByteCollection;
import it.unimi.dsi.fastutil.bytes.AbstractByteIterator;
import it.unimi.dsi.fastutil.bytes.ByteCollection;
import it.unimi.dsi.fastutil.bytes.ByteIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractFloat2ByteSortedMap
  extends AbstractFloat2ByteMap
  implements Float2ByteSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Float2ByteSortedMap headMap(Float local_to)
  {
    return headMap(local_to.floatValue());
  }
  
  public Float2ByteSortedMap tailMap(Float from)
  {
    return tailMap(from.floatValue());
  }
  
  public Float2ByteSortedMap subMap(Float from, Float local_to)
  {
    return subMap(from.floatValue(), local_to.floatValue());
  }
  
  public Float firstKey()
  {
    return Float.valueOf(firstFloatKey());
  }
  
  public Float lastKey()
  {
    return Float.valueOf(lastFloatKey());
  }
  
  public FloatSortedSet keySet()
  {
    return new KeySet();
  }
  
  public ByteCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Float, Byte>> entrySet()
  {
    return float2ByteEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractByteIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Float, Byte>> field_58;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Float, Byte>> local_i)
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
      return new AbstractFloat2ByteSortedMap.ValuesIterator(AbstractFloat2ByteSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(byte local_k)
    {
      return AbstractFloat2ByteSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractFloat2ByteSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractFloat2ByteSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractFloatBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Float, Byte>> field_52;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Float, Byte>> local_i)
    {
      this.field_52 = local_i;
    }
    
    public float nextFloat()
    {
      return ((Float)((Map.Entry)this.field_52.next()).getKey()).floatValue();
    }
    
    public float previousFloat()
    {
      return ((Float)((Map.Entry)this.field_52.previous()).getKey()).floatValue();
    }
    
    public boolean hasNext()
    {
      return this.field_52.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_52.hasPrevious();
    }
  }
  
  protected class KeySet
    extends AbstractFloatSortedSet
  {
    protected KeySet() {}
    
    public boolean contains(float local_k)
    {
      return AbstractFloat2ByteSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractFloat2ByteSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractFloat2ByteSortedMap.this.clear();
    }
    
    public FloatComparator comparator()
    {
      return AbstractFloat2ByteSortedMap.this.comparator();
    }
    
    public float firstFloat()
    {
      return AbstractFloat2ByteSortedMap.this.firstFloatKey();
    }
    
    public float lastFloat()
    {
      return AbstractFloat2ByteSortedMap.this.lastFloatKey();
    }
    
    public FloatSortedSet headSet(float local_to)
    {
      return AbstractFloat2ByteSortedMap.this.headMap(local_to).keySet();
    }
    
    public FloatSortedSet tailSet(float from)
    {
      return AbstractFloat2ByteSortedMap.this.tailMap(from).keySet();
    }
    
    public FloatSortedSet subSet(float from, float local_to)
    {
      return AbstractFloat2ByteSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public FloatBidirectionalIterator iterator(float from)
    {
      return new AbstractFloat2ByteSortedMap.KeySetIterator(AbstractFloat2ByteSortedMap.this.entrySet().iterator(new AbstractFloat2ByteMap.BasicEntry(from, (byte)0)));
    }
    
    public FloatBidirectionalIterator iterator()
    {
      return new AbstractFloat2ByteSortedMap.KeySetIterator(AbstractFloat2ByteSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2ByteSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
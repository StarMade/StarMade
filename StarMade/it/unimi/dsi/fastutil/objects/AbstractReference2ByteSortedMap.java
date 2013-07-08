package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.bytes.AbstractByteCollection;
import it.unimi.dsi.fastutil.bytes.AbstractByteIterator;
import it.unimi.dsi.fastutil.bytes.ByteCollection;
import it.unimi.dsi.fastutil.bytes.ByteIterator;
import java.util.Comparator;
import java.util.Map.Entry;

public abstract class AbstractReference2ByteSortedMap<K>
  extends AbstractReference2ByteMap<K>
  implements Reference2ByteSortedMap<K>
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public ReferenceSortedSet<K> keySet()
  {
    return new KeySet();
  }
  
  public ByteCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<K, Byte>> entrySet()
  {
    return reference2ByteEntrySet();
  }
  
  protected static class ValuesIterator<K>
    extends AbstractByteIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<K, Byte>> field_58;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<K, Byte>> local_i)
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
      return new AbstractReference2ByteSortedMap.ValuesIterator(AbstractReference2ByteSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(byte local_k)
    {
      return AbstractReference2ByteSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractReference2ByteSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractReference2ByteSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator<K>
    extends AbstractObjectBidirectionalIterator<K>
  {
    protected final ObjectBidirectionalIterator<Map.Entry<K, Byte>> field_3;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<K, Byte>> local_i)
    {
      this.field_3 = local_i;
    }
    
    public K next()
    {
      return ((Map.Entry)this.field_3.next()).getKey();
    }
    
    public K previous()
    {
      return ((Map.Entry)this.field_3.previous()).getKey();
    }
    
    public boolean hasNext()
    {
      return this.field_3.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_3.hasPrevious();
    }
  }
  
  protected class KeySet
    extends AbstractReferenceSortedSet<K>
  {
    protected KeySet() {}
    
    public boolean contains(Object local_k)
    {
      return AbstractReference2ByteSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractReference2ByteSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractReference2ByteSortedMap.this.clear();
    }
    
    public Comparator<? super K> comparator()
    {
      return AbstractReference2ByteSortedMap.this.comparator();
    }
    
    public K first()
    {
      return AbstractReference2ByteSortedMap.this.firstKey();
    }
    
    public K last()
    {
      return AbstractReference2ByteSortedMap.this.lastKey();
    }
    
    public ReferenceSortedSet<K> headSet(K local_to)
    {
      return AbstractReference2ByteSortedMap.this.headMap(local_to).keySet();
    }
    
    public ReferenceSortedSet<K> tailSet(K from)
    {
      return AbstractReference2ByteSortedMap.this.tailMap(from).keySet();
    }
    
    public ReferenceSortedSet<K> subSet(K from, K local_to)
    {
      return AbstractReference2ByteSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public ObjectBidirectionalIterator<K> iterator(K from)
    {
      return new AbstractReference2ByteSortedMap.KeySetIterator(AbstractReference2ByteSortedMap.this.entrySet().iterator(new AbstractReference2ByteMap.BasicEntry(from, (byte)0)));
    }
    
    public ObjectBidirectionalIterator<K> iterator()
    {
      return new AbstractReference2ByteSortedMap.KeySetIterator(AbstractReference2ByteSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2ByteSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
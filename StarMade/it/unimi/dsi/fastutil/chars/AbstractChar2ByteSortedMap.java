package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.bytes.AbstractByteCollection;
import it.unimi.dsi.fastutil.bytes.AbstractByteIterator;
import it.unimi.dsi.fastutil.bytes.ByteCollection;
import it.unimi.dsi.fastutil.bytes.ByteIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractChar2ByteSortedMap
  extends AbstractChar2ByteMap
  implements Char2ByteSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Char2ByteSortedMap headMap(Character local_to)
  {
    return headMap(local_to.charValue());
  }
  
  public Char2ByteSortedMap tailMap(Character from)
  {
    return tailMap(from.charValue());
  }
  
  public Char2ByteSortedMap subMap(Character from, Character local_to)
  {
    return subMap(from.charValue(), local_to.charValue());
  }
  
  public Character firstKey()
  {
    return Character.valueOf(firstCharKey());
  }
  
  public Character lastKey()
  {
    return Character.valueOf(lastCharKey());
  }
  
  public CharSortedSet keySet()
  {
    return new KeySet();
  }
  
  public ByteCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Character, Byte>> entrySet()
  {
    return char2ByteEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractByteIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Character, Byte>> field_58;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Character, Byte>> local_i)
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
      return new AbstractChar2ByteSortedMap.ValuesIterator(AbstractChar2ByteSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(byte local_k)
    {
      return AbstractChar2ByteSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractChar2ByteSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractChar2ByteSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractCharBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Character, Byte>> field_67;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Character, Byte>> local_i)
    {
      this.field_67 = local_i;
    }
    
    public char nextChar()
    {
      return ((Character)((Map.Entry)this.field_67.next()).getKey()).charValue();
    }
    
    public char previousChar()
    {
      return ((Character)((Map.Entry)this.field_67.previous()).getKey()).charValue();
    }
    
    public boolean hasNext()
    {
      return this.field_67.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_67.hasPrevious();
    }
  }
  
  protected class KeySet
    extends AbstractCharSortedSet
  {
    protected KeySet() {}
    
    public boolean contains(char local_k)
    {
      return AbstractChar2ByteSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractChar2ByteSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractChar2ByteSortedMap.this.clear();
    }
    
    public CharComparator comparator()
    {
      return AbstractChar2ByteSortedMap.this.comparator();
    }
    
    public char firstChar()
    {
      return AbstractChar2ByteSortedMap.this.firstCharKey();
    }
    
    public char lastChar()
    {
      return AbstractChar2ByteSortedMap.this.lastCharKey();
    }
    
    public CharSortedSet headSet(char local_to)
    {
      return AbstractChar2ByteSortedMap.this.headMap(local_to).keySet();
    }
    
    public CharSortedSet tailSet(char from)
    {
      return AbstractChar2ByteSortedMap.this.tailMap(from).keySet();
    }
    
    public CharSortedSet subSet(char from, char local_to)
    {
      return AbstractChar2ByteSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public CharBidirectionalIterator iterator(char from)
    {
      return new AbstractChar2ByteSortedMap.KeySetIterator(AbstractChar2ByteSortedMap.this.entrySet().iterator(new AbstractChar2ByteMap.BasicEntry(from, (byte)0)));
    }
    
    public CharBidirectionalIterator iterator()
    {
      return new AbstractChar2ByteSortedMap.KeySetIterator(AbstractChar2ByteSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2ByteSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
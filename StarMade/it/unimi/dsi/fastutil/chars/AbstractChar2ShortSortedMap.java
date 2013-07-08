package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.shorts.AbstractShortCollection;
import it.unimi.dsi.fastutil.shorts.AbstractShortIterator;
import it.unimi.dsi.fastutil.shorts.ShortCollection;
import it.unimi.dsi.fastutil.shorts.ShortIterator;
import java.util.Map.Entry;

public abstract class AbstractChar2ShortSortedMap
  extends AbstractChar2ShortMap
  implements Char2ShortSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Char2ShortSortedMap headMap(Character local_to)
  {
    return headMap(local_to.charValue());
  }
  
  public Char2ShortSortedMap tailMap(Character from)
  {
    return tailMap(from.charValue());
  }
  
  public Char2ShortSortedMap subMap(Character from, Character local_to)
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
  
  public ShortCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Character, Short>> entrySet()
  {
    return char2ShortEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractShortIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Character, Short>> field_53;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Character, Short>> local_i)
    {
      this.field_53 = local_i;
    }
    
    public short nextShort()
    {
      return ((Short)((Map.Entry)this.field_53.next()).getValue()).shortValue();
    }
    
    public boolean hasNext()
    {
      return this.field_53.hasNext();
    }
  }
  
  protected class ValuesCollection
    extends AbstractShortCollection
  {
    protected ValuesCollection() {}
    
    public ShortIterator iterator()
    {
      return new AbstractChar2ShortSortedMap.ValuesIterator(AbstractChar2ShortSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(short local_k)
    {
      return AbstractChar2ShortSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractChar2ShortSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractChar2ShortSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractCharBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Character, Short>> field_67;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Character, Short>> local_i)
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
      return AbstractChar2ShortSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractChar2ShortSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractChar2ShortSortedMap.this.clear();
    }
    
    public CharComparator comparator()
    {
      return AbstractChar2ShortSortedMap.this.comparator();
    }
    
    public char firstChar()
    {
      return AbstractChar2ShortSortedMap.this.firstCharKey();
    }
    
    public char lastChar()
    {
      return AbstractChar2ShortSortedMap.this.lastCharKey();
    }
    
    public CharSortedSet headSet(char local_to)
    {
      return AbstractChar2ShortSortedMap.this.headMap(local_to).keySet();
    }
    
    public CharSortedSet tailSet(char from)
    {
      return AbstractChar2ShortSortedMap.this.tailMap(from).keySet();
    }
    
    public CharSortedSet subSet(char from, char local_to)
    {
      return AbstractChar2ShortSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public CharBidirectionalIterator iterator(char from)
    {
      return new AbstractChar2ShortSortedMap.KeySetIterator(AbstractChar2ShortSortedMap.this.entrySet().iterator(new AbstractChar2ShortMap.BasicEntry(from, (short)0)));
    }
    
    public CharBidirectionalIterator iterator()
    {
      return new AbstractChar2ShortSortedMap.KeySetIterator(AbstractChar2ShortSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2ShortSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
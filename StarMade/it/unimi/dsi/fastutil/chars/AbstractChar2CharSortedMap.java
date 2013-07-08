package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractChar2CharSortedMap
  extends AbstractChar2CharMap
  implements Char2CharSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Char2CharSortedMap headMap(Character local_to)
  {
    return headMap(local_to.charValue());
  }
  
  public Char2CharSortedMap tailMap(Character from)
  {
    return tailMap(from.charValue());
  }
  
  public Char2CharSortedMap subMap(Character from, Character local_to)
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
  
  public CharCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Character, Character>> entrySet()
  {
    return char2CharEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractCharIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Character, Character>> field_67;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Character, Character>> local_i)
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
      return new AbstractChar2CharSortedMap.ValuesIterator(AbstractChar2CharSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(char local_k)
    {
      return AbstractChar2CharSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractChar2CharSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractChar2CharSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractCharBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Character, Character>> field_67;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Character, Character>> local_i)
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
      return AbstractChar2CharSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractChar2CharSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractChar2CharSortedMap.this.clear();
    }
    
    public CharComparator comparator()
    {
      return AbstractChar2CharSortedMap.this.comparator();
    }
    
    public char firstChar()
    {
      return AbstractChar2CharSortedMap.this.firstCharKey();
    }
    
    public char lastChar()
    {
      return AbstractChar2CharSortedMap.this.lastCharKey();
    }
    
    public CharSortedSet headSet(char local_to)
    {
      return AbstractChar2CharSortedMap.this.headMap(local_to).keySet();
    }
    
    public CharSortedSet tailSet(char from)
    {
      return AbstractChar2CharSortedMap.this.tailMap(from).keySet();
    }
    
    public CharSortedSet subSet(char from, char local_to)
    {
      return AbstractChar2CharSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public CharBidirectionalIterator iterator(char from)
    {
      return new AbstractChar2CharSortedMap.KeySetIterator(AbstractChar2CharSortedMap.this.entrySet().iterator(new AbstractChar2CharMap.BasicEntry(from, '\000')));
    }
    
    public CharBidirectionalIterator iterator()
    {
      return new AbstractChar2CharSortedMap.KeySetIterator(AbstractChar2CharSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2CharSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
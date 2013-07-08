package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.ints.AbstractIntCollection;
import it.unimi.dsi.fastutil.ints.AbstractIntIterator;
import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractChar2IntSortedMap
  extends AbstractChar2IntMap
  implements Char2IntSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Char2IntSortedMap headMap(Character local_to)
  {
    return headMap(local_to.charValue());
  }
  
  public Char2IntSortedMap tailMap(Character from)
  {
    return tailMap(from.charValue());
  }
  
  public Char2IntSortedMap subMap(Character from, Character local_to)
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
  
  public IntCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Character, Integer>> entrySet()
  {
    return char2IntEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractIntIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Character, Integer>> field_70;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Character, Integer>> local_i)
    {
      this.field_70 = local_i;
    }
    
    public int nextInt()
    {
      return ((Integer)((Map.Entry)this.field_70.next()).getValue()).intValue();
    }
    
    public boolean hasNext()
    {
      return this.field_70.hasNext();
    }
  }
  
  protected class ValuesCollection
    extends AbstractIntCollection
  {
    protected ValuesCollection() {}
    
    public IntIterator iterator()
    {
      return new AbstractChar2IntSortedMap.ValuesIterator(AbstractChar2IntSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(int local_k)
    {
      return AbstractChar2IntSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractChar2IntSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractChar2IntSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractCharBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Character, Integer>> field_67;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Character, Integer>> local_i)
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
      return AbstractChar2IntSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractChar2IntSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractChar2IntSortedMap.this.clear();
    }
    
    public CharComparator comparator()
    {
      return AbstractChar2IntSortedMap.this.comparator();
    }
    
    public char firstChar()
    {
      return AbstractChar2IntSortedMap.this.firstCharKey();
    }
    
    public char lastChar()
    {
      return AbstractChar2IntSortedMap.this.lastCharKey();
    }
    
    public CharSortedSet headSet(char local_to)
    {
      return AbstractChar2IntSortedMap.this.headMap(local_to).keySet();
    }
    
    public CharSortedSet tailSet(char from)
    {
      return AbstractChar2IntSortedMap.this.tailMap(from).keySet();
    }
    
    public CharSortedSet subSet(char from, char local_to)
    {
      return AbstractChar2IntSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public CharBidirectionalIterator iterator(char from)
    {
      return new AbstractChar2IntSortedMap.KeySetIterator(AbstractChar2IntSortedMap.this.entrySet().iterator(new AbstractChar2IntMap.BasicEntry(from, 0)));
    }
    
    public CharBidirectionalIterator iterator()
    {
      return new AbstractChar2IntSortedMap.KeySetIterator(AbstractChar2IntSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2IntSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
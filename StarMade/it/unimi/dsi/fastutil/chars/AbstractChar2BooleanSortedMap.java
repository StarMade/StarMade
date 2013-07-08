package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection;
import it.unimi.dsi.fastutil.booleans.AbstractBooleanIterator;
import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.booleans.BooleanIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractChar2BooleanSortedMap
  extends AbstractChar2BooleanMap
  implements Char2BooleanSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Char2BooleanSortedMap headMap(Character local_to)
  {
    return headMap(local_to.charValue());
  }
  
  public Char2BooleanSortedMap tailMap(Character from)
  {
    return tailMap(from.charValue());
  }
  
  public Char2BooleanSortedMap subMap(Character from, Character local_to)
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
  
  public BooleanCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Character, Boolean>> entrySet()
  {
    return char2BooleanEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractBooleanIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Character, Boolean>> field_60;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Character, Boolean>> local_i)
    {
      this.field_60 = local_i;
    }
    
    public boolean nextBoolean()
    {
      return ((Boolean)((Map.Entry)this.field_60.next()).getValue()).booleanValue();
    }
    
    public boolean hasNext()
    {
      return this.field_60.hasNext();
    }
  }
  
  protected class ValuesCollection
    extends AbstractBooleanCollection
  {
    protected ValuesCollection() {}
    
    public BooleanIterator iterator()
    {
      return new AbstractChar2BooleanSortedMap.ValuesIterator(AbstractChar2BooleanSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(boolean local_k)
    {
      return AbstractChar2BooleanSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractChar2BooleanSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractChar2BooleanSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractCharBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Character, Boolean>> field_67;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Character, Boolean>> local_i)
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
      return AbstractChar2BooleanSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractChar2BooleanSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractChar2BooleanSortedMap.this.clear();
    }
    
    public CharComparator comparator()
    {
      return AbstractChar2BooleanSortedMap.this.comparator();
    }
    
    public char firstChar()
    {
      return AbstractChar2BooleanSortedMap.this.firstCharKey();
    }
    
    public char lastChar()
    {
      return AbstractChar2BooleanSortedMap.this.lastCharKey();
    }
    
    public CharSortedSet headSet(char local_to)
    {
      return AbstractChar2BooleanSortedMap.this.headMap(local_to).keySet();
    }
    
    public CharSortedSet tailSet(char from)
    {
      return AbstractChar2BooleanSortedMap.this.tailMap(from).keySet();
    }
    
    public CharSortedSet subSet(char from, char local_to)
    {
      return AbstractChar2BooleanSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public CharBidirectionalIterator iterator(char from)
    {
      return new AbstractChar2BooleanSortedMap.KeySetIterator(AbstractChar2BooleanSortedMap.this.entrySet().iterator(new AbstractChar2BooleanMap.BasicEntry(from, false)));
    }
    
    public CharBidirectionalIterator iterator()
    {
      return new AbstractChar2BooleanSortedMap.KeySetIterator(AbstractChar2BooleanSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2BooleanSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
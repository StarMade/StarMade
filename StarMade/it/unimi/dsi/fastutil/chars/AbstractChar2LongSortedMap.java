package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.longs.AbstractLongCollection;
import it.unimi.dsi.fastutil.longs.AbstractLongIterator;
import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractChar2LongSortedMap
  extends AbstractChar2LongMap
  implements Char2LongSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Char2LongSortedMap headMap(Character local_to)
  {
    return headMap(local_to.charValue());
  }
  
  public Char2LongSortedMap tailMap(Character from)
  {
    return tailMap(from.charValue());
  }
  
  public Char2LongSortedMap subMap(Character from, Character local_to)
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
  
  public LongCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Character, Long>> entrySet()
  {
    return char2LongEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractLongIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Character, Long>> field_1;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Character, Long>> local_i)
    {
      this.field_1 = local_i;
    }
    
    public long nextLong()
    {
      return ((Long)((Map.Entry)this.field_1.next()).getValue()).longValue();
    }
    
    public boolean hasNext()
    {
      return this.field_1.hasNext();
    }
  }
  
  protected class ValuesCollection
    extends AbstractLongCollection
  {
    protected ValuesCollection() {}
    
    public LongIterator iterator()
    {
      return new AbstractChar2LongSortedMap.ValuesIterator(AbstractChar2LongSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(long local_k)
    {
      return AbstractChar2LongSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractChar2LongSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractChar2LongSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractCharBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Character, Long>> field_67;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Character, Long>> local_i)
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
      return AbstractChar2LongSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractChar2LongSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractChar2LongSortedMap.this.clear();
    }
    
    public CharComparator comparator()
    {
      return AbstractChar2LongSortedMap.this.comparator();
    }
    
    public char firstChar()
    {
      return AbstractChar2LongSortedMap.this.firstCharKey();
    }
    
    public char lastChar()
    {
      return AbstractChar2LongSortedMap.this.lastCharKey();
    }
    
    public CharSortedSet headSet(char local_to)
    {
      return AbstractChar2LongSortedMap.this.headMap(local_to).keySet();
    }
    
    public CharSortedSet tailSet(char from)
    {
      return AbstractChar2LongSortedMap.this.tailMap(from).keySet();
    }
    
    public CharSortedSet subSet(char from, char local_to)
    {
      return AbstractChar2LongSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public CharBidirectionalIterator iterator(char from)
    {
      return new AbstractChar2LongSortedMap.KeySetIterator(AbstractChar2LongSortedMap.this.entrySet().iterator(new AbstractChar2LongMap.BasicEntry(from, 0L)));
    }
    
    public CharBidirectionalIterator iterator()
    {
      return new AbstractChar2LongSortedMap.KeySetIterator(AbstractChar2LongSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2LongSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
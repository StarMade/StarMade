package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.chars.AbstractCharCollection;
import it.unimi.dsi.fastutil.chars.AbstractCharIterator;
import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.chars.CharIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractShort2CharSortedMap
  extends AbstractShort2CharMap
  implements Short2CharSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Short2CharSortedMap headMap(Short local_to)
  {
    return headMap(local_to.shortValue());
  }
  
  public Short2CharSortedMap tailMap(Short from)
  {
    return tailMap(from.shortValue());
  }
  
  public Short2CharSortedMap subMap(Short from, Short local_to)
  {
    return subMap(from.shortValue(), local_to.shortValue());
  }
  
  public Short firstKey()
  {
    return Short.valueOf(firstShortKey());
  }
  
  public Short lastKey()
  {
    return Short.valueOf(lastShortKey());
  }
  
  public ShortSortedSet keySet()
  {
    return new KeySet();
  }
  
  public CharCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Short, Character>> entrySet()
  {
    return short2CharEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractCharIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Short, Character>> field_67;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Short, Character>> local_i)
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
      return new AbstractShort2CharSortedMap.ValuesIterator(AbstractShort2CharSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(char local_k)
    {
      return AbstractShort2CharSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractShort2CharSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractShort2CharSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractShortBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Short, Character>> field_53;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Short, Character>> local_i)
    {
      this.field_53 = local_i;
    }
    
    public short nextShort()
    {
      return ((Short)((Map.Entry)this.field_53.next()).getKey()).shortValue();
    }
    
    public short previousShort()
    {
      return ((Short)((Map.Entry)this.field_53.previous()).getKey()).shortValue();
    }
    
    public boolean hasNext()
    {
      return this.field_53.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_53.hasPrevious();
    }
  }
  
  protected class KeySet
    extends AbstractShortSortedSet
  {
    protected KeySet() {}
    
    public boolean contains(short local_k)
    {
      return AbstractShort2CharSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractShort2CharSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractShort2CharSortedMap.this.clear();
    }
    
    public ShortComparator comparator()
    {
      return AbstractShort2CharSortedMap.this.comparator();
    }
    
    public short firstShort()
    {
      return AbstractShort2CharSortedMap.this.firstShortKey();
    }
    
    public short lastShort()
    {
      return AbstractShort2CharSortedMap.this.lastShortKey();
    }
    
    public ShortSortedSet headSet(short local_to)
    {
      return AbstractShort2CharSortedMap.this.headMap(local_to).keySet();
    }
    
    public ShortSortedSet tailSet(short from)
    {
      return AbstractShort2CharSortedMap.this.tailMap(from).keySet();
    }
    
    public ShortSortedSet subSet(short from, short local_to)
    {
      return AbstractShort2CharSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public ShortBidirectionalIterator iterator(short from)
    {
      return new AbstractShort2CharSortedMap.KeySetIterator(AbstractShort2CharSortedMap.this.entrySet().iterator(new AbstractShort2CharMap.BasicEntry(from, '\000')));
    }
    
    public ShortBidirectionalIterator iterator()
    {
      return new AbstractShort2CharSortedMap.KeySetIterator(AbstractShort2CharSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2CharSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
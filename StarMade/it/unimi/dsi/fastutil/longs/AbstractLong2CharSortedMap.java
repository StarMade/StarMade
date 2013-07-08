package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.chars.AbstractCharCollection;
import it.unimi.dsi.fastutil.chars.AbstractCharIterator;
import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.chars.CharIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractLong2CharSortedMap
  extends AbstractLong2CharMap
  implements Long2CharSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Long2CharSortedMap headMap(Long local_to)
  {
    return headMap(local_to.longValue());
  }
  
  public Long2CharSortedMap tailMap(Long from)
  {
    return tailMap(from.longValue());
  }
  
  public Long2CharSortedMap subMap(Long from, Long local_to)
  {
    return subMap(from.longValue(), local_to.longValue());
  }
  
  public Long firstKey()
  {
    return Long.valueOf(firstLongKey());
  }
  
  public Long lastKey()
  {
    return Long.valueOf(lastLongKey());
  }
  
  public LongSortedSet keySet()
  {
    return new KeySet();
  }
  
  public CharCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Long, Character>> entrySet()
  {
    return long2CharEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractCharIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Long, Character>> field_67;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Long, Character>> local_i)
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
      return new AbstractLong2CharSortedMap.ValuesIterator(AbstractLong2CharSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(char local_k)
    {
      return AbstractLong2CharSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractLong2CharSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractLong2CharSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractLongBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Long, Character>> field_1;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Long, Character>> local_i)
    {
      this.field_1 = local_i;
    }
    
    public long nextLong()
    {
      return ((Long)((Map.Entry)this.field_1.next()).getKey()).longValue();
    }
    
    public long previousLong()
    {
      return ((Long)((Map.Entry)this.field_1.previous()).getKey()).longValue();
    }
    
    public boolean hasNext()
    {
      return this.field_1.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_1.hasPrevious();
    }
  }
  
  protected class KeySet
    extends AbstractLongSortedSet
  {
    protected KeySet() {}
    
    public boolean contains(long local_k)
    {
      return AbstractLong2CharSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractLong2CharSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractLong2CharSortedMap.this.clear();
    }
    
    public LongComparator comparator()
    {
      return AbstractLong2CharSortedMap.this.comparator();
    }
    
    public long firstLong()
    {
      return AbstractLong2CharSortedMap.this.firstLongKey();
    }
    
    public long lastLong()
    {
      return AbstractLong2CharSortedMap.this.lastLongKey();
    }
    
    public LongSortedSet headSet(long local_to)
    {
      return AbstractLong2CharSortedMap.this.headMap(local_to).keySet();
    }
    
    public LongSortedSet tailSet(long from)
    {
      return AbstractLong2CharSortedMap.this.tailMap(from).keySet();
    }
    
    public LongSortedSet subSet(long from, long local_to)
    {
      return AbstractLong2CharSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public LongBidirectionalIterator iterator(long from)
    {
      return new AbstractLong2CharSortedMap.KeySetIterator(AbstractLong2CharSortedMap.this.entrySet().iterator(new AbstractLong2CharMap.BasicEntry(from, '\000')));
    }
    
    public LongBidirectionalIterator iterator()
    {
      return new AbstractLong2CharSortedMap.KeySetIterator(AbstractLong2CharSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2CharSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
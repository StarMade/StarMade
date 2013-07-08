package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
import it.unimi.dsi.fastutil.objects.AbstractReferenceCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import java.util.Map.Entry;

public abstract class AbstractChar2ReferenceSortedMap<V>
  extends AbstractChar2ReferenceMap<V>
  implements Char2ReferenceSortedMap<V>
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Char2ReferenceSortedMap<V> headMap(Character local_to)
  {
    return headMap(local_to.charValue());
  }
  
  public Char2ReferenceSortedMap<V> tailMap(Character from)
  {
    return tailMap(from.charValue());
  }
  
  public Char2ReferenceSortedMap<V> subMap(Character from, Character local_to)
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
  
  public ReferenceCollection<V> values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Character, V>> entrySet()
  {
    return char2ReferenceEntrySet();
  }
  
  protected static class ValuesIterator<V>
    extends AbstractObjectIterator<V>
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Character, V>> field_3;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Character, V>> local_i)
    {
      this.field_3 = local_i;
    }
    
    public V next()
    {
      return ((Map.Entry)this.field_3.next()).getValue();
    }
    
    public boolean hasNext()
    {
      return this.field_3.hasNext();
    }
  }
  
  protected class ValuesCollection
    extends AbstractReferenceCollection<V>
  {
    protected ValuesCollection() {}
    
    public ObjectIterator<V> iterator()
    {
      return new AbstractChar2ReferenceSortedMap.ValuesIterator(AbstractChar2ReferenceSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(Object local_k)
    {
      return AbstractChar2ReferenceSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractChar2ReferenceSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractChar2ReferenceSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator<V>
    extends AbstractCharBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Character, V>> field_67;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Character, V>> local_i)
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
      return AbstractChar2ReferenceSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractChar2ReferenceSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractChar2ReferenceSortedMap.this.clear();
    }
    
    public CharComparator comparator()
    {
      return AbstractChar2ReferenceSortedMap.this.comparator();
    }
    
    public char firstChar()
    {
      return AbstractChar2ReferenceSortedMap.this.firstCharKey();
    }
    
    public char lastChar()
    {
      return AbstractChar2ReferenceSortedMap.this.lastCharKey();
    }
    
    public CharSortedSet headSet(char local_to)
    {
      return AbstractChar2ReferenceSortedMap.this.headMap(local_to).keySet();
    }
    
    public CharSortedSet tailSet(char from)
    {
      return AbstractChar2ReferenceSortedMap.this.tailMap(from).keySet();
    }
    
    public CharSortedSet subSet(char from, char local_to)
    {
      return AbstractChar2ReferenceSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public CharBidirectionalIterator iterator(char from)
    {
      return new AbstractChar2ReferenceSortedMap.KeySetIterator(AbstractChar2ReferenceSortedMap.this.entrySet().iterator(new AbstractChar2ReferenceMap.BasicEntry(from, null)));
    }
    
    public CharBidirectionalIterator iterator()
    {
      return new AbstractChar2ReferenceSortedMap.KeySetIterator(AbstractChar2ReferenceSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2ReferenceSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
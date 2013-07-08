package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.objects.AbstractObjectCollection;
import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractChar2ObjectSortedMap<V>
  extends AbstractChar2ObjectMap<V>
  implements Char2ObjectSortedMap<V>
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Char2ObjectSortedMap<V> headMap(Character local_to)
  {
    return headMap(local_to.charValue());
  }
  
  public Char2ObjectSortedMap<V> tailMap(Character from)
  {
    return tailMap(from.charValue());
  }
  
  public Char2ObjectSortedMap<V> subMap(Character from, Character local_to)
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
  
  public ObjectCollection<V> values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Character, V>> entrySet()
  {
    return char2ObjectEntrySet();
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
    extends AbstractObjectCollection<V>
  {
    protected ValuesCollection() {}
    
    public ObjectIterator<V> iterator()
    {
      return new AbstractChar2ObjectSortedMap.ValuesIterator(AbstractChar2ObjectSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(Object local_k)
    {
      return AbstractChar2ObjectSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractChar2ObjectSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractChar2ObjectSortedMap.this.clear();
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
      return AbstractChar2ObjectSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractChar2ObjectSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractChar2ObjectSortedMap.this.clear();
    }
    
    public CharComparator comparator()
    {
      return AbstractChar2ObjectSortedMap.this.comparator();
    }
    
    public char firstChar()
    {
      return AbstractChar2ObjectSortedMap.this.firstCharKey();
    }
    
    public char lastChar()
    {
      return AbstractChar2ObjectSortedMap.this.lastCharKey();
    }
    
    public CharSortedSet headSet(char local_to)
    {
      return AbstractChar2ObjectSortedMap.this.headMap(local_to).keySet();
    }
    
    public CharSortedSet tailSet(char from)
    {
      return AbstractChar2ObjectSortedMap.this.tailMap(from).keySet();
    }
    
    public CharSortedSet subSet(char from, char local_to)
    {
      return AbstractChar2ObjectSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public CharBidirectionalIterator iterator(char from)
    {
      return new AbstractChar2ObjectSortedMap.KeySetIterator(AbstractChar2ObjectSortedMap.this.entrySet().iterator(new AbstractChar2ObjectMap.BasicEntry(from, null)));
    }
    
    public CharBidirectionalIterator iterator()
    {
      return new AbstractChar2ObjectSortedMap.KeySetIterator(AbstractChar2ObjectSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2ObjectSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
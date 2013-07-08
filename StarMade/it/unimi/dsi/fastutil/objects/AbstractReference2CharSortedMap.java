package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.chars.AbstractCharCollection;
import it.unimi.dsi.fastutil.chars.AbstractCharIterator;
import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.chars.CharIterator;
import java.util.Comparator;
import java.util.Map.Entry;

public abstract class AbstractReference2CharSortedMap<K>
  extends AbstractReference2CharMap<K>
  implements Reference2CharSortedMap<K>
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public ReferenceSortedSet<K> keySet()
  {
    return new KeySet();
  }
  
  public CharCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<K, Character>> entrySet()
  {
    return reference2CharEntrySet();
  }
  
  protected static class ValuesIterator<K>
    extends AbstractCharIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<K, Character>> field_67;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<K, Character>> local_i)
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
      return new AbstractReference2CharSortedMap.ValuesIterator(AbstractReference2CharSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(char local_k)
    {
      return AbstractReference2CharSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractReference2CharSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractReference2CharSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator<K>
    extends AbstractObjectBidirectionalIterator<K>
  {
    protected final ObjectBidirectionalIterator<Map.Entry<K, Character>> field_3;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<K, Character>> local_i)
    {
      this.field_3 = local_i;
    }
    
    public K next()
    {
      return ((Map.Entry)this.field_3.next()).getKey();
    }
    
    public K previous()
    {
      return ((Map.Entry)this.field_3.previous()).getKey();
    }
    
    public boolean hasNext()
    {
      return this.field_3.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_3.hasPrevious();
    }
  }
  
  protected class KeySet
    extends AbstractReferenceSortedSet<K>
  {
    protected KeySet() {}
    
    public boolean contains(Object local_k)
    {
      return AbstractReference2CharSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractReference2CharSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractReference2CharSortedMap.this.clear();
    }
    
    public Comparator<? super K> comparator()
    {
      return AbstractReference2CharSortedMap.this.comparator();
    }
    
    public K first()
    {
      return AbstractReference2CharSortedMap.this.firstKey();
    }
    
    public K last()
    {
      return AbstractReference2CharSortedMap.this.lastKey();
    }
    
    public ReferenceSortedSet<K> headSet(K local_to)
    {
      return AbstractReference2CharSortedMap.this.headMap(local_to).keySet();
    }
    
    public ReferenceSortedSet<K> tailSet(K from)
    {
      return AbstractReference2CharSortedMap.this.tailMap(from).keySet();
    }
    
    public ReferenceSortedSet<K> subSet(K from, K local_to)
    {
      return AbstractReference2CharSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public ObjectBidirectionalIterator<K> iterator(K from)
    {
      return new AbstractReference2CharSortedMap.KeySetIterator(AbstractReference2CharSortedMap.this.entrySet().iterator(new AbstractReference2CharMap.BasicEntry(from, '\000')));
    }
    
    public ObjectBidirectionalIterator<K> iterator()
    {
      return new AbstractReference2CharSortedMap.KeySetIterator(AbstractReference2CharSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2CharSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
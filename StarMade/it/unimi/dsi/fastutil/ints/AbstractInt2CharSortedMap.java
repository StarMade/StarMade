package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.chars.AbstractCharCollection;
import it.unimi.dsi.fastutil.chars.AbstractCharIterator;
import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.chars.CharIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractInt2CharSortedMap
  extends AbstractInt2CharMap
  implements Int2CharSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Int2CharSortedMap headMap(Integer local_to)
  {
    return headMap(local_to.intValue());
  }
  
  public Int2CharSortedMap tailMap(Integer from)
  {
    return tailMap(from.intValue());
  }
  
  public Int2CharSortedMap subMap(Integer from, Integer local_to)
  {
    return subMap(from.intValue(), local_to.intValue());
  }
  
  public Integer firstKey()
  {
    return Integer.valueOf(firstIntKey());
  }
  
  public Integer lastKey()
  {
    return Integer.valueOf(lastIntKey());
  }
  
  public IntSortedSet keySet()
  {
    return new KeySet();
  }
  
  public CharCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Integer, Character>> entrySet()
  {
    return int2CharEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractCharIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Integer, Character>> field_67;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Integer, Character>> local_i)
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
      return new AbstractInt2CharSortedMap.ValuesIterator(AbstractInt2CharSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(char local_k)
    {
      return AbstractInt2CharSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractInt2CharSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractInt2CharSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractIntBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Integer, Character>> field_70;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Integer, Character>> local_i)
    {
      this.field_70 = local_i;
    }
    
    public int nextInt()
    {
      return ((Integer)((Map.Entry)this.field_70.next()).getKey()).intValue();
    }
    
    public int previousInt()
    {
      return ((Integer)((Map.Entry)this.field_70.previous()).getKey()).intValue();
    }
    
    public boolean hasNext()
    {
      return this.field_70.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_70.hasPrevious();
    }
  }
  
  protected class KeySet
    extends AbstractIntSortedSet
  {
    protected KeySet() {}
    
    public boolean contains(int local_k)
    {
      return AbstractInt2CharSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractInt2CharSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractInt2CharSortedMap.this.clear();
    }
    
    public IntComparator comparator()
    {
      return AbstractInt2CharSortedMap.this.comparator();
    }
    
    public int firstInt()
    {
      return AbstractInt2CharSortedMap.this.firstIntKey();
    }
    
    public int lastInt()
    {
      return AbstractInt2CharSortedMap.this.lastIntKey();
    }
    
    public IntSortedSet headSet(int local_to)
    {
      return AbstractInt2CharSortedMap.this.headMap(local_to).keySet();
    }
    
    public IntSortedSet tailSet(int from)
    {
      return AbstractInt2CharSortedMap.this.tailMap(from).keySet();
    }
    
    public IntSortedSet subSet(int from, int local_to)
    {
      return AbstractInt2CharSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public IntBidirectionalIterator iterator(int from)
    {
      return new AbstractInt2CharSortedMap.KeySetIterator(AbstractInt2CharSortedMap.this.entrySet().iterator(new AbstractInt2CharMap.BasicEntry(from, '\000')));
    }
    
    public IntBidirectionalIterator iterator()
    {
      return new AbstractInt2CharSortedMap.KeySetIterator(AbstractInt2CharSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2CharSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection;
import it.unimi.dsi.fastutil.doubles.AbstractDoubleIterator;
import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.doubles.DoubleIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractChar2DoubleSortedMap
  extends AbstractChar2DoubleMap
  implements Char2DoubleSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Char2DoubleSortedMap headMap(Character local_to)
  {
    return headMap(local_to.charValue());
  }
  
  public Char2DoubleSortedMap tailMap(Character from)
  {
    return tailMap(from.charValue());
  }
  
  public Char2DoubleSortedMap subMap(Character from, Character local_to)
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
  
  public DoubleCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Character, Double>> entrySet()
  {
    return char2DoubleEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractDoubleIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Character, Double>> field_68;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Character, Double>> local_i)
    {
      this.field_68 = local_i;
    }
    
    public double nextDouble()
    {
      return ((Double)((Map.Entry)this.field_68.next()).getValue()).doubleValue();
    }
    
    public boolean hasNext()
    {
      return this.field_68.hasNext();
    }
  }
  
  protected class ValuesCollection
    extends AbstractDoubleCollection
  {
    protected ValuesCollection() {}
    
    public DoubleIterator iterator()
    {
      return new AbstractChar2DoubleSortedMap.ValuesIterator(AbstractChar2DoubleSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(double local_k)
    {
      return AbstractChar2DoubleSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractChar2DoubleSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractChar2DoubleSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractCharBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Character, Double>> field_67;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Character, Double>> local_i)
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
      return AbstractChar2DoubleSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractChar2DoubleSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractChar2DoubleSortedMap.this.clear();
    }
    
    public CharComparator comparator()
    {
      return AbstractChar2DoubleSortedMap.this.comparator();
    }
    
    public char firstChar()
    {
      return AbstractChar2DoubleSortedMap.this.firstCharKey();
    }
    
    public char lastChar()
    {
      return AbstractChar2DoubleSortedMap.this.lastCharKey();
    }
    
    public CharSortedSet headSet(char local_to)
    {
      return AbstractChar2DoubleSortedMap.this.headMap(local_to).keySet();
    }
    
    public CharSortedSet tailSet(char from)
    {
      return AbstractChar2DoubleSortedMap.this.tailMap(from).keySet();
    }
    
    public CharSortedSet subSet(char from, char local_to)
    {
      return AbstractChar2DoubleSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public CharBidirectionalIterator iterator(char from)
    {
      return new AbstractChar2DoubleSortedMap.KeySetIterator(AbstractChar2DoubleSortedMap.this.entrySet().iterator(new AbstractChar2DoubleMap.BasicEntry(from, 0.0D)));
    }
    
    public CharBidirectionalIterator iterator()
    {
      return new AbstractChar2DoubleSortedMap.KeySetIterator(AbstractChar2DoubleSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2DoubleSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.floats.AbstractFloatCollection;
import it.unimi.dsi.fastutil.floats.AbstractFloatIterator;
import it.unimi.dsi.fastutil.floats.FloatCollection;
import it.unimi.dsi.fastutil.floats.FloatIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractChar2FloatSortedMap
  extends AbstractChar2FloatMap
  implements Char2FloatSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Char2FloatSortedMap headMap(Character local_to)
  {
    return headMap(local_to.charValue());
  }
  
  public Char2FloatSortedMap tailMap(Character from)
  {
    return tailMap(from.charValue());
  }
  
  public Char2FloatSortedMap subMap(Character from, Character local_to)
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
  
  public FloatCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Character, Float>> entrySet()
  {
    return char2FloatEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractFloatIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Character, Float>> field_52;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Character, Float>> local_i)
    {
      this.field_52 = local_i;
    }
    
    public float nextFloat()
    {
      return ((Float)((Map.Entry)this.field_52.next()).getValue()).floatValue();
    }
    
    public boolean hasNext()
    {
      return this.field_52.hasNext();
    }
  }
  
  protected class ValuesCollection
    extends AbstractFloatCollection
  {
    protected ValuesCollection() {}
    
    public FloatIterator iterator()
    {
      return new AbstractChar2FloatSortedMap.ValuesIterator(AbstractChar2FloatSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(float local_k)
    {
      return AbstractChar2FloatSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractChar2FloatSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractChar2FloatSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractCharBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Character, Float>> field_67;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Character, Float>> local_i)
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
      return AbstractChar2FloatSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractChar2FloatSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractChar2FloatSortedMap.this.clear();
    }
    
    public CharComparator comparator()
    {
      return AbstractChar2FloatSortedMap.this.comparator();
    }
    
    public char firstChar()
    {
      return AbstractChar2FloatSortedMap.this.firstCharKey();
    }
    
    public char lastChar()
    {
      return AbstractChar2FloatSortedMap.this.lastCharKey();
    }
    
    public CharSortedSet headSet(char local_to)
    {
      return AbstractChar2FloatSortedMap.this.headMap(local_to).keySet();
    }
    
    public CharSortedSet tailSet(char from)
    {
      return AbstractChar2FloatSortedMap.this.tailMap(from).keySet();
    }
    
    public CharSortedSet subSet(char from, char local_to)
    {
      return AbstractChar2FloatSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public CharBidirectionalIterator iterator(char from)
    {
      return new AbstractChar2FloatSortedMap.KeySetIterator(AbstractChar2FloatSortedMap.this.entrySet().iterator(new AbstractChar2FloatMap.BasicEntry(from, 0.0F)));
    }
    
    public CharBidirectionalIterator iterator()
    {
      return new AbstractChar2FloatSortedMap.KeySetIterator(AbstractChar2FloatSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2FloatSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.chars.AbstractCharCollection;
import it.unimi.dsi.fastutil.chars.AbstractCharIterator;
import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.chars.CharIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;

public abstract class AbstractFloat2CharSortedMap
  extends AbstractFloat2CharMap
  implements Float2CharSortedMap
{
  public static final long serialVersionUID = -1773560792952436569L;
  
  public Float2CharSortedMap headMap(Float local_to)
  {
    return headMap(local_to.floatValue());
  }
  
  public Float2CharSortedMap tailMap(Float from)
  {
    return tailMap(from.floatValue());
  }
  
  public Float2CharSortedMap subMap(Float from, Float local_to)
  {
    return subMap(from.floatValue(), local_to.floatValue());
  }
  
  public Float firstKey()
  {
    return Float.valueOf(firstFloatKey());
  }
  
  public Float lastKey()
  {
    return Float.valueOf(lastFloatKey());
  }
  
  public FloatSortedSet keySet()
  {
    return new KeySet();
  }
  
  public CharCollection values()
  {
    return new ValuesCollection();
  }
  
  public ObjectSortedSet<Map.Entry<Float, Character>> entrySet()
  {
    return float2CharEntrySet();
  }
  
  protected static class ValuesIterator
    extends AbstractCharIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Float, Character>> field_67;
    
    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Float, Character>> local_i)
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
      return new AbstractFloat2CharSortedMap.ValuesIterator(AbstractFloat2CharSortedMap.this.entrySet().iterator());
    }
    
    public boolean contains(char local_k)
    {
      return AbstractFloat2CharSortedMap.this.containsValue(local_k);
    }
    
    public int size()
    {
      return AbstractFloat2CharSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractFloat2CharSortedMap.this.clear();
    }
  }
  
  protected static class KeySetIterator
    extends AbstractFloatBidirectionalIterator
  {
    protected final ObjectBidirectionalIterator<Map.Entry<Float, Character>> field_52;
    
    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Float, Character>> local_i)
    {
      this.field_52 = local_i;
    }
    
    public float nextFloat()
    {
      return ((Float)((Map.Entry)this.field_52.next()).getKey()).floatValue();
    }
    
    public float previousFloat()
    {
      return ((Float)((Map.Entry)this.field_52.previous()).getKey()).floatValue();
    }
    
    public boolean hasNext()
    {
      return this.field_52.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_52.hasPrevious();
    }
  }
  
  protected class KeySet
    extends AbstractFloatSortedSet
  {
    protected KeySet() {}
    
    public boolean contains(float local_k)
    {
      return AbstractFloat2CharSortedMap.this.containsKey(local_k);
    }
    
    public int size()
    {
      return AbstractFloat2CharSortedMap.this.size();
    }
    
    public void clear()
    {
      AbstractFloat2CharSortedMap.this.clear();
    }
    
    public FloatComparator comparator()
    {
      return AbstractFloat2CharSortedMap.this.comparator();
    }
    
    public float firstFloat()
    {
      return AbstractFloat2CharSortedMap.this.firstFloatKey();
    }
    
    public float lastFloat()
    {
      return AbstractFloat2CharSortedMap.this.lastFloatKey();
    }
    
    public FloatSortedSet headSet(float local_to)
    {
      return AbstractFloat2CharSortedMap.this.headMap(local_to).keySet();
    }
    
    public FloatSortedSet tailSet(float from)
    {
      return AbstractFloat2CharSortedMap.this.tailMap(from).keySet();
    }
    
    public FloatSortedSet subSet(float from, float local_to)
    {
      return AbstractFloat2CharSortedMap.this.subMap(from, local_to).keySet();
    }
    
    public FloatBidirectionalIterator iterator(float from)
    {
      return new AbstractFloat2CharSortedMap.KeySetIterator(AbstractFloat2CharSortedMap.this.entrySet().iterator(new AbstractFloat2CharMap.BasicEntry(from, '\000')));
    }
    
    public FloatBidirectionalIterator iterator()
    {
      return new AbstractFloat2CharSortedMap.KeySetIterator(AbstractFloat2CharSortedMap.this.entrySet().iterator());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2CharSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
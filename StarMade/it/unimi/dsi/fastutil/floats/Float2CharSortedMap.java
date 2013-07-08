package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Float2CharSortedMap
  extends Float2CharMap, SortedMap<Float, Character>
{
  public abstract ObjectSortedSet<Map.Entry<Float, Character>> entrySet();
  
  public abstract ObjectSortedSet<Float2CharMap.Entry> float2CharEntrySet();
  
  public abstract FloatSortedSet keySet();
  
  public abstract CharCollection values();
  
  public abstract FloatComparator comparator();
  
  public abstract Float2CharSortedMap subMap(Float paramFloat1, Float paramFloat2);
  
  public abstract Float2CharSortedMap headMap(Float paramFloat);
  
  public abstract Float2CharSortedMap tailMap(Float paramFloat);
  
  public abstract Float2CharSortedMap subMap(float paramFloat1, float paramFloat2);
  
  public abstract Float2CharSortedMap headMap(float paramFloat);
  
  public abstract Float2CharSortedMap tailMap(float paramFloat);
  
  public abstract float firstFloatKey();
  
  public abstract float lastFloatKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Float2CharMap.Entry>, Float2CharMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Float2CharMap.Entry> fastIterator(Float2CharMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2CharSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
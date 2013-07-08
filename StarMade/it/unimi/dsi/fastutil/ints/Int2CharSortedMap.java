package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Int2CharSortedMap
  extends Int2CharMap, SortedMap<Integer, Character>
{
  public abstract ObjectSortedSet<Map.Entry<Integer, Character>> entrySet();
  
  public abstract ObjectSortedSet<Int2CharMap.Entry> int2CharEntrySet();
  
  public abstract IntSortedSet keySet();
  
  public abstract CharCollection values();
  
  public abstract IntComparator comparator();
  
  public abstract Int2CharSortedMap subMap(Integer paramInteger1, Integer paramInteger2);
  
  public abstract Int2CharSortedMap headMap(Integer paramInteger);
  
  public abstract Int2CharSortedMap tailMap(Integer paramInteger);
  
  public abstract Int2CharSortedMap subMap(int paramInt1, int paramInt2);
  
  public abstract Int2CharSortedMap headMap(int paramInt);
  
  public abstract Int2CharSortedMap tailMap(int paramInt);
  
  public abstract int firstIntKey();
  
  public abstract int lastIntKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Int2CharMap.Entry>, Int2CharMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Int2CharMap.Entry> fastIterator(Int2CharMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2CharSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
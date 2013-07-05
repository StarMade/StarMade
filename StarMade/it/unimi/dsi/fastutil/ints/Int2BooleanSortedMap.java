package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Int2BooleanSortedMap extends Int2BooleanMap, SortedMap<Integer, Boolean>
{
  public abstract ObjectSortedSet<Map.Entry<Integer, Boolean>> entrySet();

  public abstract ObjectSortedSet<Int2BooleanMap.Entry> int2BooleanEntrySet();

  public abstract IntSortedSet keySet();

  public abstract BooleanCollection values();

  public abstract IntComparator comparator();

  public abstract Int2BooleanSortedMap subMap(Integer paramInteger1, Integer paramInteger2);

  public abstract Int2BooleanSortedMap headMap(Integer paramInteger);

  public abstract Int2BooleanSortedMap tailMap(Integer paramInteger);

  public abstract Int2BooleanSortedMap subMap(int paramInt1, int paramInt2);

  public abstract Int2BooleanSortedMap headMap(int paramInt);

  public abstract Int2BooleanSortedMap tailMap(int paramInt);

  public abstract int firstIntKey();

  public abstract int lastIntKey();

  public static abstract interface FastSortedEntrySet extends ObjectSortedSet<Int2BooleanMap.Entry>, Int2BooleanMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Int2BooleanMap.Entry> fastIterator(Int2BooleanMap.Entry paramEntry);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2BooleanSortedMap
 * JD-Core Version:    0.6.2
 */
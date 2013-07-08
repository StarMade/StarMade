package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.floats.FloatCollection;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Int2FloatSortedMap
  extends Int2FloatMap, SortedMap<Integer, Float>
{
  public abstract ObjectSortedSet<Map.Entry<Integer, Float>> entrySet();
  
  public abstract ObjectSortedSet<Int2FloatMap.Entry> int2FloatEntrySet();
  
  public abstract IntSortedSet keySet();
  
  public abstract FloatCollection values();
  
  public abstract IntComparator comparator();
  
  public abstract Int2FloatSortedMap subMap(Integer paramInteger1, Integer paramInteger2);
  
  public abstract Int2FloatSortedMap headMap(Integer paramInteger);
  
  public abstract Int2FloatSortedMap tailMap(Integer paramInteger);
  
  public abstract Int2FloatSortedMap subMap(int paramInt1, int paramInt2);
  
  public abstract Int2FloatSortedMap headMap(int paramInt);
  
  public abstract Int2FloatSortedMap tailMap(int paramInt);
  
  public abstract int firstIntKey();
  
  public abstract int lastIntKey();
  
  public static abstract interface FastSortedEntrySet
    extends ObjectSortedSet<Int2FloatMap.Entry>, Int2FloatMap.FastEntrySet
  {
    public abstract ObjectBidirectionalIterator<Int2FloatMap.Entry> fastIterator(Int2FloatMap.Entry paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2FloatSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
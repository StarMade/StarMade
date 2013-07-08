package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Byte2ObjectSortedMap<V>
  extends Byte2ObjectMap<V>, SortedMap<Byte, V>
{
  public abstract ObjectSortedSet<Map.Entry<Byte, V>> entrySet();
  
  public abstract ObjectSortedSet<Byte2ObjectMap.Entry<V>> byte2ObjectEntrySet();
  
  public abstract ByteSortedSet keySet();
  
  public abstract ObjectCollection<V> values();
  
  public abstract ByteComparator comparator();
  
  public abstract Byte2ObjectSortedMap<V> subMap(Byte paramByte1, Byte paramByte2);
  
  public abstract Byte2ObjectSortedMap<V> headMap(Byte paramByte);
  
  public abstract Byte2ObjectSortedMap<V> tailMap(Byte paramByte);
  
  public abstract Byte2ObjectSortedMap<V> subMap(byte paramByte1, byte paramByte2);
  
  public abstract Byte2ObjectSortedMap<V> headMap(byte paramByte);
  
  public abstract Byte2ObjectSortedMap<V> tailMap(byte paramByte);
  
  public abstract byte firstByteKey();
  
  public abstract byte lastByteKey();
  
  public static abstract interface FastSortedEntrySet<V>
    extends ObjectSortedSet<Byte2ObjectMap.Entry<V>>, Byte2ObjectMap.FastEntrySet<V>
  {
    public abstract ObjectBidirectionalIterator<Byte2ObjectMap.Entry<V>> fastIterator(Byte2ObjectMap.Entry<V> paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2ObjectSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
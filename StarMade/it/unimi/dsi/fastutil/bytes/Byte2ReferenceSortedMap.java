package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import java.util.Map.Entry;
import java.util.SortedMap;

public abstract interface Byte2ReferenceSortedMap<V>
  extends Byte2ReferenceMap<V>, SortedMap<Byte, V>
{
  public abstract ObjectSortedSet<Map.Entry<Byte, V>> entrySet();
  
  public abstract ObjectSortedSet<Byte2ReferenceMap.Entry<V>> byte2ReferenceEntrySet();
  
  public abstract ByteSortedSet keySet();
  
  public abstract ReferenceCollection<V> values();
  
  public abstract ByteComparator comparator();
  
  public abstract Byte2ReferenceSortedMap<V> subMap(Byte paramByte1, Byte paramByte2);
  
  public abstract Byte2ReferenceSortedMap<V> headMap(Byte paramByte);
  
  public abstract Byte2ReferenceSortedMap<V> tailMap(Byte paramByte);
  
  public abstract Byte2ReferenceSortedMap<V> subMap(byte paramByte1, byte paramByte2);
  
  public abstract Byte2ReferenceSortedMap<V> headMap(byte paramByte);
  
  public abstract Byte2ReferenceSortedMap<V> tailMap(byte paramByte);
  
  public abstract byte firstByteKey();
  
  public abstract byte lastByteKey();
  
  public static abstract interface FastSortedEntrySet<V>
    extends ObjectSortedSet<Byte2ReferenceMap.Entry<V>>, Byte2ReferenceMap.FastEntrySet<V>
  {
    public abstract ObjectBidirectionalIterator<Byte2ReferenceMap.Entry<V>> fastIterator(Byte2ReferenceMap.Entry<V> paramEntry);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2ReferenceSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
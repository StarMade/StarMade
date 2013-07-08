package it.unimi.dsi.fastutil.objects;

import java.util.Map;
import java.util.Map.Entry;

public abstract interface Reference2ObjectMap<K, V>
  extends Reference2ObjectFunction<K, V>, Map<K, V>
{
  public abstract ObjectSet<Map.Entry<K, V>> entrySet();
  
  public abstract ObjectSet<Entry<K, V>> reference2ObjectEntrySet();
  
  public abstract ReferenceSet<K> keySet();
  
  public abstract ObjectCollection<V> values();
  
  public static abstract interface Entry<K, V>
    extends Map.Entry<K, V>
  {}
  
  public static abstract interface FastEntrySet<K, V>
    extends ObjectSet<Reference2ObjectMap.Entry<K, V>>
  {
    public abstract ObjectIterator<Reference2ObjectMap.Entry<K, V>> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2ObjectMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
package it.unimi.dsi.fastutil.objects;

import java.util.Map;
import java.util.Map.Entry;

public abstract interface Object2ReferenceMap<K, V>
  extends Object2ReferenceFunction<K, V>, Map<K, V>
{
  public abstract ObjectSet<Map.Entry<K, V>> entrySet();
  
  public abstract ObjectSet<Entry<K, V>> object2ReferenceEntrySet();
  
  public abstract ObjectSet<K> keySet();
  
  public abstract ReferenceCollection<V> values();
  
  public static abstract interface Entry<K, V>
    extends Map.Entry<K, V>
  {}
  
  public static abstract interface FastEntrySet<K, V>
    extends ObjectSet<Object2ReferenceMap.Entry<K, V>>
  {
    public abstract ObjectIterator<Object2ReferenceMap.Entry<K, V>> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ReferenceMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
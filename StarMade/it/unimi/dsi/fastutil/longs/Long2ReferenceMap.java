package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Long2ReferenceMap<V>
  extends Long2ReferenceFunction<V>, Map<Long, V>
{
  public abstract ObjectSet<Map.Entry<Long, V>> entrySet();
  
  public abstract ObjectSet<Entry<V>> long2ReferenceEntrySet();
  
  public abstract LongSet keySet();
  
  public abstract ReferenceCollection<V> values();
  
  public static abstract interface Entry<V>
    extends Map.Entry<Long, V>
  {
    public abstract long getLongKey();
  }
  
  public static abstract interface FastEntrySet<V>
    extends ObjectSet<Long2ReferenceMap.Entry<V>>
  {
    public abstract ObjectIterator<Long2ReferenceMap.Entry<V>> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2ReferenceMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
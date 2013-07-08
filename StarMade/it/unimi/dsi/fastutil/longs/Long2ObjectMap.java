package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Long2ObjectMap<V>
  extends Long2ObjectFunction<V>, Map<Long, V>
{
  public abstract ObjectSet<Map.Entry<Long, V>> entrySet();
  
  public abstract ObjectSet<Entry<V>> long2ObjectEntrySet();
  
  public abstract LongSet keySet();
  
  public abstract ObjectCollection<V> values();
  
  public static abstract interface Entry<V>
    extends Map.Entry<Long, V>
  {
    public abstract long getLongKey();
  }
  
  public static abstract interface FastEntrySet<V>
    extends ObjectSet<Long2ObjectMap.Entry<V>>
  {
    public abstract ObjectIterator<Long2ObjectMap.Entry<V>> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2ObjectMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
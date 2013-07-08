package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.longs.LongCollection;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Object2LongMap<K>
  extends Object2LongFunction<K>, Map<K, Long>
{
  public abstract ObjectSet<Map.Entry<K, Long>> entrySet();
  
  public abstract ObjectSet<Entry<K>> object2LongEntrySet();
  
  public abstract ObjectSet<K> keySet();
  
  public abstract LongCollection values();
  
  public abstract boolean containsValue(long paramLong);
  
  public static abstract interface Entry<K>
    extends Map.Entry<K, Long>
  {
    public abstract long setValue(long paramLong);
    
    public abstract long getLongValue();
  }
  
  public static abstract interface FastEntrySet<K>
    extends ObjectSet<Object2LongMap.Entry<K>>
  {
    public abstract ObjectIterator<Object2LongMap.Entry<K>> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2LongMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
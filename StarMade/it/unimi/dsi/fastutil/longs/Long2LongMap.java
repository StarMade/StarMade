package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Long2LongMap extends Long2LongFunction, Map<Long, Long>
{
  public abstract ObjectSet<Map.Entry<Long, Long>> entrySet();

  public abstract ObjectSet<Entry> long2LongEntrySet();

  public abstract LongSet keySet();

  public abstract LongCollection values();

  public abstract boolean containsValue(long paramLong);

  public static abstract interface Entry extends Map.Entry<Long, Long>
  {
    public abstract long getLongKey();

    public abstract long setValue(long paramLong);

    public abstract long getLongValue();
  }

  public static abstract interface FastEntrySet extends ObjectSet<Long2LongMap.Entry>
  {
    public abstract ObjectIterator<Long2LongMap.Entry> fastIterator();
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2LongMap
 * JD-Core Version:    0.6.2
 */
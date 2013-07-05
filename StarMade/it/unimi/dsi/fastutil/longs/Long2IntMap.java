package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Long2IntMap extends Long2IntFunction, Map<Long, Integer>
{
  public abstract ObjectSet<Map.Entry<Long, Integer>> entrySet();

  public abstract ObjectSet<Entry> long2IntEntrySet();

  public abstract LongSet keySet();

  public abstract IntCollection values();

  public abstract boolean containsValue(int paramInt);

  public static abstract interface Entry extends Map.Entry<Long, Integer>
  {
    public abstract long getLongKey();

    public abstract int setValue(int paramInt);

    public abstract int getIntValue();
  }

  public static abstract interface FastEntrySet extends ObjectSet<Long2IntMap.Entry>
  {
    public abstract ObjectIterator<Long2IntMap.Entry> fastIterator();
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2IntMap
 * JD-Core Version:    0.6.2
 */
package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.ints.IntCollection;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Object2IntMap<K> extends Object2IntFunction<K>, Map<K, Integer>
{
  public abstract ObjectSet<Map.Entry<K, Integer>> entrySet();

  public abstract ObjectSet<Entry<K>> object2IntEntrySet();

  public abstract ObjectSet<K> keySet();

  public abstract IntCollection values();

  public abstract boolean containsValue(int paramInt);

  public static abstract interface Entry<K> extends Map.Entry<K, Integer>
  {
    public abstract int setValue(int paramInt);

    public abstract int getIntValue();
  }

  public static abstract interface FastEntrySet<K> extends ObjectSet<Object2IntMap.Entry<K>>
  {
    public abstract ObjectIterator<Object2IntMap.Entry<K>> fastIterator();
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2IntMap
 * JD-Core Version:    0.6.2
 */
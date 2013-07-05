package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Object2DoubleMap<K> extends Object2DoubleFunction<K>, Map<K, Double>
{
  public abstract ObjectSet<Map.Entry<K, Double>> entrySet();

  public abstract ObjectSet<Entry<K>> object2DoubleEntrySet();

  public abstract ObjectSet<K> keySet();

  public abstract DoubleCollection values();

  public abstract boolean containsValue(double paramDouble);

  public static abstract interface Entry<K> extends Map.Entry<K, Double>
  {
    public abstract double setValue(double paramDouble);

    public abstract double getDoubleValue();
  }

  public static abstract interface FastEntrySet<K> extends ObjectSet<Object2DoubleMap.Entry<K>>
  {
    public abstract ObjectIterator<Object2DoubleMap.Entry<K>> fastIterator();
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2DoubleMap
 * JD-Core Version:    0.6.2
 */
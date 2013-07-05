package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Double2ObjectMap<V> extends Double2ObjectFunction<V>, Map<Double, V>
{
  public abstract ObjectSet<Map.Entry<Double, V>> entrySet();

  public abstract ObjectSet<Entry<V>> double2ObjectEntrySet();

  public abstract DoubleSet keySet();

  public abstract ObjectCollection<V> values();

  public static abstract interface Entry<V> extends Map.Entry<Double, V>
  {
    public abstract double getDoubleKey();
  }

  public static abstract interface FastEntrySet<V> extends ObjectSet<Double2ObjectMap.Entry<V>>
  {
    public abstract ObjectIterator<Double2ObjectMap.Entry<V>> fastIterator();
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2ObjectMap
 * JD-Core Version:    0.6.2
 */
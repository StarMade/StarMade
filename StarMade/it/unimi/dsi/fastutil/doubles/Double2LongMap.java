package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Double2LongMap extends Double2LongFunction, Map<Double, Long>
{
  public abstract ObjectSet<Map.Entry<Double, Long>> entrySet();

  public abstract ObjectSet<Entry> double2LongEntrySet();

  public abstract DoubleSet keySet();

  public abstract LongCollection values();

  public abstract boolean containsValue(long paramLong);

  public static abstract interface Entry extends Map.Entry<Double, Long>
  {
    public abstract double getDoubleKey();

    public abstract long setValue(long paramLong);

    public abstract long getLongValue();
  }

  public static abstract interface FastEntrySet extends ObjectSet<Double2LongMap.Entry>
  {
    public abstract ObjectIterator<Double2LongMap.Entry> fastIterator();
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2LongMap
 * JD-Core Version:    0.6.2
 */
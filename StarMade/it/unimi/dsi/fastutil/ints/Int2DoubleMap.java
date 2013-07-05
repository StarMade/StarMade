package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Int2DoubleMap extends Int2DoubleFunction, Map<Integer, Double>
{
  public abstract ObjectSet<Map.Entry<Integer, Double>> entrySet();

  public abstract ObjectSet<Entry> int2DoubleEntrySet();

  public abstract IntSet keySet();

  public abstract DoubleCollection values();

  public abstract boolean containsValue(double paramDouble);

  public static abstract interface Entry extends Map.Entry<Integer, Double>
  {
    public abstract int getIntKey();

    public abstract double setValue(double paramDouble);

    public abstract double getDoubleValue();
  }

  public static abstract interface FastEntrySet extends ObjectSet<Int2DoubleMap.Entry>
  {
    public abstract ObjectIterator<Int2DoubleMap.Entry> fastIterator();
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2DoubleMap
 * JD-Core Version:    0.6.2
 */
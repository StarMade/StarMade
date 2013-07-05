package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.floats.FloatCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Double2FloatMap extends Double2FloatFunction, Map<Double, Float>
{
  public abstract ObjectSet<Map.Entry<Double, Float>> entrySet();

  public abstract ObjectSet<Entry> double2FloatEntrySet();

  public abstract DoubleSet keySet();

  public abstract FloatCollection values();

  public abstract boolean containsValue(float paramFloat);

  public static abstract interface Entry extends Map.Entry<Double, Float>
  {
    public abstract double getDoubleKey();

    public abstract float setValue(float paramFloat);

    public abstract float getFloatValue();
  }

  public static abstract interface FastEntrySet extends ObjectSet<Double2FloatMap.Entry>
  {
    public abstract ObjectIterator<Double2FloatMap.Entry> fastIterator();
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2FloatMap
 * JD-Core Version:    0.6.2
 */
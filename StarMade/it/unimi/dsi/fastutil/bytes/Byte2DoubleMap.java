package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Byte2DoubleMap extends Byte2DoubleFunction, Map<Byte, Double>
{
  public abstract ObjectSet<Map.Entry<Byte, Double>> entrySet();

  public abstract ObjectSet<Entry> byte2DoubleEntrySet();

  public abstract ByteSet keySet();

  public abstract DoubleCollection values();

  public abstract boolean containsValue(double paramDouble);

  public static abstract interface Entry extends Map.Entry<Byte, Double>
  {
    public abstract byte getByteKey();

    public abstract double setValue(double paramDouble);

    public abstract double getDoubleValue();
  }

  public static abstract interface FastEntrySet extends ObjectSet<Byte2DoubleMap.Entry>
  {
    public abstract ObjectIterator<Byte2DoubleMap.Entry> fastIterator();
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2DoubleMap
 * JD-Core Version:    0.6.2
 */
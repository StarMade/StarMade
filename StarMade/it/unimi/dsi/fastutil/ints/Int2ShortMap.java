package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.shorts.ShortCollection;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Int2ShortMap extends Int2ShortFunction, Map<Integer, Short>
{
  public abstract ObjectSet<Map.Entry<Integer, Short>> entrySet();

  public abstract ObjectSet<Entry> int2ShortEntrySet();

  public abstract IntSet keySet();

  public abstract ShortCollection values();

  public abstract boolean containsValue(short paramShort);

  public static abstract interface Entry extends Map.Entry<Integer, Short>
  {
    public abstract int getIntKey();

    public abstract short setValue(short paramShort);

    public abstract short getShortValue();
  }

  public static abstract interface FastEntrySet extends ObjectSet<Int2ShortMap.Entry>
  {
    public abstract ObjectIterator<Int2ShortMap.Entry> fastIterator();
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2ShortMap
 * JD-Core Version:    0.6.2
 */
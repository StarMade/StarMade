package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Short2ObjectMap<V> extends Short2ObjectFunction<V>, Map<Short, V>
{
  public abstract ObjectSet<Map.Entry<Short, V>> entrySet();

  public abstract ObjectSet<Entry<V>> short2ObjectEntrySet();

  public abstract ShortSet keySet();

  public abstract ObjectCollection<V> values();

  public static abstract interface Entry<V> extends Map.Entry<Short, V>
  {
    public abstract short getShortKey();
  }

  public static abstract interface FastEntrySet<V> extends ObjectSet<Short2ObjectMap.Entry<V>>
  {
    public abstract ObjectIterator<Short2ObjectMap.Entry<V>> fastIterator();
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2ObjectMap
 * JD-Core Version:    0.6.2
 */
package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Long2CharMap extends Long2CharFunction, Map<Long, Character>
{
  public abstract ObjectSet<Map.Entry<Long, Character>> entrySet();

  public abstract ObjectSet<Entry> long2CharEntrySet();

  public abstract LongSet keySet();

  public abstract CharCollection values();

  public abstract boolean containsValue(char paramChar);

  public static abstract interface Entry extends Map.Entry<Long, Character>
  {
    public abstract long getLongKey();

    public abstract char setValue(char paramChar);

    public abstract char getCharValue();
  }

  public static abstract interface FastEntrySet extends ObjectSet<Long2CharMap.Entry>
  {
    public abstract ObjectIterator<Long2CharMap.Entry> fastIterator();
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2CharMap
 * JD-Core Version:    0.6.2
 */
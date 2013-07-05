package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Short2CharMap extends Short2CharFunction, Map<Short, Character>
{
  public abstract ObjectSet<Map.Entry<Short, Character>> entrySet();

  public abstract ObjectSet<Entry> short2CharEntrySet();

  public abstract ShortSet keySet();

  public abstract CharCollection values();

  public abstract boolean containsValue(char paramChar);

  public static abstract interface Entry extends Map.Entry<Short, Character>
  {
    public abstract short getShortKey();

    public abstract char setValue(char paramChar);

    public abstract char getCharValue();
  }

  public static abstract interface FastEntrySet extends ObjectSet<Short2CharMap.Entry>
  {
    public abstract ObjectIterator<Short2CharMap.Entry> fastIterator();
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2CharMap
 * JD-Core Version:    0.6.2
 */
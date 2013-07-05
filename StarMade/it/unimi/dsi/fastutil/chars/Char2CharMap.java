package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Char2CharMap extends Char2CharFunction, Map<Character, Character>
{
  public abstract ObjectSet<Map.Entry<Character, Character>> entrySet();

  public abstract ObjectSet<Entry> char2CharEntrySet();

  public abstract CharSet keySet();

  public abstract CharCollection values();

  public abstract boolean containsValue(char paramChar);

  public static abstract interface Entry extends Map.Entry<Character, Character>
  {
    public abstract char getCharKey();

    public abstract char setValue(char paramChar);

    public abstract char getCharValue();
  }

  public static abstract interface FastEntrySet extends ObjectSet<Char2CharMap.Entry>
  {
    public abstract ObjectIterator<Char2CharMap.Entry> fastIterator();
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2CharMap
 * JD-Core Version:    0.6.2
 */
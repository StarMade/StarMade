package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Char2BooleanMap extends Char2BooleanFunction, Map<Character, Boolean>
{
  public abstract ObjectSet<Map.Entry<Character, Boolean>> entrySet();

  public abstract ObjectSet<Entry> char2BooleanEntrySet();

  public abstract CharSet keySet();

  public abstract BooleanCollection values();

  public abstract boolean containsValue(boolean paramBoolean);

  public static abstract interface Entry extends Map.Entry<Character, Boolean>
  {
    public abstract char getCharKey();

    public abstract boolean setValue(boolean paramBoolean);

    public abstract boolean getBooleanValue();
  }

  public static abstract interface FastEntrySet extends ObjectSet<Char2BooleanMap.Entry>
  {
    public abstract ObjectIterator<Char2BooleanMap.Entry> fastIterator();
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2BooleanMap
 * JD-Core Version:    0.6.2
 */
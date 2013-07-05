package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Char2ReferenceMap<V> extends Char2ReferenceFunction<V>, Map<Character, V>
{
  public abstract ObjectSet<Map.Entry<Character, V>> entrySet();

  public abstract ObjectSet<Entry<V>> char2ReferenceEntrySet();

  public abstract CharSet keySet();

  public abstract ReferenceCollection<V> values();

  public static abstract interface Entry<V> extends Map.Entry<Character, V>
  {
    public abstract char getCharKey();
  }

  public static abstract interface FastEntrySet<V> extends ObjectSet<Char2ReferenceMap.Entry<V>>
  {
    public abstract ObjectIterator<Char2ReferenceMap.Entry<V>> fastIterator();
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2ReferenceMap
 * JD-Core Version:    0.6.2
 */
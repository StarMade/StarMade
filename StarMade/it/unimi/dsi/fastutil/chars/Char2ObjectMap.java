package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Char2ObjectMap<V>
  extends Char2ObjectFunction<V>, Map<Character, V>
{
  public abstract ObjectSet<Map.Entry<Character, V>> entrySet();
  
  public abstract ObjectSet<Entry<V>> char2ObjectEntrySet();
  
  public abstract CharSet keySet();
  
  public abstract ObjectCollection<V> values();
  
  public static abstract interface Entry<V>
    extends Map.Entry<Character, V>
  {
    public abstract char getCharKey();
  }
  
  public static abstract interface FastEntrySet<V>
    extends ObjectSet<Char2ObjectMap.Entry<V>>
  {
    public abstract ObjectIterator<Char2ObjectMap.Entry<V>> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2ObjectMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
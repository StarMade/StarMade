package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Char2LongMap
  extends Char2LongFunction, Map<Character, Long>
{
  public abstract ObjectSet<Map.Entry<Character, Long>> entrySet();
  
  public abstract ObjectSet<Entry> char2LongEntrySet();
  
  public abstract CharSet keySet();
  
  public abstract LongCollection values();
  
  public abstract boolean containsValue(long paramLong);
  
  public static abstract interface Entry
    extends Map.Entry<Character, Long>
  {
    public abstract char getCharKey();
    
    public abstract long setValue(long paramLong);
    
    public abstract long getLongValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Char2LongMap.Entry>
  {
    public abstract ObjectIterator<Char2LongMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2LongMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
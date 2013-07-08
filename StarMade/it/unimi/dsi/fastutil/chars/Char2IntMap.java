package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Char2IntMap
  extends Char2IntFunction, Map<Character, Integer>
{
  public abstract ObjectSet<Map.Entry<Character, Integer>> entrySet();
  
  public abstract ObjectSet<Entry> char2IntEntrySet();
  
  public abstract CharSet keySet();
  
  public abstract IntCollection values();
  
  public abstract boolean containsValue(int paramInt);
  
  public static abstract interface Entry
    extends Map.Entry<Character, Integer>
  {
    public abstract char getCharKey();
    
    public abstract int setValue(int paramInt);
    
    public abstract int getIntValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Char2IntMap.Entry>
  {
    public abstract ObjectIterator<Char2IntMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2IntMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
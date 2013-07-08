package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Int2CharMap
  extends Int2CharFunction, Map<Integer, Character>
{
  public abstract ObjectSet<Map.Entry<Integer, Character>> entrySet();
  
  public abstract ObjectSet<Entry> int2CharEntrySet();
  
  public abstract IntSet keySet();
  
  public abstract CharCollection values();
  
  public abstract boolean containsValue(char paramChar);
  
  public static abstract interface Entry
    extends Map.Entry<Integer, Character>
  {
    public abstract int getIntKey();
    
    public abstract char setValue(char paramChar);
    
    public abstract char getCharValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Int2CharMap.Entry>
  {
    public abstract ObjectIterator<Int2CharMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2CharMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
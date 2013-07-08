package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.shorts.ShortCollection;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Char2ShortMap
  extends Char2ShortFunction, Map<Character, Short>
{
  public abstract ObjectSet<Map.Entry<Character, Short>> entrySet();
  
  public abstract ObjectSet<Entry> char2ShortEntrySet();
  
  public abstract CharSet keySet();
  
  public abstract ShortCollection values();
  
  public abstract boolean containsValue(short paramShort);
  
  public static abstract interface Entry
    extends Map.Entry<Character, Short>
  {
    public abstract char getCharKey();
    
    public abstract short setValue(short paramShort);
    
    public abstract short getShortValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Char2ShortMap.Entry>
  {
    public abstract ObjectIterator<Char2ShortMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2ShortMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
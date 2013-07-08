package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Char2DoubleMap
  extends Char2DoubleFunction, Map<Character, Double>
{
  public abstract ObjectSet<Map.Entry<Character, Double>> entrySet();
  
  public abstract ObjectSet<Entry> char2DoubleEntrySet();
  
  public abstract CharSet keySet();
  
  public abstract DoubleCollection values();
  
  public abstract boolean containsValue(double paramDouble);
  
  public static abstract interface Entry
    extends Map.Entry<Character, Double>
  {
    public abstract char getCharKey();
    
    public abstract double setValue(double paramDouble);
    
    public abstract double getDoubleValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Char2DoubleMap.Entry>
  {
    public abstract ObjectIterator<Char2DoubleMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2DoubleMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
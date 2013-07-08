package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Double2CharMap
  extends Double2CharFunction, Map<Double, Character>
{
  public abstract ObjectSet<Map.Entry<Double, Character>> entrySet();
  
  public abstract ObjectSet<Entry> double2CharEntrySet();
  
  public abstract DoubleSet keySet();
  
  public abstract CharCollection values();
  
  public abstract boolean containsValue(char paramChar);
  
  public static abstract interface Entry
    extends Map.Entry<Double, Character>
  {
    public abstract double getDoubleKey();
    
    public abstract char setValue(char paramChar);
    
    public abstract char getCharValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Double2CharMap.Entry>
  {
    public abstract ObjectIterator<Double2CharMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2CharMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
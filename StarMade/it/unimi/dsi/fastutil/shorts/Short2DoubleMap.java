package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Short2DoubleMap
  extends Short2DoubleFunction, Map<Short, Double>
{
  public abstract ObjectSet<Map.Entry<Short, Double>> entrySet();
  
  public abstract ObjectSet<Entry> short2DoubleEntrySet();
  
  public abstract ShortSet keySet();
  
  public abstract DoubleCollection values();
  
  public abstract boolean containsValue(double paramDouble);
  
  public static abstract interface Entry
    extends Map.Entry<Short, Double>
  {
    public abstract short getShortKey();
    
    public abstract double setValue(double paramDouble);
    
    public abstract double getDoubleValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Short2DoubleMap.Entry>
  {
    public abstract ObjectIterator<Short2DoubleMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2DoubleMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
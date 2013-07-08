package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Long2DoubleMap
  extends Long2DoubleFunction, Map<Long, Double>
{
  public abstract ObjectSet<Map.Entry<Long, Double>> entrySet();
  
  public abstract ObjectSet<Entry> long2DoubleEntrySet();
  
  public abstract LongSet keySet();
  
  public abstract DoubleCollection values();
  
  public abstract boolean containsValue(double paramDouble);
  
  public static abstract interface Entry
    extends Map.Entry<Long, Double>
  {
    public abstract long getLongKey();
    
    public abstract double setValue(double paramDouble);
    
    public abstract double getDoubleValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Long2DoubleMap.Entry>
  {
    public abstract ObjectIterator<Long2DoubleMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2DoubleMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
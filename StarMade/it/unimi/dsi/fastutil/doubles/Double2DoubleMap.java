package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Double2DoubleMap
  extends Double2DoubleFunction, Map<Double, Double>
{
  public abstract ObjectSet<Map.Entry<Double, Double>> entrySet();
  
  public abstract ObjectSet<Entry> double2DoubleEntrySet();
  
  public abstract DoubleSet keySet();
  
  public abstract DoubleCollection values();
  
  public abstract boolean containsValue(double paramDouble);
  
  public static abstract interface Entry
    extends Map.Entry<Double, Double>
  {
    public abstract double getDoubleKey();
    
    public abstract double setValue(double paramDouble);
    
    public abstract double getDoubleValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Double2DoubleMap.Entry>
  {
    public abstract ObjectIterator<Double2DoubleMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2DoubleMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
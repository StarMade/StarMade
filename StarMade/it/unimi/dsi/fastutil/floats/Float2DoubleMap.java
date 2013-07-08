package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Float2DoubleMap
  extends Float2DoubleFunction, Map<Float, Double>
{
  public abstract ObjectSet<Map.Entry<Float, Double>> entrySet();
  
  public abstract ObjectSet<Entry> float2DoubleEntrySet();
  
  public abstract FloatSet keySet();
  
  public abstract DoubleCollection values();
  
  public abstract boolean containsValue(double paramDouble);
  
  public static abstract interface Entry
    extends Map.Entry<Float, Double>
  {
    public abstract float getFloatKey();
    
    public abstract double setValue(double paramDouble);
    
    public abstract double getDoubleValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Float2DoubleMap.Entry>
  {
    public abstract ObjectIterator<Float2DoubleMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2DoubleMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
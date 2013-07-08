package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.shorts.ShortCollection;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Double2ShortMap
  extends Double2ShortFunction, Map<Double, Short>
{
  public abstract ObjectSet<Map.Entry<Double, Short>> entrySet();
  
  public abstract ObjectSet<Entry> double2ShortEntrySet();
  
  public abstract DoubleSet keySet();
  
  public abstract ShortCollection values();
  
  public abstract boolean containsValue(short paramShort);
  
  public static abstract interface Entry
    extends Map.Entry<Double, Short>
  {
    public abstract double getDoubleKey();
    
    public abstract short setValue(short paramShort);
    
    public abstract short getShortValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Double2ShortMap.Entry>
  {
    public abstract ObjectIterator<Double2ShortMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2ShortMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
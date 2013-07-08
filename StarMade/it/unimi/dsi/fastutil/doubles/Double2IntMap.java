package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Double2IntMap
  extends Double2IntFunction, Map<Double, Integer>
{
  public abstract ObjectSet<Map.Entry<Double, Integer>> entrySet();
  
  public abstract ObjectSet<Entry> double2IntEntrySet();
  
  public abstract DoubleSet keySet();
  
  public abstract IntCollection values();
  
  public abstract boolean containsValue(int paramInt);
  
  public static abstract interface Entry
    extends Map.Entry<Double, Integer>
  {
    public abstract double getDoubleKey();
    
    public abstract int setValue(int paramInt);
    
    public abstract int getIntValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Double2IntMap.Entry>
  {
    public abstract ObjectIterator<Double2IntMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2IntMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
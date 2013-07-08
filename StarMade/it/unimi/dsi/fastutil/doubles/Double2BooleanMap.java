package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Double2BooleanMap
  extends Double2BooleanFunction, Map<Double, Boolean>
{
  public abstract ObjectSet<Map.Entry<Double, Boolean>> entrySet();
  
  public abstract ObjectSet<Entry> double2BooleanEntrySet();
  
  public abstract DoubleSet keySet();
  
  public abstract BooleanCollection values();
  
  public abstract boolean containsValue(boolean paramBoolean);
  
  public static abstract interface Entry
    extends Map.Entry<Double, Boolean>
  {
    public abstract double getDoubleKey();
    
    public abstract boolean setValue(boolean paramBoolean);
    
    public abstract boolean getBooleanValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Double2BooleanMap.Entry>
  {
    public abstract ObjectIterator<Double2BooleanMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2BooleanMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
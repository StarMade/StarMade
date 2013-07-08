package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Float2LongMap
  extends Float2LongFunction, Map<Float, Long>
{
  public abstract ObjectSet<Map.Entry<Float, Long>> entrySet();
  
  public abstract ObjectSet<Entry> float2LongEntrySet();
  
  public abstract FloatSet keySet();
  
  public abstract LongCollection values();
  
  public abstract boolean containsValue(long paramLong);
  
  public static abstract interface Entry
    extends Map.Entry<Float, Long>
  {
    public abstract float getFloatKey();
    
    public abstract long setValue(long paramLong);
    
    public abstract long getLongValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Float2LongMap.Entry>
  {
    public abstract ObjectIterator<Float2LongMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2LongMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
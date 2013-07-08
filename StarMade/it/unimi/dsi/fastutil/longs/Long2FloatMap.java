package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.floats.FloatCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Long2FloatMap
  extends Long2FloatFunction, Map<Long, Float>
{
  public abstract ObjectSet<Map.Entry<Long, Float>> entrySet();
  
  public abstract ObjectSet<Entry> long2FloatEntrySet();
  
  public abstract LongSet keySet();
  
  public abstract FloatCollection values();
  
  public abstract boolean containsValue(float paramFloat);
  
  public static abstract interface Entry
    extends Map.Entry<Long, Float>
  {
    public abstract long getLongKey();
    
    public abstract float setValue(float paramFloat);
    
    public abstract float getFloatValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Long2FloatMap.Entry>
  {
    public abstract ObjectIterator<Long2FloatMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2FloatMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
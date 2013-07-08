package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Float2FloatMap
  extends Float2FloatFunction, Map<Float, Float>
{
  public abstract ObjectSet<Map.Entry<Float, Float>> entrySet();
  
  public abstract ObjectSet<Entry> float2FloatEntrySet();
  
  public abstract FloatSet keySet();
  
  public abstract FloatCollection values();
  
  public abstract boolean containsValue(float paramFloat);
  
  public static abstract interface Entry
    extends Map.Entry<Float, Float>
  {
    public abstract float getFloatKey();
    
    public abstract float setValue(float paramFloat);
    
    public abstract float getFloatValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Float2FloatMap.Entry>
  {
    public abstract ObjectIterator<Float2FloatMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2FloatMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
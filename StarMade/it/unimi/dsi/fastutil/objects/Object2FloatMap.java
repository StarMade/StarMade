package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.floats.FloatCollection;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Object2FloatMap<K>
  extends Object2FloatFunction<K>, Map<K, Float>
{
  public abstract ObjectSet<Map.Entry<K, Float>> entrySet();
  
  public abstract ObjectSet<Entry<K>> object2FloatEntrySet();
  
  public abstract ObjectSet<K> keySet();
  
  public abstract FloatCollection values();
  
  public abstract boolean containsValue(float paramFloat);
  
  public static abstract interface Entry<K>
    extends Map.Entry<K, Float>
  {
    public abstract float setValue(float paramFloat);
    
    public abstract float getFloatValue();
  }
  
  public static abstract interface FastEntrySet<K>
    extends ObjectSet<Object2FloatMap.Entry<K>>
  {
    public abstract ObjectIterator<Object2FloatMap.Entry<K>> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2FloatMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
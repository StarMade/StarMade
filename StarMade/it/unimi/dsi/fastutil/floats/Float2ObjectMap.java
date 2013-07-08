package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Float2ObjectMap<V>
  extends Float2ObjectFunction<V>, Map<Float, V>
{
  public abstract ObjectSet<Map.Entry<Float, V>> entrySet();
  
  public abstract ObjectSet<Entry<V>> float2ObjectEntrySet();
  
  public abstract FloatSet keySet();
  
  public abstract ObjectCollection<V> values();
  
  public static abstract interface Entry<V>
    extends Map.Entry<Float, V>
  {
    public abstract float getFloatKey();
  }
  
  public static abstract interface FastEntrySet<V>
    extends ObjectSet<Float2ObjectMap.Entry<V>>
  {
    public abstract ObjectIterator<Float2ObjectMap.Entry<V>> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2ObjectMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
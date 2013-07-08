package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Float2ReferenceMap<V>
  extends Float2ReferenceFunction<V>, Map<Float, V>
{
  public abstract ObjectSet<Map.Entry<Float, V>> entrySet();
  
  public abstract ObjectSet<Entry<V>> float2ReferenceEntrySet();
  
  public abstract FloatSet keySet();
  
  public abstract ReferenceCollection<V> values();
  
  public static abstract interface Entry<V>
    extends Map.Entry<Float, V>
  {
    public abstract float getFloatKey();
  }
  
  public static abstract interface FastEntrySet<V>
    extends ObjectSet<Float2ReferenceMap.Entry<V>>
  {
    public abstract ObjectIterator<Float2ReferenceMap.Entry<V>> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2ReferenceMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
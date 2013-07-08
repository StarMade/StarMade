package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.floats.FloatCollection;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Reference2FloatMap<K>
  extends Reference2FloatFunction<K>, Map<K, Float>
{
  public abstract ObjectSet<Map.Entry<K, Float>> entrySet();
  
  public abstract ObjectSet<Entry<K>> reference2FloatEntrySet();
  
  public abstract ReferenceSet<K> keySet();
  
  public abstract FloatCollection values();
  
  public abstract boolean containsValue(float paramFloat);
  
  public static abstract interface Entry<K>
    extends Map.Entry<K, Float>
  {
    public abstract float setValue(float paramFloat);
    
    public abstract float getFloatValue();
  }
  
  public static abstract interface FastEntrySet<K>
    extends ObjectSet<Reference2FloatMap.Entry<K>>
  {
    public abstract ObjectIterator<Reference2FloatMap.Entry<K>> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2FloatMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
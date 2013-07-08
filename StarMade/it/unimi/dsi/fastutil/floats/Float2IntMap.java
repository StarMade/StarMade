package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Float2IntMap
  extends Float2IntFunction, Map<Float, Integer>
{
  public abstract ObjectSet<Map.Entry<Float, Integer>> entrySet();
  
  public abstract ObjectSet<Entry> float2IntEntrySet();
  
  public abstract FloatSet keySet();
  
  public abstract IntCollection values();
  
  public abstract boolean containsValue(int paramInt);
  
  public static abstract interface Entry
    extends Map.Entry<Float, Integer>
  {
    public abstract float getFloatKey();
    
    public abstract int setValue(int paramInt);
    
    public abstract int getIntValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Float2IntMap.Entry>
  {
    public abstract ObjectIterator<Float2IntMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2IntMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
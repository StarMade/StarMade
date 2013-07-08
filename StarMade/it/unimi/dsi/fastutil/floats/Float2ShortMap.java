package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.shorts.ShortCollection;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Float2ShortMap
  extends Float2ShortFunction, Map<Float, Short>
{
  public abstract ObjectSet<Map.Entry<Float, Short>> entrySet();
  
  public abstract ObjectSet<Entry> float2ShortEntrySet();
  
  public abstract FloatSet keySet();
  
  public abstract ShortCollection values();
  
  public abstract boolean containsValue(short paramShort);
  
  public static abstract interface Entry
    extends Map.Entry<Float, Short>
  {
    public abstract float getFloatKey();
    
    public abstract short setValue(short paramShort);
    
    public abstract short getShortValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Float2ShortMap.Entry>
  {
    public abstract ObjectIterator<Float2ShortMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2ShortMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
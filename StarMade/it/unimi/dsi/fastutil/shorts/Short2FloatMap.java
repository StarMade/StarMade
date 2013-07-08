package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.floats.FloatCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Short2FloatMap
  extends Short2FloatFunction, Map<Short, Float>
{
  public abstract ObjectSet<Map.Entry<Short, Float>> entrySet();
  
  public abstract ObjectSet<Entry> short2FloatEntrySet();
  
  public abstract ShortSet keySet();
  
  public abstract FloatCollection values();
  
  public abstract boolean containsValue(float paramFloat);
  
  public static abstract interface Entry
    extends Map.Entry<Short, Float>
  {
    public abstract short getShortKey();
    
    public abstract float setValue(float paramFloat);
    
    public abstract float getFloatValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Short2FloatMap.Entry>
  {
    public abstract ObjectIterator<Short2FloatMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2FloatMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.floats.FloatCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Int2FloatMap
  extends Int2FloatFunction, Map<Integer, Float>
{
  public abstract ObjectSet<Map.Entry<Integer, Float>> entrySet();
  
  public abstract ObjectSet<Entry> int2FloatEntrySet();
  
  public abstract IntSet keySet();
  
  public abstract FloatCollection values();
  
  public abstract boolean containsValue(float paramFloat);
  
  public static abstract interface Entry
    extends Map.Entry<Integer, Float>
  {
    public abstract int getIntKey();
    
    public abstract float setValue(float paramFloat);
    
    public abstract float getFloatValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Int2FloatMap.Entry>
  {
    public abstract ObjectIterator<Int2FloatMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2FloatMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
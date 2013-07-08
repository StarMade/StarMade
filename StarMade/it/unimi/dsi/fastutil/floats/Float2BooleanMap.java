package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Float2BooleanMap
  extends Float2BooleanFunction, Map<Float, Boolean>
{
  public abstract ObjectSet<Map.Entry<Float, Boolean>> entrySet();
  
  public abstract ObjectSet<Entry> float2BooleanEntrySet();
  
  public abstract FloatSet keySet();
  
  public abstract BooleanCollection values();
  
  public abstract boolean containsValue(boolean paramBoolean);
  
  public static abstract interface Entry
    extends Map.Entry<Float, Boolean>
  {
    public abstract float getFloatKey();
    
    public abstract boolean setValue(boolean paramBoolean);
    
    public abstract boolean getBooleanValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Float2BooleanMap.Entry>
  {
    public abstract ObjectIterator<Float2BooleanMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2BooleanMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
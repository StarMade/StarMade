package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Float2CharMap
  extends Float2CharFunction, Map<Float, Character>
{
  public abstract ObjectSet<Map.Entry<Float, Character>> entrySet();
  
  public abstract ObjectSet<Entry> float2CharEntrySet();
  
  public abstract FloatSet keySet();
  
  public abstract CharCollection values();
  
  public abstract boolean containsValue(char paramChar);
  
  public static abstract interface Entry
    extends Map.Entry<Float, Character>
  {
    public abstract float getFloatKey();
    
    public abstract char setValue(char paramChar);
    
    public abstract char getCharValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Float2CharMap.Entry>
  {
    public abstract ObjectIterator<Float2CharMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2CharMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.floats.FloatCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Char2FloatMap
  extends Char2FloatFunction, Map<Character, Float>
{
  public abstract ObjectSet<Map.Entry<Character, Float>> entrySet();
  
  public abstract ObjectSet<Entry> char2FloatEntrySet();
  
  public abstract CharSet keySet();
  
  public abstract FloatCollection values();
  
  public abstract boolean containsValue(float paramFloat);
  
  public static abstract interface Entry
    extends Map.Entry<Character, Float>
  {
    public abstract char getCharKey();
    
    public abstract float setValue(float paramFloat);
    
    public abstract float getFloatValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Char2FloatMap.Entry>
  {
    public abstract ObjectIterator<Char2FloatMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2FloatMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
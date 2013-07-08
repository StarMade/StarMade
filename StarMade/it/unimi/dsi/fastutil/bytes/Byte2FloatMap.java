package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.floats.FloatCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Byte2FloatMap
  extends Byte2FloatFunction, Map<Byte, Float>
{
  public abstract ObjectSet<Map.Entry<Byte, Float>> entrySet();
  
  public abstract ObjectSet<Entry> byte2FloatEntrySet();
  
  public abstract ByteSet keySet();
  
  public abstract FloatCollection values();
  
  public abstract boolean containsValue(float paramFloat);
  
  public static abstract interface Entry
    extends Map.Entry<Byte, Float>
  {
    public abstract byte getByteKey();
    
    public abstract float setValue(float paramFloat);
    
    public abstract float getFloatValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Byte2FloatMap.Entry>
  {
    public abstract ObjectIterator<Byte2FloatMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2FloatMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
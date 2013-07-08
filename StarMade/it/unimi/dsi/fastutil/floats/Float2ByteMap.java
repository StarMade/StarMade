package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.bytes.ByteCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Float2ByteMap
  extends Float2ByteFunction, Map<Float, Byte>
{
  public abstract ObjectSet<Map.Entry<Float, Byte>> entrySet();
  
  public abstract ObjectSet<Entry> float2ByteEntrySet();
  
  public abstract FloatSet keySet();
  
  public abstract ByteCollection values();
  
  public abstract boolean containsValue(byte paramByte);
  
  public static abstract interface Entry
    extends Map.Entry<Float, Byte>
  {
    public abstract float getFloatKey();
    
    public abstract byte setValue(byte paramByte);
    
    public abstract byte getByteValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Float2ByteMap.Entry>
  {
    public abstract ObjectIterator<Float2ByteMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2ByteMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */